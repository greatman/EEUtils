package net.larry1123.util.logger;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

@SuppressWarnings("WeakerAccess")
public class FileManager {

    private static final HashMap<String, FileHandler> fileHandlers = new HashMap<String, FileHandler>();
    private static final HashMap<FileHandler, UtilFilter> fileFilters = new HashMap<FileHandler, UtilFilter>();
    private static final HashMap<EELogger, String> loggerPaths = new HashMap<EELogger, String>();
    private static final HashMap<LoggerLevel, String> levelPaths = new HashMap<LoggerLevel, String>();

    /**
     * Returns the TimeDate that should be used for files at this time
     *
     * @return String containing the current dateTime to use as the split in logging
     */
    public static String dateTime() {
        Date currentTime = null;
        String set = DateFormatUtils.SMTP_DATETIME_FORMAT.format(System.currentTimeMillis());
        try {
            currentTime = DateUtils.parseDate(set, DateFormatUtils.SMTP_DATETIME_FORMAT.getPattern());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!(getConfig().getCurrentSplit() == null || getConfig().getCurrentSplit().equals(""))) {
            Date currentSplit;
            try {
                currentSplit = DateUtils.parseDate(getConfig().getCurrentSplit(), DateFormatUtils.SMTP_DATETIME_FORMAT.getPattern());
            } catch (ParseException e) {
                getConfig().setCurrentSplit(set);
                return set.replace(":", "_");
            }

            Date test;
            switch (getConfig().getSplit()) {
                case HOUR:
                    test = DateUtils.addHours(currentTime, 1);
                    test = DateUtils.setMinutes(test, 0);
                    test = DateUtils.setSeconds(test, 0);
                    test = DateUtils.setMilliseconds(test, 0);
                    if (test.after(currentSplit)) {
                        set = getConfig().getCurrentSplit();
                    }
                    break;
                case DAY:
                    if (DateUtils.isSameDay(currentTime, currentSplit)) {
                        set = getConfig().getCurrentSplit();
                    }
                    break;
                case WEEK:
                    test = DateUtils.ceiling(currentTime, Calendar.WEEK_OF_MONTH);
                    if (test.after(currentSplit)) {
                        set = getConfig().getCurrentSplit();
                    }
                    break;
                case MONTH:
                    test = DateUtils.ceiling(currentTime, Calendar.MONTH);
                    if (test.after(currentSplit)) {
                        set = getConfig().getCurrentSplit();
                    }
                    break;
                default:
                    break;
            }
        }
        getConfig().setCurrentSplit(set);
        return set.replace(":", "_");
    }

    /**
     * Sets up the file for a Logger to use
     *
     * @param logger      What Logger needs setup
     * @param logPathPath Where to Log to
     * @return Fully ready to use FileHandler
     */
    public static FileHandler setUpFile(EELogger logger, String logPathPath) {
        createDirectoryFromPath(logger.logPath);
        try {
            FileHandler handler = getHandler(logger, logPathPath);
            UtilFilter filter = fileFilters.get(handler);
            filter.setLogAll(true);
            return handler;
        } catch (SecurityException e) {
            EELogManager.getLogger("EEUtil").logCustom(LoggerLevels.getLoggerLevel("FileHandlerError"), "SecurityException", e);
        } catch (IOException e) {
            EELogManager.getLogger("EEUtil").logCustom(LoggerLevels.getLoggerLevel("FileHandlerError"), "IOException", e);
        }
        return null;
    }

    /**
     * Sets up a file for a LoggerLevel to use Under a Logger
     *
     * @param logger    What Logger owns the LoggerLevel
     * @param lvl       What Level needs setup
     * @param levelPath Where To Log to
     * @return Fully ready to use FileHandler
     */
    public static FileHandler setUpFile(EELogger logger, LoggerLevel lvl, String levelPath) {
        createDirectoryFromPath(logger.path);

        FileHandler handler = setUpFile(logger, levelPath);
        UtilFilter filter = fileFilters.get(handler);
        filter.setLogAll(false);
        filter.addLogLevel(lvl);

        return handler;
    }

    /**
     * @param logger The logger that the file is made for
     * @param lvl The Level the file is made for
     * @param pathName The Path that the file is to be saved to
     * @return A setup but not fully ready FileHandler
     * @throws SecurityException
     * @throws IOException
     */
    @SuppressWarnings("UnusedDeclaration")
    public static FileHandler getHandler(EELogger logger, LoggerLevel lvl, String pathName) throws SecurityException, IOException {

        FileHandler handler = null;

        if (!getConfig().getSplit().equals(FileSplits.NONE)) {
            pathName = pathName + "_" + FileManager.dateTime();
        }

        pathName = pathName + "." + getConfig().getFileType();

        if (!fileHandlers.containsKey(pathName)) {

            handler = new FileHandler(pathName, true);
            UtilsLogFormat lf = new UtilsLogFormat();
            UtilFilter uf = new UtilFilter();
            fileFilters.put(handler, uf);
            handler.setFilter(uf);
            handler.setLevel(lvl);
            handler.setFormatter(lf);
            handler.setEncoding("UTF-8");

            logger.addHandler(handler);

            fileHandlers.put(pathName, handler);
            levelPaths.put(lvl, pathName);
        }
        if (handler == null) {
            handler = fileHandlers.get(pathName);
        }

        fileFilters.get(handler).addLogLevel(lvl);
        return fileHandlers.get(pathName);

    }

    /**
     * @param logger The logger that the file is made for
     * @param pathName The Path that the file is to be saved to
     * @return A setup but not fully ready FileHandler
     * @throws SecurityException
     * @throws IOException
     */
    public static FileHandler getHandler(EELogger logger, String pathName) throws SecurityException, IOException {

        FileHandler handler = null;

        if (!getConfig().getSplit().equals(FileSplits.NONE)) {
            pathName = pathName + "_" + FileManager.dateTime();
        }

        pathName = pathName + "." + getConfig().getFileType();

        if (!fileHandlers.containsKey(pathName)) {

            handler = new FileHandler(pathName, true);
            UtilsLogFormat lf = new UtilsLogFormat();
            UtilFilter uf = new UtilFilter();
            fileFilters.put(handler, uf);
            handler.setFilter(uf);
            handler.setLevel(Level.ALL);
            handler.setFormatter(lf);
            handler.setEncoding("UTF-8");

            logger.addHandler(handler);

            fileHandlers.put(pathName, handler);
            loggerPaths.put(logger, pathName);
        }
        if (handler == null) {
            handler = fileHandlers.get(pathName);
        }
        return handler;
    }

    /**
     * Removes A Level from the file filter that it is linked to
     *
     * @param lvl Remove a fileHandler for the given level
     */
    public static void removeLoggerLevel(LoggerLevel lvl) {
        fileFilters.get(fileHandlers.get(levelPaths.remove(lvl))).removeLogLevel(lvl);
    }

    public static void updateFileHandlers() {
        for (EELogger logger : loggerPaths.keySet()) {
            for (Handler handlerM : logger.getHandlers()) {
                if (handlerM instanceof FileHandler) {
                    FileHandler handler = (FileHandler) handlerM;
                    UtilFilter filter = (UtilFilter) handler.getFilter();
                    if (!handler.getLevel().equals(Level.ALL)) {
                        LoggerLevel lvl = (LoggerLevel) handler.getLevel();
                        logger.removeHandler(handler);
                        fileHandlers.remove(levelPaths.get(lvl));
                        handler = setUpFile(logger, lvl, levelPaths.get(lvl));
                        handler.setFilter(filter);
                    } else {
                        fileHandlers.remove(loggerPaths.get(logger));
                        logger.removeHandler(handler);
                        handler = setUpFile(logger, logger.logPath);
                        handler.setFilter(filter);
                    }
                }
            }
        }
    }

    private static void createDirectoryFromPath(String path) {
        File logDir = new File(path.substring(0, path.lastIndexOf('/')));
        if (!logDir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            logDir.mkdirs();
        }
    }

    private static LoggerSettings getConfig() {
        return EELogManager.getLoggerSettings();
    }

}

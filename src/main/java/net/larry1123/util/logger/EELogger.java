/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Aug 13, 2013 8:28:02 AM
 */

package net.larry1123.util.logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class EELogger extends Logger {

    private static final LoggerSettings config = new LoggerSettings(); // TODO

    /**
     * Logger to log about logging ... yea I know
     */
    public static final EELogger log = new EELogger("ElecEntertainmentLogger");

    /**
     * Gets the Path for Log files
     *
     * @return
     */
    public static String getLogpath() {
        return config.getLoggerPath();
    }

    /**
     * Holds All EELoggers
     */
    private final static HashMap<String, EELogger> loggers = new HashMap<String, EELogger>();

    static {
        File logDir = new File(getLogpath());
        // Ensure that the Directory is there
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        if (config.getParentLogger() != null) {
            log.setParent(Logger.getLogger(config.getParentLogger()));
        }

        log.setLevel(Level.ALL);
        ConsoleHandler consolehandler = new ConsoleHandler();
        consolehandler.setFormatter(new UtilsLogFormat());
        consolehandler.setLevel(Level.ALL);
        log.addHandler(consolehandler);
    }

    /**
     * TODO
     */
    public final static String fileHandlerError = log.addLoggerLevel("ElecEntertainmentLogger", "FileHandler");

    /**
     * Gets the EELogger for the given name
     *
     * @param name Name of the Logger
     * @return
     */
    public static EELogger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            EELogger logman = new EELogger(name);
            loggers.put(logman.getName(), logman);
        }
        return loggers.get(name);
    }

    /**
     * Gets the EELogger for the given name as a sub of the given parent
     *
     * @param name
     * @param parent
     * @return
     */
    public static EELogger getSubLogger(String name, EELogger parent) {
        if (!loggers.containsKey(parent.getName() + ":" + name)) {
            EELogger logman = new EELogger(name, parent);
            loggers.put(logman.getName(), logman);
        }
        return loggers.get(parent.getName() + ":" + name);
    }

    /**
     * This is the path for the log files of this logger
     */
    public final String path;
    public final String logpath;

    private EELogger(String name) {
        super(name, null);
        path = getLogpath() + name + "/";
        logpath = path + name;
        FileManager.setUpFile(this, logpath);
        if (log != null) {
            setParent(log);
        }
    }

    private EELogger(String name, EELogger parent) {
        super(parent.getName() + ":" + name, null);
        path = parent.path;
        logpath = path + parent.getName() + ":" + name;
        FileManager.setUpFile(this, logpath);
        setParent(parent);
    }

    /**
     * Creates a LoggerLevel for this Logger
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] {Message}
     *
     * @param levelName
     * @return
     */
    public String addLoggerLevel(String levelName) {
        return LoggerLevels.getLoggerLevel(levelName, this).getID();
    }

    /**
     * Creates a LoggerLevel for this Logger with a prefix
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] [{Prefix}] {Message}
     *
     * @param levelName
     * @param prefix
     * @return
     */
    public String addLoggerLevel(String levelName, String prefix) {
        return LoggerLevels.getLoggerLevel(levelName, prefix, this).getID();
    }

    /**
     * Creates a LoggerLevel for this Logger and saves it to a Log file
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] {Message}
     *
     * @param levelName
     * @return
     */
    public String addLoggerLevelWFile(String levelName) {
        LoggerLevel lvl = LoggerLevels.getLoggerLevel(levelName, this);
        String levelPath = logpath + "-" + levelName;
        FileManager.setUpFile(this, lvl, levelPath);
        return lvl.getID();
    }

    /**
     * Creates a LoggerLevel for this Logger with a prefix and saves it to a Log file
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] [{Prefix}] {Message}
     *
     * @param levelName
     * @param prefix
     * @return
     */
    public String addLoggerLevelWFile(String levelName, String prefix) {
        LoggerLevel lvl = LoggerLevels.getLoggerLevel(levelName, prefix, this);
        String levelPath = logpath + "-" + levelName;
        FileManager.setUpFile(this, lvl, levelPath);
        return lvl.getID();
    }

    /**
     * Get the LoggerLevel for the given name.
     *
     * @param name
     * @return
     */
    public LoggerLevel getLoggerLevel(String name) {
        return LoggerLevels.getLoggerLevel(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(LogRecord logRecord) {
        Level level = logRecord.getLevel();
        if (level instanceof LoggerLevel) {
            LoggerLevel handle = (LoggerLevel) level;
            if (!handle.getPrefix().isEmpty()) {
                logRecord.setMessage("[" + handle.getPrefix() + "] " + logRecord.getMessage());
            }
        }
        logRecord.setMessage("[" + this.getName() + "] " + logRecord.getMessage());

        super.log(logRecord);
    }

    /**
     * Used to Log a Custom Logger Level
     *
     * @param lvl The LoggerLevel object to use
     * @param msg Message to be Logged with Level
     */
    public void logCustom(LoggerLevel lvl, String msg) {
        log(lvl, msg);
    }

    /**
     * Used to Log a Custom Logger Level with a StackTrace
     *
     * @param lvl    The LoggerLevel object to use
     * @param msg    Message to be Logged with Level
     * @param thrown The Throwable Error
     */
    public void logCustom(LoggerLevel lvl, String msg, Throwable thrown) {
        log(lvl, msg, thrown);
    }

    /**
     * Used to Log a Custom Logger Level
     *
     * @param lvl The name of the LoggerLevel to use
     * @param msg Message to be Logged with Level
     */
    public void logCustom(String lvl, String msg) {
        log(LoggerLevels.getLoggerLevel(lvl), msg);
    }

    /**
     * Used to Log a Custom Logger Level with a StackTrace
     *
     * @param lvl    The name of the LoggerLevel to use
     * @param msg    Message to be Logged with Level
     * @param thrown The Throwable Error
     */
    public void logCustom(String lvl, String msg, Throwable thrown) {
        log(LoggerLevels.getLoggerLevel(lvl), msg, thrown);
    }

    /**
     * Remove a LoggerLevel
     *
     * @param name LoggerLevel's name
     */
    public void removeLoggerLevel(String name) {
        removeLoggerLevel(getLoggerLevel(name));
    }

    /**
     * Remove a LoggerLevel
     *
     * @param lvl LoggerLevel's name
     */
    public void removeLoggerLevel(LoggerLevel lvl) {
        FileManager.removeLoggerLevel(lvl);
        LoggerLevels.removeLoggerLevel(lvl);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the Level Warning
     *
     * @param message Message to be Logged
     * @param thrown  Throwable Error To be logged
     * @return True if paste was made of stacktrace false if it failed for any reason
     */
    public boolean logStacktraceToPasteBin(String message, Throwable thrown) {
        return logStacktraceToPasteBin(Level.WARNING, message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the LoggerLevel Given
     *
     * @param lvl     String of the LoggerLevel's name to throw with
     * @param message Message to be Logged
     * @param thrown  Throwable Error To be logged
     * @return True if paste was made of stacktrace false if it failed for any reason
     */
    public void logStacktraceToPasteBin(String lvl, String message, Throwable thrown) {
        logStacktraceToPasteBin(LoggerLevels.getLoggerLevel(lvl), message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the LoggerLevel Given
     *
     * @param lvl     Object of the LoggerLevel to throw with
     * @param message Message to be Logged
     * @param thrown  Throwable Error To be logged
     * @return True if paste was made of stacktrace false if it failed for any reason
     */
    public boolean logStacktraceToPasteBin(LoggerLevel lvl, String message, Throwable thrown) {
        if (!lvl.getPrefix().isEmpty()) {
            message = "[" + lvl.getPrefix() + "] " + message;
        }
        return logStacktraceToPasteBin((Level) lvl, message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the Level given
     *
     * @param lvl     The Level to be thrown with
     * @param message Message to be Logged
     * @param thrown  Throwable Error To be logged
     * @return True if paste was made of stacktrace false if it failed for any reason
     */
    public boolean logStacktraceToPasteBin(Level lvl, String message, Throwable thrown) {
        log(lvl, message, thrown);

        if (config.isPasteingAllowed()) {
            try {
                URL url = new URL("http://paste.larry1123.net/");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = "paste_data=" + "[" + lvl.getName() + "] " + message + "\n" + org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(thrown);
                urlParameters += "&";
                urlParameters += "paste_lang=Java";
                urlParameters += "&";
                urlParameters += "api_submit=true";
                urlParameters += "&";
                urlParameters += "mode=xml";
                urlParameters += "&";
                urlParameters += "paste_expire=0";
                urlParameters += "&";
                urlParameters += "paste_project=" + this.getName();
                if (!config.getUserName().equals("")) {
                    urlParameters += "&";
                    urlParameters += "paste_user=" + config.getUserName();
                }

                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return responseCode == 200 && response.toString().contains("<id>");

            } catch (MalformedURLException e) {
                log.log(Level.SEVERE, "Failed to send: Malformed", e);
                return false;
            } catch (IOException e) {
                log.log(Level.SEVERE, "Failed to send: IOException", e);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Log a message with INFO level.
     *
     * @param message the message to be logged
     */
    public void logInfo(String message) {
        log(Level.INFO, message);
    }

    /**
     * Log a message with WARNING level
     *
     * @param message the message to be logged
     */
    public void logWarning(String message) {
        log(Level.WARNING, message);
    }

    /**
     * Log a message with SEVERE level
     *
     * @param message the message to be logged
     */
    public void logSevere(String message) {
        log(Level.SEVERE, message);
    }

    /**
     * Logs a debug message.
     *
     * @param message the message to be logged
     */
    public void logDebug(String message) {
        logCustom("DEBUG", message);
    }

    /**
     * Log a derpy message
     *
     * @param message the message to be logged
     */
    public void logDerp(String message) {
        logCustom("DERP", message);
    }

    /**
     * Log a Plugin Debug message
     *
     * @param message the message to be logged
     */
    public void logPluginDebug(String message) {
        logCustom("PLUGIN_DEBUG", message);
    }

    /**
     * Dump a stacktrace to the log
     *
     * @param message the message to be logged
     * @param thrown  the {@link Throwable} thrown
     */
    public void logStacktrace(String message, Throwable thrown) {
        log(Level.SEVERE, message, thrown);
    }

}

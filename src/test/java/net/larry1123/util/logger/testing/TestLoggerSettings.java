package net.larry1123.util.logger.testing;

import net.larry1123.util.logger.FileSplits;
import net.larry1123.util.logger.LoggerSettings;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author Larry1123
 * @since 1/30/14 - 7:43 AM
 */
public class TestLoggerSettings implements LoggerSettings {

    private String loggerPath = "target" + File.separator + "tests" + File.separator;
    private boolean pastingAllowed = false;
    private FileSplits split = FileSplits.NONE;
    private String currentSplit = "";
    private String fileType = "log";
    private Logger parentLogger = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggerPath() {
        return loggerPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoggerPath(String path) {
        loggerPath = path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPastingAllowed() {
        return pastingAllowed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPastingAllowed(boolean state) {
        pastingAllowed = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileSplits getSplit() {
        return split;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileSplit(FileSplits fileSplit) {
        split = fileSplit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentSplit() {
        return currentSplit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSplit(String current) {
        currentSplit = current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFileType() {
        return fileType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileType(String type) {
        fileType = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getParentLogger() {
        return parentLogger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentLogger(Logger logger) {
        parentLogger = logger;
    }

}

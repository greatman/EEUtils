package net.larry1123.util.logger;

public interface LoggerSettings {

    /**
     * Gets the current Log Path
     *
     * @return Current Log Path
     */
    public String getLoggerPath();

    /**
     * Sets the Path for loggers
     * Will not change the location of Loggers that are running as it is but will move new ones
     *
     * @param path Local Path to place Log files
     */
    public void setLoggerPath(String path);

    /**
     * Returns if the Server is allowed to send info to https://paste.larry1123.net/
     *
     * @return true is allowed false not allowed
     */
    public boolean isPastingAllowed();

    /**
     * TODO
     *
     * @param state
     */
    public void setPastingAllowed(boolean state);

    /**
     * Get the Settings for how to Split Log Files
     *
     * @return Current setting
     */
    public FileSplits getSplit();

    /**
     * Gets the Currently set time stamp for Log files if Splitting is enabled
     * May be null if it has not been used
     *
     * @return Currently used timestamp
     */
    public String getCurrentSplit();

    /**
     * To be used by Logger Only Do not Change
     *
     * @param current
     */
    public void setCurrentSplit(String current);

    /**
     * Get what to set the File Type as
     *
     * @return String of the file type to use
     */
    public String getFileType();

    /**
     * TODO
     *
     * @param type
     */
    public void setFileType(String type);

    /**
     * TODO
     *
     * @return
     */
    public String getParentLogger();

}

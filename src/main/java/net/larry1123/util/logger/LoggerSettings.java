/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Aug 13, 2013 8:28:43 AM
 */
package net.larry1123.util.logger;

public class LoggerSettings {

    private String logger_Path = "logs/";
    private final FileSplits logsplit = FileSplits.NONE;
    private String logFileType = "log";
    private String currentsplit = "";
    private boolean pasteSend = true;
    private String pasteUserName = "";

    /**
     * Gets the current Log Path
     *
     * @return Current Log Path
     */
    public String getLoggerPath() {
        return this.logger_Path;
    }

    /**
     * Sets the Path for loggers
     * Will not change the location of Loggers that are running as it is but will move new ones
     *
     * @param path Local Path to place Log files
     */
    public void setLoggerPath(String path) {
        this.logger_Path = path;
    }

    /**
     * Returns is the Server is allowed to send info to https://paste.larry1123.net/
     *
     * @return true is allowed false not allowed
     */
    public boolean isPasteingAllowed() {
        return this.pasteSend;
    }

    /**
     * TODO
     *
     * @param state
     */
    public void setPasteingAllowed(boolean state) {
        this.pasteSend = state;
    }

    /**
     * Returns the User Name to post paste as.
     *
     * @return Returns the User Name to post paste as.
     */
    public String getUserName() {
        return this.pasteUserName;
    }

    /**
     * TODO
     *
     * @param name
     */
    public void setUserName(String name) {
        this.pasteUserName = name;
    }

    /**
     * Get the Settings for how to Split Log Files
     *
     * @return Current setting
     */
    public FileSplits getSplit() {
        return this.logsplit;
    }

    /**
     * Gets the Currently set time stamp for Log files if Splitting is enabled
     * May be null if it has not been used
     *
     * @return Currently used timestamp
     */
    public String getCurrentSplit() {
        return this.currentsplit;
    }

    /**
     * To be used by Logger Only Do not Change
     *
     * @param current
     */
    public void setCurrentSplit(String current) {
        this.currentsplit = current;
    }

    /**
     * Get what to set the File Type as
     *
     * @return String of the file type to use
     */
    public String getFileType() {
        return this.logFileType;
    }

    /**
     * TODO
     *
     * @param type
     */
    public void setFileType(String type) {
        this.logFileType = type;
    }

    /**
     * TODO
     *
     * @return
     */
    public String getParentLogger() {
        return null;
    }

}

package net.larry1123.util.logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@SuppressWarnings("WeakerAccess")
public class EELogger extends Logger {

    private LoggerSettings getConfig() {
        return EELogManager.getLoggerSettings();
    }

    /**
     * This is the path for the log files of this logger
     */
    public final String path;
    public final String logPath;

    public EELogger(String name) {
        super(name, null);
        path = getConfig().getLoggerPath() + name + "/";
        logPath = path + name;
        FileManager.setUpFile(this, logPath);
        if (getConfig().getParentLogger() != null) {
            setParent(getConfig().getParentLogger());
        }
    }

    public EELogger(String name, EELogger parent) {
        super(parent.getName() + ":" + name, null);
        path = parent.path;
        logPath = path + parent.getName() + ":" + name;
        FileManager.setUpFile(this, logPath);
        setParent(parent);
    }

    /**
     * Creates a LoggerLevel for this Logger
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] {Message}
     *
     * @param levelName The name of the level to be made
     * @return The ID of the level to be used to get the Logger once more if needed
     */
    @SuppressWarnings("UnusedDeclaration")
    public String addLoggerLevel(String levelName) {
        return LoggerLevels.getLoggerLevel(levelName, this).getID();
    }

    /**
     * Creates a LoggerLevel for this Logger with a prefix
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] [{Prefix}] {Message}
     *
     * @param levelName The name of the level to be made
     * @param prefix    The prefix to be used in the Log for this Level
     * @return The ID of the level to be used to get the Logger once more if needed
     */
    @SuppressWarnings("UnusedDeclaration")
    public String addLoggerLevel(String levelName, String prefix) {
        return LoggerLevels.getLoggerLevel(levelName, prefix, this).getID();
    }

    /**
     * Creates a LoggerLevel for this Logger and saves it to a Log file
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] {Message}
     *
     * @param levelName The name of the level to be made
     * @return The ID of the level to be used to get the Logger once more if needed
     */
    @SuppressWarnings("UnusedReturnValue")
    public String addLoggerLevelWFile(@SuppressWarnings("SameParameterValue") String levelName) {
        LoggerLevel lvl = LoggerLevels.getLoggerLevel(levelName, this);
        String levelPath = logPath + "-" + levelName;
        FileManager.setUpFile(this, lvl, levelPath);
        return lvl.getID();
    }

    /**
     * Creates a LoggerLevel for this Logger with a prefix and saves it to a Log file
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] [{Prefix}] {Message}
     *
     * @param levelName The name of the level to be made
     * @param prefix    The prefix to be used in the Log for this Level
     * @return The ID of the level to be used to get the Logger once more if needed
     */
    @SuppressWarnings("UnusedDeclaration")
    public String addLoggerLevelWFile(String levelName, String prefix) {
        LoggerLevel lvl = LoggerLevels.getLoggerLevel(levelName, prefix, this);
        String levelPath = logPath + "-" + levelName;
        FileManager.setUpFile(this, lvl, levelPath);
        return lvl.getID();
    }

    /**
     * Get the LoggerLevel for the given name.
     *
     * @param name The ID of the Level to get
     * @return The LoggerLevel for the given ID
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
        StringBuilder message = new StringBuilder();
        if (level instanceof LoggerLevel) {
            LoggerLevel handle = (LoggerLevel) level;
            if (!handle.getPrefix().isEmpty()) {
                message.append("[").append(handle.getPrefix()).append("] ").append(logRecord.getMessage());
            }
        }
        message.insert(0, "[").insert(0, this.getName()).insert(0, "] ");
        logRecord.setMessage(message.toString());

        super.log(logRecord);
    }

    /**
     * Used to Log a Custom Logger Level
     *
     * @param lvl The LoggerLevel object to use
     * @param msg Message to be Logged with Level
     */
    @SuppressWarnings("UnusedDeclaration")
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
    @SuppressWarnings("UnusedDeclaration")
    public void logCustom(String lvl, String msg, Throwable thrown) {
        log(LoggerLevels.getLoggerLevel(lvl), msg, thrown);
    }

    /**
     * Remove a LoggerLevel
     *
     * @param name The ID of the LoggerLevel to remove
     */
    @SuppressWarnings("UnusedDeclaration")
    public void removeLoggerLevel(String name) {
        removeLoggerLevel(getLoggerLevel(name));
    }

    /**
     * Remove a LoggerLevel
     *
     * @param lvl LoggerLevel to remove
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
     * @return True if paste was made of stackTrace false if it failed for any reason
     */
    @SuppressWarnings("UnusedDeclaration")
    public boolean logStackTraceToPasteBin(String message, Throwable thrown) {
        return logStackTraceToPasteBin(Level.WARNING, message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the LoggerLevel Given
     *
     * @param lvl     Name of the LoggerLevel to throw with
     * @param message Message to be Logged
     * @param thrown  Throwable Error To be logged
     * @return True if paste was made of stackTrace false if it failed for any reason
     */
    @SuppressWarnings("UnusedDeclaration")
    public boolean logStackTraceToPasteBin(String lvl, String message, Throwable thrown) {
        return logStackTraceToPasteBin(LoggerLevels.getLoggerLevel(lvl), message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the LoggerLevel Given
     *
     * @param lvl     Object of the LoggerLevel to throw with
     * @param message Message to be Logged
     * @param thrown  Throwable Error To be logged
     * @return True if paste was made of stackTrace false if it failed for any reason
     */
    public boolean logStackTraceToPasteBin(LoggerLevel lvl, String message, Throwable thrown) {
        if (!lvl.getPrefix().isEmpty()) {
            message = "[" + lvl.getPrefix() + "] " + message;
        }
        return logStackTraceToPasteBin((Level) lvl, message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the Level given
     *
     * @param lvl     The Level to be thrown with
     * @param message Message to be Logged
     * @param thrown  Throwable Error To be logged
     * @return True if paste was made of stackTrace false if it failed for any reason
     */
    public boolean logStackTraceToPasteBin(Level lvl, String message, Throwable thrown) {
        log(lvl, message, thrown);

        if (getConfig().isPastingAllowed()) {
            try {
                URL url = new URL("https://paste.larry1123.net/api/xml/create");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = "data=" + "[" + lvl.getName() + "] " + message + "\n" + org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(thrown);
                urlParameters += "&";
                urlParameters += "title=" + "[" + lvl.getName() + "] " + message;
                urlParameters += "&";
                urlParameters += "language=Java";
                urlParameters += "&";
                urlParameters += "project=" + this.getName();

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
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return responseCode == 200 && response.toString().contains("<id>");

            } catch (MalformedURLException e) {
                EELogger.getLogger("EEUtil").log(Level.SEVERE, "Failed to send: Malformed", e);
                return false;
            } catch (IOException e) {
                EELogger.getLogger("EEUtil").log(Level.SEVERE, "Failed to send: IOException", e);
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
     * Dump a stackTrace to the log
     *
     * @param message the message to be logged
     * @param thrown  the {@link Throwable} thrown
     */
    public void logStackTrace(String message, Throwable thrown) {
        log(Level.SEVERE, message, thrown);
    }

}

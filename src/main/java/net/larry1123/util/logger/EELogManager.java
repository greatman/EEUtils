package net.larry1123.util.logger;

import java.util.HashMap;

/**
 * @author Larry1123
 * @since 1/30/14 - 7:02 AM
 */
public class EELogManager {

    /**
     * Holds All EELoggers
     */
    private final static HashMap<String, EELogger> loggers = new HashMap<String, EELogger>();

    private static LoggerSettings loggerSettings = new LogSettings();

    public static void setLoggerSettings(LoggerSettings loggerSettings) {
        if (loggerSettings == null) throw new NullPointerException("LoggerSettings can not be null");
        EELogManager.loggerSettings = loggerSettings;
    }

    public static LoggerSettings getLoggerSettings() {
        if (loggerSettings == null) throw new NullPointerException("LoggerSettings is null");
        return loggerSettings;
    }

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
            EELogger logger = new EELogger(name, parent);
            loggers.put(logger.getName(), logger);
        }
        return loggers.get(parent.getName() + ":" + name);
    }

}

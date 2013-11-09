package net.larry1123.util.config;

import net.visualillusionsent.utils.PropertiesFile;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

public class ConfigFile {

    /**
     * The PropertiesFile to control
     */
    private final PropertiesFile propertiesfile;

    /**
     * The ConfigBase to base the PropertiesFile on
     */
    private final ConfigBase config;

    /**
     * Holds a list of usable Fields
     */
    private Field[] configFields;

    /**
     * Creates a ConfigFile controller for a given PropertiesFile
     * Will load data from file to ConfigBase
     *
     * @param config         ConfigBase to use
     * @param propertiesfile PropertiesFile to be controlled
     */
    public ConfigFile(ConfigBase config, PropertiesFile propertiesfile) {
        this.config = config;
        this.propertiesfile = propertiesfile;
        for (Field field : config.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigField.class)) {
                ArrayUtils.add(configFields, field);
            }
        }
        for (Field field : config.getClass().getFields()) {
            if (field.isAnnotationPresent(ConfigField.class)) {
                if (!ArrayUtils.contains(configFields, field)) {
                    ArrayUtils.add(configFields, field);
                }
            }
        }
        ConfigLogic.load(propertiesfile, config, configFields);
    }

    /**
     * Gets the controlled PropertiesFile
     *
     * @return PropertiesFile being controlled
     */
    public PropertiesFile getPropFile() {
        return propertiesfile;
    }

    /**
     * Updates the ConfigBase to match the current file without saving first
     */
    public void reload() {
        propertiesfile.reload();
        ConfigLogic.load(propertiesfile, config, configFields);
    }

    /**
     * Saves data to disk if any change has been made
     */
    public void save() {
        ConfigLogic.save(propertiesfile, config, configFields);
    }
}
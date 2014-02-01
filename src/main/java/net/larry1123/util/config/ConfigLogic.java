package net.larry1123.util.config;

import net.larry1123.util.logger.EELogger;
import net.visualillusionsent.utils.PropertiesFile;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

/**
 * The Logic for Loading and saving config files was moved to it's own class so if for some reason something using EEutils
 * can change the ConfigFile class.
 */
public class ConfigLogic {

    private final PropertiesFile propertiesFile;
    private final ConfigBase config;

    public ConfigLogic(PropertiesFile propertiesFile, ConfigBase config) {
        // Just got to make sure this stuff is not null
        if (propertiesFile == null) throw new NullPointerException("PropertiesFile can not be null");
        if (config == null) throw new NullPointerException("ConfigBase can not be null");
        this.propertiesFile = propertiesFile;
        this.config = config;
    }

    /**
     * Saves data to disk
     */
    public void save(Field[] configFields) {
        // Lets not do anything if we have no Fields to take care of
        if (ArrayUtils.isEmpty(ArrayUtils.nullToEmpty(configFields))) return;

        for (Field field : configFields) {

            FieldHandler handler = null;
            try {
                handler = new FieldHandler(field, propertiesFile, config);
            } catch (NoSuchFieldException e) {
                // Should not happen but if it does lets
                continue;
            }

            // Time to get funky
            switch (TypeValues.getFromType(handler.getFieldType())) {

                // Boolean
                case BOOLEANWRAP:
                case BOOLEAN:
                    handler.setBoolean();
                    break;

                // Byte
                case BYTEWRAP:
                case BYTE:
                    handler.setByte();
                    break;

                case BYTEWRAPARRAY:
                case BYTEARRAY:
                case BYTELIST:
                    handler.setByteArray();
                    break;

                // Character
                case CHARACTERWRAP:
                case CHARACTER:
                    handler.setCharacter();
                    break;

                // Double
                case DOUBLEWRAP:
                case DOUBLE:
                    handler.setDouble();
                    break;

                case DOUBLEWRAPARRAY:
                case DOUBLEARRAY:
                case DOUBLELIST:
                    handler.setDoubleArray();
                    break;

                // Float
                case FLOATWRAP:
                case FLOAT:
                    handler.setFloat();
                    break;

                case FLOATWRAPARRAY:
                case FLOATARRAY:
                case FLOATLIST:
                    handler.setFloatArray();
                    break;

                // Integer
                case INTEGERWRAP:
                case INTEGER:
                    handler.setInteger();
                    break;

                case INTEGERWRAPARRAY:
                case INTEGERARRAY:
                case INTEGERLIST:
                    handler.setIntegerArray();
                    break;

                // Long
                case LONGWRAP:
                case LONG:
                    handler.setLong();
                    break;

                case LONGWRAPARRAY:
                case LONGARRAY:
                case LONGLIST:
                    handler.setLongArray();
                    break;

                // Short
                case SHORTWRAP:
                case SHORT:
                    handler.setShort();
                    break;

                case SHORTWRAPARRAY:
                case SHORTARRAY:
                case SHORTLIST:
                    handler.setShortArray();
                    break;

                // String
                case STRING:
                    handler.setString();
                    break;

                case STRINGARRAY:
                    // Uses a custom spacer if given
                    handler.setStringArray();
                    break;

                default:
                    // Log not being able to save a field because it is not supported
                    EELogger.getLogger("UtilConfigSetUp").warning("Unable to Processes Type: " + handler.getFieldName().toString() + " for " + config.getClass().getSimpleName());
                    break;
            }
        }
        // Well lets SAVE!!!
        propertiesFile.save();
    }

    /**
     * Loads data from file to ConfigBase, and makes the file's default settings if needed
     */
    public void load(Field[] configFields) {
        // Lets not do anything if we have no Fields to take care of
        if (ArrayUtils.isEmpty(ArrayUtils.nullToEmpty(configFields))) return;

        for (Field field : configFields) {

            FieldHandler handler = null;
            try {
                handler = new FieldHandler(field, propertiesFile, config);
            } catch (NoSuchFieldException e) {
                // Should not happen but if it does lets
                continue;
            }

            Object result;

            // Time to get funky
            switch (TypeValues.getFromType(handler.getFieldType())) {

                // Boolean
                case BOOLEANWRAP:
                case BOOLEAN:
                    // Gets the current value or sets the default value
                    result = handler.getBoolean();
                    break;

                // Byte
                case BYTEWRAP:
                case BYTE:
                    // Gets the current value or sets the default value
                    result = handler.getByte();
                    break;

                case BYTEWRAPARRAY:
                case BYTEARRAY:
                case BYTELIST:
                    // Gets the current value or sets the default value
                    result = handler.getByteArray();
                    break;

                // Character
                case CHARACTERWRAP:
                case CHARACTER:
                    // Gets the current value or sets the default value
                    result = handler.getCharacter();
                    break;

                // Double
                case DOUBLEWRAP:
                case DOUBLE:
                    // Gets the current value or sets the default value
                    result = handler.getDouble();
                    break;

                case DOUBLEWRAPARRAY:
                case DOUBLEARRAY:
                case DOUBLELIST:
                    // Gets the current value or sets the default value
                    result = handler.getDoubleArray();
                    break;

                // Float
                case FLOATWRAP:
                case FLOAT:
                    // Gets the current value or sets the default value
                    result = handler.getFloat();
                    break;

                case FLOATWRAPARRAY:
                case FLOATARRAY:
                case FLOATLIST:
                    // Gets the current value or sets the default value
                    result = handler.getFloatArray();
                    break;

                // Integer
                case INTEGERWRAP:
                case INTEGER:
                    // Gets the current value or sets the default value
                    result = handler.getInteger();
                    break;

                case INTEGERWRAPARRAY:
                case INTEGERARRAY:
                case INTEGERLIST:
                    // Gets the current value or sets the default value
                    result = handler.getIntegerArray();
                    break;

                // Long
                case LONGWRAP:
                case LONG:
                    // Gets the current value or sets the default value
                    result = handler.getLong();
                    break;

                case LONGWRAPARRAY:
                case LONGARRAY:
                case LONGLIST:
                    // Gets the current value or sets the default value
                    result = handler.getLongArray();
                    break;

                // Short
                case SHORTWRAP:
                case SHORT:
                case SHORTLIST:
                    // Gets the current value or sets the default value
                    result = handler.getShort();
                    break;

                case SHORTWRAPARRAY:
                case SHORTARRAY:
                    // Gets the current value or sets the default value
                    result = handler.getShortArray();
                    break;

                // String
                case STRING:
                    // Gets the current value or sets the default value
                    result = handler.getString();
                    break;

                case STRINGARRAY:
                case STRINGLIST:
                    // Gets the current value or sets the default value
                    result = handler.getStringArray();
                    break;

                default:
                    result = null;
                    // Log not being able to save a field because it is not supported
                    EELogger.getLogger("UtilConfigSetUp").warning("Unable to Processes Type: " + handler.getFieldName().toString() + " for " + config.getClass().getSimpleName());
                    break;
            }

            // Now lets see if we have a value to set into the ConfigBase
            if (result != null) {
                // Adds any amount of comments to the key in the file
                handler.setComments();
                try {
                    // Now lets set that field to a value if we have one
                    handler.setField();
                } catch (IllegalArgumentException e) {
                    // Should never be the wrong type of Object being set
                } catch (IllegalAccessException e) {
                    // Should not happen because of field.setAccessible(true);
                }
            }
        }
        // Well lets SAVE!!!
        propertiesFile.save();
    }

}

package net.larry1123.util.config;

import net.larry1123.util.logger.EELogger;
import net.visualillusionsent.utils.PropertiesFile;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * The Logic for Loading and saving config files was moved to it's own class so if for some reason something using EEutils
 * can change the ConfigFile class.
 */
public class ConfigLogic {

    /**
     * Saves data to disk
     */
    public static void save(PropertiesFile propertiesFile, ConfigBase config, Field[] configFields) {
        // Just got to make sure this stuff is not null
        if (propertiesFile == null) throw new NullPointerException("PropertiesFile can not be null");
        if (config == null) throw new NullPointerException("ConfigBase can not be null");
        // Lets not do anything if we have no Fields to take care of
        if (ArrayUtils.isEmpty(configFields)) return;

        for (Field field : configFields) {
            // Ensure we can use the field
            field.setAccessible(true);
            // Get type of field to be checked later
            Type fieldType = field.getGenericType();
            // Get the Annotation that we need
            ConfigField ano = field.getAnnotation(ConfigField.class);
            boolean hasSpacer = false;
            // Checks if this field has a spacer set
            if (!ano.spacer().equals("")) {
                hasSpacer = true;
            }
            Object value = null;
            boolean hasValue;
            // Lets check if there is something to set
            try {
                value = field.get(config);
            } catch (IllegalArgumentException e1) {
            } catch (IllegalAccessException e1) {
            } finally {
                hasValue = value != null;
            }

            // Should stop here and go onto the next one if there is no value to be saved
            if (!hasValue) continue;

            // Lets give this thing a name
            String fieldName = (ano.name().equals("")) ? field.getName() : ano.name();

            // if we have an array
            byte[] barray;
            double[] darray;
            float[] farray;
            int[] iarray;
            long[] larray;
            short[] sarray;

            // Time to get funky
            switch (TypeValues.getFromType(fieldType)) {

                // Boolean
                case BOOLEANWRAP:
                case BOOLEAN:
                    propertiesFile.setBoolean(fieldName, (Boolean) value);
                    break;

                // Byte
                case BYTEWRAP:
                case BYTE:
                    propertiesFile.setByte(fieldName, (Byte) value);
                    break;

                case BYTEWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Byte[]) value);
                case BYTEARRAY:
                    barray = (byte[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesFile.setByteArray(fieldName, ano.spacer(), barray);
                    } else {
                        propertiesFile.setByteArray(fieldName, barray);
                    }
                    break;

                // Character
                case CHARACTERWRAP:
                case CHARACTER:
                    propertiesFile.setCharacter(fieldName, (Character) value);
                    break;

                // Double
                case DOUBLEWRAP:
                case DOUBLE:
                    propertiesFile.setDouble(fieldName, (Double) value);
                    break;

                case DOUBLEWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Double[]) value);
                case DOUBLEARRAY:
                    darray = (double[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesFile.setDoubleArray(fieldName, ano.spacer(), darray);
                    } else {
                        propertiesFile.setDoubleArray(fieldName, darray);
                    }
                    break;

                // Float
                case FLOATWRAP:
                case FLOAT:
                    propertiesFile.setFloat(fieldName, (Float) value);
                    break;

                case FLOATWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Float[]) value);
                case FLOATARRAY:
                    farray = (float[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesFile.setFloatArray(fieldName, ano.spacer(), farray);
                    } else {
                        propertiesFile.setFloatArray(fieldName, farray);
                    }
                    break;

                // Integer
                case INTEGERWRAP:
                case INTEGER:
                    propertiesFile.setInt(fieldName, (Integer) value);
                    break;

                case INTEGERWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Integer[]) value);
                case INTEGERARRAY:
                    iarray = (int[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesFile.setIntArray(fieldName, ano.spacer(), iarray);
                    } else {
                        propertiesFile.setIntArray(fieldName, iarray);
                    }
                    break;

                // Long
                case LONGWRAP:
                case LONG:
                    propertiesFile.setLong(fieldName, (Long) value);
                    break;

                case LONGWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Long[]) value);
                case LONGArray:
                    larray = (long[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesFile.setLongArray(fieldName, ano.spacer(), larray);
                    } else {
                        propertiesFile.setLongArray(fieldName, larray);
                    }
                    break;

                // Short
                case SHORTWRAP:
                case SHORT:
                    propertiesFile.setShort(fieldName, (Short) value);
                    break;

                case SHORTWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Short[]) value);
                case SHORTARRAY:
                    sarray = (short[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesFile.setShortArray(fieldName, ano.spacer(), sarray);
                    } else {
                        propertiesFile.setShortArray(fieldName, sarray);
                    }
                    break;

                // String
                case STRING:
                    propertiesFile.setString(fieldName, (String) value);
                    break;

                case STRINGARRAY:
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesFile.setStringArray(fieldName, ano.spacer(), (String[]) value);
                    } else {
                        propertiesFile.setStringArray(fieldName, (String[]) value);
                    }
                    break;

                default:
                    // Log not being able to save a field because it is not supported
                    EELogger.getLogger("UtilConfigSetUp").warning("Unable to Processes Type: " + fieldType.toString() + " for " + config.getClass().getSimpleName());
                    break;
            }
        }
        // Well lets SAVE!!!
        propertiesFile.save();
    }

    /**
     * Loads data from file to ConfigBase, and makes the file's default settings if needed
     */
    public static void load(PropertiesFile propertiesFile, ConfigBase config, Field[] configFields) {
        // Just got to make sure this stuff is not null
        if (propertiesFile == null) throw new NullPointerException("PropertiesFile can not be null");
        if (config == null) throw new NullPointerException("ConfigBase can not be null");
        // Lets not do anything if we have no Fields to take care of
        if (ArrayUtils.isEmpty(configFields)) return;

        for (Field field : configFields) {
            // Ensure we can use the field
            field.setAccessible(true);
            // Get type of field to be checked later
            Type fieldType = field.getGenericType();
            // Get the Annotation that we need
            ConfigField ano = field.getAnnotation(ConfigField.class);
            boolean hasSpacer = false;
            // Checks if this field has a spacer set
            if (!ano.spacer().equals("")) {
                hasSpacer = true;
            }
            Object defaultValue = null;
            boolean hasValue;
            // Lets check if there is something to set
            try {
                defaultValue = field.get(config);
            } catch (IllegalArgumentException e1) {
            } catch (IllegalAccessException e1) {
            } finally {
                hasValue = defaultValue != null;
            }
            // It does not matter if we have a value to use or not since we are just loading

            // Lets give this thing a name
            String fieldName = (ano.name().equals("")) ? field.getName() : ano.name();

            Object result;

            // if we have an array
            byte[] barray;
            double[] darray;
            float[] farray;
            int[] iarray;
            long[] larray;
            short[] sarray;

            // Time to get funky
            switch (TypeValues.getFromType(fieldType)) {

                // Boolean
                case BOOLEANWRAP:
                case BOOLEAN:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getBoolean(fieldName, (Boolean) defaultValue);
                    } else {
                        result = propertiesFile.getBoolean(fieldName);
                    }
                    break;

                // Byte
                case BYTEWRAP:
                case BYTE:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getByte(fieldName, (Byte) defaultValue);
                    } else {
                        result = propertiesFile.getByte(fieldName);
                    }
                    break;

                case BYTEWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    defaultValue = ArrayUtils.toPrimitive((Byte[]) defaultValue);
                case BYTEARRAY:
                    barray = (byte[]) defaultValue;
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getByteArray(fieldName, ano.spacer(), barray);
                        } else {
                            result = propertiesFile.getByteArray(fieldName, barray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getByteArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesFile.getByteArray(fieldName);
                        }
                    }
                    break;

                // Character
                case CHARACTERWRAP:
                case CHARACTER:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getCharacter(fieldName, (Character) defaultValue);
                    } else {
                        result = propertiesFile.getCharacter(fieldName);
                    }
                    break;

                // Double
                case DOUBLEWRAP:
                case DOUBLE:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getDouble(fieldName, (Double) defaultValue);
                    } else {
                        result = propertiesFile.getDouble(fieldName);
                    }
                    break;

                case DOUBLEWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    defaultValue = ArrayUtils.toPrimitive((Double[]) defaultValue);
                case DOUBLEARRAY:
                    darray = (double[]) defaultValue;
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getDoubleArray(fieldName, ano.spacer(), darray);
                        } else {
                            result = propertiesFile.getDoubleArray(fieldName, darray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getDoubleArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesFile.getDoubleArray(fieldName);
                        }
                    }
                    break;

                // Float
                case FLOATWRAP:
                case FLOAT:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getFloat(fieldName, (Float) defaultValue);
                    } else {
                        result = propertiesFile.getFloat(fieldName);
                    }
                    break;

                case FLOATWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    defaultValue = ArrayUtils.toPrimitive((Float[]) defaultValue);
                case FLOATARRAY:
                    farray = (float[]) defaultValue;
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getFloatArray(fieldName, ano.spacer(), farray);
                        } else {
                            result = propertiesFile.getFloatArray(fieldName, farray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getFloatArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesFile.getFloatArray(fieldName);
                        }
                    }
                    break;

                // Integer
                case INTEGERWRAP:
                case INTEGER:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getInt(fieldName, (Integer) defaultValue);
                    } else {
                        result = propertiesFile.getInt(fieldName);
                    }
                    break;

                case INTEGERWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    defaultValue = ArrayUtils.toPrimitive((Integer[]) defaultValue);
                case INTEGERARRAY:
                    iarray = (int[]) defaultValue;
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getIntArray(fieldName, ano.spacer(), iarray);
                        } else {
                            result = propertiesFile.getIntArray(fieldName, iarray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getIntArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesFile.getIntArray(fieldName);
                        }
                    }
                    break;

                // Long
                case LONGWRAP:
                case LONG:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getLong(fieldName, (Long) defaultValue);
                    } else {
                        result = propertiesFile.getLong(fieldName);
                    }
                    break;

                case LONGWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    defaultValue = ArrayUtils.toPrimitive((Long[]) defaultValue);
                case LONGArray:
                    larray = (long[]) defaultValue;
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getLongArray(fieldName, ano.spacer(), larray);
                        } else {
                            result = propertiesFile.getLongArray(fieldName, larray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getLongArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesFile.getLongArray(fieldName);
                        }
                    }
                    break;

                // Short
                case SHORTWRAP:
                case SHORT:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getShort(fieldName, (Short) defaultValue);
                    } else {
                        result = propertiesFile.getShort(fieldName);
                    }
                    break;

                case SHORTWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    defaultValue = ArrayUtils.toPrimitive((Short[]) defaultValue);
                case SHORTARRAY:
                    sarray = (short[]) defaultValue;
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getShortArray(fieldName, ano.spacer(), sarray);
                        } else {
                            result = propertiesFile.getShortArray(fieldName, sarray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getShortArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesFile.getShortArray(fieldName);
                        }
                    }
                    break;

                // String
                case STRING:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesFile.getString(fieldName, (String) defaultValue);
                    } else {
                        result = propertiesFile.getString(fieldName);
                    }
                    break;

                case STRINGARRAY:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getStringArray(fieldName, ano.spacer(), (String[]) defaultValue);
                        } else {
                            result = propertiesFile.getStringArray(fieldName, (String[]) defaultValue);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesFile.getStringArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesFile.getStringArray(fieldName);
                        }
                    }
                    break;

                default:
                    result = null;
                    // Log not being able to save a field because it is not supported
                    EELogger.getLogger("UtilConfigSetUp").warning("Unable to Processes Type: " + fieldType.toString() + " for " + config.getClass().getSimpleName());
                    break;
            }

            // Now lets see if we have a value to set into the ConfigBase
            if (result != null) {
                // Adds any amount of comments to the key in the file
                if ((ano.comments().length != 1 || !ano.comments()[0].equals(""))) {
                    propertiesFile.setComments(fieldName, ano.comments());
                }
                try {
                    // Now lets set that field to a value if we have one
                    field.set(config, result);
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

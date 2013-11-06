package net.larry1123.util.config;

import net.larry1123.util.logger.EELogger;
import net.visualillusionsent.utils.PropertiesFile;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class ConfigLogic {

    /**
     * Saves data to disk if any change has been made
     */
    public static void save(PropertiesFile propertiesfile, ConfigBase config, Field[] configFields) {
        // Just got to make sure this stuff is not null
        if (config == null) throw new NullPointerException("PropertiesFile can not be null");
        if (config == null) throw new NullPointerException("ConfigBase can not be null");
        if (configFields == null) return;

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
                    propertiesfile.setBoolean(fieldName, (Boolean) value);
                    break;

                // Byte
                case BYTEWRAP:
                case BYTE:
                    propertiesfile.setByte(fieldName, (Byte) value);
                    break;

                case BYTEWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Byte[]) value);
                case BYTEARRAY:
                    barray = (byte[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesfile.setByteArray(fieldName, ano.spacer(), barray);
                    } else {
                        propertiesfile.setByteArray(fieldName, barray);
                    }
                    break;

                // Character
                case CHARACTERWRAP:
                case CHARACTER:
                    propertiesfile.setCharacter(fieldName, (Character) value);
                    break;

                // Double
                case DOUBLEWRAP:
                case DOUBLE:
                    propertiesfile.setDouble(fieldName, (Double) value);
                    break;

                case DOUBLEWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Double[]) value);
                case DOUBLEARRAY:
                    darray = (double[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesfile.setDoubleArray(fieldName, ano.spacer(), darray);
                    } else {
                        propertiesfile.setDoubleArray(fieldName, darray);
                    }
                    break;

                // Float
                case FLOATWRAP:
                case FLOAT:
                    propertiesfile.setFloat(fieldName, (Float) value);
                    break;

                case FLOATWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Float[]) value);
                case FLOATARRAY:
                    farray = (float[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesfile.setFloatArray(fieldName, ano.spacer(), farray);
                    } else {
                        propertiesfile.setFloatArray(fieldName, farray);
                    }
                    break;

                // Integer
                case INTEGERWRAP:
                case INTEGER:
                    propertiesfile.setInt(fieldName, (Integer) value);
                    break;

                case INTEGERWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Integer[]) value);
                case INTEGERARRAY:
                    iarray = (int[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesfile.setIntArray(fieldName, ano.spacer(), iarray);
                    } else {
                        propertiesfile.setIntArray(fieldName, iarray);
                    }
                    break;

                // Long
                case LONGWRAP:
                case LONG:
                    propertiesfile.setLong(fieldName, (Long) value);
                    break;

                case LONGWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Long[]) value);
                case LONGArray:
                    larray = (long[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesfile.setLongArray(fieldName, ano.spacer(), larray);
                    } else {
                        propertiesfile.setLongArray(fieldName, larray);
                    }
                    break;

                // Short
                case SHORTWRAP:
                case SHORT:
                    propertiesfile.setShort(fieldName, (Short) value);
                    break;

                case SHORTWRAPARRAY:
                    // Make Object Array into an Primitive Array
                    value = ArrayUtils.toPrimitive((Short[]) value);
                case SHORTARRAY:
                    sarray = (short[]) value;
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesfile.setShortArray(fieldName, ano.spacer(), sarray);
                    } else {
                        propertiesfile.setShortArray(fieldName, sarray);
                    }
                    break;

                // String
                case STRING:
                    propertiesfile.setString(fieldName, (String) value);
                    break;

                case STRINGARRAY:
                    // Uses a custom spacer if given
                    if (hasSpacer) {
                        propertiesfile.setStringArray(fieldName, ano.spacer(), (String[]) value);
                    } else {
                        propertiesfile.setStringArray(fieldName, (String[]) value);
                    }
                    break;

                default:
                    // Log not being able to save a field because it is not supported
                    EELogger.getLogger("UtilConfigSetUp").warning("Unable to Processes Type: " + fieldType.toString() + " for " + config.getClass().getSimpleName());
                    break;
            }
        }
        // Well lets SAVE!!!
        propertiesfile.save();
    }

    /**
     * Loads data from file to ConfigBase, and makes the file's default settings if needed
     */
    public static void load(PropertiesFile propertiesfile, ConfigBase config, Field[] configFields) {
        // Just got to make sure this stuff is not null
        if (config == null) throw new NullPointerException("PropertiesFile can not be null");
        if (config == null) throw new NullPointerException("ConfigBase can not be null");
        if (configFields == null) return;

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

            Object result = null;

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
                        result = propertiesfile.getBoolean(fieldName, (Boolean) defaultValue);
                    } else {
                        result = propertiesfile.getBoolean(fieldName);
                    }
                    break;

                // Byte
                case BYTEWRAP:
                case BYTE:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesfile.getByte(fieldName, (Byte) defaultValue);
                    } else {
                        result = propertiesfile.getByte(fieldName);
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
                            result = propertiesfile.getByteArray(fieldName, ano.spacer(), barray);
                        } else {
                            result = propertiesfile.getByteArray(fieldName, barray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesfile.getByteArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getByteArray(fieldName);
                        }
                    }
                    break;

                // Character
                case CHARACTERWRAP:
                case CHARACTER:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesfile.getCharacter(fieldName, (Character) defaultValue);
                    } else {
                        result = propertiesfile.getCharacter(fieldName);
                    }
                    break;

                // Double
                case DOUBLEWRAP:
                case DOUBLE:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesfile.getDouble(fieldName, (Double) defaultValue);
                    } else {
                        result = propertiesfile.getDouble(fieldName);
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
                            result = propertiesfile.getDoubleArray(fieldName, ano.spacer(), darray);
                        } else {
                            result = propertiesfile.getDoubleArray(fieldName, darray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesfile.getDoubleArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getDoubleArray(fieldName);
                        }
                    }
                    break;

                // Float
                case FLOATWRAP:
                case FLOAT:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesfile.getFloat(fieldName, (Float) defaultValue);
                    } else {
                        result = propertiesfile.getFloat(fieldName);
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
                            result = propertiesfile.getFloatArray(fieldName, ano.spacer(), farray);
                        } else {
                            result = propertiesfile.getFloatArray(fieldName, farray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesfile.getFloatArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getFloatArray(fieldName);
                        }
                    }
                    break;

                // Integer
                case INTEGERWRAP:
                case INTEGER:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesfile.getInt(fieldName, (Integer) defaultValue);
                    } else {
                        result = propertiesfile.getInt(fieldName);
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
                            result = propertiesfile.getIntArray(fieldName, ano.spacer(), iarray);
                        } else {
                            result = propertiesfile.getIntArray(fieldName, iarray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesfile.getIntArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getIntArray(fieldName);
                        }
                    }
                    break;

                // Long
                case LONGWRAP:
                case LONG:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesfile.getLong(fieldName, (Long) defaultValue);
                    } else {
                        result = propertiesfile.getLong(fieldName);
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
                            result = propertiesfile.getLongArray(fieldName, ano.spacer(), larray);
                        } else {
                            result = propertiesfile.getLongArray(fieldName, larray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesfile.getLongArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getLongArray(fieldName);
                        }
                    }
                    break;

                // Short
                case SHORTWRAP:
                case SHORT:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesfile.getShort(fieldName, (Short) defaultValue);
                    } else {
                        result = propertiesfile.getShort(fieldName);
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
                            result = propertiesfile.getShortArray(fieldName, ano.spacer(), sarray);
                        } else {
                            result = propertiesfile.getShortArray(fieldName, sarray);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesfile.getShortArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getShortArray(fieldName);
                        }
                    }
                    break;

                // String
                case STRING:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        result = propertiesfile.getString(fieldName, (String) defaultValue);
                    } else {
                        result = propertiesfile.getString(fieldName);
                    }
                    break;

                case STRINGARRAY:
                    // Gets the current value or sets the default value
                    if (hasValue) {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesfile.getStringArray(fieldName, ano.spacer(), (String[]) defaultValue);
                        } else {
                            result = propertiesfile.getStringArray(fieldName, (String[]) defaultValue);
                        }
                    } else {
                        // Uses a custom spacer if given
                        if (hasSpacer) {
                            result = propertiesfile.getStringArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getStringArray(fieldName);
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
                    propertiesfile.setComments(fieldName, ano.comments());
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
        propertiesfile.save();
    }

}

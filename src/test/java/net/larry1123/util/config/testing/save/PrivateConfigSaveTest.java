package net.larry1123.util.config.testing.save;

import net.larry1123.util.config.FieldHandler;
import net.larry1123.util.config.testing.TestConfigFile;
import net.visualillusionsent.utils.PropertiesFile;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 2/1/14 - 5:09 AM
 */
public class PrivateConfigSaveTest {

    private final String savePath = "target/tests/resources/save.cfg";

    @Test
    public void privateBooleanSave() {
        String fieldName = "privateBoolean";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setBoolean();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue((savePropertiesFile.getBoolean(fieldName) == testField.getBoolean(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateBooleanSave() {
        String fieldName = "PrivateBoolean";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setBoolean();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue((savePropertiesFile.getBoolean(fieldName) == (Boolean) testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateByteSave() {
        String fieldName = "privateByte";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setByte();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue((savePropertiesFile.getByte(fieldName) == testField.getByte(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateByteSave() {
        String fieldName = "PrivateByte";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.getByte();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue((savePropertiesFile.getByte(fieldName) == (Byte) testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateByteArraySave() {
        String fieldName = "privateByteArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setByteArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getByteArray(fieldName), testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateByteArraySave() {
        String fieldName = "PrivateByteArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setByteArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            byte[] testFieldValue = ArrayUtils.toPrimitive((Byte[]) testField.get(configFile));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getByteArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateByteListSave() {
        String fieldName = "privateByteList";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setByteArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            //noinspection unchecked
            byte[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Byte>) testField.get(configFile)).toArray(ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getByteArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateCharacterSave() {
        String fieldName = "privateCharacter";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setCharacter();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getCharacter(fieldName) == testField.getChar(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateCharacterSave() {
        String fieldName = "PrivateCharacter";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setCharacter();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getCharacter(fieldName) == (Character) testField.get(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateDoubleSave() {
        String fieldName = "privateDouble";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setDouble();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getDouble(fieldName) == testField.getDouble(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateDoubleSave() {
        String fieldName = "PrivateDouble";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setDouble();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getDouble(fieldName) == (Double) testField.get(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateDoubleArraySave() {
        String fieldName = "privateDoubleArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setDoubleArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getDoubleArray(fieldName), testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateDoubleArraySave() {
        String fieldName = "PrivateDoubleArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setDoubleArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            double[] testFieldValue = ArrayUtils.toPrimitive((Double[]) testField.get(configFile));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getDoubleArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateDoubleListSave() {
        String fieldName = "privateDoubleList";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setDoubleArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            //noinspection unchecked,unchecked
            double[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Double>) testField.get(configFile)).toArray(new Double[((ArrayList<Double>) testField.get(configFile)).size()]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getDoubleArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateFloatSave() {
        String fieldName = "privateFloat";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setFloat();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getFloat(fieldName) == testField.getFloat(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateFloatSave() {
        String fieldName = "PrivateFloat";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setFloat();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getFloat(fieldName) == (Float) testField.get(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateFloatArraySave() {
        String fieldName = "privateFloatArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setFloatArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getFloatArray(fieldName), testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateFloatArraySave() {
        String fieldName = "PrivateFloatArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setFloatArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            float[] testFieldValue = ArrayUtils.toPrimitive((Float[]) testField.get(configFile));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getFloatArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateFloatListSave() {
        String fieldName = "privateFloatList";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setFloatArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            //noinspection unchecked,unchecked
            float[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Float>) testField.get(configFile)).toArray(new Float[((ArrayList<Float>) testField.get(configFile)).size()]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getFloatArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateLongSave() {
        String fieldName = "privateLong";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setLong();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getLong(fieldName) == testField.getLong(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateLongSave() {
        String fieldName = "PrivateLong";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setLong();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getLong(fieldName) == (Long) testField.get(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateLongArraySave() {
        String fieldName = "privateLongArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setLongArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getLongArray(fieldName), testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateLongArrayArraySave() {
        String fieldName = "PrivateLongArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setLongArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            long[] testFieldValue = ArrayUtils.toPrimitive((Long[]) testField.get(configFile));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getLongArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateLongListSave() {
        String fieldName = "privateLongList";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setLongArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            //noinspection unchecked,unchecked
            long[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Long>) testField.get(configFile)).toArray(new Long[((ArrayList<Long>) testField.get(configFile)).size()]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getLongArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateIntegerSave() {
        String fieldName = "privateInteger";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setInteger();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getInt(fieldName) == testField.getInt(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateIntegerSave() {
        String fieldName = "PrivateInteger";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setInteger();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getInt(fieldName) == (Integer) testField.get(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateIntegerArraySave() {
        String fieldName = "privateIntegerArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setIntegerArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getIntArray(fieldName), testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateIntegerArraySave() {
        String fieldName = "PrivateIntegerArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setIntegerArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            int[] testFieldValue = ArrayUtils.toPrimitive((Integer[]) testField.get(configFile));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getIntArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateIntegerListSave() {
        String fieldName = "privateIntegerList";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setIntegerArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            //noinspection unchecked,unchecked
            int[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Integer>) testField.get(configFile)).toArray(new Integer[((ArrayList<Integer>) testField.get(configFile)).size()]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getIntArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateShortSave() {
        String fieldName = "privateShort";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setShort();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getShort(fieldName) == testField.getShort(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateShortSave() {
        String fieldName = "PrivateShort";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setShort();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getShort(fieldName) == (Short) testField.get(configFile));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateShortArraySave() {
        String fieldName = "privateShortArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setShortArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getShortArray(fieldName), testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void PrivateShortArraySave() {
        String fieldName = "PrivateShortArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setShortArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            short[] testFieldValue = ArrayUtils.toPrimitive((Short[]) testField.get(configFile));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getShortArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateShortListSave() {
        String fieldName = "privateShortList";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setShortArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            //noinspection unchecked,unchecked
            short[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Short>) testField.get(configFile)).toArray(new Short[((ArrayList<Short>) testField.get(configFile)).size()]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getShortArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateStringSave() {
        String fieldName = "privateString";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setString();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(savePropertiesFile.getString(fieldName).equals(testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateStringArraySave() {
        String fieldName = "privateStringArray";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setStringArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getStringArray(fieldName), testField.get(configFile)));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void privateStringListSave() {
        String fieldName = "privateStringList";
        PropertiesFile savePropertiesFile = new PropertiesFile(savePath);
        TestConfigFile configFile = new TestConfigFile();
        Field testField = getField(configFile, fieldName);
        try {
            FieldHandler handler = new FieldHandler(testField, savePropertiesFile, configFile);
            handler.setStringArray();
            savePropertiesFile.save();
        } catch (NoSuchFieldException e) {
            Assert.fail("Seems that the field we just got from the object does not belong to that object!!!!!! Wat!");
        }
        try {
            //noinspection unchecked,unchecked
            String[] testFieldValue = ((ArrayList<String>) testField.get(configFile)).toArray(new String[((ArrayList<String>) testField.get(configFile)).size()]);
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getStringArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    private Field getField(Object ob, String fieldName) {
        try {
            Field ret = ob.getClass().getDeclaredField(fieldName);
            ret.setAccessible(true);
            return ret;
        } catch (NoSuchFieldException e) {
            Assert.fail("Can't get field, " + fieldName + ", from ConfigBase!");
            return null;
        }
    }

}


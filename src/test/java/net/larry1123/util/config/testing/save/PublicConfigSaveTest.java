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
 * @since 1/31/14 - 9:33 AM
 */
public class PublicConfigSaveTest {

    private String savePath = "target/tests/resources/save.cfg";

    @Test
    public void publicBooleanSave() {
        String fieldName = "publicBoolean";
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
    public void PublicBooleanSave() {
        String fieldName = "PublicBoolean";
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
    public void publicByteSave() {
        String fieldName = "publicByte";
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
    public void PublicByteSave() {
        String fieldName = "PublicByte";
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
    public void publicByteArraySave() {
        String fieldName = "publicByteArray";
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
    public void PublicByteArraySave() {
        String fieldName = "PublicByteArray";
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
    public void publicByteListSave() {
        String fieldName = "publicByteList";
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
            byte[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Byte>) testField.get(configFile)).toArray(ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getByteArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void publicCharacterSave() {
        String fieldName = "publicCharacter";
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
    public void PublicCharacterSave() {
        String fieldName = "PublicCharacter";
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
    public void publicDoubleSave() {
        String fieldName = "publicDouble";
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
    public void PublicDoubleSave() {
        String fieldName = "PublicDouble";
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
    public void publicDoubleArraySave() {
        String fieldName = "publicDoubleArray";
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
    public void PublicDoubleArraySave() {
        String fieldName = "PublicDoubleArray";
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
    public void publicDoubleListSave() {
        String fieldName = "publicDoubleList";
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
            double[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Double>) testField.get(configFile)).toArray(new Double[0]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getDoubleArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void publicFloatSave() {
        String fieldName = "publicFloat";
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
    public void PublicFloatSave() {
        String fieldName = "PublicFloat";
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
    public void publicFloatArraySave() {
        String fieldName = "publicFloatArray";
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
    public void PublicFloatArraySave() {
        String fieldName = "PublicFloatArray";
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
    public void publicFloatListSave() {
        String fieldName = "publicFloatList";
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
            float[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Float>) testField.get(configFile)).toArray(new Float[0]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getFloatArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void publicLongSave() {
        String fieldName = "publicLong";
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
    public void PublicLongSave() {
        String fieldName = "PublicLong";
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
    public void publicLongArraySave() {
        String fieldName = "publicLongArray";
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
    public void PublicLongArrayArraySave() {
        String fieldName = "PublicLongArray";
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
    public void publicLongListSave() {
        String fieldName = "publicLongList";
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
            long[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Long>) testField.get(configFile)).toArray(new Long[0]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getLongArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void publicIntegerSave() {
        String fieldName = "publicInteger";
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
    public void PublicIntegerSave() {
        String fieldName = "PublicInteger";
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
    public void publicIntegerArraySave() {
        String fieldName = "publicIntegerArray";
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
    public void PublicIntegerArraySave() {
        String fieldName = "PublicIntegerArray";
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
    public void publicIntegerListSave() {
        String fieldName = "publicIntegerList";
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
            int[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Integer>) testField.get(configFile)).toArray(new Integer[0]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getIntArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void publicShortSave() {
        String fieldName = "publicShort";
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
    public void PublicShortSave() {
        String fieldName = "PublicShort";
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
    public void publicShortArraySave() {
        String fieldName = "publicShortArray";
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
    public void PublicShortArraySave() {
        String fieldName = "PublicShortArray";
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
    public void publicShortListSave() {
        String fieldName = "publicShortList";
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
            short[] testFieldValue = ArrayUtils.toPrimitive(
                    ((ArrayList<Short>) testField.get(configFile)).toArray(new Short[0]));
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getShortArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    @Test
    public void publicStringSave() {
        String fieldName = "publicString";
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
    public void publicStringArraySave() {
        String fieldName = "publicStringArray";
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
    public void publicStringListSave() {
        String fieldName = "publicStringList";
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
            String[] testFieldValue = ((ArrayList<String>) testField.get(configFile)).toArray(new String[0]);
            Assert.assertTrue(ArrayUtils.isEquals(savePropertiesFile.getStringArray(fieldName), testFieldValue));
        } catch (IllegalAccessException e) {
            Assert.fail("Well seems that we could not read the field " + fieldName + " -.-");
        }
    }

    public Field getField(Object ob, String fieldName) {
        try {
            Field ret = ob.getClass().getDeclaredField(fieldName);
            ret.setAccessible(true);
            return ret;
        } catch (NoSuchFieldException e) {
            Assert.fail("Can't get field from ConfigBase!");
            return null;
        }
    }

}

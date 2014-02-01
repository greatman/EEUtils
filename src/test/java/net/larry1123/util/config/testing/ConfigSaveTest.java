package net.larry1123.util.config.testing;

import net.larry1123.util.config.FieldHandler;
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
public class ConfigSaveTest {

    private String savePath = "src/test/resources/save.cfg";

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
        String fieldName = "publicDoubleArray";
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
        String fieldName = "PublicDoubleArray";
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
        String fieldName = "publicDoubleList";
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

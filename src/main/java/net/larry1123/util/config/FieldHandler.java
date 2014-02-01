package net.larry1123.util.config;

import com.google.common.collect.Lists;
import com.google.common.primitives.*;
import net.visualillusionsent.utils.PropertiesFile;
import net.visualillusionsent.utils.UtilityException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Larry1123
 * @since 1/24/14 - 4:29 AM
 */
public class FieldHandler {

    private final Field field;
    private final String fieldName;
    private final Object config;
    private final PropertiesFile propertiesFile;
    private final Type fieldType;
    private final String spacer;
    private final ConfigField ano;
    private Object value = null;
    private Object retreatedValue = null;

    /**
     * TODO
     *
     * @param field
     * @param propertiesFile
     * @param config
     * @param fieldName
     * @throws NoSuchFieldException is thrown if the config object does not contain the given Field
     */
    public FieldHandler(Field field, PropertiesFile propertiesFile, Object config, String fieldName) throws NoSuchFieldException {
        config.getClass().getField(field.getName());
        this.field = field;
        // Ensure we can use the field
        this.getField().setAccessible(true);
        ano = field.getAnnotation(ConfigField.class);

        this.propertiesFile = propertiesFile;
        this.config = config;
        this.fieldName = fieldName;
        // Get type of field to be checked later
        this.fieldType = field.getGenericType();
        // Lets get the Spacer
        this.spacer = (getAno().spacer().equals("")) ? "," : getAno().spacer();

        // Lets check if there is something to set
        try {
            setValue(field.get(config));
        } catch (IllegalArgumentException e1) {
        } catch (IllegalAccessException e1) {
        }
    }

    /**
     * TODO
     *
     * @param field
     * @param propertiesFile
     * @param config
     * @throws NoSuchFieldException is thrown if the config object does not contain the given Field
     */
    public FieldHandler(Field field, PropertiesFile propertiesFile, Object config) throws NoSuchFieldException {
        config.getClass().getField(field.getName());
        this.field = field;
        // Ensure we can use the field
        this.getField().setAccessible(true);
        ano = field.getAnnotation(ConfigField.class);

        this.propertiesFile = propertiesFile;
        this.config = config;
        // Lets give this thing a name
        this.fieldName = (getAno().name().equals("")) ? field.getName() : getAno().name();
        // Get type of field to be checked later
        this.fieldType = field.getGenericType();
        // Lets get the Spacer
        this.spacer = (getAno().spacer().equals("")) ? "," : getAno().spacer();

        // Lets check if there is a value to pull from the field
        try {
            setValue(field.get(config));
        } catch (IllegalArgumentException ignored) {
            // We don't care if it errors as it does not matter
        } catch (IllegalAccessException ignored) {
            // We don't care if it errors as it does not matter
        }
    }

    /**
     * This will set the field to the object if it can
     * This method will make sure that a Long[] can be set to a long[] field or the other way around
     * Will do nothing if retreatedValue is null
     *
     * @throws IllegalAccessException
     */
    // Cast are checked with the switches
    public boolean setField() throws IllegalAccessException {
        // well it is null so nothing happens
        if (getRetreatedValue() == null) return false;

        // Lets see if we need a List
        if (getField().get(getConfig()) instanceof List) {
            if (!(getRetreatedValue() instanceof List)) {
                switch (TypeValues.getFromType(fieldType)) {
                    case SHORTLIST:
                    case LONGLIST:
                    case BYTELIST:
                    case DOUBLELIST:
                    case FLOATLIST:
                    case INTEGERLIST:
                    case STRINGLIST:
                        setRetreatedValue(arrayToList(getValue()));
                        break;
                }
            }
        }
        if (TypeUtils.isArrayType(fieldType)) {
            if (!Primitives.isWrapperType(fieldType.getClass().getComponentType())) {
                // Field is Primitive so we need to check if the object getting set is also
                if (!Primitives.isWrapperType(getRetreatedValue().getClass().getComponentType())) {
                    // Seems that the object was not time to make that Object into a Primitive
                    switch (TypeValues.getFromType(getRetreatedValue().getClass())) {
                        case SHORTWRAPARRAY:
                            setRetreatedValue(ArrayUtils.toPrimitive((Short[]) getRetreatedValue()));
                            break;
                        case LONGWRAPARRAY:
                            setRetreatedValue(ArrayUtils.toPrimitive((Long[]) getRetreatedValue()));
                            break;
                        case FLOATWRAPARRAY:
                            setRetreatedValue(ArrayUtils.toPrimitive((Float[]) getRetreatedValue()));
                            break;
                        case DOUBLEWRAPARRAY:
                            setRetreatedValue(ArrayUtils.toPrimitive((Double[]) getRetreatedValue()));
                            break;
                        case INTEGERWRAPARRAY:
                            setRetreatedValue(ArrayUtils.toPrimitive((Integer[]) getRetreatedValue()));
                            break;
                        case BYTEWRAPARRAY:
                            setRetreatedValue(ArrayUtils.toPrimitive((Byte[]) getRetreatedValue()));
                            break;
                    }
                }
            } else {
                // Field is an Object Wrapper Array so lets check of the object is also
                if (Primitives.isWrapperType(getRetreatedValue().getClass().getComponentType())) {
                    // Seems that the object was not lets make it into a Object Wrapper
                    switch (TypeValues.getFromType(getRetreatedValue().getClass().getClass())) {
                        case SHORTARRAY:
                            setRetreatedValue(ArrayUtils.toObject((short[]) getRetreatedValue()));
                            break;
                        case LONGARRAY:
                            setRetreatedValue(ArrayUtils.toObject((long[]) getRetreatedValue()));
                            break;
                        case FLOATARRAY:
                            setRetreatedValue(ArrayUtils.toObject((float[]) getRetreatedValue()));
                            break;
                        case DOUBLEARRAY:
                            setRetreatedValue(ArrayUtils.toObject((double[]) getRetreatedValue()));
                            break;
                        case INTEGERARRAY:
                            setRetreatedValue(ArrayUtils.toObject((int[]) getRetreatedValue()));
                            break;
                        case BYTEARRAY:
                            setRetreatedValue(ArrayUtils.toObject((byte[]) getRetreatedValue()));
                            break;
                    }
                }
            }
        }
        if (TypeUtils.isAssignable(getField().getType(), getRetreatedValue().getClass())) {
            getField().set(getConfig(), getRetreatedValue());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets any amount of comments to the key in the file
     * Does nothing if the file does not contain the field
     */
    public void setComments() {
        if (getValue() == null) throw new NullPointerException("Value can not be null when setting");
        if (!getPropertiesFile().containsKey(getFieldName())) return;
        // Adds any amount of comments to the key in the file
        if ((getAno().comments().length != 1 || !getAno().comments()[0].equals(""))) {
            getPropertiesFile().setComments(getFieldName(), getAno().comments());
        }
    }

    /**
     * Set Methods
     */

    public void setStringArray() {
        if (getValue() == null) throw new NullPointerException("Value can not be null when setting");
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Lists.newArrayList((List<String>) getValue()));
        if (!TypeUtils.isAssignable(getValue().getClass(), String[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((String[]) getValue()))
            getPropertiesFile().setStringArray(getFieldName(), getSpacer(), (String[]) getValue());
    }

    public void setShortArray() {
        if (getValue() == null) throw new NullPointerException("Value can not be null when setting");
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Shorts.toArray((List<Short>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.SHORTWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Short[]) getValue()));
        }
        if (!TypeUtils.isAssignable(getValue().getClass(), short[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((short[]) getValue())) {
            getPropertiesFile().setShortArray(getFieldName(), getSpacer(), (short[]) getValue());
        }
    }

    public void setLongArray() {
        if (getValue() == null) throw new NullPointerException("Value can not be null when setting");
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Longs.toArray((List<Long>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.LONGWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Long[]) getValue()));
        }
        if (!TypeUtils.isAssignable(getValue().getClass(), long[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((long[]) getValue())) {
            getPropertiesFile().setLongArray(getFieldName(), getSpacer(), (long[]) getValue());
        }
    }

    public void setIntegerArray() {
        if (getValue() == null) throw new NullPointerException("Value can not be null when setting");
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Ints.toArray((List<Integer>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.FLOATWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Integer[]) getValue()));
        }
        if (!TypeUtils.isAssignable(getValue().getClass(), int[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((int[]) getValue())) {
            getPropertiesFile().setIntArray(getFieldName(), getSpacer(), (int[]) getValue());
        }
    }

    public void setFloatArray() {
        if (getValue() == null) throw new NullPointerException("Value can not be null when setting");
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Floats.toArray((List<Float>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.FLOATWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Float[]) getValue()));
        }
        if (!TypeUtils.isAssignable(getValue().getClass(), float[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((float[]) getValue())) {
            getPropertiesFile().setFloatArray(getFieldName(), getSpacer(), (float[]) getValue());
        }
    }

    public void setDoubleArray() {
        if (getValue() == null) throw new NullPointerException("Value can not be null when setting");
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Doubles.toArray((List<Double>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.DOUBLEWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Double[]) getValue()));
        }
        if (!TypeUtils.isAssignable(getValue().getClass(), double[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((double[]) getValue())) {
            getPropertiesFile().setDoubleArray(getFieldName(), getSpacer(), (double[]) getValue());
        }
    }

    public void setByteArray() {
        if (getValue() == null) throw new NullPointerException("Value can not be null when setting");
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Bytes.toArray((List<Byte>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.BYTEWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Byte[]) getValue()));
        }
        if (!TypeUtils.isAssignable(getValue().getClass(), byte[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((byte[]) getValue())) {
            getPropertiesFile().setByteArray(getFieldName(), getSpacer(), (byte[]) getValue());
        }
    }

    public void setString() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), String.class)) throw new IllegalArgumentException();
            getPropertiesFile().setString(getFieldName(), (String) getValue());
        }
    }

    public void setShort() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), Short.class)) throw new IllegalArgumentException();
            getPropertiesFile().setShort(getFieldName(), (Short) getValue());
        }
    }

    public void setLong() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), Long.class)) throw new IllegalArgumentException();
            getPropertiesFile().setLong(getFieldName(), (Long) getValue());
        }
    }

    public void setInteger() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), Integer.class)) throw new IllegalArgumentException();
            getPropertiesFile().setInt(getFieldName(), (Integer) getValue());
        }
    }

    public void setFloat() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), Float.class)) throw new IllegalArgumentException();
            getPropertiesFile().setFloat(getFieldName(), (Float) getValue());
        }
    }

    public void setDouble() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), Double.class)) throw new IllegalArgumentException();
            getPropertiesFile().setDouble(getFieldName(), (Double) getValue());
        }
    }

    public void setByte() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), Byte.class)) throw new IllegalArgumentException();
            getPropertiesFile().setByte(getFieldName(), (Byte) getValue());
        }
    }

    public void setCharacter() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), Character.class)) throw new IllegalArgumentException();
            getPropertiesFile().setCharacter(getFieldName(), (Character) getValue());
        }
    }

    public void setBoolean() {
        if (getValue() != null) {
            if (!TypeUtils.isAssignable(getValue().getClass(), Boolean.class)) throw new IllegalArgumentException();
            getPropertiesFile().setBoolean(getFieldName(), (Boolean) getValue());
        }
    }

    /**
     * Get Methods
     */

    public String[] getStringArray() throws IllegalArgumentException, UtilityException {
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Lists.newArrayList((ArrayList<String>) getValue()));
        // Gets the current value or sets the default value
        if (!TypeUtils.isAssignable(getValue().getClass(), String[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((String[]) getValue())) {
            setRetreatedValue(getPropertiesFile().getStringArray(getFieldName(), getSpacer(), (String[]) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getStringArray(getFieldName(), getSpacer()));
        }
        return (String[]) getRetreatedValue();
    }

    public short[] getShortArray() throws IllegalArgumentException, UtilityException {
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Shorts.toArray((ArrayList<Short>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.SHORTWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Short[]) getValue()));
        }
        // Gets the current value or sets the default value
        if (!TypeUtils.isAssignable(getValue().getClass(), short[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((short[]) getValue())) {
            setRetreatedValue(getPropertiesFile().getShortArray(getFieldName(), getSpacer(), (short[]) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getShortArray(getFieldName(), getSpacer()));
        }
        return (short[]) getRetreatedValue();
    }

    public long[] getLongArray() throws IllegalArgumentException, UtilityException {
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Longs.toArray((ArrayList<Long>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.LONGWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Long[]) getValue()));
        }
        // Gets the current value or sets the default value
        if (!TypeUtils.isAssignable(getValue().getClass(), long[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((long[]) getValue())) {
            setRetreatedValue(getPropertiesFile().getLongArray(getFieldName(), getSpacer(), (long[]) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getLongArray(getFieldName(), getSpacer()));
        }
        return (long[]) getRetreatedValue();
    }

    public int[] getIntegerArray() throws IllegalArgumentException, UtilityException {
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Ints.toArray((ArrayList<Integer>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.INTEGERWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Integer[]) getValue()));
        }
        // Gets the current value or sets the default value
        if (!TypeUtils.isAssignable(getValue().getClass(), int[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((long[]) getValue())) {
            setRetreatedValue(getPropertiesFile().getIntArray(getFieldName(), getSpacer(), (int[]) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getIntArray(getFieldName(), getSpacer()));
        }
        return (int[]) getRetreatedValue();
    }

    public float[] getFloatArray() throws IllegalArgumentException, UtilityException {
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Floats.toArray((ArrayList<Float>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.FLOATWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Float[]) getValue()));
        }
        // Gets the current value or sets the default value
        if (!TypeUtils.isAssignable(getValue().getClass(), float[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((float[]) getValue())) {
            setRetreatedValue(getPropertiesFile().getFloatArray(getFieldName(), getSpacer(), (float[]) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getFloatArray(getFieldName(), getSpacer()));
        }
        return (float[]) getRetreatedValue();
    }

    public double[] getDoubleArray() throws IllegalArgumentException, UtilityException {
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Doubles.toArray((ArrayList<Double>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.DOUBLEWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Double[]) getValue()));
        }
        // Gets the current value or sets the default value
        if (!TypeUtils.isAssignable(getValue().getClass(), double[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((double[]) getValue())) {
            setRetreatedValue(getPropertiesFile().getDoubleArray(getFieldName(), getSpacer(), (double[]) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getDoubleArray(getFieldName(), getSpacer()));
        }
        return (double[]) getRetreatedValue();
    }

    public byte[] getByteArray() throws IllegalArgumentException, UtilityException {
        if (TypeValues.getFromType(getFieldType()).isList())
            setValue(Bytes.toArray((ArrayList<Byte>) getValue()));
        if (TypeValues.getFromType(getFieldType()).isWrappedArray()) {
            if (TypeValues.getFromType(getFieldType()).equals(TypeValues.BYTEWRAPARRAY))
                setValue(ArrayUtils.toPrimitive((Byte[]) getValue()));
        }
        // Gets the current value or sets the default value
        if (!TypeUtils.isAssignable(getValue().getClass(), byte[].class)) throw new IllegalArgumentException();
        if (ArrayUtils.isNotEmpty((byte[]) getValue())) {
            setRetreatedValue(getPropertiesFile().getByteArray(getFieldName(), getSpacer(), (byte[]) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getByteArray(getFieldName(), getSpacer()));
        }
        return (byte[]) getRetreatedValue();
    }

    public String getString() throws IllegalArgumentException, UtilityException {
        if (StringUtils.isNotEmpty((String) getValue())) {
            if (!TypeUtils.isAssignable(getValue().getClass(), String.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getString(getFieldName(), (String) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getString(getFieldName()));
        }
        return (String) getRetreatedValue();
    }

    public Short getShort() throws IllegalArgumentException, UtilityException {
        if (getValue() != null) {
            /**
             * This Check should work with only checking for the wrapped type as to be saved to an Object it would have
             * been autoboxed
             */
            if (!getValue().getClass().equals(Short.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getShort(getFieldName(), (Short) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getShort(getFieldName()));
        }
        return (Short) getRetreatedValue();
    }

    public Long getLong() throws IllegalArgumentException, UtilityException {
        if (getValue() != null) {
            if (!getValue().getClass().equals(Long.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getLong(getFieldName(), (Long) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getLong(getFieldName()));
        }
        return (Long) getRetreatedValue();
    }

    public Integer getInteger() throws IllegalArgumentException, UtilityException {
        if (getValue() != null) {
            if (!getValue().getClass().equals(Integer.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getInt(getFieldName(), (Integer) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getInt(getFieldName()));
        }
        return (Integer) getRetreatedValue();
    }

    public Float getFloat() throws IllegalArgumentException, UtilityException {
        if (getValue() != null) {
            if (!getValue().getClass().equals(Float.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getFloat(getFieldName(), (Float) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getFloat(getFieldName()));
        }
        return (Float) getRetreatedValue();
    }

    public Double getDouble() throws IllegalArgumentException, UtilityException {
        if (getValue() != null) {
            if (!getValue().getClass().equals(Double.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getDouble(getFieldName(), (Double) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getDouble(getFieldName()));
        }
        return (Double) getRetreatedValue();
    }

    public Character getCharacter() throws IllegalArgumentException, UtilityException {
        if (getValue() != null) {
            if (!getValue().getClass().equals(Character.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getCharacter(getFieldName(), (Character) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getCharacter(getFieldName()));
        }
        return (Character) getRetreatedValue();
    }

    public Byte getByte() throws IllegalArgumentException, UtilityException {
        if (getValue() != null) {
            if (!getValue().getClass().equals(Byte.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getByte(getFieldName(), (Byte) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getByte(getFieldName()));
        }
        return (Byte) getRetreatedValue();
    }

    public Boolean getBoolean() throws IllegalArgumentException, UtilityException {
        if (getValue() != null) {
            if (!getValue().getClass().equals(Boolean.class)) throw new IllegalArgumentException();
            setRetreatedValue(getPropertiesFile().getBoolean(getFieldName(), (Boolean) getValue()));
        } else {
            setRetreatedValue(getPropertiesFile().getBoolean(getFieldName()));
        }
        return (Boolean) getRetreatedValue();
    }

    /**
     * Other Get stuffs
     */

    public Field getField() {
        return field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getConfig() {
        return config;
    }

    public PropertiesFile getPropertiesFile() {
        return propertiesFile;
    }

    public Type getFieldType() {
        return fieldType;
    }

    public String getSpacer() {
        return spacer;
    }

    public ConfigField getAno() {
        return ano;
    }

    public synchronized Object getValue() {
        return value;
    }

    public synchronized Object getRetreatedValue() {
        return retreatedValue;
    }

    private synchronized void setRetreatedValue(Object retreatedValue) {
        this.retreatedValue = retreatedValue;
    }

    private <E> E[] primitiveArrayToWrapped(E... array) {
        return array;
    }

    private <E> List<E> arrayToList(E... array) {
        return Lists.newArrayList(array);
    }

    private <E> E[] listToArray(List<E> list) {
        return (E[]) list.toArray();
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

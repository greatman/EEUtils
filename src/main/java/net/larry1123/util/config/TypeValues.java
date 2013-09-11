package net.larry1123.util.config;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * User: Owner
 * Date: 9/11/13
 * Time: 11:21 AM
 */
public enum TypeValues {

    BOOLEAN(Boolean.TYPE), //
    BOOLEANWRAP(Boolean.class), //
    BYTE(Byte.TYPE), //
    BYTEWRAP(Byte.class), //
    BYTEARRAY(byte[].class), //
    BYTEWRAPARRAY(Byte[].class), //
    CHARACTER(Character.TYPE), //
    CHARACTERWRAP(Character.class), //
    DOUBLE(Double.TYPE), //
    DOUBLEWRAP(Double.class), //
    DOUBLEARRAY(double[].class), //
    DOUBLEWRAPARRAY(Double[].class), //
    FLOAT(Float.TYPE), //
    FLOATWRAP(Float.class), //
    FLOATARRAY(float[].class), //
    FLOATWRAPARRAY(Float[].class), //
    INTEGER(Integer.TYPE), //
    INTEGERWRAP(Integer.class), //
    INTEGERARRAY(int[].class), //
    INTEGERWRAPARRAY(Integer[].class), //
    LONG(Long.TYPE), //
    LONGWRAP(Long.class), //
    LONGArray(long[].class), //
    LONGWRAPARRAY(Long[].class), //
    SHORT(Short.TYPE), //
    SHORTWRAP(Short.class), //
    SHORTARRAY(short[].class), //
    SHORTWRAPARRAY(Short[].class), //
    STRING(String.class), //
    STRINGARRAY(String[].class), //
    OTHER(null);

    private final Type thisType;

    TypeValues(Type type) {
        thisType = type;
    }

    public static TypeValues getFromType(Type type) {
        for (TypeValues t : TypeValues.values()) {
            if (t.getTypeClass().equals(type)) {
                return t;
            }
        }
        return BOOLEAN;
    }

    public Type getTypeClass() {
        return thisType;
    }

    @SuppressWarnings ( "unused" )
    private static Type getFieldType(String name) {
        try {
            Field field = TypeValues.class.getField(name);
            field.setAccessible(true);
            return field.getGenericType();
        } catch (SecurityException e) {
            //
        } catch (NoSuchFieldException e) {
            //
        }

        return null;
    }

}

package net.larry1123.util.config;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 9/11/13 - 11:21 AM
 */
@SuppressWarnings("WeakerAccess")
public enum TypeValues {

    BOOLEAN(Boolean.TYPE), //
    BOOLEANWRAP(Boolean.class), //
    BYTE(Byte.TYPE), //
    BYTEWRAP(Byte.class), //
    BYTEARRAY(byte[].class), //
    BYTEWRAPARRAY((new Byte[0]).getClass()), //
    BYTELIST(getFieldType("byteList")), //
    CHARACTER(Character.TYPE), //
    CHARACTERWRAP(Character.class), //
    DOUBLE(Double.TYPE), //
    DOUBLEWRAP(Double.class), //
    DOUBLEARRAY(double[].class), //
    DOUBLEWRAPARRAY((new Double[0]).getClass()), //
    DOUBLELIST(getFieldType("doubleList")), //
    FLOAT(Float.TYPE), //
    FLOATWRAP(Float.class), //
    FLOATARRAY(float[].class), //
    FLOATWRAPARRAY((new Float[0]).getClass()), //
    FLOATLIST(getFieldType("floatList")), //
    INTEGER(Integer.TYPE), //
    INTEGERWRAP(Integer.class), //
    INTEGERARRAY(int[].class), //
    INTEGERWRAPARRAY((new Integer[0]).getClass()), //
    INTEGERLIST(getFieldType("integerList")), //
    LONG(Long.TYPE), //
    LONGWRAP(Long.class), //
    LONGARRAY(long[].class), //
    LONGWRAPARRAY((new Long[0]).getClass()), //
    LONGLIST(getFieldType("longList")), //
    SHORT(Short.TYPE), //
    SHORTWRAP(Short.class), //
    SHORTARRAY(short[].class), //
    SHORTWRAPARRAY((new Short[0]).getClass()), //
    SHORTLIST(getFieldType("shortList")), //
    STRING(String.class), //
    STRINGARRAY((new String[0]).getClass()), //
    STRINGLIST(getFieldType("stringList")), //
    OTHER(null);

    @SuppressWarnings("UnusedDeclaration")
    private static ArrayList<Short> shortList = new ArrayList<Short>();
    @SuppressWarnings("UnusedDeclaration")
    private static ArrayList<Long> longList = new ArrayList<Long>();
    @SuppressWarnings("UnusedDeclaration")
    private static ArrayList<Float> floatList = new ArrayList<Float>();
    @SuppressWarnings("UnusedDeclaration")
    private static ArrayList<Double> doubleList = new ArrayList<Double>();
    @SuppressWarnings("UnusedDeclaration")
    private static ArrayList<Integer> integerList = new ArrayList<Integer>();
    @SuppressWarnings("UnusedDeclaration")
    private static ArrayList<Byte> byteList = new ArrayList<Byte>();
    @SuppressWarnings("UnusedDeclaration")
    private static ArrayList<String> stringList = new ArrayList<String>();

    private final Type thisType;

    TypeValues(Type type) {
        thisType = type;
    }

    public static TypeValues getFromType(Type type) {
        for (TypeValues t : TypeValues.values()) {
            if (t == TypeValues.OTHER) continue;
            if (t.getTypeClass().equals(type)) {
                return t;
            }
        }
        return OTHER;
    }

    public Type getTypeClass() {
        return thisType;
    }

    private static Type getFieldType(String name) {
        try {
            Field field = TypeValues.class.getDeclaredField(name);
            field.setAccessible(true);
            return field.getGenericType();
        } catch (SecurityException e) {
            //
        } catch (NoSuchFieldException e) {
            //
        }

        return null;
    }

    public boolean isList() {
        switch (this) {
            case BYTELIST:
            case DOUBLELIST:
            case FLOATLIST:
            case INTEGERLIST:
            case LONGLIST:
            case SHORTLIST:
            case STRINGLIST:
                return true;
        }
        return false;
    }

    @SuppressWarnings("UnusedDeclaration")
    public boolean isPrimitiveArray() {
        if (TypeUtils.isArrayType(thisType)) {
            switch (this) {
                case BYTEARRAY:
                case DOUBLEARRAY:
                case FLOATARRAY:
                case INTEGERARRAY:
                case LONGARRAY:
                case SHORTARRAY:
                    return true;
            }
        }
        return false;
    }

    public boolean isWrappedArray() {
        if (TypeUtils.isArrayType(thisType)) {
            switch (this) {
                case BYTEWRAPARRAY:
                case DOUBLEWRAPARRAY:
                case FLOATWRAPARRAY:
                case INTEGERWRAPARRAY:
                case LONGWRAPARRAY:
                case SHORTWRAPARRAY:
                    return true;
            }
        }
        return false;
    }

}

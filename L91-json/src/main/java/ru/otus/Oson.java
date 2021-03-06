package ru.otus;

import java.lang.reflect.Field;
import java.util.Collection;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public final class Oson {

    public String toJson(final Object src) throws IllegalAccessException {
        final JsonObjectBuilder builder = Json.createObjectBuilder();
        for (Field field : src.getClass().getDeclaredFields()) {
            this.handle(src, field, builder);
        }
        return builder.build().toString();
    }

    private void handle(
        final Object src,
        final Field field,
        final JsonObjectBuilder builder
    ) throws IllegalAccessException {
        field.setAccessible(true);
        final Class<?> type = field.getType();
        if (type.isArray()) {
            builder.add(field.getName(), array(src, field).build());
        }
        if (type.equals(Collection.class)) {
            final JsonArrayBuilder array = Json.createArrayBuilder();
            for (Object obj : ((Collection) field.get(src))) {
                iterateThrough(array, obj);
            }
            builder.add(field.getName(), array.build());
        }
        if (type.equals(int.class)) {
            builder.add(field.getName(), field.getInt(src));
        }
        if (type.equals(long.class)) {
            builder.add(field.getName(), field.getLong(src));
        }
        if (type.equals(double.class)) {
            builder.add(field.getName(), field.getDouble(src));
        }
        if (type.equals(float.class)) {
            builder.add(field.getName(), field.getFloat(src));
        }
        if (type.equals(byte.class)) {
            builder.add(field.getName(), field.getByte(src));
        }
        if (type.equals(short.class)) {
            builder.add(field.getName(), field.getShort(src));
        }
        if (type.equals(boolean.class)) {
            builder.add(field.getName(), field.getBoolean(src));
        }
        if (type.equals(String.class)) {
            builder.add(field.getName(), field.get(src).toString());
        }
    }

    private JsonArrayBuilder array(final Object src, final Field field) throws IllegalAccessException {
        final JsonArrayBuilder array = Json.createArrayBuilder();
        final Class<?> type = field.getType().getComponentType();
        if (type.equals(double.class)) {
            for (double obj : (double[]) field.get(src)) {
                array.add(obj);
            }
        } else if (type.equals(int.class)) {
            for (int obj : (int[]) field.get(src)) {
                array.add(obj);
            }
        } else if (type.equals(float.class)) {
            for (float obj : (float[]) field.get(src)) {
                array.add(obj);
            }
        } else if (type.equals(long.class)) {
            for (long obj : (long[]) field.get(src)) {
                array.add(obj);
            }
        } else if (type.equals(short.class)) {
            for (short obj : (short[]) field.get(src)) {
                array.add(obj);
            }
        } else if (type.equals(byte.class)) {
            for (byte obj : (byte[]) field.get(src)) {
                array.add(obj);
            }
        } else if (type.equals(boolean.class)) {
            for (boolean obj : (boolean[]) field.get(src)) {
                array.add(obj);
            }
        } else {
            for (Object obj : (Object[]) field.get(src)) {
                iterateThrough(array, obj);
            }
        }
        return array;
    }

    private void iterateThrough(
        final JsonArrayBuilder array,
        final Object obj
    ) throws IllegalAccessException {
        if (obj instanceof Double
            || obj.getClass().equals(double.class)) {
            array.add((double) obj);
        } else if (obj instanceof Float
            || obj.getClass().equals(float.class)) {
            array.add((float) obj);
        } else if (obj instanceof Integer
            || obj.getClass().equals(int.class)) {
            array.add((int) obj);
        } else if (obj instanceof Long
            || obj.getClass().equals(long.class)) {
            array.add((long) obj);
        } else if (obj instanceof Short
            || obj.getClass().equals(short.class)) {
            array.add((short) obj);
        } else if (obj instanceof Byte
            || obj.getClass().equals(byte.class)) {
            array.add((byte) obj);
        } else if (obj instanceof Boolean
            || obj.getClass().equals(boolean.class)) {
            array.add((boolean) obj);
        } else if (obj instanceof Character) {
            array.add(obj.toString());
        } else if (obj instanceof String) {
            array.add(obj.toString());
        } else {
            final JsonObjectBuilder second = Json.createObjectBuilder();
            for (Field f : obj.getClass().getDeclaredFields()) {
                handle(obj, f, second);
            }
            array.add(second);
        }
    }
}

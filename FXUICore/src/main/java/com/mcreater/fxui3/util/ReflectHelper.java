package com.mcreater.fxui3.util;

import java.lang.reflect.Field;

public class ReflectHelper {
    public static <T> void setPrivateField(Class<T> targetClass, T object, String fieldName, Object value) throws Exception {
        Field field = targetClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }
    public static <T> void setPrivateField(T object, String fieldName, Object value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }
    public static <T1, T2> T2 getPrivateField(Class<T1> targetClass, T1 object, String fieldName, Class<T2> fieldClass) throws Exception {
        Field field = targetClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T2) field.get(object);
    }
    public static <T1, T2> T2 getPrivateField(T1 object, String fieldName, Class<T2> fieldClass) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T2) field.get(object);
    }
}

package com.mcreater.fxui3.util;

import java.lang.reflect.Field;

public class ReflectHelper {
    public static <T, C> T getMethodPrivate(Class<C> sourceClass, C object, String name, Class<T> clazz) {
        try {
            Field f = sourceClass.getDeclaredField(name);
            f.setAccessible(true);
            return (T) f.get(object);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

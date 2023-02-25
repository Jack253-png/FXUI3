package com.mcreater.fxui3.controls.converters;

import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

public class EnumConverter<T extends Enum<T>> extends StyleConverter<String, T> {
    private final Class<T> metaClass;
    private EnumConverter(Class<T> metaClass) {
        this.metaClass = metaClass;
    }
    public static <T extends Enum<T>> EnumConverter<T> getInstance(Class<T> metaClass) {
        return new EnumConverter<>(metaClass);
    }
    public T convert(ParsedValue<String, T> parsedValue, Font font) {
        try {
            return T.valueOf(metaClass, parsedValue.getValue().toUpperCase().replace("-", "_"));
        }
        catch (Exception e) {
            return null;
        }
    }
}

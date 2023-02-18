package com.mcreater.fxui3.controls.converters;

import com.sun.javafx.css.StyleConverterImpl;
import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

import java.util.logging.Logger;

public class NumberConverter extends StyleConverterImpl<String, Number> {
    private NumberConverter() {
        super();
    }
    private static class Holder {
        static final NumberConverter INSTANCE = new NumberConverter();

        private Holder() {
            throw new IllegalAccessError("Holder class");
        }
    }

    public static StyleConverter<String, Number> getInstance() {
        return NumberConverter.Holder.INSTANCE;
    }
    public Number convert(ParsedValue<String, Number> value, Font notUsedFont) {
        String string = value.getValue();
        try {
            try {
                return Long.valueOf(string);
            }
            catch (Exception ignored) {}

            try {
                return Double.valueOf(string);
            }
            catch (Exception ignored) {}

            throw new NumberFormatException("Not a vaild number.");
        }
        catch (Exception e) {
            Logger.getLogger(NumberConverter.class.getName()).warning(String.format("invaild number %s", string));
            return null;
        }
    }
    public String toString() {
        return "NumberConverter";
    }
}

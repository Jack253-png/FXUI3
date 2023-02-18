package com.mcreater.fxui3.controls.converters;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.sun.javafx.css.StyleConverterImpl;
import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;
import javafx.util.converter.NumberStringConverter;

import java.util.logging.Logger;

public class ThemeConverter extends StyleConverterImpl<String, ResourceProcessor.ThemeType> {
    private ThemeConverter() {
        super();
    }
    private static class Holder {
        static final ThemeConverter INSTANCE = new ThemeConverter();

        private Holder() {
            throw new IllegalAccessError("Holder class");
        }
    }

    public static StyleConverter<String, ResourceProcessor.ThemeType> getInstance() {
        return ThemeConverter.Holder.INSTANCE;
    }
    public ResourceProcessor.ThemeType convert(ParsedValue<String, ResourceProcessor.ThemeType> value, Font notUsedFont) {
        String string = value.getValue();
        try {
            return ResourceProcessor.ThemeType.valueOf(string);
        }
        catch (Exception e) {
            Logger.getLogger(ThemeConverter.class.getName()).warning(String.format("invaild theme name %s", string));
            return null;
        }
    }
    public String toString() {
        return "ThemeConverter";
    }
}

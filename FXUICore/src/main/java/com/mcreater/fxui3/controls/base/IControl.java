package com.mcreater.fxui3.controls.base;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.UIButton;
import com.mcreater.fxui3.controls.converters.ThemeConverter;
import javafx.beans.property.ObjectProperty;
import javafx.css.CssMetaData;
import javafx.css.ParsedValue;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.text.Font;

public interface IControl {
    void initialize();
    void setTheme(ResourceProcessor.ThemeType type);
    ResourceProcessor.ThemeType getTheme();
    ObjectProperty<ResourceProcessor.ThemeType> themeProperty();
}

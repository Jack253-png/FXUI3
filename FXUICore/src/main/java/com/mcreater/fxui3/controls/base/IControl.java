package com.mcreater.fxui3.controls.base;

import com.mcreater.fxui3.assets.ResourceProcessor;
import javafx.beans.property.ObjectProperty;
import javafx.css.StyleableIntegerProperty;
import javafx.scene.paint.Color;

public interface IControl {
    void initialize();
    void setTheme(ResourceProcessor.ThemeType type);
    ResourceProcessor.ThemeType getTheme();
    ObjectProperty<ResourceProcessor.ThemeType> themeProperty();
    void setColorizationColor(Color color);
    Color getColorizationColor();
    ObjectProperty<Color> colorizationColorProperty();
    public void setAnimationSpeed(int speed);
    public int getAnimationSpeed();
    public StyleableIntegerProperty animationSpeedProperty();
}

package com.mcreater.fxui3.controls;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.base.IControl;
import com.mcreater.fxui3.controls.converters.NumberConverter;
import com.mcreater.fxui3.controls.converters.ThemeConverter;
import com.mcreater.fxui3.controls.skins.UICheckBoxSkin;
import com.mcreater.fxui3.util.FXUtil;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableIntegerProperty;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableIntegerProperty;
import javafx.css.StyleableObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mcreater.fxui3.assets.ResourceProcessor.DARK_USERAGENT_STYLESHEET;
import static com.mcreater.fxui3.assets.ResourceProcessor.LIGHT_USERAGENT_STYLESHEET;

public class UICheckBox extends CheckBox implements IControl {
    private static final String DEFAULT_STYLE_CLASS = "ui-check-box";
    public UICheckBox() {
        super();
        initialize();
    }

    public UICheckBox(String var1) {
        super(var1);
        initialize();
    }

    public void initialize() {
        this.getStyleClass().add(DEFAULT_STYLE_CLASS);
    }
    public void requestFocus() {}
    private final StyleableObjectProperty<ResourceProcessor.ThemeType> themeProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.THEME,
            UICheckBox.this,
            "theme",
            ResourceProcessor.ThemeType.LIGHT
    );
    private final StyleableIntegerProperty animationSpeedProperty = new SimpleStyleableIntegerProperty(
            StyleableProperties.ANIMATION_SPEED,
            40
    );
    private final StyleableObjectProperty<Color> colorizationColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.COLORIZATION_COLOR,
            UICheckBox.this,
            "colorization_color",
            null
    );

    public void setTheme(ResourceProcessor.ThemeType type) {
        themeProperty().set(type);
    }

    public ResourceProcessor.ThemeType getTheme() {
        return themeProperty().get();
    }

    public StyleableObjectProperty<ResourceProcessor.ThemeType> themeProperty() {
        return themeProperty;
    }

    public StyleableObjectProperty<Color> colorizationColorProperty() {
        return colorizationColorProperty;
    }

    public void setAnimationSpeed(int speed) {
        animationSpeedProperty().set(speed);
    }

    public int getAnimationSpeed() {
        return animationSpeedProperty().get();
    }

    public StyleableIntegerProperty animationSpeedProperty() {
        return animationSpeedProperty;
    }

    public Color getColorizationColor() {
        return colorizationColorProperty().get();
    }
    public void setColorizationColor(Color color) {
        colorizationColorProperty().set(color);
    }


    private static final class StyleableProperties {
        private static final CssMetaData<UICheckBox, ResourceProcessor.ThemeType> THEME =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-theme", ThemeConverter.getInstance(), ResourceProcessor.ThemeType.LIGHT, UICheckBox::themeProperty);
        private static final CssMetaData<UICheckBox, Number> ANIMATION_SPEED =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-animation-speed", NumberConverter.getInstance(), 40, UICheckBox::animationSpeedProperty);
        private static final CssMetaData<UICheckBox, Color> COLORIZATION_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-colorization-color", StyleConverter.getColorConverter(), null, UICheckBox::colorizationColorProperty);

        private static final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<>(Button.getClassCssMetaData());
            Collections.addAll(styleables, THEME, COLORIZATION_COLOR);
            CHILD_STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return StyleableProperties.CHILD_STYLEABLES;
    }
    public String getUserAgentStylesheet() {
        return getTheme() == ResourceProcessor.ThemeType.LIGHT ? LIGHT_USERAGENT_STYLESHEET : DARK_USERAGENT_STYLESHEET;
    }
    protected Skin<?> createDefaultSkin() {
        return new UICheckBoxSkin(this);
    }
}

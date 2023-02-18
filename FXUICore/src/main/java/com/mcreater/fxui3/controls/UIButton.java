package com.mcreater.fxui3.controls;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.base.IControl;
import com.mcreater.fxui3.controls.converters.NumberConverter;
import com.mcreater.fxui3.controls.converters.ThemeConverter;
import com.mcreater.fxui3.controls.skins.UIButtonSkin;
import com.mcreater.fxui3.util.FXUtil;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableIntegerProperty;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableIntegerProperty;
import javafx.css.StyleableObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mcreater.fxui3.assets.ResourceProcessor.DARK_USERAGENT_STYLESHEET;
import static com.mcreater.fxui3.assets.ResourceProcessor.LIGHT_USERAGENT_STYLESHEET;

public class UIButton extends Button implements IControl {
    private static final String DEFAULT_STYLE_CLASS = "ui-button";
    private final StyleableProperties DEFAULT_PROPERTIES = new StyleableProperties();
    public UIButton() {
        super();
        initialize();
    }
    public UIButton(String text) {
        super(text);
        initialize();
    }
    public UIButton(String text, Node graphic) {
        super(text, graphic);
        initialize();
    }
    public void requestFocus() {}
    public void initialize() {
        this.getStyleClass().add(DEFAULT_STYLE_CLASS);
    }
    private final StyleableObjectProperty<ResourceProcessor.ThemeType> themeProperty = new SimpleStyleableObjectProperty<>(
            DEFAULT_PROPERTIES.THEME,
            UIButton.this,
            "theme",
            ResourceProcessor.ThemeType.LIGHT
    );
    private final StyleableIntegerProperty animationSpeedProperty = new SimpleStyleableIntegerProperty(
            DEFAULT_PROPERTIES.ANIMATION_SPEED,
            100
    );
    private final StyleableIntegerProperty definedBorderRadiusProperty = new SimpleStyleableIntegerProperty(
            DEFAULT_PROPERTIES.DEFINED_BORDER_RADIUS,
            5
    );
    private final StyleableIntegerProperty definedBorderWidthProperty = new SimpleStyleableIntegerProperty(
            DEFAULT_PROPERTIES.DEFINED_BORDER_WIDTH,
            1
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

    public void setAnimationSpeed(int speed) {
        animationSpeedProperty.set(speed);
    }
    public int getAnimationSpeed() {
        return animationSpeedProperty.get();
    }
    public StyleableIntegerProperty animationSpeedProperty() {
        return animationSpeedProperty;
    }
    private StyleableIntegerProperty definedBorderRadiusProperty() {
        return definedBorderRadiusProperty;
    }
    public int getDefinedBorderRadiusProperty() {
        return definedBorderRadiusProperty().get();
    }
    private StyleableIntegerProperty definedBorderWidthProperty() {
        return definedBorderWidthProperty;
    }
    public int getDefinedBorderWidthProperty() {
        return definedBorderWidthProperty().get();
    }

    private final class StyleableProperties {
        private final CssMetaData<UIButton, ResourceProcessor.ThemeType> THEME =
                FXUtil.createCSSMetaData(ThemeConverter.getInstance(), UIButton.this::themeProperty, "-ui-button-theme", ResourceProcessor.ThemeType.LIGHT);
        private final CssMetaData<UIButton, Number> ANIMATION_SPEED =
                FXUtil.createCSSMetaData(NumberConverter.getInstance(), UIButton.this::animationSpeedProperty, "-ui-button-animation-speed", 100);
        private final CssMetaData<UIButton, Number> DEFINED_BORDER_RADIUS =
                FXUtil.createCSSMetaData(NumberConverter.getInstance(), UIButton.this::definedBorderRadiusProperty, "-ui-button-defined-border-radius", 5);
        private final CssMetaData<UIButton, Number> DEFINED_BORDER_WIDTH =
                FXUtil.createCSSMetaData(NumberConverter.getInstance(), UIButton.this::definedBorderWidthProperty, "-ui-button-defined-border-width", 1);

        private final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        private StyleableProperties() {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<>(Button.getClassCssMetaData());
            Collections.addAll(styleables, THEME, ANIMATION_SPEED, DEFINED_BORDER_RADIUS, DEFINED_BORDER_WIDTH);
            CHILD_STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return new StyleableProperties().CHILD_STYLEABLES;
    }
    public String getUserAgentStylesheet() {
        return getTheme() == ResourceProcessor.ThemeType.LIGHT ? LIGHT_USERAGENT_STYLESHEET : DARK_USERAGENT_STYLESHEET;
    }
    protected Skin<?> createDefaultSkin() {
        return new UIButtonSkin(this);
    }
}
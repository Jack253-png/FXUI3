package com.mcreater.fxui3.controls;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.base.IControl;
import com.mcreater.fxui3.controls.converters.ThemeConverter;
import com.mcreater.fxui3.controls.skins.UIButtonSkin;
import javafx.css.CssMetaData;
import javafx.css.ParsedValue;
import javafx.css.SimpleStyleableIntegerProperty;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableIntegerProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mcreater.fxui3.assets.ResourceProcessor.DARK_USERAGENT_STYLESHEET;
import static com.mcreater.fxui3.assets.ResourceProcessor.LIGHT_USERAGENT_STYLESHEET;

public class UIButton extends Button implements IControl {
    private static final String DEFAULT_STYLE_CLASS = "ui-button";
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
    public void initialize() {
        this.getStyleClass().add(DEFAULT_STYLE_CLASS);
    }
    private final StyleableObjectProperty<ResourceProcessor.ThemeType> themeProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.THEME,
            UIButton.this,
            "theme",
            ResourceProcessor.ThemeType.LIGHT
    );
    private final StyleableIntegerProperty animationSpeedProperty = new SimpleStyleableIntegerProperty(
            StyleableProperties.ANIMATION_SPEED,
            100
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

    private static class StyleableProperties {
        private static final CssMetaData<UIButton, ResourceProcessor.ThemeType> THEME =
                new CssMetaData<UIButton, ResourceProcessor.ThemeType>("-ui-button-theme",
                        ThemeConverter.getInstance(), ResourceProcessor.ThemeType.LIGHT) {
                    public boolean isSettable(UIButton control) {
                        return !control.themeProperty.isBound();
                    }
                    public StyleableProperty<ResourceProcessor.ThemeType> getStyleableProperty(UIButton control) {
                        return control.themeProperty();
                    }
                };
        private static final CssMetaData<UIButton, Number> ANIMATION_SPEED =
                new CssMetaData<UIButton, Number>("-ui-button-animation-speed",
                        new StyleConverter<String, Number>() {
                            public Number convert(ParsedValue<String, Number> var1, Font var2) {
                                try {
                                    return Integer.parseInt(var1.getValue());
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                    return 100;
                                }
                            }
                        }, 100) {
                    public boolean isSettable(UIButton control) {
                        return !control.animationSpeedProperty.isBound();
                    }
                    public StyleableProperty<Number> getStyleableProperty(UIButton control) {
                        return control.animationSpeedProperty();
                    }
                };


        private static final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<>(Button.getClassCssMetaData());
            Collections.addAll(styleables, THEME, ANIMATION_SPEED);
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
        return new UIButtonSkin(this);
    }
}
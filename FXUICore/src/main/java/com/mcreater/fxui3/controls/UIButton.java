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
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableIntegerProperty;
import javafx.css.StyleableObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

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
    public void requestFocus() {}
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
    private final StyleableIntegerProperty definedBorderRadiusProperty = new SimpleStyleableIntegerProperty(
            StyleableProperties.DEFINED_BORDER_RADIUS,
            5
    );
    private final StyleableIntegerProperty definedBorderWidthProperty = new SimpleStyleableIntegerProperty(
            StyleableProperties.DEFINED_BORDER_WIDTH,
            1
    );
    private final StyleableObjectProperty<Color> targetTextColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.TARGET_TEXT_COLOR,
            UIButton.this,
            "target_text_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> stdTextColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.STD_TEXT_COLOR,
            UIButton.this,
            "std_text_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> targetBorderColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.TARGET_BORDER_COLOR,
            UIButton.this,
            "target_border_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> stdBorderColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.STD_BORDER_COLOR,
            UIButton.this,
            "std_border_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> stdBackgroundColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.STD_BACKGROUND_COLOR,
            UIButton.this,
            "std_background_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> enterBackgroundColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.ENTER_BACKGROUND_COLOR,
            UIButton.this,
            "enter_background_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> pressedBackgroundColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.PRESSED_BACKGROUND_COLOR,
            UIButton.this,
            "pressed_background_color",
            Color.BLACK
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
    public int getDefinedBorderRadius() {
        return definedBorderRadiusProperty().get();
    }
    private StyleableIntegerProperty definedBorderWidthProperty() {
        return definedBorderWidthProperty;
    }
    public int getDefinedBorderWidth() {
        return definedBorderWidthProperty().get();
    }
    private StyleableObjectProperty<Color> targetTextColorProperty() {
        return targetTextColorProperty;
    }
    public Color getTargetTextColor() {
        return targetTextColorProperty().get();
    }
    private StyleableObjectProperty<Color> stdTextColorProperty() {
        return stdTextColorProperty;
    }
    public Color getStdTextColor() {
        return stdTextColorProperty().get();
    }
    private StyleableObjectProperty<Color> targetBorderColorProperty() {
        return targetBorderColorProperty;
    }
    public Color getTargetBorderColor() {
        return targetBorderColorProperty().get();
    }
    private StyleableObjectProperty<Color> stdBorderColorProperty() {
        return stdBorderColorProperty;
    }
    public Color getStdBorderColor() {
        return stdBorderColorProperty().get();
    }
    private StyleableObjectProperty<Color> stdBackgroundColorProperty() {
        return stdBackgroundColorProperty;
    }
    public Color getStdBackgroundColor() {
        return stdBackgroundColorProperty().get();
    }
    private StyleableObjectProperty<Color> enterBackgroundColorProperty() {
        return enterBackgroundColorProperty;
    }
    public Color getEnterBackgroundColor() {
        return enterBackgroundColorProperty().get();
    }
    private StyleableObjectProperty<Color> pressedBackgroundColorProperty() {
        return pressedBackgroundColorProperty;
    }
    public Color getPressedBackgroundColor() {
        return pressedBackgroundColorProperty().get();
    }

    private static final class StyleableProperties {
        private static final CssMetaData<UIButton, ResourceProcessor.ThemeType> THEME =
                new FXUtil.CssMetaDataImpl<>("-ui-button-theme", ThemeConverter.getInstance(), ResourceProcessor.ThemeType.LIGHT, UIButton::themeProperty);
        private static final CssMetaData<UIButton, Number> ANIMATION_SPEED =
                new FXUtil.CssMetaDataImpl<>("-ui-button-animation-speed", NumberConverter.getInstance(), 100, UIButton::animationSpeedProperty);
        private static final CssMetaData<UIButton, Number> DEFINED_BORDER_RADIUS =
                new FXUtil.CssMetaDataImpl<>("-ui-button-defined-border-radius", NumberConverter.getInstance(), 5, UIButton::definedBorderRadiusProperty);
        private static final CssMetaData<UIButton, Number> DEFINED_BORDER_WIDTH =
                new FXUtil.CssMetaDataImpl<>("-ui-button-defined-border-width",NumberConverter.getInstance(), 1, UIButton::definedBorderWidthProperty);
        private static final CssMetaData<UIButton, Color> TARGET_TEXT_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-button-target-text-color", StyleConverter.getColorConverter(), Color.BLACK, UIButton::targetTextColorProperty);
        private static final CssMetaData<UIButton, Color> STD_TEXT_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-button-std-text-color", StyleConverter.getColorConverter(), Color.BLACK, UIButton::stdTextColorProperty);
        private static final CssMetaData<UIButton, Color> TARGET_BORDER_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-button-target-border-color", StyleConverter.getColorConverter(), Color.BLACK, UIButton::targetBorderColorProperty);
        private static final CssMetaData<UIButton, Color> STD_BORDER_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-button-std-border-color", StyleConverter.getColorConverter(), Color.BLACK, UIButton::stdBorderColorProperty);
        private static final CssMetaData<UIButton, Color> STD_BACKGROUND_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-button-std-background-color", StyleConverter.getColorConverter(), Color.BLACK, UIButton::stdBackgroundColorProperty);
        private static final CssMetaData<UIButton, Color> ENTER_BACKGROUND_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-button-enter-background-color", StyleConverter.getColorConverter(), Color.BLACK, UIButton::enterBackgroundColorProperty);
        private static final CssMetaData<UIButton, Color> PRESSED_BACKGROUND_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-button-pressed-background-color", StyleConverter.getColorConverter(), Color.BLACK, UIButton::pressedBackgroundColorProperty);

        private static final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<>(Button.getClassCssMetaData());
            Collections.addAll(styleables, THEME, ANIMATION_SPEED, DEFINED_BORDER_RADIUS, DEFINED_BORDER_WIDTH, TARGET_TEXT_COLOR, STD_TEXT_COLOR, TARGET_BORDER_COLOR, STD_BORDER_COLOR, STD_BACKGROUND_COLOR, ENTER_BACKGROUND_COLOR, PRESSED_BACKGROUND_COLOR);
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
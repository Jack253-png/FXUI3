package com.mcreater.fxui3.controls;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.base.IControl;
import com.mcreater.fxui3.controls.converters.EnumConverter;
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
    private final StyleableObjectProperty<Color> colorizationColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.COLORIZATION_COLOR,
            UICheckBox.this,
            "colorization_color",
            null
    );
    private final StyleableObjectProperty<ColorizationType> colorizationTypeProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.COLORIZATION_TYPE,
            UICheckBox.this,
            "colorization_type",
            ColorizationType.NOT_SELECTED
    );
    private final StyleableIntegerProperty backgroundInsetProperty = new SimpleStyleableIntegerProperty(
            StyleableProperties.BACKGROUND_INSET,
            1
    );
    private final StyleableIntegerProperty borderRadiusProperty = new SimpleStyleableIntegerProperty(
            StyleableProperties.BORDER_RADIUS,
            5
    );
    private final StyleableObjectProperty<Color> targetBorderColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.TARGET_BORDER_COLOR,
            UICheckBox.this,
            "target_border_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> stdBorderColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.STD_BORDER_COLOR,
            UICheckBox.this,
            "std_border_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> stdBackgroundColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.STD_BACKGROUND_COLOR,
            UICheckBox.this,
            "std_background_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> enterBackgroundColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.ENTER_BACKGROUND_COLOR,
            UICheckBox.this,
            "enter_background_color",
            Color.BLACK
    );
    private final StyleableObjectProperty<Color> pressedBackgroundColorProperty = new SimpleStyleableObjectProperty<>(
            StyleableProperties.PRESSED_BACKGROUND_COLOR,
            UICheckBox.this,
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

    public StyleableObjectProperty<Color> colorizationColorProperty() {
        return colorizationColorProperty;
    }

    public Color getColorizationColor() {
        return colorizationColorProperty().get();
    }
    public void setColorizationColor(Color color) {
        colorizationColorProperty().set(color);
    }
    public StyleableObjectProperty<ColorizationType> colorizationTypeProperty() {
        return colorizationTypeProperty;
    }
    public ColorizationType getColorizationType() {
        return colorizationTypeProperty().get();
    }
    private StyleableIntegerProperty backgroundInsetProperty() {
        return backgroundInsetProperty;
    }
    public int getBackgroundInset() {
        return backgroundInsetProperty().get();
    }
    private StyleableIntegerProperty borderRadiusProperty() {
        return borderRadiusProperty;
    }
    public int getBorderRadius() {
        return borderRadiusProperty().get();
    }
    public StyleableObjectProperty<Color> targetBorderColorProperty() {
        return targetBorderColorProperty;
    }
    public Color getTargetBorderColor() {
        return targetBorderColorProperty().get();
    }
    public StyleableObjectProperty<Color> stdBorderColorProperty() {
        return stdBorderColorProperty;
    }
    public Color getStdBorderColor() {
        return stdBorderColorProperty().get();
    }
    public StyleableObjectProperty<Color> stdBackgroundColorProperty() {
        return stdBackgroundColorProperty;
    }
    public Color getStdBackgroundColor() {
        return stdBackgroundColorProperty().get();
    }
    public StyleableObjectProperty<Color> enterBackgroundColorProperty() {
        return enterBackgroundColorProperty;
    }
    public Color getEnterBackgroundColor() {
        return enterBackgroundColorProperty().get();
    }
    public StyleableObjectProperty<Color> pressedBackgroundColorProperty() {
        return pressedBackgroundColorProperty;
    }
    public Color getPressedBackgroundColor() {
        return pressedBackgroundColorProperty().get();
    }

    public enum ColorizationType {
        NOT_SELECTED,
        NORMAL,
        HOVER,
        PRESSED
    }

    private static final class StyleableProperties {
        private static final CssMetaData<UICheckBox, ResourceProcessor.ThemeType> THEME =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-theme", ThemeConverter.getInstance(), ResourceProcessor.ThemeType.LIGHT, UICheckBox::themeProperty);
        private static final CssMetaData<UICheckBox, Color> COLORIZATION_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-colorization-color", StyleConverter.getColorConverter(), null, UICheckBox::colorizationColorProperty);
        private static final CssMetaData<UICheckBox, ColorizationType> COLORIZATION_TYPE =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-colorization-type", EnumConverter.getInstance(ColorizationType.class), ColorizationType.NOT_SELECTED, UICheckBox::colorizationTypeProperty);
        private static final CssMetaData<UICheckBox, Number> BACKGROUND_INSET =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-background-inset", NumberConverter.getInstance(), 1, UICheckBox::backgroundInsetProperty);
        private static final CssMetaData<UICheckBox, Number> BORDER_RADIUS =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-border-radius", NumberConverter.getInstance(), 5, UICheckBox::borderRadiusProperty);
        
        private static final CssMetaData<UICheckBox, Color> TARGET_BORDER_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-target-border-color", StyleConverter.getColorConverter(), Color.BLACK, UICheckBox::targetBorderColorProperty);
        private static final CssMetaData<UICheckBox, Color> STD_BORDER_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-std-border-color", StyleConverter.getColorConverter(), Color.BLACK, UICheckBox::stdBorderColorProperty);
        private static final CssMetaData<UICheckBox, Color> STD_BACKGROUND_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-std-background-color", StyleConverter.getColorConverter(), Color.BLACK, UICheckBox::stdBackgroundColorProperty);
        private static final CssMetaData<UICheckBox, Color> ENTER_BACKGROUND_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-enter-background-color", StyleConverter.getColorConverter(), Color.BLACK, UICheckBox::enterBackgroundColorProperty);
        private static final CssMetaData<UICheckBox, Color> PRESSED_BACKGROUND_COLOR =
                new FXUtil.CssMetaDataImpl<>("-ui-check-box-pressed-background-color", StyleConverter.getColorConverter(), Color.BLACK, UICheckBox::pressedBackgroundColorProperty);

        private static final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<>(Button.getClassCssMetaData());
            Collections.addAll(styleables, THEME, COLORIZATION_COLOR, COLORIZATION_TYPE, BACKGROUND_INSET, BORDER_RADIUS, TARGET_BORDER_COLOR, STD_BORDER_COLOR, STD_BACKGROUND_COLOR, ENTER_BACKGROUND_COLOR, PRESSED_BACKGROUND_COLOR);
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

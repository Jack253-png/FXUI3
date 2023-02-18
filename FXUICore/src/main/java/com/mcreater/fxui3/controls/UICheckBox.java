package com.mcreater.fxui3.controls;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.base.IControl;
import com.mcreater.fxui3.controls.converters.ThemeConverter;
import com.mcreater.fxui3.controls.skins.UICheckBoxSkin;
import com.mcreater.fxui3.util.FXUtil;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Skin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mcreater.fxui3.assets.ResourceProcessor.DARK_USERAGENT_STYLESHEET;
import static com.mcreater.fxui3.assets.ResourceProcessor.LIGHT_USERAGENT_STYLESHEET;

public class UICheckBox extends CheckBox implements IControl {
    private static final String DEFAULT_STYLE_CLASS = "ui-check-box";
    private final StyleableProperties DEFAULT_PROPERTIES = new StyleableProperties();
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
            DEFAULT_PROPERTIES.THEME,
            UICheckBox.this,
            "theme",
            ResourceProcessor.ThemeType.LIGHT
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

    private final class StyleableProperties {
        private final CssMetaData<UICheckBox, ResourceProcessor.ThemeType> THEME =
                FXUtil.createCSSMetaData(ThemeConverter.getInstance(), UICheckBox.this::themeProperty, "-ui-check-box-theme", ResourceProcessor.ThemeType.LIGHT);

        private final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        private StyleableProperties() {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<>(Button.getClassCssMetaData());
            Collections.addAll(styleables, THEME);
            CHILD_STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return DEFAULT_PROPERTIES.CHILD_STYLEABLES;
    }
    public String getUserAgentStylesheet() {
        return getTheme() == ResourceProcessor.ThemeType.LIGHT ? LIGHT_USERAGENT_STYLESHEET : DARK_USERAGENT_STYLESHEET;
    }
    protected Skin<?> createDefaultSkin() {
        return new UICheckBoxSkin(this);
    }
}

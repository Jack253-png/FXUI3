package com.mcreater.fxui3.controls;

import com.mcreater.fxui3.controls.base.IControl;
import com.mcreater.fxui3.controls.skins.UIButtonSkin;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;

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
    public String getUserAgentStylesheet() {
        return LIGHT_USERAGENT_STYLESHEET;
    }
    protected Skin<?> createDefaultSkin() {
        return new UIButtonSkin(this);
    }
}
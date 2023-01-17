package com.mcreater.fxui3.controls;

import com.mcreater.fxui3.controls.skins.UIButtonSkin;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;

public class UIButton extends Button {
    public UIButton() {

    }
    protected Skin<?> createDefaultSkin() {
        return new UIButtonSkin(this);
    }
}
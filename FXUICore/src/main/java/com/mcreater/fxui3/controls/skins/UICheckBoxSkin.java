package com.mcreater.fxui3.controls.skins;

import com.mcreater.fxui3.controls.UICheckBox;
import com.sun.javafx.scene.control.skin.CheckBoxSkin;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;

public class UICheckBoxSkin extends CheckBoxSkin {
    private final Line checkLeft;
    private final Line checkRight;
    private Timeline checkOutAnimation;
    public UICheckBoxSkin(UICheckBox checkBox) {
        super(checkBox);

        checkLeft = new Line(6, 11, 9, 14);
        checkLeft.setStrokeWidth(1.5);
        checkLeft.getStyleClass().add("check-line");
        checkRight = new Line(9, 14, 15, 8);
        checkRight.setStrokeWidth(1.5);
        checkRight.getStyleClass().add("check-line");

        this.getChildren().addAll(checkLeft, checkRight);
    }
}
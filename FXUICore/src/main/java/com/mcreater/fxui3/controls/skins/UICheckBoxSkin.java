package com.mcreater.fxui3.controls.skins;

import com.mcreater.fxui3.controls.UICheckBox;
import com.sun.javafx.scene.control.skin.CheckBoxSkin;
import javafx.animation.Timeline;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;

import java.lang.reflect.Field;

public class UICheckBoxSkin extends CheckBoxSkin {
    private Line checkLeft;
    private Line checkRight;
    private Timeline checkOutAnimation;
    public UICheckBoxSkin(UICheckBox checkBox) {
        super(checkBox);

        checkLeft = new Line(5, 11, 9, 15);

        checkRight = new Line(9, 15, 16, 8);

        this.getChildren().addAll(checkLeft, checkRight);
        try {
            Field field = CheckBoxSkin.class.getDeclaredField("box");
            field.setAccessible(true);
            ((Region) field.get(this)).setPrefSize(22, 22);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
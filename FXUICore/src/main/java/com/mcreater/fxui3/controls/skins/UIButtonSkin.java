package com.mcreater.fxui3.controls.skins;

import com.mcreater.fxui3.controls.UIButton;
import com.mcreater.fxui3.util.FXUtil;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class UIButtonSkin extends ButtonSkin {
    private final ObjectProperty<Color> backgroundColorProperty;
    private final ObjectProperty<Color> textColorProperty;
    private final ObjectProperty<Color> borderButtomColorProperty;
    private final Object lock = new Object();
    private Timeline timeline;

    public UIButtonSkin(UIButton button) {
        super(button);
        button.themeProperty().addListener((observableValue, type, t1) -> genThemeChangeAnimation(button));
        button.defaultButtonProperty().addListener((observableValue, aBoolean, t1) -> genThemeChangeAnimation(button));
        button.colorizationColorProperty().addListener((observableValue, color, t1) -> updateColor(button, t1));
        button.disabledProperty().addListener((observableValue, aBoolean, t1) -> updateColor(button, button.getColorizationColor()));
        updateColor(button, button.getColorizationColor());

        backgroundColorProperty = new SimpleObjectProperty<>();
        backgroundColorProperty.addListener((observableValue, number, t1) -> button.setBackground(new Background(
                new BackgroundFill(
                        t1,
                        new CornerRadii(button.getDefinedBorderRadius()),
                        Insets.EMPTY
                )
        )));
        backgroundColorProperty.set(button.getStdBackgroundColor());

        textColorProperty = new SimpleObjectProperty<>();
        textColorProperty.addListener((observableValue, number, t1) -> button.setTextFill(t1));
        textColorProperty.set(button.getStdTextColor());

        borderButtomColorProperty = new SimpleObjectProperty<>();
        borderButtomColorProperty.addListener((observableValue, number, t1) -> {
            Border border = new Border(new BorderStroke(
                    button.getStdBorderColor(),
                    button.getStdBorderColor(),
                    t1,
                    button.getStdBorderColor(),
                    BorderStrokeStyle.SOLID,
                    BorderStrokeStyle.SOLID,
                    BorderStrokeStyle.SOLID,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(button.getDefinedBorderRadius()),
                    new BorderWidths(0, 0, button.getDefinedBorderWidth(), 0),
                    Insets.EMPTY
            ));

            button.setBorder(border);
        });
        borderButtomColorProperty.set(button.getTargetBorderColor());

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> genMouseEnterAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> genMouseExitAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> genMousePressedAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> genMouseEnterAnimation(button));
    }
    private void updateColor(UIButton button, Color color) {
        if (color != null && button.isDefaultButton()) {
            if (!button.isDisabled()) {
                Color base = FXUtil.ColorizationProcessor.styleColorToBaseColor(color, button.getTheme());
                button.stdBackgroundColorProperty().set(base);
                button.enterBackgroundColorProperty().set(FXUtil.ColorizationProcessor.baseColorToL1Color(base, button.getTheme()));
                button.pressedBackgroundColorProperty().set(FXUtil.ColorizationProcessor.baseColorToL2Color(base, button.getTheme()));
                button.stdBorderColorProperty().set(FXUtil.ColorizationProcessor.baseColorToStdBorderColor(base, button.getTheme()));
                button.targetBorderColorProperty().set(FXUtil.ColorizationProcessor.baseColorToBottomBorderColor(base, button.getTheme()));
            }
            else {
                Color base = FXUtil.ColorizationProcessor.styleColorToBaseColor(color, button.getTheme());
                button.stdBackgroundColorProperty().set(FXUtil.ColorizationProcessor.baseColorToL2Color(base, button.getTheme()));
                button.enterBackgroundColorProperty().set(FXUtil.ColorizationProcessor.baseColorToL2Color(base, button.getTheme()));
                button.pressedBackgroundColorProperty().set(FXUtil.ColorizationProcessor.baseColorToL2Color(base, button.getTheme()));
                button.stdBorderColorProperty().set(FXUtil.ColorizationProcessor.baseColorToStdBorderColor(base, button.getTheme()));
                button.targetBorderColorProperty().set(FXUtil.ColorizationProcessor.baseColorToStdBorderColor(base, button.getTheme()));
            }
        }
    }
    private void genThemeChangeAnimation(UIButton button) {
        synchronized (lock) {
            if (timeline != null) timeline.stop();

            timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(button.getAnimationSpeed()),
                            new KeyValue(
                                    backgroundColorProperty,
                                    button.getStdBackgroundColor(),
                                    Interpolator.EASE_BOTH
                            ),
                            new KeyValue(
                                    textColorProperty,
                                    button.getStdTextColor(),
                                    Interpolator.EASE_BOTH
                            ),
                            new KeyValue(
                                    borderButtomColorProperty,
                                    button.getTargetBorderColor(),
                                    Interpolator.EASE_BOTH
                            )
                    )
            );
            timeline.playFromStart();
        }
    }
    private void genMouseEnterAnimation(UIButton button) {
        synchronized (lock) {
            if (timeline != null) timeline.stop();

            timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(button.getAnimationSpeed()),
                            new KeyValue(
                                    backgroundColorProperty,
                                    button.getEnterBackgroundColor(),
                                    Interpolator.EASE_BOTH
                            ),
                            new KeyValue(
                                    textColorProperty,
                                    button.getStdTextColor(),
                                    Interpolator.EASE_BOTH
                            ),
                            new KeyValue(
                                    borderButtomColorProperty,
                                    button.getTargetBorderColor(),
                                    Interpolator.EASE_BOTH
                            )
                    )
            );
            timeline.playFromStart();
        }
    }
    private void genMouseExitAnimation(UIButton button) {
        synchronized (lock) {
            if (timeline != null) timeline.stop();

            timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(button.getAnimationSpeed()),
                            new KeyValue(
                                    backgroundColorProperty,
                                    button.getStdBackgroundColor(),
                                    Interpolator.EASE_BOTH
                            ),
                            new KeyValue(
                                    textColorProperty,
                                    button.getStdTextColor(),
                                    Interpolator.EASE_BOTH
                            ),
                            new KeyValue(
                                    borderButtomColorProperty,
                                    button.getTargetBorderColor(),
                                    Interpolator.EASE_BOTH
                            )
                    )
            );
            timeline.playFromStart();
        }
    }
    private void genMousePressedAnimation(UIButton button) {
        synchronized (lock) {
            if (timeline != null) timeline.stop();

            timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(button.getAnimationSpeed()),
                            new KeyValue(
                                    backgroundColorProperty,
                                    button.getPressedBackgroundColor(),
                                    Interpolator.EASE_BOTH
                            ),
                            new KeyValue(
                                    textColorProperty,
                                    button.getTargetTextColor(),
                                    Interpolator.EASE_BOTH
                            ),
                            new KeyValue(
                                    borderButtomColorProperty,
                                    button.getStdBorderColor(),
                                    Interpolator.EASE_BOTH
                            )
                    )
            );
            timeline.playFromStart();
        }
    }
}

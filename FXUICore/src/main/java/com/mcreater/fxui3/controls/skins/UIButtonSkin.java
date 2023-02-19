package com.mcreater.fxui3.controls.skins;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.UIButton;
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
    private static final int backgroundMod1GreyLight = 246;
    private static final int backgroundMod2GreyLight = 242;

    private static final int backgroundMod1GreyDark = 50;
    private static final int backgroundMod2GreyDark = 39;

    public UIButtonSkin(UIButton button) {
        super(button);
        button.themeProperty().addListener((observableValue, type, t1) -> genThemeChangeAnimation(button));
        button.defaultButtonProperty().addListener((observableValue, aBoolean, t1) -> genThemeChangeAnimation(button));

        backgroundColorProperty = new SimpleObjectProperty<>(button.getStdBackgroundColor());
        backgroundColorProperty.addListener((observableValue, number, t1) -> button.setBackground(new Background(
                new BackgroundFill(
                        t1,
                        new CornerRadii(button.getDefinedBorderRadius()),
                        Insets.EMPTY
                )
        )));
        textColorProperty = new SimpleObjectProperty<>(button.getStdTextColor());
        textColorProperty.addListener((observableValue, number, t1) -> button.setTextFill(t1));

        borderButtomColorProperty = new SimpleObjectProperty<>(button.getTargetBorderColor());
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
                    new BorderWidths(button.getDefinedBorderWidth()),
                    Insets.EMPTY
            ));

            button.setBorder(border);
        });

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> genMouseEnterAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> genMouseExitAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> genMousePressedAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> genMouseEnterAnimation(button));
    }
    private boolean checkState(UIButton button) {
        return !button.isDefaultButton() && !button.isDisabled();
    }
    private void genThemeChangeAnimation(UIButton button) {
        if (checkState(button)) {
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
    }
    private void genMouseEnterAnimation(UIButton button) {
        if (checkState(button)) {
            synchronized (lock) {
                if (timeline != null) timeline.stop();
                boolean isLight = button.getTheme() == ResourceProcessor.ThemeType.LIGHT;

                timeline = new Timeline(
                        new KeyFrame(
                                Duration.millis(button.getAnimationSpeed()),
                                new KeyValue(
                                        backgroundColorProperty,
                                        Color.grayRgb(isLight ? backgroundMod1GreyLight : backgroundMod1GreyDark),
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
    }
    private void genMouseExitAnimation(UIButton button) {
        if (checkState(button)) {
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
    }
    private void genMousePressedAnimation(UIButton button) {
        if (checkState(button)) {
            synchronized (lock) {
                if (timeline != null) timeline.stop();
                boolean isLight = button.getTheme() == ResourceProcessor.ThemeType.LIGHT;

                timeline = new Timeline(
                        new KeyFrame(
                                Duration.millis(button.getAnimationSpeed()),
                                new KeyValue(
                                        backgroundColorProperty,
                                        Color.grayRgb(isLight ? backgroundMod2GreyLight : backgroundMod2GreyDark),
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
}

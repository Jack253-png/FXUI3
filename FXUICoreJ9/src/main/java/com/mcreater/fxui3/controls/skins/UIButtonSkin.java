package com.mcreater.fxui3.controls.skins;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.UIButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.skin.ButtonSkin;
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
    private final IntegerProperty backgroundGrey;
    private final IntegerProperty textColorGrey;
    private final IntegerProperty borderButtomColorGrey;
    private final Object lock = new Object();
    private Timeline timeline;

    private static final int borderCornerRadii = 5;
    private static final int borderWidth = 1;

    private static final int borderStdColorGreyLight = 227;
    private static final int borderModColorGreyLight = 202;
    private static final Color borderStdColorLight = Color.rgb(borderStdColorGreyLight, borderStdColorGreyLight, borderStdColorGreyLight);
    private static final int textStdColorGreyLight = 28;
    private static final int textModColorGreyLight = 93;
    private static final int backgroundStdGreyLight = 249;
    private static final int backgroundMod1GreyLight = 246;
    private static final int backgroundMod2GreyLight = 242;

    private static final int borderStdColorGreyDark = 53;
    private static final int borderModColorGreyDark = 48;
    private static final Color borderStdColorDark = Color.rgb(borderStdColorGreyDark, borderStdColorGreyDark, borderStdColorGreyDark);
    private static final int textStdColorGreyDark = 253;
    private static final int textModColorGreyDark = 206;
    private static final int backgroundStdGreyDark = 45;
    private static final int backgroundMod1GreyDark = 50;
    private static final int backgroundMod2GreyDark = 39;

    public UIButtonSkin(UIButton button) {
        super(button);
        button.themeProperty().addListener((observableValue, type, t1) -> genThemeChangeAnimation(button));
        button.defaultButtonProperty().addListener((observableValue, aBoolean, t1) -> genThemeChangeAnimation(button));

        backgroundGrey = new SimpleIntegerProperty(button.getTheme() == ResourceProcessor.ThemeType.LIGHT ? backgroundStdGreyLight : backgroundStdGreyDark);
        backgroundGrey.addListener((observableValue, number, t1) -> button.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(t1.intValue(), t1.intValue(), t1.intValue()),
                        new CornerRadii(borderCornerRadii),
                        Insets.EMPTY
                )
        )));
        textColorGrey = new SimpleIntegerProperty(button.getTheme() == ResourceProcessor.ThemeType.LIGHT ? textStdColorGreyLight : textStdColorGreyDark);
        textColorGrey.addListener((observableValue, number, t1) -> button.setTextFill(Color.rgb(t1.intValue(), t1.intValue(), t1.intValue())));
        borderButtomColorGrey = new SimpleIntegerProperty(button.getTheme() == ResourceProcessor.ThemeType.LIGHT ? borderModColorGreyLight : borderModColorGreyDark);
        borderButtomColorGrey.addListener((observableValue, number, t1) -> {
            Border border = new Border(new BorderStroke(
                    button.getTheme() == ResourceProcessor.ThemeType.LIGHT ? borderStdColorLight : borderStdColorDark,
                    button.getTheme() == ResourceProcessor.ThemeType.LIGHT ? borderStdColorLight : borderStdColorDark,
                    Color.rgb(t1.intValue(), t1.intValue(), t1.intValue()),
                    button.getTheme() == ResourceProcessor.ThemeType.LIGHT ? borderStdColorLight : borderStdColorDark,
                    BorderStrokeStyle.SOLID,
                    BorderStrokeStyle.SOLID,
                    BorderStrokeStyle.SOLID,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(borderCornerRadii),
                    new BorderWidths(borderWidth),
                    Insets.EMPTY
            ));

            button.setBorder(border);
        });

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> genMouseEnterAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> genMouseExitAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> genMousePressedAnimation(button));
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> genMouseEnterAnimation(button));
    }
    private void genThemeChangeAnimation(UIButton button) {
        if (!button.isDefaultButton()) {
            synchronized (lock) {
                if (timeline != null) timeline.stop();
                boolean isLight = button.getTheme() == ResourceProcessor.ThemeType.LIGHT;

                timeline = new Timeline(
                        new KeyFrame(
                                Duration.millis(button.getAnimationSpeed()),
                                new KeyValue(
                                        backgroundGrey,
                                        isLight ? backgroundStdGreyLight : backgroundStdGreyDark,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        textColorGrey,
                                        isLight ? textStdColorGreyLight : textStdColorGreyDark,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        borderButtomColorGrey,
                                        isLight ? borderModColorGreyLight : borderModColorGreyDark,
                                        Interpolator.EASE_BOTH
                                )
                        )
                );
                timeline.playFromStart();
            }
        }
    }
    private void genMouseEnterAnimation(UIButton button) {
        if (!button.isDefaultButton()) {
            synchronized (lock) {
                if (timeline != null) timeline.stop();
                boolean isLight = button.getTheme() == ResourceProcessor.ThemeType.LIGHT;

                timeline = new Timeline(
                        new KeyFrame(
                                Duration.millis(button.getAnimationSpeed()),
                                new KeyValue(
                                        backgroundGrey,
                                        isLight ? backgroundMod1GreyLight : backgroundMod1GreyDark,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        textColorGrey,
                                        isLight ? textStdColorGreyLight : textStdColorGreyDark,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        borderButtomColorGrey,
                                        isLight ? borderModColorGreyLight : borderModColorGreyDark,
                                        Interpolator.EASE_BOTH
                                )
                        )
                );
                timeline.playFromStart();
            }
        }
    }
    private void genMouseExitAnimation(UIButton button) {
        if (!button.isDefaultButton()) {
            synchronized (lock) {
                if (timeline != null) timeline.stop();
                boolean isLight = button.getTheme() == ResourceProcessor.ThemeType.LIGHT;

                timeline = new Timeline(
                        new KeyFrame(
                                Duration.millis(button.getAnimationSpeed()),
                                new KeyValue(
                                        backgroundGrey,
                                        isLight ? backgroundStdGreyLight : backgroundStdGreyDark,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        textColorGrey,
                                        isLight ? textStdColorGreyLight : textStdColorGreyDark,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        borderButtomColorGrey,
                                        isLight ? borderModColorGreyLight : borderModColorGreyDark,
                                        Interpolator.EASE_BOTH
                                )
                        )
                );
                timeline.playFromStart();
            }
        }
    }
    private void genMousePressedAnimation(UIButton button) {
        if (!button.isDefaultButton()) {
            synchronized (lock) {
                if (timeline != null) timeline.stop();
                boolean isLight = button.getTheme() == ResourceProcessor.ThemeType.LIGHT;

                timeline = new Timeline(
                        new KeyFrame(
                                Duration.millis(button.getAnimationSpeed()),
                                new KeyValue(
                                        backgroundGrey,
                                        isLight ? backgroundMod2GreyLight : backgroundMod2GreyDark,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        textColorGrey,
                                        isLight ? textModColorGreyLight : textModColorGreyDark,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        borderButtomColorGrey,
                                        isLight ? borderStdColorGreyLight : borderStdColorGreyDark,
                                        Interpolator.EASE_BOTH
                                )
                        )
                );
                timeline.playFromStart();
            }
        }
    }
}

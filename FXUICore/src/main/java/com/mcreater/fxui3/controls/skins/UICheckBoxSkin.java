package com.mcreater.fxui3.controls.skins;

import com.mcreater.fxui3.controls.UICheckBox;
import com.sun.javafx.scene.control.skin.CheckBoxSkin;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class UICheckBoxSkin extends CheckBoxSkin {
    private final Line checkLeft;
    private final Line checkRight;
    private final Line checkIndeterminate;
    private Timeline checkInAnimation;
    private static final Point2D checkLeftStartPoint = new Point2D(6, 11);
    private static final int checkLeftAdd = 3;
    private static final Point2D checkRightStartPoint = new Point2D(9, 14);
    private static final int checkRightXAdd = 6;
    private static final int checkRightYAdd = -6;
    private final Pane topContainer = new Pane();
    public UICheckBoxSkin(UICheckBox checkBox) {
        super(checkBox);
        
        Interpolator interpolator = Interpolator.LINEAR;

        checkLeft = new Line(checkLeftStartPoint.getX(), checkLeftStartPoint.getY(), checkLeftStartPoint.getX() + checkLeftAdd, checkLeftStartPoint.getY() + checkLeftAdd);
        checkLeft.setStrokeWidth(1.5);
        checkLeft.getStyleClass().add("check-line");
        checkRight = new Line(checkRightStartPoint.getX(), checkRightStartPoint.getY(), checkRightStartPoint.getX() + checkRightXAdd, checkRightStartPoint.getY() + checkRightYAdd);
        checkRight.setStrokeWidth(1.5);
        checkRight.getStyleClass().add("check-line");
        checkIndeterminate = new Line(7, 11, 14, 11);
        checkIndeterminate.setStrokeWidth(1.5);
        checkIndeterminate.getStyleClass().add("check-line");

        checkInAnimation = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(
                                checkLeft.endXProperty(),
                                checkLeftStartPoint.getX(),
                                interpolator
                        ),
                        new KeyValue(
                                checkLeft.endYProperty(),
                                checkLeftStartPoint.getY(),
                                interpolator
                        ),
                        new KeyValue(
                                checkRight.endXProperty(),
                                checkRightStartPoint.getX(),
                                interpolator
                        ),
                        new KeyValue(
                                checkRight.endYProperty(),
                                checkRightStartPoint.getY(),
                                interpolator
                        ),
                        new KeyValue(
                                checkRight.opacityProperty(),
                                0,
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.millis(150),
                        new KeyValue(
                                checkLeft.endXProperty(),
                                checkLeftStartPoint.getX() + checkLeftAdd,
                                interpolator
                        ),
                        new KeyValue(
                                checkLeft.endYProperty(),
                                checkLeftStartPoint.getY() + checkLeftAdd,
                                interpolator
                        ),
                        new KeyValue(
                                checkRight.opacityProperty(),
                                1,
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.millis(500),
                        new KeyValue(
                                checkRight.endXProperty(),
                                checkRightStartPoint.getX() + checkRightXAdd,
                                interpolator
                        ),
                        new KeyValue(
                                checkRight.endYProperty(),
                                checkRightStartPoint.getY() + checkRightYAdd,
                                interpolator
                        )
                )
        );

        updateCheckBoxState();
        checkBox.setOnAction(actionEvent -> updateCheckBoxState());
        topContainer.getChildren().addAll(checkLeft, checkRight);

        this.getChildren().addAll(topContainer, checkIndeterminate);
    }
    private void updateCheckBoxState() {
        boolean isSelected = getSkinnable().isSelected();
        boolean isIndeterminate = getSkinnable().isIndeterminate();

        setCheckIndeterminateHide(!isIndeterminate);

        if (!isIndeterminate) {
            if (isSelected) {
                runCheckAnimation();
            } else {
                hideCheck();
            }
        } else {
            hideCheck();
        }
    }
    private void hideCheck() {
        topContainer.setOpacity(0);
    }
    private void runCheckAnimation() {
        topContainer.setOpacity(1);
        checkInAnimation.playFromStart();
    }
    private void setCheckIndeterminateHide(boolean hide) {
        checkIndeterminate.setOpacity(hide ? 0 : 1);
    }
}
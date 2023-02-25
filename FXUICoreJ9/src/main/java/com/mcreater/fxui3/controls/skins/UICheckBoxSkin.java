package com.mcreater.fxui3.controls.skins;

import com.mcreater.fxui3.controls.UICheckBox;
import com.mcreater.fxui3.util.FXUtil;
import com.mcreater.fxui3.util.ReflectHelper;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.skin.CheckBoxSkin;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import static java.lang.Math.abs;

public class UICheckBoxSkin extends CheckBoxSkin {
    private final Line checkIndeterminate;
    private final Timeline checkInAnimation;
    private static final Point2D checkLeftStartPoint = new Point2D(6, 11);
    private static final int checkLeftAdd = 3;
    private static final Point2D checkRightStartPoint = new Point2D(9, 14);
    private static final int checkRightAdd = -6;
    private final Pane topContainer = new Pane();
    private StackPane _super_box;
    public UICheckBoxSkin(UICheckBox checkBox) {
        super(checkBox);

        _super_box = new StackPane();
        try {
            _super_box = ReflectHelper.getPrivateField(CheckBoxSkin.class, this, "box", StackPane.class);
        }
        catch (Exception ignored) {

        }

        checkBox.themeProperty().addListener((observableValue, type, t1) -> updateColor(checkBox));
        checkBox.colorizationColorProperty().addListener((observableValue, type, t1) -> updateColor(checkBox));
        checkBox.colorizationTypeProperty().addListener((observableValue, s, t1) -> updateColor(checkBox));
        updateColor(checkBox);

        Interpolator interpolator = Interpolator.LINEAR;

        Line checkLeft = new Line(checkLeftStartPoint.getX(), checkLeftStartPoint.getY(), checkLeftStartPoint.getX() + checkLeftAdd, checkLeftStartPoint.getY() + checkLeftAdd);
        checkLeft.setStrokeWidth(1.5);
        checkLeft.getStyleClass().add("check-line");
        Line checkRight = new Line(checkRightStartPoint.getX(), checkRightStartPoint.getY(), checkRightStartPoint.getX() + abs(checkRightAdd), checkRightStartPoint.getY() + checkRightAdd);
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
                                checkRightStartPoint.getX() + abs(checkRightAdd),
                                interpolator
                        ),
                        new KeyValue(
                                checkRight.endYProperty(),
                                checkRightStartPoint.getY() + checkRightAdd,
                                interpolator
                        )
                )
        );

        updateCheckBoxState();
        checkBox.setOnAction(actionEvent -> updateCheckBoxState());
        topContainer.getChildren().addAll(checkLeft, checkRight);

        this.getChildren().addAll(topContainer, checkIndeterminate);
    }
    private void updateColor(UICheckBox checkBox) {
        if (checkBox.getColorizationColor() != null) {
            Color base = FXUtil.ColorizationProcessor.styleColorToBaseColor(checkBox.getColorizationColor(), checkBox.getTheme());
            Color hover = FXUtil.ColorizationProcessor.baseColorToL1Color(base, checkBox.getTheme());
            Color pressed = FXUtil.ColorizationProcessor.baseColorToL2Color(base, checkBox.getTheme());
            switch (checkBox.getColorizationType()) {
                default:
                case NOT_SELECTED:
                    updateColorImpl(checkBox, Color.TRANSPARENT);
                    break;
                case NORMAL:
                    updateColorImpl(checkBox, base);
                    break;
                case HOVER:
                    updateColorImpl(checkBox, hover);
                    break;
                case PRESSED:
                    updateColorImpl(checkBox, pressed);
                    break;
            }
        }
        else {
            switch (checkBox.getColorizationType()) {
                default:
                case NOT_SELECTED:
                case NORMAL:
                    updateColorImpl(checkBox, checkBox.getStdBackgroundColor(), checkBox.getStdBorderColor());
                    break;
                case HOVER:
                    updateColorImpl(checkBox, checkBox.getEnterBackgroundColor(), checkBox.getStdBorderColor());
                    break;
                case PRESSED:
                    updateColorImpl(checkBox, checkBox.getPressedBackgroundColor(), checkBox.getTargetBorderColor());
                    break;
            }
        }
    }
    private void updateColorImpl(UICheckBox checkBox, Color color) {
        updateColorImpl(checkBox, color, color);
    }
    private void updateColorImpl(UICheckBox checkBox, Color color, Color border) {
        _super_box.setBackground(new Background(
                new BackgroundFill(
                        color,
                        new CornerRadii(checkBox.getBorderRadius()),
                        new Insets(0, 0, checkBox.getBackgroundInset(), 0)
                )
        ));
        _super_box.setBorder(new Border(
                new BorderStroke(
                        border,
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(checkBox.getBorderRadius()),
                        new BorderWidths(checkBox.getBackgroundInset())
                )
        ));
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
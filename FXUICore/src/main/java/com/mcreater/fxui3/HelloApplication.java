package com.mcreater.fxui3;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.UIButton;
import com.mcreater.fxui3.controls.UICheckBox;
import com.mcreater.fxui3.controls.brush.IBrush;
import com.mcreater.fxui3.controls.brush.InAppAeroGlassBrush;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        UIButton button = new UIButton("Standard XAML Button");
        Label label = new Label();

        Color red = Color.web("#f16d7a");
        Color blue = Color.web("#b8f1ed");

        Rectangle rectangleRed = new Rectangle();
        rectangleRed.setFill(red);
        rectangleRed.setWidth(100);
        rectangleRed.setHeight(100);

        Rectangle rectangleBlue = new Rectangle();
        rectangleBlue.setFill(blue);
        rectangleBlue.setWidth(100);
        rectangleBlue.setHeight(100);

        new Thread(() -> {
            while (true) {
                rectangleRed.setFill(rectangleRed.getFill() == red ? blue : red);
                rectangleBlue.setFill(rectangleRed.getFill() == blue ? red : blue);
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {

                }
            }
        }).start();

        UIButton button1 = new UIButton("test");
        UIButton button2 = new UIButton("test");
        UICheckBox checkBox = new UICheckBox("test");
        checkBox.setTheme(ResourceProcessor.ThemeType.DARK);

        button1.setTheme(ResourceProcessor.ThemeType.DARK);
        button2.setTheme(ResourceProcessor.ThemeType.DARK);

        HBox target = new HBox(button1, button2, checkBox);
        target.setAlignment(Pos.BOTTOM_LEFT);
        target.setSpacing(50);

        button.setOnAction(event -> {
            System.gc();
            label.setText("Hello JavaFX Application!");
        });
        button.setFont(new Font(null, 16));
        button.setWrapText(true);

        button.setTheme(ResourceProcessor.ThemeType.DARK);

        vbox.getChildren().addAll(label, button, new ChoiceBox<>(FXCollections.observableList(Arrays.asList("a", "b", "c"))), new ComboBox<>(FXCollections.observableList(Arrays.asList("a", "b", "c"))));

        Pane p = new Pane();
        p.getChildren().addAll(new HBox(rectangleRed, rectangleBlue), target, vbox);
        p.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(50, 50, 50),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(
                                target.layoutXProperty(),
                                0,
                                Interpolator.EASE_BOTH
                        )
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(
                                target.layoutXProperty(),
                                100,
                                Interpolator.EASE_BOTH
                        )
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
//        timeline.playFromStart();

        IBrush.getInAppAeroGrassBrush().apply(target, Color.rgb(50, 50, 50, 0.25));

        Scene scene = new Scene(p, 320, 240);
        target.prefWidthProperty().bind(scene.widthProperty());
        target.prefHeightProperty().bind(scene.heightProperty());
        scene.setFill(Color.rgb(50, 50, 50));
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
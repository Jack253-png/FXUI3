package com.mcreater.fxui3;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.UIButton;
import com.mcreater.fxui3.controls.UICheckBox;
import com.mcreater.fxui3.controls.brush.IBrush;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

        button1.setTheme(ResourceProcessor.ThemeType.DARK);
        button2.setTheme(ResourceProcessor.ThemeType.DARK);

        VBox target = new VBox(button1, button2);
        target.setPrefWidth(300);
        target.setPrefHeight(200);
        target.setAlignment(Pos.CENTER);
        target.setSpacing(50);
        IBrush.getInAppAeroGrassBrush().apply(target);

        UICheckBox checkBox = new UICheckBox("test");

        button.setOnAction(event -> label.setText("Hello JavaFX Application!"));
        button.setFont(new Font(null, 16));
        button.setWrapText(true);

        button.setTheme(ResourceProcessor.ThemeType.DARK);

        vbox.getChildren().addAll(label, button, checkBox);

        Pane p = new Pane(new HBox(rectangleRed, rectangleBlue), target, vbox);
        HBox box = new HBox(new Pane(), new Pane(), p);
        box.setSpacing(25);
        box.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(50, 50, 50),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));

        Scene scene = new Scene(box, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
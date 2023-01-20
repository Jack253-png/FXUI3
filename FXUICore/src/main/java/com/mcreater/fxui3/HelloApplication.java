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
    public void start(Stage stage) throws Exception {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        UIButton button = new UIButton("Standard XAML Button");
        Label label = new Label();

        Rectangle rectangleRed = new Rectangle();
        rectangleRed.setFill(Color.RED);
        rectangleRed.setWidth(100);
        rectangleRed.setHeight(100);

        Rectangle rectangleBlue = new Rectangle();
        rectangleBlue.setFill(Color.BLUE);
        rectangleBlue.setWidth(100);
        rectangleBlue.setHeight(100);

        VBox target = new VBox(new UIButton("test"), new UIButton("test"));
        target.setPrefWidth(300);
        target.setPrefHeight(200);
        target.setAlignment(Pos.CENTER);
        target.setSpacing(50);

        UICheckBox checkBox = new UICheckBox("test");

        button.setOnAction(event -> {
            label.setText("Hello JavaFX Application!");
            rectangleRed.setFill(Color.BLACK);
        });
        button.setFont(new Font(null, 16));
        button.setWrapText(true);

        button.setTheme(ResourceProcessor.ThemeType.DARK);

        vbox.getChildren().addAll(label, button, checkBox);

        Pane p = new Pane(new HBox(rectangleRed, rectangleBlue), target, vbox);
        p.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(50, 50, 50),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));

        Scene scene = new Scene(p, 320, 240);
        long i1 = System.currentTimeMillis();
        IBrush.getInAppAeroGrassBrush().apply(target);
        long i2 = System.currentTimeMillis();
        System.out.println(i2 - i1);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
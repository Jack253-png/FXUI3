package com.mcreater.fxui3;

import com.mcreater.fxui3.assets.ResourceProcessor;
import com.mcreater.fxui3.controls.UIButton;
import com.mcreater.fxui3.controls.UICheckBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        UIButton button = new UIButton("Standard XAML Button");
        Label label = new Label();

        UICheckBox checkBox = new UICheckBox("test");

        button.setOnAction(event -> label.setText("Hello JavaFX Application!"));
        button.setFont(new Font(null, 16));
        button.setWrapText(true);

        vbox.getChildren().addAll(label, button, checkBox);

        Scene scene = new Scene(vbox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
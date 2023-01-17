package com.mcreater.fxui3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Button button = new Button("hello");
        Label label = new Label();

        button.setOnAction(event -> label.setText("Hello JavaFX Application!"));

        vbox.getChildren().addAll(label, button);

        Scene scene = new Scene(vbox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
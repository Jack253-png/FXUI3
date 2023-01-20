package com.mcreater.fxui3.util;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class FXUtil {
    public static List<Node> getParentTreeNodes(Parent root) {
        List<Node> result = new Vector<>();
        root.getChildrenUnmodifiable().forEach(node -> {
            result.add(node);
            if (node instanceof Pane) {
                ((Pane) node).getChildren().forEach(node1 -> result.addAll(getParentTreeNodes((Parent) node1)));
            }
            if (node instanceof ScrollPane) {
                result.addAll(getParentTreeNodes((Parent) ((ScrollPane) node).getContent()));
            }
        });
        return result;
    }
    public static void runOnThread(Runnable runnable) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            runnable.run();
            latch.countDown();
        });
        latch.await();
    }
}

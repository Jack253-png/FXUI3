package com.mcreater.fxui3.util;

import javafx.scene.Node;

public class FXUtil {
    public static Node getControlRoot(Node node) {
        Node result = node;
        while (true) {
            if (result.getParent() == null) return result;
            else result = result.getParent();
        }
    }

}

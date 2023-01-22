package com.mcreater.fxui3.util;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class FXUtil {
    private static final List<Class<? extends Pane>> ignoreClassList = Arrays.asList(Pane.class, AnchorPane.class, BorderPane.class, DialogPane.class);
    public static List<Node> getTopNode(Node node) {
        Parent parent = node.getParent();
        if (ignoreClassList.contains(parent.getClass())) {
            List<Node> result = new Vector<>();
            Pane parentPane = (Pane) parent;
            int index = parentPane.getChildren().indexOf(node);
            for (int i = 0; i < parentPane.getChildren().size(); i++) {
                if (i > index) {
                    result.add(parentPane.getChildren().get(i));
                }
            }
            if (parentPane.getParent() != null) result.addAll(getTopNode(parentPane.getParent()));
            return result;
        }
        else {
            if (parent.getParent() != null) return getTopNode(parent);
            return new Vector<>();
        }
    }
}

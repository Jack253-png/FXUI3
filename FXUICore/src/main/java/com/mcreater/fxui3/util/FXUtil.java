package com.mcreater.fxui3.util;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.naming.OperationNotSupportedException;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class FXUtil {
    private static final List<Class<? extends Pane>> ignoreClassList = Arrays.asList(Pane.class, AnchorPane.class, BorderPane.class, DialogPane.class);
    public static List<Node> getTopNode(Node node) {
        Parent parent = node.getParent();
        if (parent == null) return new Vector<>();
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
    public static Point2D localToParent(Node target, Parent parent) throws Exception {
        if (target.getParent() == null) {
            if (target != parent) throw new OperationNotSupportedException("Parent not found.");
            return target.localToScene(0, 0);
        }
        else {
            if (target == parent) return new Point2D(0, 0);
            Point2D parentPoint = localToParent(target.getParent(), parent);
            Point2D currentPoint = target.localToParent(0, 0);
            return new Point2D(parentPoint.getX() + currentPoint.getX(), parentPoint.getY() + currentPoint.getY());
        }
    }
}

package com.mcreater.fxui3.util;

import com.mcreater.fxui3.assets.ResourceProcessor;
import javafx.beans.property.Property;
import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;

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
    public static class CssMetaDataImpl<C extends Styleable, V, P extends StyleableProperty<V> & Property<V>> extends CssMetaData<C, V> {
        private final Function<C, P> propertyFunc;
        public CssMetaDataImpl(String s, StyleConverter<?, V> styleConverter, V o, Function<C, P> propertyFunc) {
            super(s, styleConverter, o);
            this.propertyFunc = propertyFunc;
        }

        public boolean isSettable(C button) {
            return !propertyFunc.apply(button).isBound();
        }

        public StyleableProperty<V> getStyleableProperty(C button) {
            return propertyFunc.apply(button);
        }
    }

    public static class ColorizationProcessor {
        public static Color styleColorToBaseColor(Color styleColor, ResourceProcessor.ThemeType type) {
            return type == ResourceProcessor.ThemeType.LIGHT ? styleColor.interpolate(Color.rgb(0, 0, 0), 0.11) : styleColor.interpolate(Color.rgb(80, 205, 255), 0.9);
        }

        public static Color baseColorToL1Color(Color baseColor, ResourceProcessor.ThemeType type) {
            return type == ResourceProcessor.ThemeType.LIGHT ? baseColor.interpolate(Color.rgb(253, 253, 253), 0.1) : baseColor.interpolate(Color.rgb(50, 50, 50), 0.1);
        }

        public static Color baseColorToL2Color(Color baseColor, ResourceProcessor.ThemeType type) {
            Color l1Color = baseColorToL1Color(baseColor, type);
            return type == ResourceProcessor.ThemeType.LIGHT ? l1Color.interpolate(Color.rgb(253, 253, 253), 0.1) : l1Color.interpolate(Color.rgb(50, 50, 50), 0.1);
        }

        public static Color baseColorToBottomBorderColor(Color baseColor, ResourceProcessor.ThemeType type) {
            return type == ResourceProcessor.ThemeType.LIGHT ? baseColor.interpolate(Color.BLACK, 0.4) : baseColor.interpolate(Color.BLACK, 0.17);
        }

        public static Color baseColorToStdBorderColor(Color baseColor, ResourceProcessor.ThemeType type) {
            return type == ResourceProcessor.ThemeType.LIGHT ? baseColor.interpolate(Color.rgb(253, 253, 253), 0.08) : baseColor;
        }
    }
}

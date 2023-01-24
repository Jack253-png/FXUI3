package com.mcreater.fxui3.controls.brush;

import com.mcreater.fxui3.util.FXUtil;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class InAppAeroGlassBrush implements IBrush {
    private final List<Region> targetNodeList = new Vector<>();
    private final Map<Region, Point2D> pointMap = new HashMap<>();
    private final ObjectProperty<Color> glassColorProperty = new SimpleObjectProperty<>(Color.TRANSPARENT);
    public ObjectProperty<Color> glassColorProperty() {
        return glassColorProperty;
    }
    public void setGlassColor(Color color) {
        glassColorProperty().set(color);
    }
    public Color getGlassColor() {
        return glassColorProperty().get();
    }
    InAppAeroGlassBrush() {
        new Thread(() -> {
            AtomicReference<Integer> offset = new AtomicReference<>();
            while (true) {
                try {
                    for (Region region : targetNodeList) {
                        CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            offset.set(applyImpl(region, offset.get(), getGlassColor()));
                            latch.countDown();
                        });
                        latch.await();
                    }
                    Thread.sleep(10);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void apply(Region region) {
        targetNodeList.add(region);
    }

    public void remove(Region region) {
        region.setBackground(null);
        targetNodeList.remove(region);
    }

    private Integer applyImpl(Region region, Integer offset, Color glassColor) {
        try {
//            Pane parent = (Pane) FXUtil.getControlRoot(region);
            Parent parent = region.getParent();
            region.setBackground(null);

            Point2D point = region.localToParent(0, 0);
            Point2D cache = pointMap.get(region);

            if (cache == null) cache = new Point2D(-1, -1);
            if (cache.getX() != point.getX() || cache.getY() != point.getY()) pointMap.put(region, point);

            Map<Node, Double> opacityMap = new HashMap<>();
            opacityMap.put(region, region.getOpacity());

            region.setOpacity(0);
            List<Node> topNodes = FXUtil.getTopNode(region);
            topNodes.forEach(node -> opacityMap.put(node, node.getOpacity()));
            topNodes.forEach(node -> node.setOpacity(0));
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);

            WritableImage result2 = null;
            if (offset == null) result2 = parent.snapshot(null, null);
            parent.setEffect(new GaussianBlur(64));
            WritableImage result = parent.snapshot(null, null);
            opacityMap.forEach(Node::setOpacity);
            parent.setEffect(null);

            if (offset == null) {
                offset = (int) (result.getWidth() - result2.getWidth()) / 2;
            }

            WritableImage image = new WritableImage(
                    result.getPixelReader(),
                    (int) (point.getX() + offset),
                    (int) (point.getY() + offset),
                    (int) (result.getWidth() - point.getX() - offset),
                    (int) (result.getHeight() - point.getY() - offset)
            );

            Pane topPane = new Pane();
            topPane.setPrefSize(image.getWidth(), image.getHeight());
            topPane.setBackground(new Background(
                    new BackgroundFill(
                            glassColor,
                            CornerRadii.EMPTY,
                            Insets.EMPTY
                    )
            ));
            ImageView view = new ImageView(image);
            view.setFitWidth(image.getWidth());
            view.setFitHeight(image.getHeight());

            Pane res = new Pane(view, topPane);
            image = res.snapshot(parameters, null);

            region.setBackground(new Background(
                    new BackgroundImage(
                            image,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT
                    )
            ));
            return offset;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.mcreater.fxui3.controls.brush;

import com.mcreater.fxui3.util.FXUtil;
import javafx.application.Platform;
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
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class InAppAeroGlassBrush implements IBrush {
    protected static final IBrush INSTANCE = new InAppAeroGlassBrush();
    private final Map<Region, Parent> targetNodeList = new HashMap<>();
    private final Map<Region, Color> colorMap = new HashMap<>();
    private final Map<Region, Background> backgroundBuf = new HashMap<>();
    InAppAeroGlassBrush() {
        new Thread(() -> {
            AtomicReference<Integer> offset = new AtomicReference<>();
            while (true) {
                try {
                    for (Region region : targetNodeList.keySet()) {
                        CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            Integer newOffset = applyImpl(region, offset.get(), colorMap.get(region), targetNodeList.get(region));
                            if (!Objects.equals(newOffset, offset.get())) offset.set(newOffset);
                            latch.countDown();
                        });
                        latch.await();
                    }
                    Thread.sleep(10);
                }
                catch (Exception e) {
//                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void apply(Region region) {
        apply(region, null, Color.TRANSPARENT);
    }

    public void apply(Region region, Color glassColor) {
        apply(region, null, glassColor);
    }

    public void apply(Region region, Parent parent) {
        apply(region, parent, Color.TRANSPARENT);
    }

    public void apply(Region region, Parent parent, Color glassColor) {
        targetNodeList.put(region, parent);
        colorMap.put(region, glassColor);
        backgroundBuf.put(region, region.getBackground());
    }

    public void remove(Region region) {
        region.setBackground(backgroundBuf.get(region));
        targetNodeList.remove(region);
        colorMap.remove(region);
        backgroundBuf.remove(region);
    }

    private Integer applyImpl(Region region, Integer offset, Color glassColor, Parent parent2) {
        try {
//            Pane parent = (Pane) FXUtil.getControlRoot(region);
            if (glassColor == null) glassColor = Color.TRANSPARENT;
            Parent parent = parent2 != null ? parent2 : region.getParent();
            region.setBackground(null);

            Point2D point = parent2 != null ? FXUtil.localToParent(region, parent2) : region.localToParent(0, 0);

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
//            e.printStackTrace();
        }
        return null;
    }
}

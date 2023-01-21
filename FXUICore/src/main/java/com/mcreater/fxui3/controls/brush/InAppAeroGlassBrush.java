package com.mcreater.fxui3.controls.brush;

import com.mcreater.fxui3.util.FXUtil;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

public class InAppAeroGlassBrush implements IBrush {
    private final List<Region> targetNodeList = new Vector<>();
    private final Map<Region, Point2D> pointMap = new HashMap<>();
    InAppAeroGlassBrush() {
        new Thread(() -> {
            AtomicReference<Integer> offset = new AtomicReference<>();
            while (true) {
                targetNodeList.forEach(region -> {
                    try {
                        Platform.runLater(() -> offset.set(applyImpl(region, offset.get())));
                        Thread.sleep(10);
                    }
                    catch (Exception ignored) {

                    }
                });
            }
        }).start();
    }
    public void apply(Region region) {
        targetNodeList.add(region);
    }
    private Integer applyImpl(Region region, Integer offset) {
        return applyImpl(region, offset, Color.rgb(50, 50, 50));
    }
    private Integer applyImpl(Region region, Integer offset, Paint fill) {
        try {
            Pane parent = (Pane) FXUtil.getControlRoot(region);
            region.setBackground(null);

            Point2D point = region.localToScene(0, 0);
            Point2D cache = pointMap.get(region);

            if (cache == null) cache = new Point2D(-1, -1);
            if (cache.getX() != point.getX() || cache.getY() != point.getY()) pointMap.put(region, point);

            region.setOpacity(0);

            WritableImage result2 = null;
            if (offset == null) result2 = parent.snapshot(null, null);
            parent.setEffect(new GaussianBlur(64));
            WritableImage result = parent.snapshot(null, null);
            region.setOpacity(1);
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
        catch (Exception ignored) {

        }
        return null;
    }
}

package com.mcreater.fxui3.controls.brush;

import com.mcreater.fxui3.util.ReflectHelper;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.List;

public class InAppAeroGlassBrush implements IBrush {
    public void apply(Region region) {
        region.setBackground(null);
        Bounds bound = region.getLayoutBounds();

        Point2D point = region.localToParent(0, 0);
        Parent parent = region.getParent();

        ReflectHelper.getMethodPrivate(Parent.class, parent, "children", List.class).remove(region);

        Bounds boundParent = parent.getLayoutBounds();
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.rgb(50, 50, 50));
        parent.setEffect(new GaussianBlur(32));
        WritableImage result = parent.snapshot(parameters, null);
        parent.setEffect(null);

        ReflectHelper.getMethodPrivate(Parent.class, parent, "children", List.class).add(region);

        WritableImage image = new WritableImage(
                result.getPixelReader(),
                (int) point.getX() + 32,
                (int) point.getY() + 32,
                (int) (result.getWidth() - point.getX() - 32),
                (int) (result.getHeight() - point.getY() - 32)
        );

        System.out.println(point);

        region.setBackground(new Background(
                new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT
                )
        ));
    }
}

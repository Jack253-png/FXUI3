package com.mcreater.fxui3.controls.brush;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class InAppAeroGlassBrush implements IBrush {
    public void apply(Region region) {
        region.setBackground(null);
        Bounds bound = region.getLayoutBounds();

        Point2D point = region.localToParent(0, 0);
        Parent parent = region.getParent();

        try {
            Field f = Parent.class.getDeclaredField("children");
            f.setAccessible(true);
            ((List<Node>) f.get(parent)).remove(region);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Bounds boundParent = parent.getLayoutBounds();
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        Platform.runLater(() -> {
            WritableImage result = parent.snapshot(parameters, null);

            try {
                Field f = Parent.class.getDeclaredField("children");
                f.setAccessible(true);
                ((List<Node>) f.get(parent)).add(region);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            WritableImage image = new WritableImage(
                    result.getPixelReader(),
                    (int) point.getX(),
                    (int) point.getY(),
                    (int) (result.getWidth() - point.getX()),
                    (int) (result.getHeight() - point.getY())
            );

            System.out.println(point);

            ImageView view = new ImageView();
            view.setImage(image);
            view.setFitWidth(image.getWidth());
            view.setFitHeight(image.getHeight());
            view.setEffect(new GaussianBlur(16));

            image = new WritableImage(
                    view.snapshot(parameters, null).getPixelReader(),
                    16,
                    16,
                    (int) image.getWidth(),
                    (int) image.getHeight()
            );

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File("bg.png"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            region.setBackground(new Background(
                    new BackgroundImage(
                            image,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT
                    )
            ));
        });
    }
}

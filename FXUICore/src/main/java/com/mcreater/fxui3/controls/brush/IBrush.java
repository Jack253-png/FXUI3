package com.mcreater.fxui3.controls.brush;

import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public interface IBrush {
    void apply(Region region);
    void apply(Region region, Color glassColor);
    void apply(Region region, Parent parent);
    void apply(Region region, Parent parent, Color glassColor);
    void remove(Region region);
    static IBrush getInAppAeroGrassBrush() {
        return InAppAeroGlassBrush.INSTANCE;
    }
}

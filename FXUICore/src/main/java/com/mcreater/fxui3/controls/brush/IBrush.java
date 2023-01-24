package com.mcreater.fxui3.controls.brush;

import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public interface IBrush {
    InAppAeroGlassBrush IN_APP_AERO_GLASS_BRUSH_INSTANCE = new InAppAeroGlassBrush();
    void apply(Region region);
    void apply(Region region, Parent parent);
    void remove(Region region);
    void setGlassColor(Color color);
    Color getGlassColor();
    static IBrush getInAppAeroGrassBrush() {
        return IN_APP_AERO_GLASS_BRUSH_INSTANCE;
    }
}

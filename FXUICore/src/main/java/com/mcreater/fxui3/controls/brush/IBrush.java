package com.mcreater.fxui3.controls.brush;

import javafx.scene.layout.Region;

public interface IBrush {
    InAppAeroGlassBrush IN_APP_AERO_GLASS_BRUSH_INSTANCE = new InAppAeroGlassBrush();
    void apply(Region region);
    void remove(Region region);
    static IBrush getInAppAeroGrassBrush() {
        return IN_APP_AERO_GLASS_BRUSH_INSTANCE;
    }
}

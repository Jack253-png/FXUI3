package com.mcreater.fxui3.controls.brush;

import javafx.scene.layout.Region;

public interface IBrush {
    void apply(Region region);
    static IBrush getInAppAeroGrassBrush() {
        return new InAppAeroGlassBrush();
    }
}

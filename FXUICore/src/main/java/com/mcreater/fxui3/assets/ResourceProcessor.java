package com.mcreater.fxui3.assets;

import java.net.URL;

public class ResourceProcessor {
    public static final String LIGHT_USERAGENT_STYLESHEET = ResourceProcessor.getResource("assets/css/theme-light.css").toExternalForm();
    public static final String DARK_USERAGENT_STYLESHEET = ResourceProcessor.getResource("assets/css/theme-dark.css").toExternalForm();
    public static URL getResource(String path) {
        return ResourceProcessor.class.getClassLoader().getResource(path);
    }
    public enum ThemeType {
        LIGHT,
        DARK
    }
    private ResourceProcessor() {}
}

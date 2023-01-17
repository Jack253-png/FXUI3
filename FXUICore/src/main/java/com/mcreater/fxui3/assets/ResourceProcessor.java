package com.mcreater.fxui3.assets;

import java.net.URL;

public class ResourceProcessor {
    public static final String LIGHT_USERAGENT_STYLESHEET;
    static {
        try {
            ResourceProcessor.getResource("assets/css/theme-light.css").toExternalForm();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        LIGHT_USERAGENT_STYLESHEET = ResourceProcessor.getResource("assets/css/theme-light.css").toExternalForm();
    }
    public static URL getResource(String path) {
        return ResourceProcessor.class.getClassLoader().getResource(path);
    }
    private ResourceProcessor() {}
}

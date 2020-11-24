package com.jarvis.plugin.demo.core;

import java.util.ArrayList;
import java.util.List;

public class Const {
    public static final String PLUGIN_FILE_NAME = "plugin-debug.apk";

    public static List<String> PLUGIN_FILE_NAME_LIST;

    static {
        PLUGIN_FILE_NAME_LIST = new ArrayList<>();
        PLUGIN_FILE_NAME_LIST.add("plugin-debug.apk");
        PLUGIN_FILE_NAME_LIST.add("plugin2-debug.apk");
    }
}

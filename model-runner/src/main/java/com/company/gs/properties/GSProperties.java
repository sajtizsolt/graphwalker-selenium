package com.company.gs.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GSProperties {

    private static final String GS_PROPERTIES = "src/main/resources/gs.properties";

    private static Properties instance;

    static {
        try {
            readProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getInstance() throws IOException {
        if (instance == null) {
            readProperties();
        }
        return instance;
    }

    public static String get(String key) {
        return instance.getProperty(key);
    }

    private static void readProperties() throws IOException {
        try (FileInputStream fis = new FileInputStream(GS_PROPERTIES)) {
            instance = new Properties();
            instance.load(fis);
        }
    }

}

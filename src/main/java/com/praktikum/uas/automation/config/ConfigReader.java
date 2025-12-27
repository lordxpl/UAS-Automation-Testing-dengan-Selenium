package com.praktikum.uas.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    // Static block untuk meload file config.properties secara otomatis
    static {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            System.err.println("File konfigurasi tidak ditemukan: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
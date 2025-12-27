package com.praktikum.uas.automation.config; // Ini harus sama dengan nama folder tempat file berada

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    // Static block untuk meload file config.properties secara otomatis
    static {
        try {
            prop = new Properties();
            // Sesuaikan path ini dengan letak file config.properties kamu
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
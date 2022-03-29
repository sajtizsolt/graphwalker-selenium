package com.company.gs.mbt.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.WebStorage;

public class WebDriverHandler {

    private static WebDriver instance;

    public static WebDriver getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    public static void closeInstance() {
        if (instance != null) {
            instance.quit();
        }
    }

    public static void resetInstance() {
        if (instance == null) {
            return;
        }
        ((WebStorage) instance).getLocalStorage().clear();
        ((WebStorage) instance).getSessionStorage().clear();
        instance.manage().deleteAllCookies();
        instance.navigate().refresh();
    }

    public static void setLocalStorageItem(String key, String value) {
        if (instance == null) {
            return;
        }
        ((WebStorage) instance).getLocalStorage().setItem(key, value);
    }

    private static void createInstance() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--ignore-ssl-errors=yes");

        instance = new ChromeDriver(options);
        instance.manage().window().setSize(new Dimension(1920, 1080));
    }

}

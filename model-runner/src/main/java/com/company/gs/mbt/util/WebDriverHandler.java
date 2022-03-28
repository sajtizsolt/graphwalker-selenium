package com.company.gs.mbt.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverHandler {

    private static final WebDriver instance;

    static {
        WebDriverManager.getInstance(ChromeDriver.class).setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--ignore-ssl-errors=yes");

        instance = new ChromeDriver(options);
        instance.manage().window().setSize(new Dimension(1920, 1080));
    }

    public static WebDriver getInstance() {
        return instance;
    }

}

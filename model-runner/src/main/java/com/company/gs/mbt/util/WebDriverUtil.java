package com.company.gs.mbt.util;

import com.company.gs.properties.GSProperties;
import com.company.gs.properties.util.ClassName;
import java.time.Duration;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtil {

    private static final long WAIT_SECONDS = 60;

    public static void waitForUrl(WebDriver driver, String url) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
            .until(ExpectedConditions.urlToBe(url));
    }

    public static void waitForPresenceByClassName(WebDriver driver, String className) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
            .until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
    }

    public static void waitForPresenceById(WebDriver driver, String id) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
            .until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public static void waitForPresenceByXPath(WebDriver driver, String xpath) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public static void waitForClickableByClassName(WebDriver driver, String className) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
            .until(ExpectedConditions.elementToBeClickable(By.className(className)));
    }

    public static void waitForClickableById(WebDriver driver, String id) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
            .until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

    public static void waitForClickableByXPath(WebDriver driver, String xpath) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public static void clickOnByClassName(WebDriver driver, String className) {
        waitForClickableByClassName(driver, className);
        waitForPresenceByClassName(driver, className);

        try {
            driver.findElement(By.className(className)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.className(className)));
        }
    }

    public static void clickOnById(WebDriver driver, String id) {
        waitForClickableById(driver, id);
        waitForPresenceById(driver, id);

        try {
            driver.findElement(By.id(id)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.id(id)));
        }
    }

    public static void clickOnByXPath(WebDriver driver, String xpath) {
        waitForClickableByXPath(driver, xpath);
        waitForPresenceByXPath(driver, xpath);

        try {
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
        }
    }

    public static void hoverOverByClassName(WebDriver driver, String className) {
        waitForClickableByClassName(driver, className);
        waitForPresenceByClassName(driver, className);

        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.className(className))).perform();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.className(className)));
        }
    }

    public static void hoverOverById(WebDriver driver, String id) {
        waitForClickableById(driver, id);
        waitForPresenceById(driver, id);

        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.id(id))).perform();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.id(id)));
        }
    }

    public static void hoverOverByXPath(WebDriver driver, String xpath) {
        waitForClickableByXPath(driver, xpath);
        waitForPresenceByXPath(driver, xpath);

        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath(xpath))).perform();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
        }
    }

    public static void typeById(WebDriver driver, String id, String text) {
        waitForClickableById(driver, id);
        waitForPresenceById(driver, id);

        driver.findElement(By.id(id)).sendKeys(text);
    }

    public static void typeByXPath(WebDriver driver, String xpath, String text) {
        waitForClickableByXPath(driver, xpath);
        waitForPresenceByXPath(driver, xpath);

        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public static boolean isAntTabActive(WebDriver driver, String xpath) {
        waitForPresenceByXPath(driver, xpath);

        String htmlClass = driver.findElement(By.xpath(xpath)).getAttribute("class");
        return StringUtils.contains(htmlClass, GSProperties.get(ClassName.TAB_ACTIVE));
    }

    public static boolean isModalActive(WebDriver driver) {
        waitForPresenceByClassName(driver, GSProperties.get(ClassName.MODAL_ROOT));

        try {
            driver.findElement(By.className(GSProperties.get(ClassName.MODAL_ROOT)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean existsById(WebDriver driver, String id) {
        waitForPresenceById(driver, id);

        try {
            driver.findElement(By.id(id));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isEmptyById(WebDriver driver, String id) {
        waitForPresenceById(driver, id);

        return StringUtils.isBlank(driver.findElement(By.id(id)).getAttribute("value"));
    }

}

package com.company.gs.mbt.util;

import java.time.Duration;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtil {

    /**
     * Waits for the maximum of the specified amount of time until the specified
     * element becomes visible.
     *
     * @param driver    The web driver object.
     * @param seconds   The number of seconds to wait.
     * @param className The class name of the element.
     */
    public static void waitForPresenceByClassName(WebDriver driver, long seconds, String className) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
    }

    /**
     * Waits for the maximum of the specified amount of time until the specified
     * element becomes visible.
     *
     * @param driver  The web driver object.
     * @param seconds The number of seconds to wait.
     * @param id      The id of the element.
     */
    public static void waitForPresenceById(WebDriver driver, long seconds, String id) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    /**
     * Waits for the maximum of the specified amount of time until the specified
     * element becomes visible.
     *
     * @param driver  The web driver object.
     * @param seconds The number of seconds to wait.
     * @param xpath   The XPath of the element.
     */
    public static void waitForPresenceByXPath(WebDriver driver, long seconds, String xpath) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Waits for the maximum of the specified amount of time until the specified
     * element becomes clickable.
     *
     * @param driver    The web driver object.
     * @param seconds   The number of seconds to wait.
     * @param className The className of the element.
     */
    public static void waitForClickableByClassName(WebDriver driver, long seconds, String className) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(By.className(className)));
    }

    /**
     * Waits for the maximum of the specified amount of time until the specified
     * element becomes clickable.
     *
     * @param driver  The web driver object.
     * @param seconds The number of seconds to wait.
     * @param id      The id of the element.
     */
    public static void waitForClickableById(WebDriver driver, long seconds, String id) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

    /**
     * Waits for the maximum of the specified amount of time until the specified
     * element becomes clickable.
     *
     * @param driver  The web driver object.
     * @param seconds The number of seconds to wait.
     * @param xpath   The xpath of the element.
     */
    public static void waitForClickableByXPath(WebDriver driver, long seconds, String xpath) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    /**
     * Waits for a maximum of 5 seconds until the specified element becomes
     * visible and then clicks on it.
     *
     * @param driver    The web driver object.
     * @param className The class name of the element.
     */
    public static void clickOnByClassName(WebDriver driver, String className) {
        waitForClickableByClassName(driver, 5, className);
        waitForPresenceByClassName(driver, 5, className);
        try {
            driver.findElement(By.className(className)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.className(className)));
        }
    }

    /**
     * Waits for a maximum of 5 seconds until the specified element becomes
     * visible and then clicks on it.
     *
     * @param driver The web driver object.
     * @param id     The id of the element.
     */
    public static void clickOnById(WebDriver driver, String id) {
        waitForClickableById(driver, 5, id);
        waitForPresenceById(driver, 5, id);
        try {
            driver.findElement(By.id(id)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.id(id)));
        }
    }

    /**
     * Waits for a maximum of 5 seconds until the specified element becomes
     * visible and then clicks on it.
     *
     * @param driver The web driver object.
     * @param xpath  The XPath of the element.
     */
    public static void clickOnByXPath(WebDriver driver, String xpath) {
        waitForClickableByXPath(driver, 5, xpath);
        waitForPresenceByXPath(driver, 5, xpath);
        try {
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
        }
    }

    /**
     * Waits for a maximum of 5 seconds until the specified element becomes
     * visible and then types the specified string.
     *
     * @param driver The web driver object.
     * @param id     The id of the element.
     * @param text   The text to be typed.
     */
    public static void typeById(WebDriver driver, String id, String text) {
        waitForPresenceById(driver, 5, id);
        driver.findElement(By.id(id)).sendKeys(text);
    }

    /**
     * Waits for a maximum of 5 seconds until the specified element becomes
     * visible and then types the specified string.
     *
     * @param driver The web driver object.
     * @param xpath  The XPath of the element.
     * @param text   The text to be typed.
     */
    public static void typeByXPath(WebDriver driver, String xpath, String text) {
        waitForPresenceByXPath(driver, 5, xpath);
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    /**
     * Returns true if the Ant tab is currently selected, based on it's HTML
     * class value.
     *
     * @param driver The web driver object.
     * @param xpath  The XPath of the element.
     * @return Returns true if the Ant tab is currently selected.
     */
    public static boolean isAntTabActive(WebDriver driver, String xpath) {
        waitForPresenceByXPath(driver, 5, xpath);
        String htmlClass = driver.findElement(By.xpath(xpath)).getAttribute("class");
        return StringUtils.contains(htmlClass, AntClassName.TAB_ACTIVE);
    }

    /**
     * Returns true if an Ant modal window is currently active.
     *
     * @param driver The web driver object.
     * @return Returns true if an Ant modal window is currently active.
     */
    public static boolean isModalActive(WebDriver driver) {
        try {
            driver.findElement(By.className(AntClassName.MODAL_ROOT));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Returns true if the specified element exists.
     *
     * @param driver The web driver object.
     * @param id     The id of the element.
     * @return Returns true if the specified element exists.
     */
    public static boolean existsById(WebDriver driver, String id) {
        try {
            waitForPresenceById(driver, 5, id);
            driver.findElement(By.id(id));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Returns true if the specified element is empty.
     *
     * @param driver The web driver object.
     * @param id     The id of the element.
     * @return Returns true if the specified element is empty.
     */
    public static boolean isEmptyById(WebDriver driver, String id) {
        waitForPresenceById(driver, 5, id);
        return StringUtils.isBlank(driver.findElement(By.id(id)).getAttribute("value"));
    }

}

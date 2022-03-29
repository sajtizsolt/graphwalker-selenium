package com.company.gs.mbt.util;

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

    /**
     * Waits for the maximum of the specified amount of time until the specified
     * URL becomes active.
     *
     * @param driver  The web driver object.
     * @param seconds The number of seconds to wait.
     * @param url     The URL to wait for.
     */
    public static void waitForUrl(WebDriver driver, long seconds, String url) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.urlToBe(url));
    }

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
     * Waits until the specified element becomes visible and then clicks on it.
     *
     * @param driver    The web driver object.
     * @param className The class name of the element.
     */
    public static void clickOnByClassName(WebDriver driver, String className) {
        waitForClickableByClassName(driver, WAIT_SECONDS, className);
        waitForPresenceByClassName(driver, WAIT_SECONDS, className);
        try {
            driver.findElement(By.className(className)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.className(className)));
        }
    }

    /**
     * Waits until the specified element becomes visible and then clicks on it.
     *
     * @param driver The web driver object.
     * @param id     The id of the element.
     */
    public static void clickOnById(WebDriver driver, String id) {
        waitForClickableById(driver, WAIT_SECONDS, id);
        waitForPresenceById(driver, WAIT_SECONDS, id);
        try {
            driver.findElement(By.id(id)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.id(id)));
        }
    }

    /**
     * Waits until the specified element becomes visible and then clicks on it.
     *
     * @param driver The web driver object.
     * @param xpath  The XPath of the element.
     */
    public static void clickOnByXPath(WebDriver driver, String xpath) {
        waitForClickableByXPath(driver, WAIT_SECONDS, xpath);
        waitForPresenceByXPath(driver, WAIT_SECONDS, xpath);
        try {
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
        }
    }

    /**
     * Waits until the specified element becomes visible and then hovers over it.
     *
     * @param driver    The web driver object.
     * @param className The class name of the element.
     */
    public static void hoverOverByClassName(WebDriver driver, String className) {
        waitForClickableByClassName(driver, WAIT_SECONDS, className);
        waitForPresenceByClassName(driver, WAIT_SECONDS, className);
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.className(className))).perform();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.className(className)));
        }
    }

    /**
     * Waits until the specified element becomes visible and then hovers over it.
     *
     * @param driver The web driver object.
     * @param id     The id of the element.
     */
    public static void hoverOverById(WebDriver driver, String id) {
        waitForClickableById(driver, WAIT_SECONDS, id);
        waitForPresenceById(driver, WAIT_SECONDS, id);
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.id(id))).perform();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.id(id)));
        }
    }

    /**
     * Waits until the specified element becomes visible and then hovers over it.
     *
     * @param driver The web driver object.
     * @param xpath  The XPath of the element.
     */
    public static void hoverOverByXPath(WebDriver driver, String xpath) {
        waitForClickableByXPath(driver, WAIT_SECONDS, xpath);
        waitForPresenceByXPath(driver, WAIT_SECONDS, xpath);
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath(xpath))).perform();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
        }
    }

    /**
     * Waits until the specified element becomes visible and then types the
     * specified string.
     *
     * @param driver The web driver object.
     * @param id     The id of the element.
     * @param text   The text to be typed.
     */
    public static void typeById(WebDriver driver, String id, String text) {
        waitForClickableById(driver, WAIT_SECONDS, id);
        waitForPresenceById(driver, WAIT_SECONDS, id);
        driver.findElement(By.id(id)).sendKeys(text);
    }

    /**
     * Waits until the specified element becomes visible and then types the
     * specified string.
     *
     * @param driver The web driver object.
     * @param xpath  The XPath of the element.
     * @param text   The text to be typed.
     */
    public static void typeByXPath(WebDriver driver, String xpath, String text) {
        waitForClickableByXPath(driver, WAIT_SECONDS, xpath);
        waitForPresenceByXPath(driver, WAIT_SECONDS, xpath);
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
        waitForPresenceByXPath(driver, WAIT_SECONDS, xpath);
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
            waitForPresenceByClassName(driver, WAIT_SECONDS, AntClassName.MODAL_ROOT);
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
            waitForPresenceById(driver, WAIT_SECONDS, id);
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
        waitForPresenceById(driver, WAIT_SECONDS, id);
        return StringUtils.isBlank(driver.findElement(By.id(id)).getAttribute("value"));
    }

}

package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import io.qameta.allure.Step;
import java.time.Duration;

/**
 * Utility class for wait conditions
 */
public class WaitUtil {

    private static final int TIMEOUT_SECONDS = 10;
    private static WebDriverWait wait;

    /**
     * Initialize wait with driver
     */
    public static void setWait(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    /**
     * Wait for element to be visible
     */
    @Step("Wait for element to be visible")
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        LogUtil.info("Waiting for element to be visible: " + locator);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    @Step("Wait for element to be clickable")
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        LogUtil.info("Waiting for element to be clickable: " + locator);
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be present
     */
    @Step("Wait for element to be present")
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        LogUtil.info("Waiting for element to be present: " + locator);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to be invisible
     */
    @Step("Wait for element to be invisible")
    public static boolean waitForElementInvisible(WebDriver driver, By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        LogUtil.info("Waiting for element to be invisible: " + locator);
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for URL to contain
     */
    @Step("Wait for URL to contain: {urlPortion}")
    public static boolean waitForUrlToContain(WebDriver driver, String urlPortion) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        LogUtil.info("Waiting for URL to contain: " + urlPortion);
        return webDriverWait.until(ExpectedConditions.urlContains(urlPortion));
    }

    /**
     * Wait for text to be present in element
     */
    @Step("Wait for text: {text} in element")
    public static boolean waitForTextInElement(WebDriver driver, By locator, String text) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        LogUtil.info("Waiting for text '" + text + "' in element: " + locator);
        return webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Custom wait with custom timeout
     */
    @Step("Wait for element with custom timeout: {timeoutSeconds}s")
    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        LogUtil.info("Waiting for element to be visible with timeout " + timeoutSeconds + "s: " + locator);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait and perform action
     */
    public static void waitAndClick(WebDriver driver, By locator) {
        LogUtil.info("Wait and click element: " + locator);
        waitForElementClickable(driver, locator).click();
    }

    /**
     * Wait and send keys
     */
    public static void waitAndSendKeys(WebDriver driver, By locator, String text) {
        LogUtil.info("Wait and send keys to element: " + locator + " - Text: " + text);
        waitForElementVisible(driver, locator).sendKeys(text);
    }

    /**
     * Wait and get text
     */
    public static String waitAndGetText(WebDriver driver, By locator) {
        LogUtil.info("Wait and get text from element: " + locator);
        return waitForElementVisible(driver, locator).getText();
    }

    /**
     * Wait and select by value
     */
    public static void waitAndSelectByValue(WebDriver driver, By locator, String value) {
        LogUtil.info("Wait and select by value: " + value + " from element: " + locator);
        WebElement element = waitForElementVisible(driver, locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }
}

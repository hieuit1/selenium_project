package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import io.qameta.allure.Allure;
import utils.LogUtil;
import utils.ScreenshotUtil;
import java.io.ByteArrayInputStream;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public void clickByJS(WebElement element) {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Click on element
     */
    public void click(By locator) {
        LogUtil.info("Clicking on element: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Type text into element
     */
    public void type(By locator, String text) {
        LogUtil.info("Typing '" + text + "' into element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from element
     */
    public String getText(By locator) {
        LogUtil.info("Getting text from element: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        LogUtil.info("Checking if element is displayed: " + locator);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            LogUtil.error("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Check if element exists
     */
    public boolean isElementPresent(By locator) {
        LogUtil.info("Checking if element exists: " + locator);
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
        } catch (Exception e) {
            LogUtil.error("Element not found: " + locator);
            return false;
        }
    }

    /**
     * Wait for element to be visible
     */
    public WebElement waitForElement(By locator) {
        LogUtil.info("Waiting for element: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForClickable(By locator) {
        LogUtil.info("Waiting for element to be clickable: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Clear and type
     */
    public void clearAndType(By locator, String text) {
        LogUtil.info("Clearing and typing into element: " + locator);
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Select dropdown by visible text
     */
    public void selectByVisibleText(By locator, String text) {
        LogUtil.info("Selecting text '" + text + "' from dropdown: " + locator);
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
        select.selectByVisibleText(text);
    }

    /**
     * JavaScript click
     */
    public void jsClick(By locator) {
        LogUtil.info("JavaScript click on element: " + locator);
        WebElement element = waitForElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Scroll to element
     */
    public void scrollToElement(By locator) {
        LogUtil.info("Scrolling to element: " + locator);
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Select dropdown by value
     */
    public void selectDropdownByValue(By locator, String value) {
        LogUtil.info("Selecting dropdown value: " + value);
        Select select = new Select(waitForElement(locator));
        select.selectByValue(value);
    }

    /**
     * Select dropdown by visible text
     */
    public void selectDropdownByText(By locator, String text) {
        LogUtil.info("Selecting dropdown text: " + text);
        Select select = new Select(waitForElement(locator));
        select.selectByVisibleText(text);
    }

    /**
     * Get attribute value
     */
    public String getAttribute(By locator, String attribute) {
        LogUtil.info("Getting attribute '" + attribute + "' from element: " + locator);
        return waitForElement(locator).getAttribute(attribute);
    }

    /**
     * Wait for URL to contain
     */
    public boolean waitForUrl(String urlPortion) {
        LogUtil.info("Waiting for URL to contain: " + urlPortion);
        return wait.until(ExpectedConditions.urlContains(urlPortion));
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        LogUtil.info("Getting current URL");
        return driver.getCurrentUrl();
    }

    /**
     * Take screenshot
     */
    public void takeScreenshot(String name) {
        ScreenshotUtil.attachScreenshot(driver, name);
    }

    /**
     * Wait for text in element
     */
    public boolean waitForText(By locator, String text) {
        LogUtil.info("Waiting for text '" + text + "' in element: " + locator);
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            LogUtil.error("Text not found in element: " + text);
            return false;
        }
    }

    // Giữ lại hàm này để dùng chụp ảnh linh hoạt ở tầng Page
    protected void stepWithScreenshot(String stepName, Runnable action) {
        Allure.step(stepName, () -> {
            action.run();
            // LUÔN LUÔN chụp ảnh sau khi hành động kết thúc
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot: " + stepName, new ByteArrayInputStream(screenshot));
        });
    }
}
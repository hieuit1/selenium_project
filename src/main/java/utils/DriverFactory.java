package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.qameta.allure.Step;

/**
 * WebDriver factory for creating and managing drivers
 */
public class DriverFactory {

    private static WebDriver driver;

    /**
     * Initialize WebDriver based on browser type
     */
    @Step("Initialize WebDriver - Browser: {browser}")
    public static WebDriver initializeDriver(String browser) {
        LogUtil.info("Initializing WebDriver for browser: " + browser);

        switch (browser.toLowerCase()) {
            case "firefox":
                driver = createFirefoxDriver();
                break;
            case "edge":
                driver = createEdgeDriver();
                break;
            case "chrome":
            default:
                driver = createChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        LogUtil.info("WebDriver initialized successfully");
        WaitUtil.setWait(driver);
        return driver;
    }

    /**
     * Create Chrome driver
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (ConfigReader.isHeadlessMode()) {
            options.addArguments("--headless");
            LogUtil.info("Chrome running in headless mode");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }

    /**
     * Create Firefox driver
     */
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        if (ConfigReader.isHeadlessMode()) {
            options.addArguments("--headless");
            LogUtil.info("Firefox running in headless mode");
        }

        return new FirefoxDriver(options);
    }

    /**
     * Create Edge driver
     */
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();

        if (ConfigReader.isHeadlessMode()) {
            options.addArguments("--headless");
            LogUtil.info("Edge running in headless mode");
        }

        return new EdgeDriver(options);
    }

    /**
     * Get driver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = initializeDriver(ConfigReader.getBrowser());
        }
        return driver;
    }

    /**
     * Quit driver
     */
    @Step("Close WebDriver")
    public static void quitDriver() {
        if (driver != null) {
            LogUtil.info("Closing WebDriver");
            driver.quit();
            driver = null;
        }
    }

    /**
     * Navigate to URL
     */
    @Step("Navigate to URL: {url}")
    public static void navigateTo(String url) {
        LogUtil.info("Navigating to: " + url);
        driver.get(url);
    }
}

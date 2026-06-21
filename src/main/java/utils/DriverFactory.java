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

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser type
     */
    @Step("Initialize WebDriver - Browser: {browser}")
    public static WebDriver initializeDriver(String browser) {
        LogUtil.info("Initializing WebDriver for browser: " + browser);
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                webDriver = createFirefoxDriver();
                break;
            case "edge":
                webDriver = createEdgeDriver();
                break;
            case "chrome":
            default:
                webDriver = createChromeDriver();
                break;
        }

        driver.set(webDriver);

        if (!ConfigReader.isHeadlessMode()) {
            getDriver().manage().window().maximize();
        }
        LogUtil.info("WebDriver initialized successfully");
        WaitUtil.setWait(getDriver());
        return getDriver();
    }

    /**
     * Create Chrome driver
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (ConfigReader.isHeadlessMode()) {
            options.addArguments("--headless=new");
            options.addArguments("--force-device-scale-factor=1");
            LogUtil.info("Chrome running in headless mode");
        }
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");

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
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
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
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            LogUtil.info("Edge running in headless mode");
        }

        return new EdgeDriver(options);
    }

    /**
     * Get driver instance
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver(ConfigReader.getBrowser());
        }
        return driver.get();
    }

    /**
     * Quit driver
     */
    @Step("Close WebDriver")
    public static void quitDriver() {
        if (driver.get() != null) {
            LogUtil.info("Closing WebDriver");
            driver.get().quit();
            driver.remove();
        }
    }

    /**
     * Navigate to URL
     */
    @Step("Navigate to URL: {url}")
    public static void navigateTo(String url) {
        LogUtil.info("Navigating to: " + url);
        getDriver().get(url);
    }
}

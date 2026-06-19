package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.qameta.allure.Allure;
import utils.DriverFactory;
import utils.ConfigReader;
import utils.LogUtil;
import utils.ScreenshotUtil;

/**
 * Base Test class with common setup and teardown
 */
public class BaseTest {

    protected WebDriver driver;

    /**
     * Setup method - runs before each test
     */
    @BeforeMethod
    public void setup() {
        LogUtil.info("========== Starting Test ==========");
        String browser = ConfigReader.getBrowser();
        LogUtil.info("Browser: " + browser);

        // Initialize driver
        driver = DriverFactory.initializeDriver(browser);

        // Navigate to base URL
        String baseUrl = ConfigReader.getBaseUrl();
        DriverFactory.navigateTo(baseUrl);

        LogUtil.info("Test setup completed");
    }

    /**
     * Teardown method - runs after each test
     */
    @AfterMethod
    public void teardown() {
        LogUtil.info("========== Ending Test ==========");
        if (driver != null) {
            DriverFactory.quitDriver();
            LogUtil.info("Driver closed");
        }
    }

    /**
     * Add text to Allure report
     */
    public void addInfoToReport(String info) {
        LogUtil.info(info);
        Allure.addDescription(info);
    }
}

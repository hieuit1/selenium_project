package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import io.qameta.allure.Allure;
import utils.DriverFactory;
import utils.ConfigReader;
import utils.LogUtil;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup(Method method) throws Exception {
        LogUtil.info("========== Starting Test: " + method.getName() + " ==========");

        // Initialize WebDriver
        String browser = ConfigReader.getBrowser();
        LogUtil.info("Initializing WebDriver with browser: " + browser);
        DriverFactory.initializeDriver(browser);

        // Navigate to base URL
        String baseUrl = ConfigReader.getBaseUrl();
        DriverFactory.navigateTo(baseUrl);
        LogUtil.info("Test setup completed successfully");
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        // Chụp ảnh kết quả ở cấp độ root của test case trước khi đóng trình duyệt
        if (DriverFactory.getDriver() != null) {
            DriverFactory.quitDriver();
        }
    }

    /**
     * Add description to Allure report
     */
    public void addInfoToReport(String info) {
        LogUtil.info(info);
        Allure.addDescription(info);
    }
}
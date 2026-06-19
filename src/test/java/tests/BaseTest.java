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
// import utils.ScreenshotUtil;
import utils.VideoRecorderUtil;

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

    @BeforeMethod
    public void setUp(Method method) throws Exception {
        // Bắt đầu quay video, lấy tên video là tên của kịch bản test
        VideoRecorderUtil.startRecord(method.getName());

        // ... (các đoạn code khởi tạo trình duyệt cũ của bạn giữ nguyên)
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        // Tắt máy quay
        VideoRecorderUtil.stopRecord();

        // ... (đóng trình duyệt)
    }
}

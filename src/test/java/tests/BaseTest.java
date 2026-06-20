package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import java.io.File;
import java.nio.file.Files;
import io.qameta.allure.Allure;
import utils.DriverFactory;
import utils.ConfigReader;
import utils.LogUtil;
import utils.VideoRecorderUtil;

/**
 * Base Test class with video recording and driver management
 */
public class BaseTest {

    protected WebDriver driver;
    private boolean isVideoRecordingEnabled;

    @BeforeMethod
    public void setup(Method method) throws Exception {
        LogUtil.info("========== Starting Test: " + method.getName() + " ==========");

        // Check if video recording is enabled
        isVideoRecordingEnabled = ConfigReader.getVideoRecording();

        // Start video recording
        if (isVideoRecordingEnabled) {
            try {
                LogUtil.info("Starting video recording for test: " + method.getName());
                VideoRecorderUtil.startRecord(method.getName());
            } catch (Exception e) {
                LogUtil.error("Failed to start video recording: " + e.getMessage());
            }
        }

        // Initialize WebDriver
        String browser = ConfigReader.getBrowser();
        LogUtil.info("Initializing WebDriver with browser: " + browser);
        driver = DriverFactory.initializeDriver(browser);

        // Navigate to base URL
        String baseUrl = ConfigReader.getBaseUrl();
        DriverFactory.navigateTo(baseUrl);
        LogUtil.info("Test setup completed successfully");
    }

    @AfterMethod
    public void teardown(ITestResult result, Method method) throws Exception {
        LogUtil.info("========== Ending Test: " + method.getName() + " ==========");

        // Get test result
        String testStatus = result.isSuccess() ? "PASSED" : "FAILED";
        LogUtil.info("Test Status: " + testStatus);

        // Stop video recording and attach to Allure
        if (isVideoRecordingEnabled) {
            try {
                LogUtil.info("Stopping video recording...");
                VideoRecorderUtil.stopRecord();

                // Attach video to Allure report
                File videoFile = new File("./test-recordings/" + method.getName() + ".avi");
                if (videoFile.exists()) {
                    long videoSize = videoFile.length() / (1024 * 1024); // Size in MB
                    LogUtil.info("Video file created: " + videoFile.getAbsolutePath() + " (" + videoSize + " MB)");

                    try {
                        Allure.addAttachment(
                                "Test Video - " + testStatus,
                                "video/avi",
                                Files.newInputStream(videoFile.toPath()),
                                "avi");
                        LogUtil.info("Video attached to Allure report successfully");
                    } catch (Exception e) {
                        LogUtil.error("Failed to attach video to Allure: " + e.getMessage());
                    }
                } else {
                    LogUtil.warn("Video file not found: " + videoFile.getAbsolutePath());
                }
            } catch (Exception e) {
                LogUtil.error("Error during video recording stop: " + e.getMessage());
            }
        }

        // Close WebDriver
        if (driver != null) {
            LogUtil.info("Closing WebDriver...");
            DriverFactory.quitDriver();
            LogUtil.info("WebDriver closed successfully");
        }

        LogUtil.info("Test teardown completed");
    }

    /**
     * Add description to Allure report
     */
    public void addInfoToReport(String info) {
        LogUtil.info(info);
        Allure.addDescription(info);
    }
}
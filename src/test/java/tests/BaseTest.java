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

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup(Method method) throws Exception {
        LogUtil.info("========== Starting Test ==========");
        VideoRecorderUtil.startRecord(method.getName());

        String browser = ConfigReader.getBrowser();
        driver = DriverFactory.initializeDriver(browser);

        String baseUrl = ConfigReader.getBaseUrl();
        DriverFactory.navigateTo(baseUrl);
    }

    @AfterMethod
    public void teardown(ITestResult result) throws Exception {
        LogUtil.info("========== Ending Test ==========");

        // 1. Tắt máy quay video trước
        VideoRecorderUtil.stopRecord();

        // 2. Tiến hành đính kèm Video vừa quay vào Allure Report
        try {
            // Đường dẫn tới file video vừa quay (tên file thường được sinh ra dựa trên tên
            // method test)
            File videoFolder = new File("./test-recordings/");
            // Tìm file video mới nhất liên quan đến test case này
            File[] files = videoFolder
                    .listFiles((dir, name) -> name.contains(result.getName()) && name.endsWith(".avi"));

            if (files != null && files.length > 0) {
                File targetVideo = files[files.length - 1]; // Lấy file mới nhất
                // Đính kèm vào Allure dưới dạng tập tin đính kèm
                Allure.addAttachment("Test Execution Video Clip", "video/avi",
                        Files.newInputStream(targetVideo.toPath()), ".avi");
            }
        } catch (Exception e) {
            LogUtil.error("Không thể đính kèm video vào Allure Report: " + e.getMessage());
        }

        // 3. Đóng trình duyệt dọn dẹp môi trường
        if (driver != null) {
            DriverFactory.quitDriver();
            LogUtil.info("Driver closed");
        }
    }
}
package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Allure;
import utils.ScreenshotUtil;
import utils.LogUtil;
import java.lang.reflect.Field; // <-- Đã thêm thư viện này

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogUtil.info("Test Started: " + result.getName());
        Allure.feature("Test Execution");
        Allure.story(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtil.info("Test Passed: " + result.getName());
        Allure.step("Test completed successfully");

        // Chụp ảnh khi Pass
        captureFinalScreenshot(result, "SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtil.error("Test Failed: " + result.getName());

        // Add failure message to report
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            Allure.step("Failure Reason: " + throwable.getMessage());
            LogUtil.error("Failure Reason: " + throwable.getMessage());
        }

        // Chụp ảnh khi Fail (Đoạn code dài dòng cũ đã được xóa bỏ để dùng chung hàm
        // này)
        captureFinalScreenshot(result, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtil.warn("Test Skipped: " + result.getName());
        Allure.step("Test was skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtil.warn("Test Failed But Within Success Percentage: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        LogUtil.info("Test Suite Started: " + context.getName());
        Allure.suite(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtil.info("Test Suite Finished: " + context.getName());
        LogUtil.info("Total tests: " + context.getAllTestMethods().length);
        LogUtil.info("Passed: " + context.getPassedTests().size());
        LogUtil.info("Failed: " + context.getFailedTests().size());
        LogUtil.info("Skipped: " + context.getSkippedTests().size());
    }

    private void captureFinalScreenshot(ITestResult result, String status) {
        Object testObject = result.getInstance();
        WebDriver driver = null;

        try {
            if (testObject != null) {
                Class<?> clazz = testObject.getClass();
                Field driverField = null;

                // TÌM KIẾM THÔNG MINH: Quét từ class hiện tại lùi về class cha
                while (clazz != null && driverField == null) {
                    try {
                        driverField = clazz.getDeclaredField("driver");
                    } catch (NoSuchFieldException e) {
                        clazz = clazz.getSuperclass();
                    }
                }

                if (driverField != null) {
                    driverField.setAccessible(true);
                    driver = (WebDriver) driverField.get(testObject);
                }
            }

            // Nếu tìm thấy driver, ra lệnh cho anh thợ ảnh (ScreenshotUtil)
            if (driver != null) {
                String testName = result.getName();

                if (status.equals("FAILED")) {
                    LogUtil.info("Taking final screenshot for FAILED test: " + testName);
                    ScreenshotUtil.takeScreenshotOnFailure(driver, testName);
                } else {
                    String fileName = "FINAL_RESULT_SUCCESS_" + testName;
                    LogUtil.info("Taking final screenshot for SUCCESS test: " + fileName);
                    ScreenshotUtil.attachScreenshot(driver, fileName);
                }
            } else {
                LogUtil.warn("Không tìm thấy WebDriver để chụp ảnh!");
            }
        } catch (Exception e) {
            LogUtil.warn("Lỗi trong quá trình chụp ảnh tự động: " + e.getMessage());
        }
    }
}
package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Allure;
import utils.ScreenshotUtil;
import utils.LogUtil;
import java.lang.reflect.Field;

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
        // Đã xóa lệnh Allure.step ở đây để ảnh không bị chui vào trong
        captureFinalScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtil.error("Test Failed: " + result.getName());

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            LogUtil.error("Failure Reason: " + throwable.getMessage());
            // Đã xóa lệnh Allure.step ở đây để ảnh không bị chui vào trong
        }

        // Chụp ảnh chốt hạ ở ngoài cùng
        captureFinalScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtil.warn("Test Skipped: " + result.getName());
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

    private void captureFinalScreenshot(ITestResult result) {
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
                    Object driverObj = driverField.get(testObject);

                    // Xử lý an toàn cho cả trường hợp biến driver thường và ThreadLocal (Parallel)
                    if (driverObj instanceof ThreadLocal) {
                        driver = (WebDriver) ((ThreadLocal<?>) driverObj).get();
                    } else {
                        driver = (WebDriver) driverObj;
                    }
                }
            }

            // Nếu tìm thấy driver, gọi hàm đính kèm ảnh root
            if (driver != null) {
                ScreenshotUtil.attachRootScreenshot(driver);
            } else {
                LogUtil.warn("Không tìm thấy WebDriver để chụp ảnh!");
            }
        } catch (Exception e) {
            LogUtil.warn("Lỗi trong quá trình chụp ảnh tự động: " + e.getMessage());
        }
    }
}
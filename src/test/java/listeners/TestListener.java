package listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Allure;
import utils.ScreenshotUtil;
import utils.DriverFactory;
import utils.LogUtil;

public class TestListener implements ITestListener, IInvokedMethodListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogUtil.info("Test Started: " + result.getName());
        if (Allure.getLifecycle().getCurrentTestCase().isPresent()) {
            Allure.feature("Test Execution");
            Allure.story(result.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtil.info("Test Passed: " + result.getName());
        // Chụp ảnh khi Pass -> Ảnh nằm ngang hàng ở cuối cùng
        ScreenshotUtil.attachRootScreenshot(DriverFactory.getDriver());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtil.error("Test Failed: " + result.getName());
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            LogUtil.error("Failure Reason: " + throwable.getMessage());
        }
        // Chụp ảnh khi Fail -> Ảnh nằm ngang hàng ở cuối cùng
        ScreenshotUtil.attachRootScreenshot(DriverFactory.getDriver());
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Không cần làm gì trước khi hàm chạy
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Kiểm tra xem phương thức vừa chạy xong có phải là một hàm @Test hay không
        // (Để tránh nó chụp ảnh cả ở các hàm @BeforeMethod, @AfterMethod)
        if (method.isTestMethod()) {
            if (DriverFactory.getDriver() != null) {
                // Tự động chụp ảnh. Vì lúc này Allure chưa đóng Test Case,
                // ảnh sẽ tự động nằm ngang hàng ở cuối cùng của Test Body!
                ScreenshotUtil.takeScreenshot(DriverFactory.getDriver());
            }
        }
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
        if (Allure.getLifecycle().getCurrentTestCase().isPresent()) {
            Allure.suite(context.getName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtil.info("Test Suite Finished: " + context.getName());
        LogUtil.info("Total tests: " + context.getAllTestMethods().length);
        LogUtil.info("Passed: " + context.getPassedTests().size());
        LogUtil.info("Failed: " + context.getFailedTests().size());
        LogUtil.info("Skipped: " + context.getSkippedTests().size());
    }
}
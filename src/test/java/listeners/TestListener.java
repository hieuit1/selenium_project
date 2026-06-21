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
        if (Allure.getLifecycle().getCurrentTestCase().isPresent()) {
            Allure.feature("Test Execution");
            Allure.story(result.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtil.info("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtil.error("Test Failed: " + result.getName());

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            LogUtil.error("Failure Reason: " + throwable.getMessage());
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
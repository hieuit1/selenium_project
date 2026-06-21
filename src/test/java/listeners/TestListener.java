package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Allure;
import utils.ScreenshotUtil;
import utils.LogUtil;

public class TestListener implements ITestListener {

    /**
     * Called when test starts
     */
    @Override
    public void onTestStart(ITestResult result) {
        LogUtil.info("Test Started: " + result.getName());
        Allure.feature("Test Execution");
        Allure.story(result.getName());
    }

    /**
     * Called when test succeeds
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtil.info("Test Passed: " + result.getName());
        Allure.step("Test completed successfully");
    }

    /**
     * Called when test fails
     */
    @Override
    public void onTestFailure(ITestResult result) {
        LogUtil.error("Test Failed: " + result.getName());

        // Get the test class instance to access the driver
        Object testObject = result.getInstance();
        WebDriver driver = null;

        try {
            // Try to get driver from BaseTest class
            if (testObject != null) {
                java.lang.reflect.Field driverField = testObject.getClass().getDeclaredField("driver");
                driverField.setAccessible(true);
                driver = (WebDriver) driverField.get(testObject);
            }

            // Take screenshot if driver is available
            if (driver != null && testObject != null) {
                String testName = result.getName();
                LogUtil.info("Taking screenshot for failed test: " + testName);
                ScreenshotUtil.takeScreenshotOnFailure(driver, testName);
                ScreenshotUtil.attachScreenshot(driver, "Failure Screenshot - " + testName);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LogUtil.error("Could not access driver from test object: " + e.getMessage());
        } catch (Exception e) {
            LogUtil.error("Error taking screenshot on failure: " + e.getMessage());
        }

        // Add failure message to report
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            Allure.step("Failure Reason: " + throwable.getMessage());
            LogUtil.error("Failure Reason: " + throwable.getMessage());
        }
    }

    /**
     * Called when test is skipped
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtil.warn("Test Skipped: " + result.getName());
        Allure.step("Test was skipped");
    }

    /**
     * Called when test fails but within success percentage
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtil.warn("Test Failed But Within Success Percentage: " + result.getName());
    }

    /**
     * Called when all tests start
     */
    @Override
    public void onStart(ITestContext context) {
        LogUtil.info("Test Suite Started: " + context.getName());
        Allure.suite(context.getName());
    }

    /**
     * Called when all tests finish
     */
    @Override
    public void onFinish(ITestContext context) {
        LogUtil.info("Test Suite Finished: " + context.getName());
        LogUtil.info("Total tests: " + context.getAllTestMethods().length);
        LogUtil.info("Passed: " + context.getPassedTests().size());
        LogUtil.info("Failed: " + context.getFailedTests().size());
        LogUtil.info("Skipped: " + context.getSkippedTests().size());
    }
}

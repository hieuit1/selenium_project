package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import pages.LoginPage;
import utils.LogUtil;

/**
 * Login Test - Tests for login functionality
 */
@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    /**
     * Test login with valid credentials
     */
    @Test(description = "Verify user can login with valid credentials")
    @Description("This test verifies that a user can successfully log in using valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginSuccessfully() {
        LogUtil.info("Starting test: testLoginSuccessfully");

        // Initialize login page
        LoginPage loginPage = new LoginPage(driver);
        addInfoToReport("LoginPage object created");

        // Perform login
        LogUtil.info("Step 1: Clicking login menu");
        loginPage.clickLoginMenu();

        LogUtil.info("Step 2: Entering email credentials");
        loginPage.enterUsername("test@gmail.com");

        LogUtil.info("Step 3: Entering password");
        loginPage.enterPassword("testweb123");

        LogUtil.info("Step 4: Clicking login button");
        loginPage.clickLogin();

        LogUtil.info("Step 5: Verifying login success");
        boolean isSuccess = loginPage.isLoginSuccessful();

        // Take screenshot after login
        loginPage.takeScreenshot("After Login");

        // Assertion
        assert isSuccess : "Đăng nhập thất bại, không tìm thấy box tài khoản!";
        LogUtil.info("Test passed: User successfully logged in");
    }

    /**
     * Test login with single step (alternative approach)
     */
    @Test(description = "Verify user login using single login method")
    @Description("This test uses the combined login method")
    public void testLoginWithSingleStep() {
        LogUtil.info("Starting test: testLoginWithSingleStep");

        // Initialize login page
        LoginPage loginPage = new LoginPage(driver);

        // Login using combined method
        loginPage.login("test@gmail.com", "testweb123");

        // Take screenshot
        loginPage.takeScreenshot("Login Result");

        // Verify
        boolean isSuccess = loginPage.isLoginSuccessful();
        assert isSuccess : "Login failed, account box not found!";
        LogUtil.info("Test passed: Login successful");
    }
}
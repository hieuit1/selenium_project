package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Allure;
import listeners.TestListener;
import pages.LoginPage;
import utils.ScreenshotUtil;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(description = "Verify user can login with valid credentials")
    @Description("This test verifies that a user can successfully log in using valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);

        // Step 1
        Allure.step("Step 1: Navigate and Open Login Menu", () -> {
            loginPage.clickLoginMenu();
        });

        // Step 2
        Allure.step("Step 2: Enter Credentials", () -> {
            loginPage.enterUsername("test@gmail.com");
            loginPage.enterPassword("testweb123");
        });

        // Step 3
        Allure.step("Step 3: Submit Login Form", () -> {
            loginPage.clickLogin();
        });

        // Step 4
        Allure.step("Step 4: Verify Login Success", () -> {
            boolean isSuccess = loginPage.isLoginSuccessful();
            loginPage.takeScreenshot("Final Result"); // Chụp ảnh chốt hạ
            assert isSuccess : "Đăng nhập thất bại!";
        });

        ScreenshotUtil.saveResultScreenshot(driver);
    }
}
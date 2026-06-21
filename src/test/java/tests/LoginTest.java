package tests;

import org.testng.Assert; // Thêm import Assert của TestNG
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import pages.LoginPage;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    // Test case 1: Verify user can login with valid credentials
    @Test(description = "Verify user can login with valid credentials")
    @Description("This test verifies that a user can successfully log in using valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        // Các bước này sẽ tự động được ghi vào Allure nhờ @Step đã gắn bên LoginPage
        loginPage.clickLoginMenu();
        loginPage.enterUsername("test@gmail.com");
        loginPage.enterPassword("testweb123");
        loginPage.clickLogin();

        boolean isSuccess = loginPage.isLoginSuccessful();
        // Dùng Assert của TestNG
        Assert.assertTrue(isSuccess, "Đăng nhập thất bại! Không tìm thấy hộp tài khoản hiển thị.");
    }

    // =========Negative Test Cases (Dữ liệu không hợp lệ)===========

    // TC 2: Verify login fails with wrong password
    @Test(description = "Verify login fails with wrong password")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithWrongPassword() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.clickLoginMenu();
        loginPage.enterUsername("test@gmail.com");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();

        Assert.assertFalse(loginPage.isLoginSuccessful(), "NGUY HIỂM: Đăng nhập thành công dù sai mật khẩu!");
    }

    // TC 3: Verify login fails with non-existent email
    @Test(description = "Verify login fails with non-existent email")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithInvalidEmail() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.clickLoginMenu();
        loginPage.enterUsername("invalid@gmail.com");
        loginPage.enterPassword("testweb123");
        loginPage.clickLogin();

        Assert.assertFalse(loginPage.isLoginSuccessful(), "NGUY HIỂM: Đăng nhập thành công dù email không tồn tại!");
    }

    // TC 4: Verify login fails with empty credentials
    @Test(description = "Verify login fails with empty credentials")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyFields() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.clickLoginMenu();
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isEmailErrorDisplayed(), "Lỗi email không hiện khi để trống!");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "NGUY HIỂM: Đăng nhập thành công khi để trống!");
    }

    // TC 5: Verify login fails with empty email
    @Test(description = "Verify login fails with empty email")
    @Severity(SeverityLevel.MINOR)
    public void testLoginWithEmptyEmail() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.clickLoginMenu();
        loginPage.enterUsername("");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isEmailErrorDisplayed(), "Lỗi email không hiện!");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "NGUY HIỂM: Đăng nhập thành công dù trống email!");
    }

    // TC 6: Verify login fails with empty password
    @Test(description = "Verify login fails with empty password")
    @Severity(SeverityLevel.MINOR)
    public void testLoginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.clickLoginMenu();
        loginPage.enterUsername("test@gmail.com");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isPasswordErrorDisplayed(), "Lỗi mật khẩu không hiện!");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "NGUY HIỂM: Đăng nhập thành công dù trống mật khẩu!");
    }

    // TC 7: Verify login fails with invalid email format
    @Test(description = "Verify login fails with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithInvalidEmailFormat() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.clickLoginMenu();
        loginPage.enterUsername("invalid-format-email");
        loginPage.enterPassword("password123");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isEmailErrorDisplayed(), "Lỗi định dạng email không hiển thị!");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "NGUY HIỂM: Đăng nhập thành công với email sai định dạng!");
    }

}
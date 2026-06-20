package tests;

import org.testng.Assert; // Thêm import Assert của TestNG
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(description = "Verify user can login with valid credentials")
    @Description("This test verifies that a user can successfully log in using valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);

        // Các bước này sẽ tự động được ghi vào Allure nhờ @Step đã gắn bên LoginPage
        loginPage.clickLoginMenu();
        loginPage.enterUsername("test@gmail.com");
        loginPage.enterPassword("testweb123");
        loginPage.clickLogin();

        // Kiểm tra kết quả
        boolean isSuccess = loginPage.isLoginSuccessful();

        // Chụp ảnh chốt hạ kết quả cuối cùng để làm bằng chứng
        loginPage.takeScreenshot("Kết quả sau khi Đăng Nhập");

        // Dùng Assert của TestNG
        Assert.assertTrue(isSuccess, "Đăng nhập thất bại! Không tìm thấy hộp tài khoản hiển thị.");
    }
}
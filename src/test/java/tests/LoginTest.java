package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.LoginData;
import dataproviders.LoginDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import pages.LoginPage;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        DriverFactory.getDriver().get("https://www.tncstore.vn/");
        loginPage = new LoginPage(DriverFactory.getDriver());

    }

    // Test case 1: Verify user can login with valid credentials
    @Test(description = "Verify user can login with valid credentials")
    @Description("This test verifies that a user can successfully log in using valid email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        // Các bước này sẽ tự động được ghi vào Allure nhờ @Step đã gắn bên LoginPage
        loginPage.clickLoginMenu();
        loginPage.login("test@gmail.com", "testweb123");

        boolean isSuccess = loginPage.isLoginSuccessful();
        // Dùng Assert của TestNG
        Assert.assertTrue(isSuccess, "Đăng nhập thất bại! Không tìm thấy hộp tài khoản hiển thị.");
    }

    // =========data-drive nagetive test case===========

    @Test(dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class)
    public void testLoginDynamic(LoginData data) {
        Allure.getLifecycle().updateTestCase(t -> t.setName("Login should fail: " + data.testDescription));

        loginPage.clickLoginMenu();
        loginPage.login(data.email, data.password);

        // Chờ 3 giây cho web kịp hiện thông báo lỗi
        // try {
        // Thread.sleep(3000);
        // } catch (InterruptedException e) {
        // }

        if (data.expectedErrorField.equals("email")) {
            Assert.assertTrue(loginPage.isEmailErrorDisplayed(), "Không thấy thông báo lỗi Email!");
        } else if (data.expectedErrorField.equals("password")) {
            Assert.assertTrue(loginPage.isPasswordErrorDisplayed(), "Không thấy thông báo lỗi Mật khẩu!");
        } else {
            boolean isSuccess = loginPage.isLoginSuccessful();
            Assert.assertFalse(isSuccess, "Lỗi: Vẫn đăng nhập thành công!");
        }
    }
}
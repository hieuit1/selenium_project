package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.RegisterData;
import dataproviders.RegisterDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import pages.RegisterPage;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @BeforeMethod
    public void setUpPage() {
        DriverFactory.getDriver().get("https://www.tncstore.vn/");
        registerPage = new RegisterPage(DriverFactory.getDriver());
    }

    // ================= POSITIVE TEST CASE =================
    @Test(description = "Verify user can register with valid credentials", groups = {"smoke", "regression", "positive", "priority:critical"})
    @Description("This test verifies that a user can successfully create a new account")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegisterSuccessfully() {
        registerPage.clickLoginMenu();
        registerPage.clickCreateAccountTab();

        // Random email to prevent duplicate email issues in repeated runs
        long timestamp = System.currentTimeMillis();
        String randomEmail = "testuser" + timestamp + "@gmail.com";

        registerPage.register("Nguyễn Văn Automation", randomEmail, "Password123!");

        boolean isSuccess = registerPage.isRegisterSuccessful();
        Assert.assertTrue(isSuccess, "Đăng ký thất bại! Không chuyển hướng thành công hoặc không thấy trạng thái đăng nhập.");
    }

    // ================= NEGATIVE TEST CASE (DATA-DRIVEN) =================
    @Test(dataProvider = "invalidRegisterData", dataProviderClass = RegisterDataProvider.class, groups = {"regression", "negative", "priority:medium"})
    @Description("Verify register page displays correct validation errors for invalid input values")
    @Severity(SeverityLevel.NORMAL)
    public void testRegisterDynamic(RegisterData data) {
        Allure.getLifecycle().updateTestCase(t -> t.setName("Register should fail: " + data.testDescription));

        registerPage.clickLoginMenu();
        registerPage.clickCreateAccountTab();

        registerPage.register(data.name, data.email, data.password);

        // Xử lý xác minh lỗi (Assert) dựa theo Expected Error Field
        if (data.expectedErrorField.equals("name")) {
            Assert.assertTrue(registerPage.isNameErrorDisplayed(), "Không thấy thông báo lỗi tại trường Họ Tên!");
        } else if (data.expectedErrorField.equals("email")) {
            Assert.assertTrue(registerPage.isEmailErrorDisplayed(), "Không thấy thông báo lỗi tại trường Email!");
        } else if (data.expectedErrorField.equals("password")) {
            Assert.assertTrue(registerPage.isPasswordErrorDisplayed(), "Không thấy thông báo lỗi tại trường Mật khẩu!");
        } else if (data.expectedErrorField.equals("all")) {
            Assert.assertTrue(registerPage.isNameErrorDisplayed(), "Thiếu lỗi Họ Tên");
            Assert.assertTrue(registerPage.isEmailErrorDisplayed(), "Thiếu lỗi Email");
            Assert.assertTrue(registerPage.isPasswordErrorDisplayed(), "Thiếu lỗi Mật khẩu");
        } else if (data.expectedErrorField.equals("email_exists")) {
            Assert.assertTrue(registerPage.isEmailExistsErrorDisplayed(), "Thiếu thông báo lỗi Email đã tồn tại");
        } else if (data.expectedErrorField.equals("password_short")) {
            Assert.assertTrue(registerPage.isPasswordShortErrorDisplayed(),
                    "Thiếu thông báo lỗi Mật khẩu có tối thiểu 6 ký tự");
        } else {
            boolean isSuccess = registerPage.isRegisterSuccessful();
            Assert.assertFalse(isSuccess, "Lỗi: Tài khoản không hợp lệ nhưng vẫn tạo thành công!");
        }
    }
}

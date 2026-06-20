package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import utils.LogUtil;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================
    private By clickUserLogin = By.xpath("(//span[contains(text(),'Tài khoản')])[1]");
    private By usernameField = By.id("js-login-email");
    private By passwordField = By.id("js-login-password");
    private By loginButton = By.xpath("(//a[@class='btn-submit'])[1]");
    private By loginSuccessfully = By.xpath("(//div[@class='box-account background-white d-flex'])[1]");

    // 1. Click menu Tài khoản
    public void clickLoginMenu() {
        stepWithScreenshot("Click menu Tài khoản", () -> {
            jsClick(clickUserLogin);
        });
    }

    // 2. Nhập tên đăng nhập
    public void enterUsername(String username) {
        stepWithScreenshot("Nhập tên đăng nhập: " + username, () -> {
            type(usernameField, username);
        });
    }

    // 3. Nhập mật khẩu
    public void enterPassword(String password) {
        stepWithScreenshot("Nhập mật khẩu", () -> {
            type(passwordField, password);
        });
    }

    // 4. Click nút Đăng nhập
    public void clickLogin() {
        stepWithScreenshot("Click nút Đăng nhập", () -> {
            scrollToElement(loginButton);
            jsClick(loginButton);
        });
    }

    // 5. Hàm kiểm tra xem đã đăng nhập thành công chưa
    public boolean isLoginSuccessful() {
        return isElementDisplayed(loginSuccessfully);
    }

    @Step("Login with email: {email}")
    public void login(String email, String password) {
        LogUtil.info("Performing login with email: " + email);
        enterUsername(email);
        enterPassword(password);
        clickLogin();
    }
}
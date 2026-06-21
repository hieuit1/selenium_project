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

    public void clickLoginMenu() {
        stepWithScreenshot("Click vào Đăng nhập", () -> {
            scrollToElement(clickUserLogin);
            jsClick(clickUserLogin);
        });
    }

    @Step("Nhập email: {username}")
    public void enterUsername(String username) {
        type(usernameField, username);
    }

    @Step("Nhập mật khẩu")
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        stepWithScreenshot("Click nút Đăng nhập", () -> {
            scrollToElement(loginButton);
            jsClick(loginButton);
        });
    }

    @Step("Kiểm tra trạng thái đăng nhập thành công")
    public boolean isLoginSuccessful() {
        boolean isSuccess = isElementDisplayed(loginSuccessfully);

        if (isSuccess) {
            takeScreenshot("Đăng nhập thành công");
        }

        return isSuccess;
    }

    @Step("Thực hiện Đăng nhập với tài khoản: {email}")
    public void login(String email, String password) {
        LogUtil.info("Performing login with email: " + email);
        enterUsername(email);
        enterPassword(password);
        clickLogin();
    }
}
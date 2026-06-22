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
    private By loginSuccessfully = By.xpath("(//span[contains(text(),'Đăng xuất')])[1]");
    private By emailErrorMessage = By.xpath("(//div[contains(text(),'Email đăng nhập không đúng')])[1]");
    private By passwordErrorMessage = By.xpath("(//div[contains(text(),'Mật khẩu không đúng')])[1]");

    public void clickLoginMenu() {
        stepWithScreenshot("Click vào Đăng nhập", () -> {
            scrollToElement(clickUserLogin);
            jsClick(clickUserLogin);
        });
    }

    @Step("Thực hiện Đăng nhập với tài khoản: {email}")
    public void login(String email, String password) {
        LogUtil.info("Performing login with email: " + email);
        type(usernameField, email);
        type(passwordField, password);
        jsClick(loginButton);
    }

    @Step("Kiểm tra đăng nhập")
    public boolean isLoginSuccessful() {
        boolean isSuccess = isElementDisplayed(loginSuccessfully);
        if (isSuccess) {
            takeScreenshot("Đăng nhập thành công");
        } else {
            takeScreenshot("Đăng nhập thất bại");
        }
        return isSuccess;
    }

    @Step("Kiểm tra thông báo lỗi email hiển thị")
    public boolean isEmailErrorDisplayed() {
        boolean isDisplayed = isElementDisplayed(emailErrorMessage);
        if (isDisplayed) {
            takeScreenshot("Thông báo lỗi email đã hiển thị");
        }
        return isDisplayed;
    }

    @Step("Kiểm tra thông báo lỗi mật khẩu hiển thị")
    public boolean isPasswordErrorDisplayed() {
        boolean isDisplayed = isElementDisplayed(passwordErrorMessage);
        if (isDisplayed) {
            takeScreenshot("Thông báo lỗi mật khẩu đã hiển thị");
        }
        return isDisplayed;
    }

}
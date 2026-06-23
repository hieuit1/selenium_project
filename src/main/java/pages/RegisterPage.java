package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import utils.LogUtil;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================
    private By clickUserLogin = By.xpath("(//span[contains(text(),'Tài khoản')])[1]");
    private By createAccountTab = By.xpath("(//a[contains(text(),'o tài')])[1]");

    private By nameField = By.id("js-popup-register-name");
    private By emailField = By.id("js-popup-register-email");
    private By passwordField = By.id("js-popup-register-password");
    private By registerButton = By.xpath("(//span[contains(text(),'Tạo tài khoản')])[1]");

    private By registerSuccessfully = By.xpath("(//div[contains(text(),'Đăng Nhập')])[1]");
    private By nameErrorMessage = By.xpath("(//div[contains(text(),'Tên quá ngắn')])[1]");
    private By emailErrorMessage = By.xpath("(//div[contains(text(),'Email không hợp lệ')])[1]");
    private By passwordErrorMessage = By.xpath("(//div[contains(text(),'Mật khẩu không hợp lệ')])[1]");
    private By passwordShortErrorMessage = By.xpath("(//div[contains(text(),'Mật khẩu có tối thiểu 6 ký tự')])[1]");
    private By emailExistsErrorMessage = By.xpath("(//div[@id='js-popup-register-note'])[1]");

    // ================= ACTIONS =================

    public void clickLoginMenu() {
        stepWithScreenshot("Click vào Tài khoản để mở popup", () -> {
            scrollToElement(clickUserLogin);
            jsClick(clickUserLogin);
        });
    }

    @Step("Chuyển sang tab Tạo tài khoản")
    public void clickCreateAccountTab() {
        stepWithScreenshot("Click vào tab Tạo tài khoản", () -> {
            waitForClickable(createAccountTab);
            jsClick(createAccountTab);
        });
    }

    @Step("Thực hiện Đăng ký với Tên: {name}, Email: {email}")
    public void register(String name, String email, String password) {
        LogUtil.info("Performing register for: " + email);
        type(nameField, name);
        type(emailField, email);
        type(passwordField, password);

        stepWithScreenshot("Click nút Tạo tài khoản", () -> {
            jsClick(registerButton);
        });
    }

    @Step("Kiểm tra đăng ký thành công")
    public boolean isRegisterSuccessful() {
        boolean isSuccess = isElementDisplayed(registerSuccessfully);
        if (isSuccess) {
            takeScreenshot("Đăng ký thành công");
        } else {
            takeScreenshot("Đăng ký thất bại");
        }
        return isSuccess;
    }

    @Step("Kiểm tra thông báo lỗi Email đã tồn tại")
    public boolean isEmailExistsErrorDisplayed() {
        boolean isDisplayed = isElementDisplayed(emailExistsErrorMessage);
        if (isDisplayed)
            takeScreenshot("Lỗi email đã tồn tại hiển thị");
        return isDisplayed;
    }

    @Step("Kiểm tra thông báo lỗi Tên hiển thị")
    public boolean isNameErrorDisplayed() {
        boolean isDisplayed = isElementDisplayed(nameErrorMessage);
        if (isDisplayed)
            takeScreenshot("Lỗi tên hiển thị");
        return isDisplayed;
    }

    @Step("Kiểm tra thông báo lỗi Email hiển thị")
    public boolean isEmailErrorDisplayed() {
        boolean isDisplayed = isElementDisplayed(emailErrorMessage);
        if (isDisplayed)
            takeScreenshot("Lỗi email hiển thị");
        return isDisplayed;
    }

    @Step("Kiểm tra thông báo lỗi Mật khẩu hiển thị")
    public boolean isPasswordErrorDisplayed() {
        boolean isDisplayed = isElementDisplayed(passwordErrorMessage);
        if (isDisplayed)
            takeScreenshot("Lỗi mật khẩu hiển thị");
        return isDisplayed;
    }

    @Step("Kiểm tra thông báo lỗi Mật khẩu có tối thiểu 6 ký tự hiển thị")
    public boolean isPasswordShortErrorDisplayed() {
        boolean isDisplayed = isElementDisplayed(passwordShortErrorMessage);
        if (isDisplayed)
            takeScreenshot("Lỗi mật khẩu có tối thiểu 6 ký tự hiển thị");
        return isDisplayed;
    }
}
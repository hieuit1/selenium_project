package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import utils.LogUtil;
import utils.WaitUtil;

/**
 * LoginPage - Page Object for login functionality
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By lickUserLogin = By.xpath("(//span[contains(text(),'Tài khoản')])[1]");
    private By usernameField = By.id("js-login-email"); // Đã khai báo là usernameField
    private By passwordField = By.id("js-login-password"); // Đã khai báo là passwordField
    private By loginButton = By.xpath("(//a[@class='btn-submit'])[1]");
    private By loginSuccessfully = By.xpath("(//div[@class='box-account background-white d-flex'])[1]");

    // 1. Hàm click vào menu "Tài khoản" trước khi đăng nhập
    public void clickLoginMenu() {
        stepWithScreenshot("Click menu Tài khoản", () -> {
            // Vẫn đợi phần tử hiện ra
            WebElement menu = WaitUtil.waitForElementClickable(driver, lickUserLogin);

            // Dùng Javascript để click, bỏ qua vật cản
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", menu);
        });
    }

    public void enterUsername(String username) {
        stepWithScreenshot("Nhập tên đăng nhập: " + username, () -> {
            // Đã sửa 'usernameInput' thành 'usernameField' cho khớp với khai báo ở trên
            WebElement userElement = WaitUtil.waitForElementVisible(driver, usernameField);
            userElement.clear();
            userElement.sendKeys(username);
        });
    }

    public void enterPassword(String password) {
        stepWithScreenshot("Nhập mật khẩu", () -> {
            // Đã sửa 'passwordInput' thành 'passwordField' cho khớp với khai báo ở trên
            WebElement passElement = WaitUtil.waitForElementVisible(driver, passwordField);
            passElement.clear();
            passElement.sendKeys(password);
        });
    }

    public void clickLogin() {
        stepWithScreenshot("Click nút Đăng nhập", () -> {
            WebElement loginBtn = WaitUtil.waitForElementClickable(driver, loginButton);
            // Thay vì dùng loginBtn.click(); bị chặn, ta dùng JavaScript để click ép buộc
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginBtn);
        });
    }

    // 2. Hàm kiểm tra xem đã đăng nhập thành công chưa
    public boolean isLoginSuccessful() {
        try {
            // Đợi cái hộp tài khoản xuất hiện
            WebElement accountBox = WaitUtil.waitForElementVisible(driver, loginSuccessfully);
            return accountBox.isDisplayed();
        } catch (Exception e) {
            return false; // Nếu sau 1 thời gian không thấy thì trả về false (đăng nhập thất bại)
        }
    }

    // 3. Hàm chụp ảnh chủ động (nếu trong BasePage chưa có)
    public void takeScreenshot(String stepName) {
        stepWithScreenshot(stepName, () -> {
            LogUtil.info("Chụp ảnh màn hình: " + stepName);
        });
    }

    /**
     * Perform login
     */
    @Step("Login with email: {email}")
    public void login(String email, String password) {
        LogUtil.info("Performing login with email: " + email);
        enterUsername(email);
        enterPassword(password);
        clickLogin();
    }
}
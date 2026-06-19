package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // 1. Định nghĩa các Locators (Khai báo địa chỉ)
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    // 2. Hàm khởi tạo (Constructor) để nhận driver từ bài test
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Các hành động (Methods) trên trang này
    public void enterUsername(String user) {
        driver.findElement(usernameField).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(passwordField).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Một hàm tổng hợp cho nhanh
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}
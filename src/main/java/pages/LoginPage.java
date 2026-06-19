package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By lickUserLogin = By.xpath("(//span[contains(text(),'Tài khoản')])[1]");
    private By usernameField = By.id("js-login-email");
    private By passwordField = By.id("js-login-password");
    private By loginButton = By.xpath("(//a[@class='btn-submit'])[1]");
    private By loginSuccessfully = By.xpath("(//div[@class='box-account background-white d-flex'])[1]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click() {
        driver.findElement(lickUserLogin).click();
    }

    // 3. Các hành động (Methods) trên trang này

    public void enterUsername(String user) {
        driver.findElement(usernameField).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(passwordField).sendKeys(pass);
    }

    // Dùng Javascript để click nếu bị che khuất
    public void clickLogin() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        try {
            button.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", button);
        }
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginSuccessfully)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void login(String user, String pass) {
        click();
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}
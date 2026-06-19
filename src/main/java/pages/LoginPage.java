package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import utils.LogUtil;

/**
 * LoginPage - Page Object for login functionality
 */
public class LoginPage extends BasePage {

    // Locators
    private By lickUserLogin = By.xpath("(//span[contains(text(),'Tài khoản')])[1]");
    private By usernameField = By.id("js-login-email");
    private By passwordField = By.id("js-login-password");
    private By loginButton = By.xpath("(//a[@class='btn-submit'])[1]");
    private By loginSuccessfully = By.xpath("(//div[@class='box-account background-white d-flex'])[1]");

    public LoginPage(WebDriver driver) {
        super(driver);
        LogUtil.info("LoginPage initialized");
    }

    /**
     * Click on login menu
     */
    @Step("Click on login menu")
    public void clickLoginMenu() {
        LogUtil.info("Clicking on login menu");
        click(lickUserLogin);
    }

    /**
     * Enter email
     */
    @Step("Enter email: {email}")
    public void enterUsername(String email) {
        LogUtil.info("Entering email: " + email);
        type(usernameField, email);
    }

    /**
     * Enter password
     */
    @Step("Enter password")
    public void enterPassword(String password) {
        LogUtil.info("Entering password");
        type(passwordField, password);
    }

    /**
     * Click login button
     */
    @Step("Click login button")
    public void clickLogin() {
        LogUtil.info("Clicking login button");
        try {
            click(loginButton);
        } catch (Exception e) {
            LogUtil.warn("Standard click failed, trying JavaScript click");
            jsClick(loginButton);
        }
    }

    /**
     * Check if login is successful
     */
    @Step("Verify login is successful")
    public boolean isLoginSuccessful() {
        LogUtil.info("Verifying if login is successful");
        boolean result = isElementDisplayed(loginSuccessfully);
        LogUtil.info("Login successful: " + result);
        return result;
    }

    /**
     * Perform login
     */
    @Step("Login with email: {email}")
    public void login(String email, String password) {
        LogUtil.info("Performing login with email: " + email);
        clickLoginMenu();
        enterUsername(email);
        enterPassword(password);
        clickLogin();
    }
}
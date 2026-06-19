package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage; // Import class LoginPage vừa tạo

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Khởi tạo đối tượng LoginPage
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccessfully() {
        // Gọi hàm login từ Page Object
        loginPage.login("standard_user", "secret_sauce");

        // Kiểm tra kết quả
        assert driver.getCurrentUrl().contains("inventory.html");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
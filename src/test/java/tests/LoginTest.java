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
        driver.get("https://www.tncstore.vn/");

        // Khởi tạo đối tượng LoginPage
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccessfully() {
        loginPage.login("test@gmail.com", "testweb123");

        // Kiểm tra xem đăng nhập có thành công không thay vì dùng URL
        boolean isSuccess = loginPage.isLoginSuccessful();
        assert isSuccess : "Đăng nhập thất bại, không tìm thấy box tài khoản!";
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
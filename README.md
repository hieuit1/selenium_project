# 🤖 Selenium Automation Project

## 📋 Project Overview

Đây là một project Selenium WebDriver automation testing framework được xây dựng với:

- ✅ **Page Object Model (POM)** - Cấu trúc tối ưu cho maintenance
- ✅ **Allure Report** - Báo cáo chi tiết từng bước test
- ✅ **Screenshot Capture** - Tự động chụp ảnh khi test fail
- ✅ **Custom Logging** - Logging chi tiết với Log4j2
- ✅ **GitHub Actions CI/CD** - Tự động chạy test khi push code và lên lịch hằng ngày

---

## 🏗️ Project Structure

```
selenium-project/
├── src/
│   ├── main/java/
│   │   ├── pages/
│   │   │   ├── BasePage.java          # Base page class
│   │   │   └── LoginPage.java         # Login page
│   │   └── utils/
│   │       ├── DriverFactory.java     # WebDriver factory
│   │       ├── ConfigReader.java      # Configuration management
│   │       ├── ScreenshotUtil.java    # Screenshot utility
│   │       ├── LogUtil.java           # Logging
│   │       └── WaitUtil.java          # Wait utilities
│   ├── test/java/
│   │   ├── tests/
│   │   │   ├── BaseTest.java          # Base test class
│   │   │   └── LoginTest.java         # Login tests
│   │   └── listeners/
│   │       └── TestListener.java      # Test event listener
│   └── resources/
│       ├── config.properties          # Configuration
│       ├── testng.xml                 # TestNG configuration
│       ├── allure.properties          # Allure configuration
│       └── log4j2.xml                 # Logging configuration
├── .github/workflows/
│   └── ci-cd.yml                      # GitHub Actions workflow
├── pom.xml                            # Maven configuration
└── README.md                          # This file
```

---

## 🚀 Getting Started

### Prerequisites

- ✅ Java 21+
- ✅ Maven 3.6+
- ✅ Git
- ✅ Chrome Browser (or configure for Firefox/Edge)

### Installation

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd selenium-project
   ```

2. **Install dependencies**

   ```bash
   mvn clean install
   ```

3. **Configure base URL** (if needed)
   Edit `src/test/resources/config.properties`:
   ```properties
   base.url=https://www.tncstore.vn/
   browser=chrome
   headless.mode=false
   ```

---

## 🧪 Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn clean test -Dtest=LoginTest
```

### Run Specific Test Method

```bash
mvn clean test -Dtest=LoginTest#testLoginSuccessfully
```

### Run with Headless Mode

Edit `src/test/resources/config.properties`:

```properties
headless.mode=true
```

---

## 🎬 Video Recording & Screenshots

### Automatic Video Recording

Every test is automatically recorded as a video (AVI format):

```bash
# Run tests - videos will be recorded
mvn clean test

# Videos are saved in:
test-recordings/
├── testLoginSuccessfully.avi
└── testLoginWithSingleStep.avi
```

### Configuration

**Enable/Disable Video Recording:**

```properties
# src/test/resources/config.properties
video.recording=true      # Enable video
screenshot.on.failure=true # Screenshot on failure
```

### View Results with Videos

```bash
# Generate Allure report
mvn allure:report

# Open in browser (videos embedded in report)
mvn allure:serve
```

**Report includes:**

- 🎬 **Videos** - Full screen recording of each test
- 📸 **Screenshots** - Captured when test fails
- 📝 **Logs** - Detailed step-by-step execution
- 📊 **Metrics** - Test statistics and duration

---

## 📚 Documentation

- **[QUICK_START.md](QUICK_START.md)** - 30 second quick start
- **[VIDEO_AND_SCREENSHOT_GUIDE.md](VIDEO_AND_SCREENSHOT_GUIDE.md)** - Complete video/screenshot guide
- **[IMPLEMENTATION_PLAN.md](IMPLEMENTATION_PLAN.md)** - Detailed implementation plan
- **[PROGRESS.md](PROGRESS.md)** - Implementation progress tracker

---

### Generate Report

```bash
mvn clean test
mvn allure:report
```

### View Report in Browser

```bash
mvn allure:serve
```

This command will automatically open the Allure report in your default browser.

---

## 📸 Screenshots & Logs

### Screenshots

- **Location**: `screenshots/` directory
- **Captured**: Automatically when test fails
- **Format**: PNG with timestamp

### Logs

- **Location**: `logs/` directory
- **Configuration**: `src/test/resources/log4j2.xml`
- **Levels**: INFO, DEBUG, ERROR, WARN

### Run Example

```bash
# All outputs will be in:
# - screenshots/LoginTest_20-06-2026_10-30-45.png
# - logs/automation.log
# - target/allure-results/
```

---

## 🔧 Configuration

### Browser Configuration

Edit `src/test/resources/config.properties`:

```properties
# Chrome, Firefox, Edge
browser=chrome

# Headless mode
headless.mode=false

# Timeout (seconds)
timeout=10

# Screenshot on failure
screenshot.on.failure=true
```

### TestNG Configuration

Edit `src/test/resources/testng.xml`:

```xml
<suite name="Selenium Automation Test Suite" parallel="false" thread-count="1">
    <test name="Login Tests">
        <classes>
            <class name="tests.LoginTest"/>
        </classes>
    </test>
</suite>
```

---

## 📝 Writing Tests

### Example Test with Allure Steps

```java
@Test(description = "Verify user login")
@Description("This test verifies that a user can login")
@Severity(SeverityLevel.CRITICAL)
public void testUserLogin() {
    // Arrange
    LoginPage loginPage = new LoginPage(driver);

    // Act
    loginPage.login("test@gmail.com", "password123");

    // Assert
    assert loginPage.isLoginSuccessful();
}
```

### Using Page Object Model

```java
// Pages extend BasePage
public class LoginPage extends BasePage {

    @Step("Login with credentials")
    public void login(String email, String password) {
        type(usernameField, email);
        type(passwordField, password);
        click(loginButton);
    }

    public boolean isLoginSuccessful() {
        return isElementDisplayed(accountBox);
    }
}

// Tests extend BaseTest
@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        LoginPage page = new LoginPage(driver);
        page.login("test@gmail.com", "password");
        assert page.isLoginSuccessful();
    }
}
```

---

## 🔄 GitHub Actions CI/CD

### Workflow Features

✅ **On Push**: Tests run automatically when pushing to `main` or `develop` branch

✅ **Scheduled**: Tests run daily at 00:00 UTC

✅ **Artifacts**:

- Test results
- Allure report
- Screenshots
- Logs

### View Results

1. Go to GitHub repository → **Actions** tab
2. Click on the workflow run
3. Download artifacts or view logs

---

## 🛠️ Key Features

### 1. Page Object Model (POM)

- `BasePage.java` - Common actions (click, type, wait, etc.)
- Page-specific classes extend `BasePage`
- Maintainable and scalable test code

### 2. Allure Reporting

- Detailed steps for each test
- Screenshots attached to report
- Test execution timeline
- Test history and trends

### 3. Custom Listeners

- `TestListener.java` - Captures screenshots on failure
- Automatic Allure step integration
- Test start/end logging

### 4. Utilities

- `DriverFactory.java` - WebDriver initialization
- `ConfigReader.java` - Configuration management
- `LogUtil.java` - Centralized logging
- `WaitUtil.java` - Wait conditions
- `ScreenshotUtil.java` - Screenshot management

### 5. Configuration Management

- `config.properties` - Application settings
- `testng.xml` - Test execution configuration
- `log4j2.xml` - Logging configuration
- `allure.properties` - Allure report settings

---

## 📊 Test Report Example

Allure Report includes:

- ✅ Test execution timeline
- ✅ Detailed steps with duration
- ✅ Screenshots on failure
- ✅ Logs and debugging information
- ✅ Statistics and trends
- ✅ Test history

---

## 🐛 Troubleshooting

### Issue: Tests failing with timeout

**Solution**: Increase timeout in `config.properties`

```properties
timeout=15
```

### Issue: Cannot find element

**Solution**: Check Allure report for screenshots and logs

### Issue: Screenshots not showing

**Solution**: Ensure directory exists

```bash
mkdir -p screenshots
```

### Issue: Allure report not generating

**Solution**:

```bash
mvn clean
mvn allure:reset
mvn clean test
mvn allure:report
```

---

## 📚 Additional Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Allure Framework](https://docs.qameta.io/allure/)
- [TestNG Documentation](https://testng.org/doc/)
- [Page Object Model Best Practices](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

---

## 📧 Support

For issues or questions, please create an issue in the repository or contact the team.

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Last Updated**: 2026-06-20

# 📋 Kế Hoạch Phát Triển Selenium Project với Allure Report

## 🎯 Mục Tiêu

Xây dựng automation testing framework hoàn chỉnh với:

- ✅ Page Object Model (POM) cấu trúc tối ưu
- ✅ Allure Report with detailed steps
- ✅ Tự động chụp ảnh (Screenshots)
- ✅ Tự động ghi video (Video Recording)
- ✅ Custom Listeners và Logging
- ✅ GitHub Actions CI/CD Integration

---

## 📁 Cấu Trúc Project Sau Khi Hoàn Thành

```
selenium-project/
├── src/
│   ├── main/java/
│   │   ├── pages/                  # POM - Page classes
│   │   │   ├── BasePage.java      # ✨ NEW - Base class
│   │   │   ├── LoginPage.java     # Refactored
│   │   │   └── [OtherPages].java
│   │   └── utils/                  # ✨ NEW - Utilities
│   │       ├── ScreenshotUtil.java    # Chụp ảnh
│   │       ├── VideoRecorder.java     # Ghi video
│   │       ├── WaitUtil.java          # Wait helpers
│   │       ├── LogUtil.java           # Logging
│   │       ├── ConfigReader.java      # Config management
│   │       └── DriverFactory.java     # Driver initialization
│   ├── test/java/
│   │   ├── tests/
│   │   │   ├── BaseTest.java       # ✨ NEW - Base test class
│   │   │   ├── LoginTest.java      # Refactored
│   │   │   └── [OtherTests].java
│   │   └── listeners/              # ✨ NEW - Test listeners
│   │       ├── TestListener.java       # Capture screenshots on failure
│   │       └── AllureListener.java     # Allure integration
│   └── resources/                  # ✨ NEW
│       ├── config.properties           # Configuration
│       ├── testng.xml                  # TestNG configuration
│       └── allure.properties           # Allure config
├── .github/workflows/
│   └── ci-cd.yml                   # Updated with Allure reporting
├── pom.xml                         # Updated dependencies
└── README.md
```

---

## 📦 Dependencies Cần Thêm (pom.xml)

### 1. **Allure Framework**

```xml
<!-- Allure TestNG -->
<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-testng</artifactId>
    <version>2.24.0</version>
</dependency>

<!-- Allure Maven Plugin -->
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.13.0</version>
</plugin>
```

### 2. **Video Recording**

```xml
<!-- Monte Screen Recorder -->
<dependency>
    <groupId>monte</groupId>
    <artifactId>monte-screen-recorder</artifactId>
    <version>0.7.7.0</version>
</dependency>
```

### 3. **Logging & Utilities**

```xml
<!-- Log4j2 -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.22.1</version>
</dependency>

<!-- Commons IO -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.15.1</version>
</dependency>
```

### 4. **Updated Plugins**

```xml
<!-- Maven Surefire with Allure -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.3</version>
    <configuration>
        <includes>
            <include>**/*Test.java</include>
        </includes>
        <argLine>
            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
        </argLine>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.20</version>
        </dependency>
    </dependencies>
</plugin>
```

---

## 🔧 Files Cần Tạo

### **Phase 1: Core Infrastructure**

- [ ] `src/main/java/utils/ScreenshotUtil.java` - Screenshot utility
- [ ] `src/main/java/utils/VideoRecorder.java` - Video recording
- [ ] `src/main/java/utils/WaitUtil.java` - Wait helpers
- [ ] `src/main/java/utils/LogUtil.java` - Logging wrapper
- [ ] `src/main/java/utils/ConfigReader.java` - Configuration management
- [ ] `src/main/java/utils/DriverFactory.java` - WebDriver factory
- [ ] `src/main/java/pages/BasePage.java` - Base page with Allure steps
- [ ] `src/resources/config.properties` - Configuration file
- [ ] `src/resources/log4j2.xml` - Logging configuration

### **Phase 2: Test Infrastructure**

- [ ] `src/test/java/listeners/TestListener.java` - Screenshot on failure
- [ ] `src/test/java/listeners/AllureListener.java` - Allure integration
- [ ] `src/test/java/tests/BaseTest.java` - Base test setup
- [ ] `src/resources/testng.xml` - TestNG configuration
- [ ] `src/resources/allure.properties` - Allure configuration

### **Phase 3: Refactoring**

- [ ] Refactor `LoginPage.java` - Use BasePage, add Allure steps
- [ ] Refactor `LoginTest.java` - Use BaseTest, add listeners
- [ ] Update `pom.xml` - Add all dependencies and plugins

### **Phase 4: CI/CD**

- [ ] Update `.github/workflows/ci-cd.yml` - Add Allure Report generation
- [ ] Create `README.md` - Documentation

---

## 📊 Key Features Implementation

### **1. Allure Steps in Tests**

```java
@Step("Login with email: {email}")
public void login(String email, String password) {
    // Test code with detailed steps
}
```

### **2. Automatic Screenshots**

- ✅ Chụp ảnh khi test FAIL
- ✅ Chụp ảnh theo từng bước (optional)
- ✅ Lưu trong Allure Report

### **3. Video Recording**

- ✅ Tự động ghi video khi test chạy
- ✅ Lưu video khi test FAIL
- ✅ Attach video vào Allure Report

### **4. Custom Logging**

```java
Log4j2 + Allure Logger
- INFO: Step execution
- ERROR: Test failures
- DEBUG: Detailed flow
```

---

## ⚙️ GitHub Actions Updates

### **New Features in CI/CD:**

```yaml
- Generate Allure Report
- Upload Allure artifacts
- Deploy Allure Report to GitHub Pages (optional)
- Archive screenshots & videos
```

---

## 🚀 Execution Flow

### **Local Execution:**

```bash
# Run all tests with Allure
mvn clean test

# Generate Allure report
mvn allure:report

# Serve Allure report (open in browser)
mvn allure:serve
```

### **GitHub Actions:**

```
Push Code → Run Tests → Capture Screenshots/Video
→ Generate Allure Report → Upload Artifacts → Display Report
```

---

## ⏱️ Timeline Estimate

| Phase     | Task                            | Time        |
| --------- | ------------------------------- | ----------- |
| 1         | Core Utilities & Configuration  | 30 min      |
| 2         | Test Infrastructure & Listeners | 20 min      |
| 3         | Refactoring Existing Code       | 20 min      |
| 4         | Update GitHub Actions           | 15 min      |
| 5         | Testing & Documentation         | 15 min      |
| **Total** |                                 | **100 min** |

---

## 📝 Implementation Steps

### **Step 1: Update pom.xml** (5 min)

- Add Allure, Video Recording, Log4j2 dependencies
- Update Maven plugins

### **Step 2: Create Utility Classes** (20 min)

- ScreenshotUtil.java
- VideoRecorder.java
- LogUtil.java
- ConfigReader.java
- DriverFactory.java
- WaitUtil.java

### **Step 3: Create Base Classes** (15 min)

- BasePage.java with POM best practices
- BaseTest.java with setup/teardown

### **Step 4: Create Listeners** (15 min)

- TestListener.java - Screenshot on failure
- AllureListener.java - Allure integration

### **Step 5: Refactor Existing Code** (20 min)

- Update LoginPage.java
- Update LoginTest.java

### **Step 6: Update Configurations** (10 min)

- config.properties
- testng.xml
- allure.properties
- log4j2.xml

### **Step 7: Update GitHub Actions** (10 min)

- Add Allure Report generation
- Archive results

### **Step 8: Testing & Documentation** (5 min)

- Local test run
- Update README

---

## ✅ Success Criteria

- [ ] All tests run successfully with Allure steps
- [ ] Screenshots captured automatically on failure
- [ ] Video recording saved for failed tests
- [ ] Allure Report generated and readable
- [ ] GitHub Actions runs complete without errors
- [ ] All artifacts uploaded and accessible
- [ ] Documentation complete and clear

---

## 📚 References

- Allure Framework: https://docs.qameta.io/allure/
- POM Best Practices: https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/
- TestNG: https://testng.org/
- GitHub Actions: https://docs.github.com/en/actions

# 🤖 Selenium Automation Project - TNC Store Testing Framework

## 📋 Tổng quan dự án

Dự án này là một **Selenium WebDriver Automation Testing Framework** chuyên nghiệp được xây dựng bằng ngôn ngữ **Java**, áp dụng cho trang web bán lẻ thiết bị công nghệ **TNC Store** (https://www.tncstore.vn/). Framework sử dụng thiết kế hướng đối tượng tối ưu cùng các công nghệ kiểm thử tự động tiên tiến nhất:

- ✅ **Page Object Model (POM)** - Cấu trúc phân tách rõ ràng giữa UI Locators/Actions và Test Scripts, giúp bảo trì code dễ dàng.
- ✅ **TestNG & Data-Driven Testing** - Hỗ trợ quản lý ca kiểm thử, chạy song song (Parallel execution) và nạp dữ liệu từ Data Providers.
- ✅ **Allure Report & History** - Xuất báo cáo trực quan, chi tiết từng bước (`@Step`), có tích hợp lịch sử kiểm thử qua GitHub Pages.
- ✅ **Video Recording & Screenshots** - Tự động quay video màn hình toàn bộ quá trình chạy và tự động chụp ảnh màn hình khi có ca kiểm thử thất bại (Test failure).
- ✅ **Custom Logging with Log4j2** - Ghi nhận nhật ký chạy chi tiết phục vụ quá trình debug và kiểm tra luồng hệ thống.
- ✅ **Multi-Runner CI/CD Sharding Pipeline** - Tích hợp GitHub Actions chạy song song chia đều các ca kiểm thử trên **4 máy ảo độc lập** (Sharding) giúp giảm thời gian thực thi tối đa.

---

## 🏗️ Cấu trúc thư mục dự án

```
selenium-project/
├── .github/workflows/
│   └── ci-cd.yml                      # Cấu hình GitHub Actions CI/CD Pipeline
├── src/
│   ├── main/java/
│   │   ├── pages/                     # Tầng định nghĩa các Page và Action tương tác
│   │   │   ├── BasePage.java          # Lớp cơ sở chứa các tương tác Selenium cơ bản (Click, Type, Wait,...)
│   │   │   ├── BuildPCPage.java       # Trang xây dựng cấu hình PC
│   │   │   ├── CartPage.java          # Trang quản lý giỏ hàng
│   │   │   ├── CheckoutPage.java      # Trang thanh toán và điền thông tin đơn hàng
│   │   │   ├── E2EPage.java           # Trang điều phối luồng End-to-End
│   │   │   ├── LoginPage.java         # Trang đăng nhập tài khoản
│   │   │   ├── ProductDetailPage.java # Trang chi tiết sản phẩm và đánh giá
│   │   │   ├── ProfilePage.java       # Trang thông tin tài khoản cá nhân
│   │   │   ├── RegisterPage.java      # Trang đăng ký tài khoản mới
│   │   │   └── SearchPage.java        # Trang tìm kiếm và lọc sản phẩm
│   │   └── utils/                     # Tầng tiện ích hỗ trợ Framework
│   │       ├── ConfigReader.java      # Đọc và quản lý cấu hình hệ thống từ config.properties
│   │       ├── DriverFactory.java     # Quản lý vòng đời WebDriver (ThreadLocal hỗ trợ chạy song song)
│   │       ├── LogUtil.java           # Trình ghi log tập trung bằng Log4j2
│   │       ├── ScreenshotUtil.java    # Tiện ích chụp và đính kèm ảnh chụp màn hình vào Allure
│   │       └── WaitUtil.java          # Tiện ích quản lý thời gian chờ của driver
│   └── test/java/
│       ├── data/                      # Định nghĩa các Data Model cho Data-Driven Testing
│       │   ├── BuildPCData.java
│       │   ├── CheckoutData.java
│       │   ├── LoginData.java
│       │   └── ...
│       ├── dataproviders/             # Các lớp cung cấp nguồn dữ liệu mẫu cho TestNG
│       │   ├── BuildPCDataProvider.java
│       │   ├── LoginDataProvider.java
│       │   └── ...
│       ├── listeners/                 # Các trình lắng nghe sự kiện kiểm thử
│       │   ├── TestListener.java      # Lắng nghe sự kiện để chụp ảnh màn hình khi lỗi & đính kèm Allure
│       │   └── ShardListener.java     # Can thiệp vào danh sách test case phục vụ tính năng CI/CD Sharding
│       └── tests/                     # Tầng chứa các lớp Kịch bản kiểm thử (Test Cases)
│           ├── BaseTest.java          # Lớp cơ sở thiết lập môi trường, driver khởi chạy và đóng
│           ├── BuildPCTest.java       # Kiểm thử luồng tự xây dựng cấu hình PC
│           ├── CartTest.java          # Kiểm thử chức năng thêm, sửa, xóa sản phẩm trong giỏ hàng
│           ├── CheckoutTest.java      # Kiểm thử quá trình đặt hàng & thanh toán
│           ├── E2ETest.java           # Kiểm thử luồng tích hợp hệ thống từ đầu đến cuối (E2E)
│           ├── LoginTest.java         # Kiểm thử tính năng đăng nhập (Positive & Negative)
│           ├── ProfileTest.java       # Kiểm thử cập nhật thông tin cá nhân khách hàng
│           ├── RegisterTest.java      # Kiểm thử tính năng tạo tài khoản mới
│           ├── ReviewTest.java        # Kiểm thử tính năng gửi đánh giá và nhận xét sản phẩm
│           └── SearchTest.java        # Kiểm thử tính năng tìm kiếm và bộ lọc sản phẩm
├── pom.xml                            # Tập tin cấu hình Maven (dependencies, plugins, testng suite)
└── README.md                          # Tài liệu hướng dẫn sử dụng dự án này
```

---

## 🚀 Hướng dẫn cài đặt ban đầu (Setup)

### Yêu cầu hệ thống tối thiểu:
- **Java Development Kit (JDK)**: Phiên bản 21 hoặc mới hơn.
- **Apache Maven**: Phiên bản 3.6 trở lên.
- **Trình duyệt**: Google Chrome đã được cài đặt.

### Các bước chuẩn bị:

1. **Tải mã nguồn về máy**:
   ```bash
   git clone <repository-url>
   cd selenium-project
   ```

2. **Cài đặt thư viện dependencies**:
   Sử dụng Maven để tải và cài đặt các thư viện cần thiết:
   ```bash
   mvn clean install -DskipTests
   ```

3. **Cấu hình môi trường**:
   Mở tập tin [src/test/resources/config.properties](file:///e:/automation_tester/selenium-project/src/test/resources/config.properties) để tùy chỉnh các thuộc tính chạy test:
   ```properties
   base.url=https://www.tncstore.vn/
   browser=chrome
   headless.mode=false         # Đặt thành true nếu muốn chạy ẩn giao diện (ví dụ trên máy CI)
   timeout=10                  # Thời gian chờ mặc định (giây)
   screenshot.on.failure=true  # Tự động chụp ảnh khi test thất bại
   video.recording=true        # Tự động quay video màn hình kiểm thử
   ```

---

## 🧪 Thực thi kiểm thử (Running Tests)

### Chạy toàn bộ các ca kiểm thử
```bash
mvn clean test
```

### Chạy một Class kiểm thử cụ thể
```bash
mvn clean test -Dtest=LoginTest
```

### Chạy một kịch bản kiểm thử (Test Method) cụ thể trong Class
```bash
mvn clean test -Dtest=LoginTest#testLoginSuccessfully
```

### Chạy ẩn giao diện (Headless mode) từ Command Line
```bash
mvn clean test -Dheadless.mode=true
```

---

## 📊 Allure Report & Kết quả kiểm thử

Allure được sử dụng để tổng hợp kết quả chạy test một cách trực quan và sinh động nhất.

### Tạo báo cáo HTML sau khi chạy test:
```bash
mvn allure:report
```

### Khởi chạy Allure Server cục bộ để xem trực tiếp trên Trình duyệt:
```bash
mvn allure:serve
```
Hệ thống sẽ tự động mở trình duyệt mặc định hiển thị giao diện báo cáo với:
- 🎬 **Video ghi lại toàn bộ quá trình** chạy được đính kèm ở từng bước kiểm thử.
- 📸 **Hình ảnh chụp lỗi thực tế** trực quan khi một bước kiểm thử bị hỏng hoặc thất bại.
- 📝 **Nhật ký hệ thống** chi tiết, lưu dấu vết từ lúc driver khởi động đến khi giải phóng bộ nhớ.

---

## 🎬 Quay video kiểm thử & Ảnh chụp lỗi

### Quay video màn hình
- Phim quay hoạt động kiểm thử được ghi lại dưới định dạng `.avi` và lưu tại thư mục `test-recordings/`.
- Cấu hình bật/tắt tính năng này trong tập tin cấu hình `config.properties` thông qua thuộc tính `video.recording=true`.

### Ảnh chụp màn hình khi xảy ra lỗi
- Khi bất kỳ test case nào gặp lỗi hiển thị hoặc lỗi kiểm thử (Assertion), [TestListener.java](file:///e:/automation_tester/selenium-project/src/test/java/listeners/TestListener.java) sẽ kích hoạt chức năng chụp màn hình.
- Ảnh chụp màn hình định dạng `.png` được lưu trữ tại thư mục `screenshots/` và tự động nhúng vào bước lỗi tương ứng trong **Allure Report**.

---

## 🔄 GitHub Actions CI/CD & Sharding (Phân tải máy ảo)

Dự án thiết lập luồng CI/CD mạnh mẽ bằng GitHub Actions ([ci-cd.yml](file:///e:/automation_tester/selenium-project/.github/workflows/ci-cd.yml)):

1. **Kích hoạt tự động**: Khi có hành động `push` hoặc tạo Pull Request lên nhánh `main` và `develop`, hoặc định kỳ hàng ngày vào lúc `00:00 UTC`.
2. **Chiến lược phân tải (Sharding)**: 
   - Sử dụng một Ma trận thực thi (`matrix`) với **4 máy ảo Ubuntu chạy song song**.
   - Trình nghe [ShardListener.java](file:///e:/automation_tester/selenium-project/src/test/java/listeners/ShardListener.java) áp dụng thuật toán **Round-Robin** để chia đều danh sách các kịch bản kiểm thử cho từng máy ảo dựa trên biến `-DshardIndex` và `-DshardTotal`.
3. **Tổng hợp báo cáo và triển khai**:
   - Sau khi các máy ảo kết thúc lượt chạy độc lập, một tác vụ tổng hợp dữ liệu kiểm thử (Job `report`) sẽ thu thập kết quả thô (`allure-results`) từ toàn bộ các shard.
   - Biên dịch ra báo cáo Allure cuối cùng và tự động cập nhật lên **GitHub Pages**.
   - Đường dẫn báo cáo Allure nghiệm thu: **https://hieuit1.github.io/selenium_project/**

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import utils.LogUtil;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // LOCATORS
    private By accountMenu = By.xpath("(//span[@class='hover-txt line-clamp-1'])[1]");
    private By profileInfoMenu = By.xpath("(//span[contains(text(),'Thông tin tài khoản')])[1]");

    private By fullNameField = By.xpath("(//input[@id='fullname'])[1]");
    private By mobileField = By.xpath("(//input[@id='mobile'])[1]");

    // Cập nhật lại ID chuẩn từ HTML bạn cung cấp
    private By provinceDropdown = By.xpath("//select[@id='js-shipto-province']");
    private By districtDropdown = By.xpath("//select[@id='js-district-holder']");
    private By wardDropdown = By.xpath("//select[@id='js-ward-holder']");
    private By addressField = By.xpath("(//textarea[@id='address'])[1]");

    private By btnSave = By.xpath("(//span[contains(text(),'Lưu thông tin')])[1]");
    private By updateSuccessMessage = By.xpath("//div[contains(text(),'Cập nhật thành công')]");

    // METHODS

    @Step("Mở trang Thông tin tài khoản")
    public void openProfilePage() {
        stepWithScreenshot("Mở trang Thông tin tài khoản", () -> {
            click(accountMenu);
            click(profileInfoMenu);
        });
    }

    // Thêm tham số province vào Method
    @Step("Thực hiện cập nhật thông tin")
    public void updateProfileInfo(String fullName, String mobile, String province, String district, String ward,
            String address) {
        stepWithScreenshot("Cập nhật thông tin cá nhân", () -> {

            clearAndType(fullNameField, fullName);

            try {
                WebElement mobileEle = waitForElement(mobileField);
                if (mobileEle.isEnabled() && mobileEle.getAttribute("readonly") == null) {
                    mobileEle.clear();
                    mobileEle.sendKeys(mobile);
                } else {
                    LogUtil.info("Mobile field is disabled or readonly. Skipping mobile update.");
                }
            } catch (Exception e) {
                LogUtil.warn("Could not update mobile field: " + e.getMessage());
            }

            // 1. CHỌN TỈNH / THÀNH PHỐ
            if (province != null && !province.isEmpty()) {
                WebElement provinceEle = wait.until(ExpectedConditions.visibilityOfElementLocated(provinceDropdown));
                Select provinceSelect = new Select(provinceEle);

                for (WebElement option : provinceSelect.getOptions()) {
                    // Dùng trim() để loại bỏ khoảng trắng dư thừa trong HTML (ví dụ: " TP HCM ")
                    if (option.getText().trim().contains(province)) {
                        provinceSelect.selectByVisibleText(option.getText());
                        LogUtil.info("Selected province: " + option.getText().trim());
                        break;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                } // Đợi API load Quận/Huyện
            }

            // 2. CHỌN QUẬN / HUYỆN
            WebElement districtEle = wait.until(ExpectedConditions.elementToBeClickable(districtDropdown));
            new Select(districtEle).selectByValue(district);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            } // Đợi API load Phường/Xã

            // 3. CHỌN PHƯỜNG / XÃ
            WebElement wardEle = wait.until(ExpectedConditions.elementToBeClickable(wardDropdown));
            new Select(wardEle).selectByValue(ward);

            // 4. ĐIỀN ĐỊA CHỈ & LƯU
            clearAndType(addressField, address);
            clickByJS(driver.findElement(btnSave));
        });
    }

    @Step("Kiểm tra hiển thị thông báo thành công")
    public boolean verifyUpdateSuccess() {
        boolean isDisplayed = isElementDisplayed(updateSuccessMessage);
        if (isDisplayed) {
            takeScreenshot("Thông báo thành công đã hiển thị");
        } else {
            takeScreenshot("Thông báo thành công chưa hiển thị");
        }
        return isDisplayed;
    }
}
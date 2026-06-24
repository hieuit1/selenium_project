package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import utils.LogUtil;

public class ProductDetailPage extends BasePage {

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================
    private By btnWriteReview = By.id("js-show-review");
    private By modalReview = By.id("show-form");
    private By star5Rating = By.cssSelector("label[for='rating-input-review-0-5']");
    private By star4Rating = By.cssSelector("label[for='rating-input-review-0-4']");
    private By txtContent = By.id("content0");
    private By txtName = By.id("name0");
    private By txtPhone = By.id("phone0");
    private By btnSubmitReview = By.cssSelector("#show-form a.btn-submit");
    private By btnCloseReview = By.cssSelector("#show-form .close-review");

    private By contentError = By.xpath("//textarea[@id='content0']/following-sibling::div[contains(@class,'note-error')]");
    private By nameError = By.xpath("//input[@id='name0']/following-sibling::div[contains(@class,'note-error')]");
    private By phoneError = By.xpath("//input[@id='phone0']/following-sibling::div[contains(@class,'note-error')]");

    // ================= ACTIONS =================
    @Step("Cuộn xuống và click nút Viết đánh giá")
    public void openReviewModal() {
        scrollToElement(btnWriteReview);
        jsClick(btnWriteReview);
    }

    @Step("Kiểm tra Modal đánh giá hiển thị")
    public boolean isReviewModalDisplayed() {
        return isElementDisplayed(modalReview);
    }

    @Step("Điền thông tin đánh giá: {stars} sao, nội dung: {content}, tên: {name}, sđt: {phone}")
    public void fillReviewForm(int stars, String content, String name, String phone) {
        if (stars == 5) {
            jsClick(star5Rating);
        } else if (stars == 4) {
            jsClick(star4Rating);
        } // Thêm các sao khác nếu cần thiết
        
        type(txtContent, content);
        type(txtName, name);
        type(txtPhone, phone);
    }

    @Step("Click Gửi đánh giá")
    public void submitReview() {
        click(btnSubmitReview);
    }

    @Step("Đóng modal đánh giá")
    public void closeReviewModal() {
        jsClick(btnCloseReview);
    }

    @Step("Lấy thông báo lỗi của trường Nội dung")
    public String getContentErrorText() {
        return getText(contentError);
    }

    @Step("Lấy thông báo lỗi của trường Họ và tên")
    public String getNameErrorText() {
        return getText(nameError);
    }

    @Step("Lấy thông báo lỗi của trường Số điện thoại")
    public String getPhoneErrorText() {
        return getText(phoneError);
    }

    @Step("Lấy văn bản của hộp thoại Alert (nếu có)")
    public String getAlertTextAndAccept() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            LogUtil.info("Alert displayed: " + text);
            alert.accept();
            return text;
        } catch (Exception e) {
            LogUtil.error("No alert found or timed out: " + e.getMessage());
            return "";
        }
    }
}


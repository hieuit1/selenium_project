package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.ReviewData;
import dataproviders.ReviewDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import pages.ProductDetailPage;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class ReviewTest extends BaseTest {

    private ProductDetailPage productPage;

    @BeforeMethod
    public void setUpPage() {
        DriverFactory.getDriver()
                .get("https://www.tncstore.vn/card-man-hinh-gigabyte-aorus-geforce-rtx-5070-ti-master-16g.html");
        productPage = new ProductDetailPage(DriverFactory.getDriver());
    }

    @Test(
        dataProvider = "positiveReviewData", 
        dataProviderClass = ReviewDataProvider.class, 
        groups = {"smoke", "regression", "positive", "priority:high"}
    )
    @Description("Kiểm thử luồng tích cực (Positive): Gửi đánh giá sản phẩm thành công với thông tin hợp lệ")
    @Severity(SeverityLevel.CRITICAL)
    public void testSendReviewSuccessfully(ReviewData reviewData) {
        Allure.getLifecycle().updateTestCase(t -> t.setName(reviewData.testDescription));

        productPage.openReviewModal();
        Assert.assertTrue(productPage.isReviewModalDisplayed(), "Modal đánh giá không hiển thị!");

        productPage.fillReviewForm(reviewData.stars, reviewData.content, reviewData.name, reviewData.phone);
        productPage.submitReview();

        // Kiểm tra và accept alert thành công
        String alertText = productPage.getAlertTextAndAccept();
        Assert.assertEquals(alertText, reviewData.expectedErrorMessage, "Thông điệp Alert thành công không khớp!");
    }

    @Test(
        dataProvider = "negativeReviewData", 
        dataProviderClass = ReviewDataProvider.class, 
        groups = {"regression", "negative", "priority:medium"}
    )
    @Description("Kiểm thử luồng tiêu cực (Negative): Kiểm tra các lỗi xác thực dữ liệu khi gửi đánh giá")
    @Severity(SeverityLevel.NORMAL)
    public void testSendReviewValidationErrors(ReviewData reviewData) {
        Allure.getLifecycle().updateTestCase(t -> t.setName(reviewData.testDescription));

        productPage.openReviewModal();
        Assert.assertTrue(productPage.isReviewModalDisplayed(), "Modal đánh giá không hiển thị!");

        productPage.fillReviewForm(reviewData.stars, reviewData.content, reviewData.name, reviewData.phone);
        productPage.submitReview();

        // Kiểm tra và accept alert cảnh báo lỗi
        String alertText = productPage.getAlertTextAndAccept();
        Assert.assertEquals(alertText, "Vui lòng kiểm tra lại thông tin đánh giá", "Cảnh báo lỗi chung bằng alert không hiển thị hoặc không chính xác!");

        // Kiểm tra thông báo lỗi cụ thể hiển thị ngay dưới trường dữ liệu tương ứng
        if ("content".equalsIgnoreCase(reviewData.expectedErrorField)) {
            String contentError = productPage.getContentErrorText();
            Assert.assertEquals(contentError, reviewData.expectedErrorMessage, "Lỗi hiển thị ở trường Nội dung không khớp!");
        } else if ("name".equalsIgnoreCase(reviewData.expectedErrorField)) {
            String nameError = productPage.getNameErrorText();
            Assert.assertEquals(nameError, reviewData.expectedErrorMessage, "Lỗi hiển thị ở trường Họ tên không khớp!");
        } else if ("phone".equalsIgnoreCase(reviewData.expectedErrorField)) {
            String phoneError = productPage.getPhoneErrorText();
            Assert.assertEquals(phoneError, reviewData.expectedErrorMessage, "Lỗi hiển thị ở trường Số điện thoại không khớp!");
        }
        
        // Đóng form đánh giá để hoàn thành test case
        productPage.closeReviewModal();
    }
}


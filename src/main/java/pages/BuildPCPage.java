package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import utils.LogUtil;

public class BuildPCPage extends BasePage {

    private final By modalPopup = By.id("js-modal-popup");
    private final By searchInput = By.id("buildpc-search-keyword");
    private final By searchButton = By.id("js-buildpc-search-btn");
    private final By selectProductBtn = By.xpath("(//a[contains(@class, 'js-select-product')])[1]");
    private final By addBuildToCartBtn = By.cssSelector("a.add-buildpc");

    public BuildPCPage(WebDriver driver) {
        super(driver);
    }

    @Step("Mở trang Build PC")
    public void openBuildPCPage() {
        driver.get("https://www.tncstore.vn/buildpc");
        waitForElement(addBuildToCartBtn);
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Step("Chọn linh kiện cho danh mục ID: {categoryId} với từ khóa: {keyword}")
    public void selectPart(int categoryId, String keyword) {
        String categoryName = "";
        switch (categoryId) {
            case 1: categoryName = "Bộ vi xử lý"; break;
            case 2: categoryName = "Bo mạch chủ"; break;
            case 3: categoryName = "Bộ nhớ trong"; break;
            default: categoryName = "Bộ vi xử lý";
        }
        
        By selectBtnLocator = By.cssSelector("#js-category-info-" + categoryId + " .btn-select, #js-category-info-" + categoryId + " a, #js-category-info-" + categoryId + " span, #js-buildpc-layout > *:nth-child(" + categoryId + ") span");
        
        LogUtil.info("Click chọn linh kiện danh mục ID: " + categoryId);
        scrollToElement(selectBtnLocator);
        try {
            click(selectBtnLocator);
        } catch (Exception e) {
            jsClick(selectBtnLocator);
        }

        // Chờ modal hiển thị
        waitForElement(modalPopup);

        // Nhập từ khóa tìm kiếm sản phẩm trong modal
        LogUtil.info("Tìm kiếm linh kiện với từ khóa: " + keyword);
        type(searchInput, keyword);
        click(searchButton);

        // Chờ kết quả hiển thị và nút chọn sản phẩm khả dụng
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        waitForClickable(selectProductBtn);

        // Chọn sản phẩm đầu tiên
        LogUtil.info("Click chọn sản phẩm đầu tiên từ kết quả tìm kiếm");
        try {
            click(selectProductBtn);
        } catch (Exception e) {
            jsClick(selectProductBtn);
        }

        // Chờ modal đóng
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalPopup));
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Step("Thêm cấu hình PC vào giỏ hàng")
    public void addBuildToCart() {
        scrollToElement(addBuildToCartBtn);
        try {
            click(addBuildToCartBtn);
        } catch (Exception e) {
            jsClick(addBuildToCartBtn);
        }
        // Chờ chuyển hướng sang trang giỏ hàng
        waitForUrl("cart");
    }

    @Step("Kiểm tra xem trang giỏ hàng đã được tải thành công chưa")
    public boolean isCartPageLoaded() {
        return getCurrentUrl().contains("cart");
    }
}

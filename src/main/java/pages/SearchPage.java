package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private By searchInput = By.xpath("(//input[@id='js-global-seach'])[1]");
    private By searchButton = By.xpath("(//span[contains(text(),'Tìm kiếm')])[1]");
    private By categoryDropdown = By.xpath("(//a[@id='js-search-title'])[1]");
    private By pcGamingCategory = By.xpath("(//a[contains(text(),'PC Gaming')])[1]");
    private By searchResultContainer = By.xpath("(//div[@class='total-product'])[1]");

    // Đã XÓA biến noResultMessage bị hardcode text ở đây

    public void enterSearchKeyword(String keyword) {
        type(searchInput, keyword);
    }

    public void clickSearchButton() {
        jsClick(searchButton);
    }

    public void selectCategoryPCGaming() {
        stepWithScreenshot("Chọn danh mục PC Gaming", () -> {
            jsClick(categoryDropdown);
            jsClick(pcGamingCategory);
        });
    }

    public void performSearch(String keyword) {
        stepWithScreenshot("Thực hiện tìm kiếm với từ khóa: '" + keyword + "'", () -> {
            enterSearchKeyword(keyword);
            clickSearchButton();
        });
    }

    public boolean isSearchResultDisplayed() {
        return isElementDisplayed(searchResultContainer);
    }

    // [THÊM MỚI] Dùng Dynamic XPath để tìm chính xác thông báo lỗi mong muốn
    public String getDynamicErrorMessage(String expectedErrorMsg) {
        // Tạo xpath động dựa vào text truyền vào
        By dynamicErrorLocator = By.xpath("//*[contains(text(),'" + expectedErrorMsg + "')]");

        if (isElementDisplayed(dynamicErrorLocator)) {
            return getText(dynamicErrorLocator);
        }
        return "";
    }
}
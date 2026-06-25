package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import utils.LogUtil;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================
    private By pcGamingCategory = By.xpath("//span[@class='txt' and @alt='PC Gaming']");
    private By product05 = By
            .xpath("(//a[@class='product-name line-clamp-2'][normalize-space()='PC Gaming TNC Turbo Max - 05'])[1]");
    private By product04 = By
            .xpath("(//a[@class='product-name line-clamp-2'][normalize-space()='PC Gaming TNC Turbo Max - 04'])[1]");
    private By product03 = By.xpath("(//a[normalize-space()='PC Gaming TNC Turbo Pro - 03'])[1]");
    private By buyNowBtn = By.xpath("(//a[@class='buy-go-cart btn-buyNow js-buyNow'])[1]");
    private By cartContainer = By.xpath("(//div[@class='background-white'])[1]");
    private By deleteBtn = By.cssSelector("a.delete-item-cart, a.js-delete-item");
    private By emptyCartMessage = By.xpath("(//p[@class='text-18 font-600'])[1]");
    private By backToHomeBtn = By.xpath("(//a[contains(text(),'Quay lại trang chủ')])[1]");
    private By txtQuantity = By.cssSelector("input.js-buy-quantity");
    private By btnPlus = By.cssSelector("a.js-quantity-change[data-value='1']");
    private By btnMinus = By.cssSelector("a.js-quantity-change[data-value='-1']");
    private By txtVoucher = By.id("js_voucher_input");
    private By voucherErrorMsg = By.xpath(
            "//div[contains(text(),'Lỗi xảy ra') or contains(text(),'khuyến mại') or contains(text(),'hết hạn')]");

    // ================= ACTIONS =================
    @Step("Click on Category: {categoryName}")
    public void clickCategory(String categoryName) {
        stepWithScreenshot("Click on Category: " + categoryName, () -> {
            By categoryLocator = By.xpath("//span[@class='txt' and @alt='" + categoryName + "']");
            scrollToElement(categoryLocator);
            jsClick(categoryLocator);
        });
    }

    @Step("Click on Product: {productName}")
    public void clickProductByName(String productName) {
        stepWithScreenshot("Click on Product: " + productName, () -> {
            By productLocator = By.xpath("(//a[contains(@class,'product-name') and normalize-space()='" + productName
                    + "'] | //a[normalize-space()='" + productName + "'])[1]");
            scrollToElement(productLocator);
            jsClick(productLocator);
        });
    }

    @Step("Click on PC Gaming category")
    public void clickPcGamingCategory() {
        stepWithScreenshot("Click on PC Gaming category", () -> {
            scrollToElement(pcGamingCategory);
            jsClick(pcGamingCategory);
        });
    }

    @Step("Click on Product: PC Gaming TNC Turbo Max - 05")
    public void clickProduct05() {
        stepWithScreenshot("Click on Product: PC Gaming TNC Turbo Max - 05", () -> {
            scrollToElement(product05);
            jsClick(product05);
        });
    }

    @Step("Click on Product: PC Gaming TNC Turbo Max - 04")
    public void clickProduct04() {
        stepWithScreenshot("Click on Product: PC Gaming TNC Turbo Max - 04", () -> {
            scrollToElement(product04);
            jsClick(product04);
        });
    }

    @Step("Click on Product: PC Gaming TNC Turbo Pro - 03")
    public void clickProduct03() {
        stepWithScreenshot("Click on Product: PC Gaming TNC Turbo Pro - 03", () -> {
            scrollToElement(product03);
            jsClick(product03);
        });
    }



        @Step("Click on Buy Now (Add to cart)")
    private void clickBuyNow() {
        stepWithScreenshot("Click on Buy Now (Add to cart)", () -> {
            scrollToElement(buyNowBtn);
            jsClick(buyNowBtn);
        });
    }

    /**
     * Adds a single product to the cart by selecting the category and product name.
     */
    @Step("Add product to cart: {category}/{productName}")
    public void addProductToCart(String category, String productName) {
        clickCategory(category);
        clickProductByName(productName);
        clickBuyNow();
    }

    /**
     * Adds multiple products to the cart for the given category.
     */
    @Step("Add multiple products to cart: {category}")
    public void addMultipleProductsToCart(String category, List<String> productNames) {
        for (String productName : productNames) {
            addProductToCart(category, productName);
        }
    }

    @Step("Check if cart container is displayed")
    public boolean isCartContainerDisplayed() {
        return isElementDisplayed(cartContainer);
    }

    @Step("Click delete button in cart")
    public void clickDelete() {
        stepWithScreenshot("Click delete button in cart", () -> {
            scrollToElement(deleteBtn);
            jsClick(deleteBtn);
            try {
                org.openqa.selenium.Alert alert = wait
                        .until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (Exception e) {
                utils.LogUtil.error("No alert appeared after delete click: " + e.getMessage());
            }
        });
    }

    @Step("Get cart status text (e.g. empty cart message)")
    public String getCartStatusText() {
        return getText(emptyCartMessage);
    }

    @Step("Click Back to Home Page button")
    public void clickBackToHome() {
        stepWithScreenshot("Click Back to Home Page button", () -> {
            try {
                scrollToElement(backToHomeBtn);
                jsClick(backToHomeBtn);
            } catch (Exception e) {
                LogUtil.info("Could not click 'Quay lại trang chủ' link, navigating to home URL directly. Error: "
                        + e.getMessage());
                driver.get("https://www.tncstore.vn/");
            }
        });
    }

    @Step("Update quantity to {quantity}")
    public void updateQuantity(String quantity) {
        stepWithScreenshot("Update quantity to " + quantity, () -> {
            WebElement inputElement = waitForElement(txtQuantity);
            
            boolean isNumber = true;
            int targetQty = 1;
            try {
                targetQty = Integer.parseInt(quantity);
                if (targetQty <= 0) {
                    isNumber = false;
                }
            } catch (NumberFormatException e) {
                isNumber = false;
            }

            if (isNumber) {
                int currentQty = Integer.parseInt(getQuantityValue());
                int attempts = 0;
                while (currentQty != targetQty && attempts < 15) {
                    attempts++;
                    if (currentQty < targetQty) {
                        jsClick(btnPlus);
                    } else {
                        jsClick(btnMinus);
                    }
                    try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                    currentQty = Integer.parseInt(getQuantityValue());
                }
            } else {
                inputElement.click();
                inputElement.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"));
                inputElement.sendKeys(org.openqa.selenium.Keys.DELETE);
                try {
                    inputElement.clear();
                } catch (Exception e) {
                    // Ignore
                }
                inputElement.sendKeys(quantity);
                inputElement.sendKeys(org.openqa.selenium.Keys.ENTER);
                
                try {
                    jsClick(By.xpath("//body"));
                } catch (Exception e) {
                    // Ignore
                }
                
                try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                
                try {
                    org.openqa.selenium.Alert alert = driver.switchTo().alert();
                    LogUtil.info("Accepted alert during negative quantity update: " + alert.getText());
                    alert.accept();
                } catch (org.openqa.selenium.NoAlertPresentException e) {
                    // Ignore
                }
            }
        });
    }

    @Step("Get current quantity value")
    public String getQuantityValue() {
        return getAttribute(txtQuantity, "value");
    }

    @Step("Apply voucher code: {voucherCode}")
    public void applyVoucher(String voucherCode) {
        stepWithScreenshot("Apply voucher code: " + voucherCode, () -> {
            type(txtVoucher, voucherCode);
            driver.findElement(txtVoucher).sendKeys(org.openqa.selenium.Keys.ENTER);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Step("Get voucher error message")
    public String getVoucherErrorMessage() {
        return getText(voucherErrorMsg);
    }

    @Step("Clear all items in cart")
    public void clearCart() {
        try {
            // Dismiss any pre-existing alerts
            try {
                org.openqa.selenium.Alert alert = driver.switchTo().alert();
                LogUtil.info("Dismissing active alert before clearing cart: " + alert.getText());
                alert.accept();
            } catch (Exception e) {
                // No alert
            }

            driver.get("https://www.tncstore.vn/cart");
            
            int attempts = 0;
            while (attempts < 15 && isElementPresent(deleteBtn)) {
                attempts++;
                try {
                    // Click delete directly using Javascript to avoid stepWithScreenshot alert failures
                    WebElement delBtn = driver.findElement(deleteBtn);
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", delBtn);
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", delBtn);
                    
                    // Accept deletion confirmation
                    try {
                        org.openqa.selenium.Alert alert = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
                        alert.accept();
                    } catch (Exception e) {
                        LogUtil.info("No confirmation alert on delete: " + e.getMessage());
                    }
                    
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    
                    // Clear any unexpected alert
                    try {
                        org.openqa.selenium.Alert alert = driver.switchTo().alert();
                        LogUtil.info("Dismissing alert post delete: " + alert.getText());
                        alert.accept();
                    } catch (Exception e) {
                        // No alert
                    }
                } catch (Exception e) {
                    LogUtil.info("Retry deleting item: " + e.getMessage());
                    driver.get("https://www.tncstore.vn/cart");
                }
            }
        } catch (Exception e) {
            LogUtil.error("Error during clearCart: " + e.getMessage());
        }
    }
}

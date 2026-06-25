package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import utils.LogUtil;

/**
 * Page Object for Checkout & Payment flow on tncstore.vn.
 *
 * Flow:
 *   Cart page → click "Đặt hàng" → fills shipping info (if needed)
 *              → selects payment method → confirms order
 *              → verifies order confirmation page
 */
public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // ===================== LOCATORS =====================

    // ── Cart page ────────────────────────────────────────
    /** "Đặt hàng" / "Tiến hành đặt hàng" button on cart page */
    private By btnProceedToCheckout = By.cssSelector("a.button-send-cart");

    // ── Checkout / Shipping info page ────────────────────
    private By inputShippingName    = By.id("buyer_name");
    private By inputShippingPhone   = By.id("buyer_tel");
    private By inputShippingAddress = By.id("buyer_address");

    // ── Payment method selection ──────────────────────────
    /** Generic locator; we click by visible label text */
    private By paymentOptions = By.xpath("//label[contains(@class,'item-radio')]");

    // Payment methods by keyword in label text
    private By paymentCOD          = By.xpath("//label[contains(@class, 'item-radio') and contains(., 'Thanh toán khi nhận hàng')]");
    private By paymentBankTransfer  = By.xpath("//label[contains(@class, 'item-radio') and contains(., 'Thanh toán bằng chuyển khoản')]");

    // ── Confirm order button ──────────────────────────────
    private By btnConfirmOrder = By.cssSelector("button.button-send-cart");

    // ── Order confirmation indicators ─────────────────────
    /** Text that typically appears after a successful order */
    private By orderConfirmationContainer = By.xpath(
            "//div[contains(@class,'order-success') or contains(@class,'thank-you') or contains(@class,'success')]" +
            " | //h1[contains(text(),'Cảm ơn') or contains(text(),'Đặt hàng thành công')]" +
            " | //p[contains(text(),'Đặt hàng thành công') or contains(text(),'thành công')]");

    private By orderIdElement = By.xpath(
            "(//span[contains(@class,'order-id') or contains(text(),'Mã đơn hàng')] | " +
            "//strong[contains(text(),'Mã đơn')] | //td[contains(@class,'order-code')])[1]");

    // ===================== ACTIONS =====================

    @Step("Tiến hành đặt hàng từ giỏ hàng")
    public void proceedToCheckout() {
        stepWithScreenshot("Click Tiến hành đặt hàng", () -> {
            scrollToElement(btnProceedToCheckout);
            jsClick(btnProceedToCheckout);
        });
    }

    @Step("Điền thông tin giao hàng: {name} / {phone}")
    public void fillShippingInfo(String name, String phone, String address) {
        stepWithScreenshot("Điền thông tin giao hàng", () -> {
            if (name != null && !name.isEmpty()) {
                try { type(inputShippingName, name); }
                catch (Exception e) { LogUtil.info("Shipping name field not found, skipping: " + e.getMessage()); }
            }
            if (phone != null && !phone.isEmpty()) {
                try { type(inputShippingPhone, phone); }
                catch (Exception e) { LogUtil.info("Shipping phone field not found, skipping: " + e.getMessage()); }
            }
            if (address != null && !address.isEmpty()) {
                try { type(inputShippingAddress, address); }
                catch (Exception e) { LogUtil.info("Shipping address field not found, skipping: " + e.getMessage()); }
            }
        });
    }

    @Step("Chọn phương thức thanh toán: {method}")
    public void selectPaymentMethod(String method) {
        stepWithScreenshot("Chọn phương thức thanh toán: " + method, () -> {
            try {
                if (method != null && method.toLowerCase().contains("cod")) {
                    jsClick(paymentCOD);
                } else if (method != null && (method.toLowerCase().contains("chuyển khoản")
                        || method.toLowerCase().contains("bank"))) {
                    jsClick(paymentBankTransfer);
                } else {
                    // Generic fallback – click first available payment option
                    WebElement firstOption = driver.findElements(paymentOptions).stream().findFirst().orElse(null);
                    if (firstOption != null) {
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption);
                    }
                }
            } catch (Exception e) {
                LogUtil.info("Payment method selection skipped: " + e.getMessage());
            }
        });
    }

    @Step("Xác nhận đặt hàng")
    public void confirmOrder() {
        stepWithScreenshot("Click Xác nhận đặt hàng", () -> {
            scrollToElement(btnConfirmOrder);
            jsClick(btnConfirmOrder);
        });
    }

    @Step("Kiểm tra trang xác nhận đơn hàng")
    public boolean isOrderConfirmed() {
        try {
            return isElementDisplayed(orderConfirmationContainer);
        } catch (Exception e) {
            LogUtil.error("Order confirmation container not found: " + e.getMessage());
            return false;
        }
    }

    @Step("Lấy mã đơn hàng")
    public String getOrderId() {
        try {
            return getText(orderIdElement);
        } catch (Exception e) {
            LogUtil.info("Order ID element not found: " + e.getMessage());
            return "";
        }
    }

    @Step("Kiểm tra URL chứa từ khoá: {keyword}")
    public boolean isUrlContaining(String keyword) {
        return getCurrentUrl().toLowerCase().contains(keyword.toLowerCase());
    }

    @Step("Kiểm tra trang xác nhận chứa text: {expectedText}")
    public boolean isConfirmationTextPresent(String expectedText) {
        try {
            String pageSource = driver.getPageSource();
            return pageSource != null && pageSource.toLowerCase().contains(expectedText.toLowerCase());
        } catch (Exception e) {
            LogUtil.error("Error reading page source: " + e.getMessage());
            return false;
        }
    }
}

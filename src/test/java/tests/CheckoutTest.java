package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.CheckoutData;
import dataproviders.CheckoutDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listeners.TestListener;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import utils.DriverFactory;

/**
 * ============================================================
 *  CheckoutTest – Positive Checkout & Payment Test Suite
 * ============================================================
 *
 * Coverage:
 *  • Smoke   (P1) – TC-CHK-01  : Single product + COD
 *  • Regression (P2):
 *      TC-CHK-02  : Multiple products + COD
 *      TC-CHK-03  : Single product + Bank Transfer
 *      TC-CHK-04  : Multiple products + different address + COD
 *
 * Strategy: Data-Driven via @DataProvider (CheckoutDataProvider)
 * Groups   : smoke, regression, positive, priority
 * ============================================================
 */
@Listeners(TestListener.class)
@Epic("E-Commerce")
@Feature("Checkout & Payment")
public class CheckoutTest extends BaseTest {

    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage    = new LoginPage(DriverFactory.getDriver());
        cartPage     = new CartPage(DriverFactory.getDriver());
        checkoutPage = new CheckoutPage(DriverFactory.getDriver());
    }

    // ══════════════════════════════════════════════════════════════
    //  POSITIVE CHECKOUT & PAYMENT – Data-Driven
    // ══════════════════════════════════════════════════════════════

    @Test(
        dataProvider      = "positiveCheckoutData",
        dataProviderClass = CheckoutDataProvider.class,
        groups            = { "smoke", "regression", "positive", "priority" },
        description       = "Positive Checkout & Payment – data-driven"
    )
    @Story("Positive Checkout & Payment")
    @Description(
        "Verify positive checkout & payment scenarios:\n" +
        "  1. Login successfully.\n" +
        "  2. Add product(s) to cart.\n" +
        "  3. Proceed to checkout page.\n" +
        "  4. Fill shipping information.\n" +
        "  5. Select payment method.\n" +
        "  6. Confirm the order.\n" +
        "  7. Assert order confirmation is displayed."
    )
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutPositiveFlows(CheckoutData data) {

        // ── Dynamic test name in Allure report ────────────────────
        Allure.getLifecycle().updateTestCase(tc -> tc.setName(data.testDescription));

        // ── Step 1: Login ──────────────────────────────────────────
        Allure.step("Step 1: Đăng nhập với tài khoản: " + data.email, () -> {
            loginPage.clickLoginMenu();
            loginPage.login(data.email, data.password);
            Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Đăng nhập thất bại! Không thể tiến hành test checkout."
            );
        });

        // ── Step 2: Clear cart để đảm bảo trạng thái sạch ────────
        Allure.step("Step 2: Xóa sạch giỏ hàng trước khi test", () -> {
            cartPage.clearCart();
        });

        // ── Step 3: Thêm sản phẩm chính vào giỏ hàng ─────────────
        Allure.step("Step 3: Thêm sản phẩm vào giỏ hàng: " + data.singleProduct, () -> {
            cartPage.addProductToCart(data.category, data.singleProduct);
            Assert.assertTrue(
                cartPage.isCartContainerDisplayed(),
                "Giỏ hàng không hiển thị sau khi thêm sản phẩm: " + data.singleProduct
            );
        });

        // ── Step 4: Thêm các sản phẩm bổ sung (nếu có) ───────────
        if (data.additionalProducts != null && !data.additionalProducts.isEmpty()) {
            Allure.step("Step 4: Thêm " + data.additionalProducts.size() + " sản phẩm bổ sung", () -> {
                cartPage.addMultipleProductsToCart(data.category, data.additionalProducts);
                Assert.assertTrue(
                    cartPage.isCartContainerDisplayed(),
                    "Giỏ hàng không hiển thị sau khi thêm các sản phẩm bổ sung!"
                );
            });
        }

        // ── Step 5: Tiến hành đặt hàng ────────────────────────────
        Allure.step("Step 5: Tiến hành đặt hàng", () -> {
            // Ensure we're on cart page before proceeding
            DriverFactory.getDriver().get("https://www.tncstore.vn/cart");
            try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            checkoutPage.proceedToCheckout();
        });

        // ── Step 6: Điền thông tin giao hàng ─────────────────────
        Allure.step("Step 6: Điền thông tin giao hàng", () -> {
            checkoutPage.fillShippingInfo(
                data.shippingName,
                data.shippingPhone,
                data.shippingAddress
            );
        });

        // ── Step 7: Chọn phương thức thanh toán ──────────────────
        Allure.step("Step 7: Chọn phương thức thanh toán: " + data.paymentMethod, () -> {
            checkoutPage.selectPaymentMethod(data.paymentMethod);
        });

        // ── Step 8: Xác nhận đặt hàng ────────────────────────────
        Allure.step("Step 8: Xác nhận đặt hàng", () -> {
            checkoutPage.confirmOrder();
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        // ── Step 9: Kiểm tra xác nhận đơn hàng thành công ────────
        Allure.step("Step 9: Kiểm tra trang xác nhận đơn hàng", () -> {
            boolean isConfirmed = checkoutPage.isOrderConfirmed()
                || checkoutPage.isUrlContaining("thank")
                || checkoutPage.isUrlContaining("success")
                || checkoutPage.isUrlContaining("order")
                || checkoutPage.isConfirmationTextPresent(data.expectedOrderText);

            Assert.assertTrue(
                isConfirmed,
                "Đặt hàng thất bại! Không tìm thấy trang xác nhận đơn hàng thành công.\n" +
                "URL hiện tại: " + DriverFactory.getDriver().getCurrentUrl()
            );
        });
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import data.CartData;
import dataproviders.CartDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import pages.LoginPage;
import pages.CartPage;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(DriverFactory.getDriver());
        cartPage = new CartPage(DriverFactory.getDriver());
    }

    @Test(dataProvider = "positiveCartData", dataProviderClass = CartDataProvider.class, groups = { "smoke",
            "regression", "positive" })
    @Description("Verify positive scenarios on Cart: adding single/multiple products, updating to valid quantities.")
    @Severity(SeverityLevel.CRITICAL)
    public void testCartPositiveFlows(CartData cartData) {
        Allure.getLifecycle().updateTestCase(t -> t.setName(cartData.testDescription));

        // Step 1: Login
        loginPage.clickLoginMenu();
        loginPage.login(cartData.email, cartData.password);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Đăng nhập thất bại trước khi thực hiện test case!");

        // Step 1b: Clear cart to ensure clean state
        cartPage.clearCart();

        cartPage.addProductToCart(cartData.category, cartData.singleProduct);
        Assert.assertTrue(cartPage.isCartContainerDisplayed(), "Không tìm thấy giỏ hàng sau khi thêm sản phẩm!");

        // Step 3: Handle specific scenario based on dataset
        if (cartData.multipleProducts != null) {
            // Add multiple products and then add single product again
            cartPage.addMultipleProductsToCart(cartData.category, cartData.multipleProducts);
            cartPage.addProductToCart(cartData.category, cartData.singleProduct);
            Assert.assertTrue(cartPage.isCartContainerDisplayed(),
                    "Không hiển thị giỏ hàng sau khi thêm nhiều sản phẩm!");
        } else if (cartData.targetQuantity != null) {
            // Update quantity
            cartPage.updateQuantity(cartData.targetQuantity);
            String actualQty = cartPage.getQuantityValue();
            Assert.assertEquals(actualQty, cartData.expectedQuantity, "Số lượng sản phẩm không cập nhật đúng!");
        }
    }


}

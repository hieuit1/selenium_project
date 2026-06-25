package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.E2EData;
import dataproviders.E2EDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listeners.TestListener;
import pages.E2EPage;
import utils.DriverFactory;

@Listeners(TestListener.class)
@Epic("E-Commerce")
@Feature("End-to-End Flow")
public class E2ETest extends BaseTest {

    private E2EPage e2ePage;

    @BeforeMethod
    public void setUpPages() {
        e2ePage = new E2EPage(DriverFactory.getDriver());
    }

    @Test(
        dataProvider      = "positiveE2EData",
        dataProviderClass = E2EDataProvider.class,
        groups            = { "e2e", "regression" },
        description       = "E2E: Login -> Update Profile -> Search & Add Product -> Checkout with prefilled info"
    )
    @Story("Complete Purchase Flow")
    @Description(
        "Verify complete End-to-End flow:\n" +
        "  1. Login successfully.\n" +
        "  2. Update Shipping Profile details.\n" +
        "  3. Search for a product.\n" +
        "  4. Add the product to cart.\n" +
        "  5. Proceed to checkout.\n" +
        "  6. Verify profile info is prefilled on Checkout page.\n" +
        "  7. Complete order via COD."
    )
    @Severity(SeverityLevel.BLOCKER)
    public void testE2EOrderFlow(E2EData data) {
        
        // ── Dynamic test name in Allure report ────────────────────
        Allure.getLifecycle().updateTestCase(tc -> tc.setName(data.testDescription));

        // ── Execute E2E Purchase Flow ─────────────────────────────
        e2ePage.executeE2EPurchaseFlow(
            data.email,
            data.password,
            data.fullName,
            data.mobile,
            data.province,
            data.districtValue,
            data.wardValue,
            data.address,
            data.searchKeyword,
            data.paymentMethod
        );

        // ── Step 8: Assert order confirmation ──────────────────────
        Allure.step("Step 8: Xác nhận đơn hàng thành công", () -> {
            boolean isConfirmed = e2ePage.isOrderConfirmed(data.expectedOrderText);

            Assert.assertTrue(
                isConfirmed,
                "Đặt hàng E2E thất bại! Không tìm thấy trang xác nhận đơn hàng thành công.\n" +
                "URL hiện tại: " + DriverFactory.getDriver().getCurrentUrl()
            );
        });
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.BuildPCData;
import dataproviders.BuildPCDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listeners.TestListener;
import pages.BuildPCPage;
import utils.DriverFactory;

@Listeners(TestListener.class)
@Epic("E-Commerce")
@Feature("Build PC Flow")
public class BuildPCTest extends BaseTest {

    private BuildPCPage buildPCPage;

    @BeforeMethod
    public void setUpPage() {
        buildPCPage = new BuildPCPage(DriverFactory.getDriver());
    }

    @Test(
        dataProvider      = "buildPCData",
        dataProviderClass = BuildPCDataProvider.class,
        groups            = { "buildpc", "regression" },
        description       = "Build PC: Select CPU -> Select Mainboard -> Select RAM -> Add to Cart"
    )
    @Story("Build Custom PC")
    @Description("Verify that a user can build a custom PC by choosing CPU, Mainboard, RAM and adding to cart successfully.")
    @Severity(SeverityLevel.CRITICAL)
    public void testBuildPCAndAddToCart(BuildPCData data) {
        
        // Cập nhật tên test trong báo cáo Allure
        Allure.getLifecycle().updateTestCase(tc -> tc.setName(data.testDescription));

        // Bước 1: Mở trang Build PC
        buildPCPage.openBuildPCPage();

        // Bước 2: Chọn CPU
        buildPCPage.selectPart(1, data.cpuKeyword);

        // Bước 3: Chọn Mainboard
        buildPCPage.selectPart(2, data.mainboardKeyword);

        // Bước 4: Chọn RAM
        buildPCPage.selectPart(3, data.ramKeyword);

        // Bước 5: Thêm cấu hình vào giỏ hàng
        buildPCPage.addBuildToCart();

        // Bước 6: Xác nhận chuyển hướng đến giỏ hàng thành công
        Assert.assertTrue(
            buildPCPage.isCartPageLoaded(),
            "Không chuyển hướng được tới trang giỏ hàng sau khi thêm cấu hình PC!\n" +
            "URL hiện tại: " + DriverFactory.getDriver().getCurrentUrl()
        );
    }
}

package tests;

import dataproviders.SearchDataProvider;
import io.qameta.allure.Allure; // Bắt buộc import thư viện Allure
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.SearchData;
import pages.SearchPage;
import listeners.TestListener;
import utils.DriverFactory;
import utils.LogUtil;

@Listeners(TestListener.class)
public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    @BeforeMethod
    public void setUpPage() {

        searchPage = new SearchPage(DriverFactory.getDriver());
    }

    @Test(dataProvider = "positiveSearchData", dataProviderClass = SearchDataProvider.class, groups = {"smoke", "regression", "positive", "priority:medium"})
    @Description("Verify that search returns matching results for valid search keywords")
    @Severity(SeverityLevel.NORMAL)
    public void testPositiveSearch(SearchData data) {

        Allure.getLifecycle().updateTestCase(t -> t.setName("Positive Search: " + data.testDescription));

        LogUtil.info("Bắt đầu test: " + data.testDescription + " | Keyword: '" + data.keyword + "'");

        searchPage.performSearch(data.keyword);

        boolean isDisplayed = searchPage.isSearchResultDisplayed();
        Assert.assertTrue(isDisplayed, "Lỗi: Không hiển thị kết quả tìm kiếm cho từ khóa hợp lệ: " + data.keyword);
    }

    @Test(dataProvider = "negativeSearchData", dataProviderClass = SearchDataProvider.class, groups = {"regression", "negative", "priority:low"})
    @Description("Verify that searching with invalid keywords displays the correct no-results message")
    @Severity(SeverityLevel.MINOR)
    public void testNegativeSearch(SearchData data) {

        Allure.getLifecycle().updateTestCase(t -> t.setName("Negative Search: " + data.testDescription));

        LogUtil.info("Bắt đầu test: " + data.testDescription + " | Keyword: '" + data.keyword + "'");

        searchPage.performSearch(data.keyword);

        String actualMessage = searchPage.getDynamicErrorMessage(data.expectedErrorField);

        Assert.assertTrue(actualMessage.contains(data.expectedErrorField),
                "Kết quả không khớp. \nThực tế không tìm thấy thông báo: " + data.expectedErrorField);
    }
}
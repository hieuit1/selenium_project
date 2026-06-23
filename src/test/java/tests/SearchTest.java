package tests;

import dataproviders.SearchData;
import dataproviders.SearchDataProvider;
import io.qameta.allure.Allure; // Bắt buộc import thư viện Allure
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SearchPage;
import listeners.TestListener;
import utils.LogUtil;

@Listeners(TestListener.class)
public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    @BeforeMethod
    public void setUpPage() {
        searchPage = new SearchPage(driver);
    }

    @Test(dataProvider = "positiveSearchData", dataProviderClass = SearchDataProvider.class)
    public void testPositiveSearch(SearchData data) {

        Allure.getLifecycle().updateTestCase(t -> t.setName("Positive Search: " + data.testDescription));

        LogUtil.info("Bắt đầu test: " + data.testDescription + " | Keyword: '" + data.keyword + "'");

        searchPage.performSearch(data.keyword);

        boolean isDisplayed = searchPage.isSearchResultDisplayed();
        Assert.assertTrue(isDisplayed, "Lỗi: Không hiển thị kết quả tìm kiếm cho từ khóa hợp lệ: " + data.keyword);
    }

    @Test(dataProvider = "negativeSearchData", dataProviderClass = SearchDataProvider.class)
    public void testNegativeSearch(SearchData data) {

        Allure.getLifecycle().updateTestCase(t -> t.setName("Negative Search: " + data.testDescription));

        LogUtil.info("Bắt đầu test: " + data.testDescription + " | Keyword: '" + data.keyword + "'");

        searchPage.performSearch(data.keyword);

        String actualMessage = searchPage.getDynamicErrorMessage(data.expectedErrorField);

        Assert.assertTrue(actualMessage.contains(data.expectedErrorField),
                "Kết quả không khớp. \nThực tế không tìm thấy thông báo: " + data.expectedErrorField);
    }
}
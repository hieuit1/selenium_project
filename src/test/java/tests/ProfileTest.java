package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import data.ProfileData;
import dataproviders.ProfileDataProvider;
import io.qameta.allure.Allure;
import listeners.TestListener;
import pages.LoginPage;
import pages.ProfilePage;
import utils.DriverFactory;

@Listeners(TestListener.class)
public class ProfileTest extends BaseTest {
    private ProfilePage profilePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        profilePage = new ProfilePage(DriverFactory.getDriver());
        loginPage = new LoginPage(DriverFactory.getDriver());

        loginPage.clickLoginMenu();
        loginPage.login("test@gmail.com", "testweb123");
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed before test!");
    }

    @Test(dataProvider = "validProfileData", dataProviderClass = ProfileDataProvider.class)
    public void testUpdateProfile(ProfileData profileData) {

        Allure.getLifecycle().updateTestCase(t -> t.setName("Test update profile: " + profileData.testDescription));
        profilePage.openProfilePage();

        // Truyền thêm profileData.province vào đây
        profilePage.updateProfileInfo(
                profileData.fullName,
                profileData.mobile,
                profileData.province, // Tham số province
                profileData.districtValue,
                profileData.wardValue,
                profileData.address);

        Assert.assertTrue(profilePage.verifyUpdateSuccess(), "Update profile failed");
    }
}
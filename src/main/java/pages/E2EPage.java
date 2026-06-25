package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import utils.LogUtil;

public class E2EPage extends BasePage {

    private LoginPage loginPage;
    private ProfilePage profilePage;
    private SearchPage searchPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public E2EPage(WebDriver driver) {
        super(driver);
        this.loginPage = new LoginPage(driver);
        this.profilePage = new ProfilePage(driver);
        this.searchPage = new SearchPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    @Step("Thực hiện đăng nhập")
    public void login(String email, String password) {
        loginPage.clickLoginMenu();
        loginPage.login(email, password);
    }

    @Step("Kiểm tra đăng nhập thành công")
    public boolean isLoginSuccessful() {
        return loginPage.isLoginSuccessful();
    }

    @Step("Cập nhật thông tin tài khoản")
    public void updateProfile(String fullName, String mobile, String province, String district, String ward, String address) {
        profilePage.openProfilePage();
        profilePage.updateProfileInfo(fullName, mobile, province, district, ward, address);
    }

    @Step("Xác nhận cập nhật thông tin tài khoản thành công")
    public boolean verifyProfileUpdateSuccess(String fullName, String address) {
        return profilePage.verifyUpdateSuccess(fullName, address);
    }

    @Step("Xóa sạch giỏ hàng")
    public void clearCart() {
        cartPage.clearCart();
    }

    @Step("Tìm kiếm sản phẩm: {productName}")
    public void searchProduct(String productName) {
        searchPage.performSearch(productName);
    }

    @Step("Chọn sản phẩm từ kết quả tìm kiếm: {productName}")
    public void selectProduct(String productName) {
        cartPage.clickProductByName(productName);
    }

    @Step("Kiểm tra kết quả tìm kiếm hiển thị")
    public boolean isSearchResultDisplayed() {
        return searchPage.isSearchResultDisplayed();
    }

    @Step("Thêm sản phẩm đang xem vào giỏ hàng")
    public void addProductToCart() {
        By buyNowBtn = By.xpath("(//a[@class='buy-go-cart btn-buyNow js-buyNow'])[1]");
        scrollToElement(buyNowBtn);
        jsClick(buyNowBtn);
    }

    @Step("Thực hiện quy trình mua sắm E2E")
    public void executeE2EPurchaseFlow(
            String email,
            String password,
            String fullName,
            String mobile,
            String province,
            String district,
            String ward,
            String address,
            String searchKeyword,
            String paymentMethod) {
        // Step 1: Login
        login(email, password);

        // Step 2: Update Profile
        updateProfile(fullName, mobile, province, district, ward, address);

        // Step 3: Clear Cart
        clearCart();

        // Step 4: Search product
        searchProduct(searchKeyword);

        // Step 5: Select product to view details
        selectProduct(searchKeyword);

        // Step 6: Add to Cart
        addProductToCart();

        // Step 7: Proceed to Checkout
        proceedToCheckout();

        // Step 8: Verify prefilled info
        String prefilledName = getCheckoutPrefilledName();
        String prefilledPhone = getCheckoutPrefilledPhone();
        String prefilledAddress = getCheckoutPrefilledAddress();
        LogUtil.info("Prefilled checkout - Tên: " + prefilledName + ", SĐT: " + prefilledPhone + ", Địa chỉ: " + prefilledAddress);
        if (prefilledName == null || prefilledName.isEmpty()) {
            fillShippingInfoFallback(fullName, mobile, address);
        }

        // Step 9: Select payment method & confirm
        selectPaymentMethod(paymentMethod);
        confirmOrder();
    }

    @Step("Kiểm tra giỏ hàng hiển thị")
    public boolean isCartContainerDisplayed() {
        return cartPage.isCartContainerDisplayed();
    }

    @Step("Tiến hành đặt hàng từ giỏ hàng")
    public void proceedToCheckout() {
        driver.get("https://www.tncstore.vn/cart");
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        checkoutPage.proceedToCheckout();
    }

    @Step("Lấy thông tin tự điền trên Checkout")
    public String getCheckoutPrefilledName() {
        return checkoutPage.getAttribute(By.id("buyer_name"), "value");
    }

    @Step("Lấy số điện thoại tự điền trên Checkout")
    public String getCheckoutPrefilledPhone() {
        return checkoutPage.getAttribute(By.id("buyer_tel"), "value");
    }

    @Step("Lấy địa chỉ tự điền trên Checkout")
    public String getCheckoutPrefilledAddress() {
        return checkoutPage.getAttribute(By.id("buyer_address"), "value");
    }

    @Step("Điền thông tin giao hàng dự phòng")
    public void fillShippingInfoFallback(String name, String phone, String address) {
        checkoutPage.fillShippingInfo(name, phone, address);
    }

    @Step("Chọn phương thức thanh toán: {method}")
    public void selectPaymentMethod(String method) {
        checkoutPage.selectPaymentMethod(method);
    }

    @Step("Xác nhận đặt hàng")
    public void confirmOrder() {
        checkoutPage.confirmOrder();
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Step("Xác nhận đặt hàng thành công")
    public boolean isOrderConfirmed(String expectedText) {
        return checkoutPage.isOrderConfirmed()
            || checkoutPage.isUrlContaining("thank")
            || checkoutPage.isUrlContaining("success")
            || checkoutPage.isUrlContaining("order")
            || checkoutPage.isConfirmationTextPresent(expectedText);
    }
}

package dataproviders;

import org.testng.annotations.DataProvider;
import data.CheckoutData;
import java.util.Arrays;

public class CheckoutDataProvider {

    @DataProvider(name = "positiveCheckoutData")
    public static Object[][] getPositiveCheckoutData() {
        return new Object[][] {

                // ── TC-CHK-01: Single product, COD payment [SMOKE] ──────────────────────────
                { new CheckoutData(
                        "test@gmail.com",
                        "testweb123",
                        "PC Gaming",
                        "PC Gaming TNC Turbo Max - 05",
                        // shipping info
                        "Nguyen Van A",
                        "0901234567",
                        "123 Nguyen Trai, Q.1, TP.HCM",
                        // payment & expected result
                        "COD",
                        "thành công",
                        "[P1][Smoke] TC-CHK-01: Checkout 1 sản phẩm với thanh toán COD thành công") },

                // ── TC-CHK-02: Multiple products, COD payment [REGRESSION] ──────────────────
                { new CheckoutData(
                        "test@gmail.com",
                        "testweb123",
                        "PC Gaming",
                        "PC Gaming TNC Turbo Max - 05",
                        Arrays.asList("PC Gaming TNC Turbo Max - 04"),
                        // shipping info
                        "Tran Thi B",
                        "0912345678",
                        "456 Le Loi, Q.3, TP.HCM",
                        // payment & expected result
                        "COD",
                        "thành công",
                        "[P2][Regression] TC-CHK-02: Checkout nhiều sản phẩm với thanh toán COD thành công") },

                // ── TC-CHK-03: Single product, Bank Transfer payment [REGRESSION]
                // ─────────────
                { new CheckoutData(
                        "test@gmail.com",
                        "testweb123",
                        "PC Gaming",
                        "PC Gaming TNC Turbo Pro - 03",
                        // shipping info
                        "Le Van C",
                        "0987654321",
                        "789 Pham Ngu Lao, Q.1, TP.HCM",
                        // payment & expected result
                        "Chuyen khoan",
                        "thành công",
                        "[P2][Regression] TC-CHK-03: Checkout 1 sản phẩm với thanh toán chuyển khoản thành công") },

                // ── TC-CHK-04: Multiple products + different shipping address [REGRESSION] ───
                { new CheckoutData(
                        "test@gmail.com",
                        "testweb123",
                        "PC Gaming",
                        "PC Gaming TNC Turbo Max - 04",
                        Arrays.asList("PC Gaming TNC Turbo Pro - 03"),
                        // shipping info
                        "Pham Thi D",
                        "0978888999",
                        "101 Tran Hung Dao, Q.5, TP.HCM",
                        // payment & expected result
                        "COD",
                        "thành công",
                        "[P2][Regression] TC-CHK-04: Checkout nhiều sản phẩm, địa chỉ giao hàng khác nhau, thanh toán COD") },
        };
    }
}

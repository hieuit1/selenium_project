package dataproviders;

import org.testng.annotations.DataProvider;
import data.E2EData;

public class E2EDataProvider {

    @DataProvider(name = "positiveE2EData")
    public static Object[][] getPositiveE2EData() {
        return new Object[][] {
            { new E2EData(
                "test@gmail.com",
                "testweb123",
                "Name HCM E2E",
                "0987654321",
                "TP HCM",
                "31", // Quận 1
                "581", // Phường Đa Kao
                "123 Duong Nguyen Du, Quan 1",
                "PC Gaming TNC Turbo Max - 05",
                "COD",
                "thành công",
                "TC-E2E-01: End-to-End flow: Login -> Update Profile -> Search -> Checkout COD"
            )}
        };
    }
}

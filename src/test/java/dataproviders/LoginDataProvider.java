package dataproviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public static Object[][] getInvalidLoginData() {
        return new Object[][] {
                // Đảm bảo các giá trị expectedErrorField khớp với Logic trong Test
                { new LoginData("test@gmail.com", "wrongpass", "none", "Sai mật khẩu") },
                { new LoginData("invalid@gmail.com", "testweb123", "none", "Email không tồn tại") },
                { new LoginData("", "", "email", "Trống cả 2 trường") },
                { new LoginData("", "password123", "email", "Trống email") },
                { new LoginData("test@gmail.com", "", "password", "Trống mật khẩu") },
                { new LoginData("invalid-format", "password123", "none", "Sai định dạng email") }
        };
    }
}
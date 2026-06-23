package dataproviders;

import org.testng.annotations.DataProvider;
import data.RegisterData;

public class RegisterDataProvider {

        @DataProvider(name = "invalidRegisterData")
        public static Object[][] getInvalidRegisterData() {
                return new Object[][] {
                                // 1. Tên quá ngắn (Ví dụ: 1 ký tự)
                                { new RegisterData("Nguyễn Văn A", "testweb@gmail.com", "Password123!", "email_exists",
                                                "Đăng ký với email đã tồn tại") },
                                { new RegisterData("A", "testweb1@gmail.com", "Password123!", "name",
                                                "Tên quá ngắn (1 ký tự)") },
                                { new RegisterData("Nguyễn Văn A", "testweb123@gmail.com", "123", "password_short",
                                                "Mật khẩu dưới 6 ký tự") },
                                // { new RegisterData("Nguyễn Văn A", "testweb12@gmail.com", " ", "password",
                                // "Mật khẩu toàn dấu cách") },
                                { new RegisterData("Nguyễn Văn A", "testweb123gmail.com", "Password123!", "email",
                                                "Email thiếu còng (@)") },
                                { new RegisterData("Nguyễn Văn A", "test123@", "Password123!", "email",
                                                "Email thiếu domain") },
                                { new RegisterData("Nguyễn Văn A", "test123@.com", "Password123!", "email",
                                                "Email sai định dạng domain") },
                                { new RegisterData("Nguyễn Văn A", "test web@gmail.com", "Password123!", "email",
                                                "Email chứa dấu cách") },
                                { new RegisterData("", "test123@gmail.com", "Password123!", "name",
                                                "Trống trường Họ và Tên") },
                                { new RegisterData("Nguyễn Văn A", "", "Password123!", "email", "Trống trường Email") },
                                { new RegisterData("Nguyễn Văn A", "test123@gmail.com", "", "password",
                                                "Trống trường Mật khẩu") },
                                { new RegisterData("", "", "", "all", "Trống tất cả các trường") },
                                // { new RegisterData("Nguyễn @#$", "testnew123@gmail.com", "Password123!",
                                // "name",
                                // "Tên chứa ký tự đặc biệt") },
                                // { new RegisterData("Nguyễn 123", "testnew456@gmail.com", "Password123!",
                                // "name", "Tên chứa số") }
                };
        }
}

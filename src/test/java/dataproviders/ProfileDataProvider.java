package dataproviders;

import org.testng.annotations.DataProvider;
import data.ProfileData;

public class ProfileDataProvider {
        @DataProvider(name = "validProfileData")
        public static Object[][] getValidProfileData() {
                return new Object[][] {
                                {
                                                new ProfileData(
                                                                "Hiếu Hồ Chí Minh", // Tên hiển thị
                                                                "0987654321",
                                                                "TP HCM", // Tỉnh/Thành phố
                                                                "31", // Value của Quận 1
                                                                "581", // Value của Phường Bến Nghé
                                                                "Đường Lê Duẩn, Quận 1",
                                                                null,
                                                                "Cập nhật thành công với tên Hiếu Hồ Chí Minh")
                                },
                                {
                                                new ProfileData(
                                                                "Nguyễn Văn Test",
                                                                "0987654321",
                                                                "TP HCM",
                                                                "31", // Quận 1
                                                                "582", // Phường Bến Nghé
                                                                "123 Đường Số 1, Thôn ABC",
                                                                null,
                                                                "Cập nhật thành công với tên Nguyễn Văn Test")
                                },
                                {
                                                new ProfileData(
                                                                "Nguyễn Văn Test",
                                                                "0987654321",
                                                                "TP HCM",
                                                                "31", // Quận 1
                                                                "583", // Phường Bến Nghé
                                                                "123 Đường Số 1, Thôn ABC",
                                                                null,
                                                                "Cập nhật thành công với tên Nguyễn Văn Test")
                                },
                                {
                                                new ProfileData(
                                                                "Nguyễn Văn Test",
                                                                "0987654321",
                                                                "TP HCM",
                                                                "31", // Quận 1
                                                                "584", // Phường Bến Nghé
                                                                "123 Đường Số 1, Thôn ABC",
                                                                null,
                                                                "Cập nhật thành công với tên Nguyễn Văn Test")
                                },
                                {
                                                new ProfileData(
                                                                "Nguyễn Văn Test",
                                                                "0987654321",
                                                                "TP HCM",
                                                                "31", // Quận 1
                                                                "585", // Phường Bến Nghé
                                                                "123 Đường Số 1, Thôn ABC",
                                                                null,
                                                                "Cập nhật thành công với tên Nguyễn Văn Test")
                                },
                                {
                                                new ProfileData(
                                                                "Nguyễn Văn Test",
                                                                "0987654321",
                                                                "TP HCM",
                                                                "31", // Quận 1
                                                                "586", // Phường Bến Nghé
                                                                "123 Đường Số 1, Thôn ABC",
                                                                null,
                                                                "Cập nhật thành công với tên Nguyễn Văn Test")
                                },

                };
        }
}

package dataproviders;

import org.testng.annotations.DataProvider;
import data.ProfileData;

public class ProfileDataProvider {
        @DataProvider(name = "validProfileData")
        public static Object[][] getValidProfileData() {
                return new Object[][] {
                                { new ProfileData(
                                                "Name HCM 1",
                                                "0987654321",
                                                "TP HCM",
                                                "31", // Quận 1
                                                "581", // Phường Đa Kao
                                                "123 Đường Nguyễn Du, Quận 1",
                                                null,
                                                "cập nhập tên : Name HCM 1 , số điện thoại, tỉnh TP HCM, Quận , Phường, địa chỉ")
                                },
                                { new ProfileData(
                                                "Name HCM 2",
                                                "0987654322",
                                                "TP HCM",
                                                "31", // Quận 1
                                                "582", // Phường Bến Nghé
                                                "456 Đường Lê Duẩn, Quận 1",
                                                null,
                                                "cập nhập tên : Name HCM 2 , số điện thoại, tỉnh TP HCM, Quận , Phường, địa chỉ")
                                },
                                { new ProfileData(
                                                "Name HCM 3",
                                                "0987654323",
                                                "TP HCM",
                                                "31", // Quận 1
                                                "583", // Phường Bến Thành
                                                "789 Đường Lý Tự Trọng, Quận 1",
                                                null,
                                                "cập nhập tên : Name HCM 3 , số điện thoại, tỉnh TP HCM, Quận , Phường, địa chỉ")
                                },
                                { new ProfileData(
                                                "Name HCM 4",
                                                "0987654324",
                                                "TP HCM",
                                                "31", // Quận 1
                                                "584", // Phường Nguyễn Thái Bình
                                                "12 Đường Lê Lợi, Quận 1",
                                                null,
                                                "cập nhập tên : Name HCM 4 , số điện thoại, tỉnh TP HCM, Quận , Phường, địa chỉ")
                                },
                                { new ProfileData(
                                                "Name HCM 5",
                                                "0987654325",
                                                "TP HCM",
                                                "31", // Quận 1
                                                "585", // Phường Phạm Ngũ Lão
                                                "34 Đường Bùi Viện, Quận 1",
                                                null,
                                                "cập nhập tên : Name HCM 5 , số điện thoại, tỉnh TP HCM, Quận , Phường, địa chỉ")
                                },
                                { new ProfileData(
                                                "Name HCM 6",
                                                "0987654326",
                                                "TP HCM",
                                                "31", // Quận 1
                                                "586", // Phường Cầu Ông Lãnh
                                                "56 Đường Trần Hưng Đạo, Quận 1",
                                                null,
                                                "cập nhập tên : Name HCM 6 , số điện thoại, tỉnh TP HCM, Quận , Phường, địa chỉ")
                                },
                                { new ProfileData(
                                                "Name HN 1",
                                                "0911111111",
                                                "Hà Nội",
                                                "1", // Quận Ba Đình
                                                "1", // Phường Phúc Xá
                                                "Số 10 phố Tân Ấp, Ba Đình",
                                                null,
                                                "cập nhập tên : Name HN 1 , số điện thoại, tỉnh Hà Nội, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name HN 2",
                                                "0922222222",
                                                "Hà Nội",
                                                "1", // Quận Ba Đình
                                                "2", // Phường Trúc Bạch
                                                "Số 15 phố Châu Long, Ba Đình",
                                                null,
                                                "cập nhập tên : Name HN 2 , số điện thoại, tỉnh Hà Nội, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name HN 3",
                                                "0933333333",
                                                "Hà Nội",
                                                "1", // Quận Ba Đình
                                                "3", // Phường Vĩnh Phúc
                                                "Số 20 phố Đội Nhân, Ba Đình",
                                                null,
                                                "cập nhập tên : Name HN 3 , số điện thoại, tỉnh Hà Nội, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name HN 4",
                                                "0944444444",
                                                "Hà Nội",
                                                "1", // Quận Ba Đình
                                                "4", // Phường Cống Vị
                                                "Số 25 phố Đào Tấn, Ba Đình",
                                                null,
                                                "cập nhập tên : Name HN 4 , số điện thoại, tỉnh Hà Nội, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name HN 5",
                                                "0955555555",
                                                "Hà Nội",
                                                "1", // Quận Ba Đình
                                                "5", // Phường Liễu Giai
                                                "Số 30 phố Văn Cao, Ba Đình",
                                                null,
                                                "cập nhập tên : Name HN 5 , số điện thoại, tỉnh Hà Nội, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name HN 6",
                                                "0966666666",
                                                "Hà Nội",
                                                "1", // Quận Ba Đình
                                                "6", // Phường Nguyễn Trung Trực
                                                "Số 35 phố Hàng Than, Ba Đình",
                                                null,
                                                "cập nhập tên : Name HN 6 , số điện thoại, tỉnh Hà Nội, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DN 1",
                                                "0971111111",
                                                "Đà Nẵng",
                                                "206", // Quận Liên Chiểu
                                                "2889", // Phường Hòa Hiệp Bắc
                                                "Số 10 Đường Nguyễn Văn Cừ, Liên Chiểu",
                                                null,
                                                "cập nhập tên : Name DN 1 , số điện thoại, tỉnh Đà Nẵng, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DN 2",
                                                "0972222222",
                                                "Đà Nẵng",
                                                "206", // Quận Liên Chiểu
                                                "2890", // Phường Hòa Hiệp Nam
                                                "Số 20 Đường Nguyễn Lương Bằng, Liên Chiểu",
                                                null,
                                                "cập nhập tên : Name DN 2 , số điện thoại, tỉnh Đà Nẵng, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DN 3",
                                                "0973333333",
                                                "Đà Nẵng",
                                                "206", // Quận Liên Chiểu
                                                "2891", // Phường Hòa Khánh Bắc
                                                "Số 30 Đường Âu Cơ, Liên Chiểu",
                                                null,
                                                "cập nhập tên : Name DN 3 , số điện thoại, tỉnh Đà Nẵng, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DN 4",
                                                "0974444444",
                                                "Đà Nẵng",
                                                "206", // Quận Liên Chiểu
                                                "2892", // Phường Hòa Khánh Nam
                                                "Số 40 Đường Tôn Đức Thắng, Liên Chiểu",
                                                null,
                                                "cập nhập tên : Name DN 4 , số điện thoại, tỉnh Đà Nẵng, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DN 5",
                                                "0975555555",
                                                "Đà Nẵng",
                                                "206", // Quận Liên Chiểu
                                                "2893", // Phường Hòa Minh
                                                "Số 50 Đường Kinh Dương Vương, Liên Chiểu",
                                                null,
                                                "cập nhập tên : Name DN 5 , số điện thoại, tỉnh Đà Nẵng, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DNO 1",
                                                "0981111111",
                                                "Đăk Nông",
                                                "198", // Thành phố Gia Nghĩa
                                                "2818", // Phường Nghĩa Đức
                                                "Số 10 Đường Hùng Vương, Gia Nghĩa",
                                                null,
                                                "cập nhập tên : Name DNO 1 , số điện thoại, tỉnh Đăk Nông, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DNO 2",
                                                "0982222222",
                                                "Đăk Nông",
                                                "198", // Thành phố Gia Nghĩa
                                                "2819", // Phường Nghĩa Thành
                                                "Số 20 Đường 23/3, Gia Nghĩa",
                                                null,
                                                "cập nhập tên : Name DNO 2 , số điện thoại, tỉnh Đăk Nông, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DNO 3",
                                                "0983333333",
                                                "Đăk Nông",
                                                "198", // Thành phố Gia Nghĩa
                                                "2820", // Phường Nghĩa Phú
                                                "Số 30 Đường Chu Văn An, Gia Nghĩa",
                                                null,
                                                "cập nhập tên : Name DNO 3 , số điện thoại, tỉnh Đăk Nông, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DNO 4",
                                                "0984444444",
                                                "Đăk Nông",
                                                "198", // Thành phố Gia Nghĩa
                                                "2821", // Phường Nghĩa Tân
                                                "Số 40 Đường Tôn Đức Thắng, Gia Nghĩa",
                                                null,
                                                "cập nhập tên : Name DNO 4 , số điện thoại, tỉnh Đăk Nông, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DNO 5",
                                                "0985555555",
                                                "Đăk Nông",
                                                "198", // Thành phố Gia Nghĩa
                                                "2822", // Phường Nghĩa Trung
                                                "Số 50 Đường Lê Duẩn, Gia Nghĩa",
                                                null,
                                                "cập nhập tên : Name DNO 5 , số điện thoại, tỉnh Đăk Nông, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DNO 6",
                                                "0986666666",
                                                "Đăk Nông",
                                                "198", // Thành phố Gia Nghĩa
                                                "2823", // Xã Đăk R'Moan
                                                "Thôn Tân Hòa, Xã Đăk R'Moan, Gia Nghĩa",
                                                null,
                                                "cập nhập tên : Name DNO 6 , số điện thoại, tỉnh Đăk Nông, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DNO 7",
                                                "0987777777",
                                                "Đăk Nông",
                                                "198", // Thành phố Gia Nghĩa
                                                "2824", // Phường Quảng Thành
                                                "Số 70 Đường Quang Trung, Gia Nghĩa",
                                                null,
                                                "cập nhập tên : Name DNO 7 , số điện thoại, tỉnh Đăk Nông, Quận , Phường, địa chỉ") },
                                { new ProfileData(
                                                "Name DNO 8",
                                                "0988888888",
                                                "Đăk Nông",
                                                "198", // Thành phố Gia Nghĩa
                                                "2825", // Xã Đắk Nia
                                                "Thôn Đắk Nia, Xã Đắk Nia, Gia Nghĩa",
                                                null,
                                                "cập nhập tên : Name DNO 8 , số điện thoại, tỉnh Đăk Nông, Quận , Phường, địa chỉ") }
                };
        }
}

package dataproviders;

import org.testng.annotations.DataProvider;
import data.SearchData;

public class SearchDataProvider {

    @DataProvider(name = "positiveSearchData")
    public static Object[][] getPositiveSearchData() {
        return new Object[][] {
                { new SearchData("Laptop", "Tìm kiếm sản phẩm chung chung") },
                { new SearchData("ASUS", "Tìm kiếm theo tên thương hiệu") },
                { new SearchData("Chuột", "Tìm kiếm phụ kiện") },
                { new SearchData("chuot", "Tìm kiếm từ khóa tiếng Việt không dấu") },
                { new SearchData("   Màn hình   ", "Tìm kiếm có chứa khoảng trắng ở 2 đầu (Trim test)") },
        };
    }

    @DataProvider(name = "negativeSearchData")
    public static Object[][] getNegativeSearchData() {
        return new Object[][] {
                // Java sẽ tự động gọi Constructor có 3 tham số của class SearchData
                // { new SearchData("!@#$%^&*", "Ôi! Rất tiếc không tìm thấy sản phẩm nào...!",
                // "Tìm kiếm với ký tự đặc biệt") },
                { new SearchData("sadasdasd123123", "Ôi! Rất tiếc không tìm thấy sản phẩm nào...!",
                        "Tìm kiếm với từ khóa không tồn tại") },
                { new SearchData("NVIDIATCKAIECSDFGHGHJSE GeForce RTXTHCTCEXSE 40800283",
                        "Ôi! Rất tiếc không tìm thấy sản phẩm nào...!",
                        "Tìm kiếm sản phẩm cụ thể") },
                { new SearchData("<script>alert(1)</script>", "Ôi! Rất tiếc", "Kiểm thử bảo mật XSS Injection") },
                { new SearchData("123123123123123123123123123",
                        "Ôi! Rất tiếc không tìm thấy sản phẩm nào...!", "Tìm kiếm với chuỗi ký tự số dài") },
                // { new SearchData("' OR 1=1 --", "Ôi! Rất tiếc", "Kiểm thử bảo mật SQL
                // Injection") },
        };
    }
}
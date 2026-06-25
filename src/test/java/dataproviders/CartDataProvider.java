package dataproviders;

import org.testng.annotations.DataProvider;
import data.CartData;
import java.util.Arrays;

public class CartDataProvider {

    @DataProvider(name = "positiveCartData")
    public static Object[][] getPositiveCartData() {
        return new Object[][] {
            // Case 1: Add multiple products
            { new CartData(
                "test@gmail.com",
                "testweb123",
                "PC Gaming",
                "PC Gaming TNC Turbo Max - 05",
                Arrays.asList("PC Gaming TNC Turbo Max - 04", "PC Gaming TNC Turbo Pro - 03"),
                "Positive: Thêm một và nhiều sản phẩm vào giỏ hàng thành công"
            ) },
            // Case 2: Update quantity to a valid number
            { new CartData(
                "test@gmail.com",
                "testweb123",
                "PC Gaming",
                "PC Gaming TNC Turbo Max - 05",
                "2", // targetQuantity
                "2", // expectedQuantity
                "Positive: Cập nhật số lượng sản phẩm lên giá trị hợp lệ"
            ) }
        };
    }


}

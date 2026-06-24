package dataproviders;

import org.testng.annotations.DataProvider;
import data.ReviewData;

public class ReviewDataProvider {

    @DataProvider(name = "positiveReviewData")
    public static Object[][] getPositiveReviewData() {
        return new Object[][] {
            { new ReviewData(
                5, 
                "San pham dung rat tot, hieu nang manh me.", 
                "Nguyen Van A", 
                "0987654321", 
                "none", 
                "Bạn đã gửi thành công", 
                "Positive: Gửi đánh giá thành công với thông tin hợp lệ"
            ) }
        };
    }

    @DataProvider(name = "negativeReviewData")
    public static Object[][] getNegativeReviewData() {
        return new Object[][] {
            { new ReviewData(
                4, 
                "Tot", // < 4 chars
                "Nguyen Van A", 
                "0987654321", 
                "content", 
                "Nội dung quá ngắn", 
                "Negative: Nội dung đánh giá quá ngắn (< 4 ký tự)"
            ) },

            { new ReviewData(
                5, 
                "San pham dung rat tot, hieu nang manh me.", 
                "Huy", // < 4 chars
                "0987654321", 
                "name", 
                "Tên quá ngắn", 
                "Negative: Tên người đánh giá quá ngắn (< 4 ký tự)"
            ) },

            { new ReviewData(
                5, 
                "San pham dung rat tot, hieu nang manh me.", 
                "Nguyen Van A", 
                "123", // < 4 chars
                "phone", 
                "Bạn chưa nhập SĐT", 
                "Negative: Số điện thoại quá ngắn"
            ) },

            { new ReviewData(
                5, 
                "San pham dung rat tot, hieu nang manh me.", 
                "Nguyen Van A", 
                "01234567", // Sai định dạng (thiếu số lượng chữ số)
                "phone", 
                "Số điện thoại chưa chính xác", 
                "Negative: Số điện thoại không đúng định dạng 10 hoặc 11 số bắt đầu bằng 0"
            ) },

            { new ReviewData(
                5, 
                "XSS attack <script>alert(1)</script>", // Chứa thẻ script
                "Nguyen Van A", 
                "0987654321", 
                "content", 
                "Nội dung chứa các ký tự không hợp lệ, bạn vui lòng kiểm tra lại", 
                "Negative: Nội dung đánh giá chứa thẻ script không hợp lệ"
            ) }
        };
    }
}


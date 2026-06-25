package data;

import java.util.List;

public class CartData {
    public String email;
    public String password;
    public String category;
    public String singleProduct;
    public List<String> multipleProducts;
    public String targetQuantity;
    public String expectedQuantity;
    public String voucherCode;
    public String expectedErrorMessage;
    public String testDescription;

    // Full constructor for multiple products
    public CartData(String email, String password, String category, String singleProduct, List<String> multipleProducts, String testDescription) {
        this.email = email;
        this.password = password;
        this.category = category;
        this.singleProduct = singleProduct;
        this.multipleProducts = multipleProducts;
        this.testDescription = testDescription;
    }

    // Constructor for quantity updates
    public CartData(String email, String password, String category, String singleProduct, String targetQuantity, String expectedQuantity, String testDescription) {
        this.email = email;
        this.password = password;
        this.category = category;
        this.singleProduct = singleProduct;
        this.targetQuantity = targetQuantity;
        this.expectedQuantity = expectedQuantity;
        this.testDescription = testDescription;
    }

    // Constructor for vouchers
    public CartData(String email, String password, String category, String singleProduct, String voucherCode, String expectedErrorMessage, String testDescription, boolean isVoucherTest) {
        this.email = email;
        this.password = password;
        this.category = category;
        this.singleProduct = singleProduct;
        this.voucherCode = voucherCode;
        this.expectedErrorMessage = expectedErrorMessage;
        this.testDescription = testDescription;
    }
}

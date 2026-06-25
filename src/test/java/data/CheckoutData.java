package data;

import java.util.List;

/**
 * Data model for Checkout & Payment positive test cases.
 * Covers: single product checkout, multiple products checkout, checkout with COD payment.
 */
public class CheckoutData {

    public String email;
    public String password;
    public String category;
    public String singleProduct;
    public List<String> additionalProducts;   // nullable – used for multi-product scenarios
    public String shippingName;
    public String shippingPhone;
    public String shippingAddress;
    public String paymentMethod;              // e.g. "COD" or "Chuyen khoan"
    public String expectedOrderText;          // text that appears on the order confirmation page
    public String testDescription;

    // Constructor: single product checkout
    public CheckoutData(
            String email,
            String password,
            String category,
            String singleProduct,
            String shippingName,
            String shippingPhone,
            String shippingAddress,
            String paymentMethod,
            String expectedOrderText,
            String testDescription) {
        this.email = email;
        this.password = password;
        this.category = category;
        this.singleProduct = singleProduct;
        this.shippingName = shippingName;
        this.shippingPhone = shippingPhone;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.expectedOrderText = expectedOrderText;
        this.testDescription = testDescription;
    }

    // Constructor: multiple products checkout
    public CheckoutData(
            String email,
            String password,
            String category,
            String singleProduct,
            List<String> additionalProducts,
            String shippingName,
            String shippingPhone,
            String shippingAddress,
            String paymentMethod,
            String expectedOrderText,
            String testDescription) {
        this.email = email;
        this.password = password;
        this.category = category;
        this.singleProduct = singleProduct;
        this.additionalProducts = additionalProducts;
        this.shippingName = shippingName;
        this.shippingPhone = shippingPhone;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.expectedOrderText = expectedOrderText;
        this.testDescription = testDescription;
    }
}

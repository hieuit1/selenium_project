package data;

public class E2EData {
    public String email;
    public String password;
    public String fullName;
    public String mobile;
    public String province;
    public String districtValue;
    public String wardValue;
    public String address;
    public String searchKeyword;
    public String paymentMethod;
    public String expectedOrderText;
    public String testDescription;

    public E2EData(
            String email,
            String password,
            String fullName,
            String mobile,
            String province,
            String districtValue,
            String wardValue,
            String address,
            String searchKeyword,
            String paymentMethod,
            String expectedOrderText,
            String testDescription) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.mobile = mobile;
        this.province = province;
        this.districtValue = districtValue;
        this.wardValue = wardValue;
        this.address = address;
        this.searchKeyword = searchKeyword;
        this.paymentMethod = paymentMethod;
        this.expectedOrderText = expectedOrderText;
        this.testDescription = testDescription;
    }
}

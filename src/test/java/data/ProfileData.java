package data;

public class ProfileData {
    public String fullName;
    public String mobile;
    public String province; // Đã thêm province
    public String districtValue;
    public String wardValue;
    public String address;
    public String expectedErrorField;
    public String testDescription;

    public ProfileData(String fullName, String mobile, String province, String districtValue, String wardValue,
            String address, String expectedErrorField, String testDescription) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.province = province; // Gán giá trị province
        this.districtValue = districtValue;
        this.wardValue = wardValue;
        this.address = address;
        this.expectedErrorField = expectedErrorField;
        this.testDescription = testDescription;
    }
}
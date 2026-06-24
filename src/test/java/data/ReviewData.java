package data;

public class ReviewData {
    public int stars;
    public String content;
    public String name;
    public String phone;
    public String expectedErrorField; // "content", "name", "phone", or "none"
    public String expectedErrorMessage;
    public String testDescription;

    public ReviewData(int stars, String content, String name, String phone, String expectedErrorField, String expectedErrorMessage, String testDescription) {
        this.stars = stars;
        this.content = content;
        this.name = name;
        this.phone = phone;
        this.expectedErrorField = expectedErrorField;
        this.expectedErrorMessage = expectedErrorMessage;
        this.testDescription = testDescription;
    }
}

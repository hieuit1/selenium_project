package data;

public class RegisterData {
    public String name;
    public String email;
    public String password;
    public String expectedErrorField;
    public String testDescription;

    public RegisterData(String name, String email, String password, String expectedErrorField, String testDescription) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.expectedErrorField = expectedErrorField;
        this.testDescription = testDescription;
    }
}

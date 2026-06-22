package dataproviders;

public class LoginData {
    public String email;
    public String password;
    public String expectedErrorField;
    public String testDescription;

    public LoginData(String email, String password, String expectedErrorField, String testDescription) {
        this.email = email;
        this.password = password;
        this.expectedErrorField = expectedErrorField;
        this.testDescription = testDescription;
    }
}

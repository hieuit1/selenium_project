package data;

public class SearchData {

    public String keyword;
    public String expectedErrorField;
    public String testDescription;

    public SearchData(String keyword, String expectedErrorField, String testDescription) {
        this.keyword = keyword;
        this.expectedErrorField = expectedErrorField;
        this.testDescription = testDescription;
    }

    public SearchData(String keyword, String testDescription) {
        this.keyword = keyword;
        this.testDescription = testDescription;
    }
}
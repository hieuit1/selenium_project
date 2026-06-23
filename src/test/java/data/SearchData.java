package data;

public class SearchData {

    public String keyword;
    public String expectedErrorField;
    public String testDescription;

    // Constructor dùng cho Negative Test (Truyền 3 tham số)
    // TÊN PHẢI LÀ SearchData
    public SearchData(String keyword, String expectedErrorField, String testDescription) {
        this.keyword = keyword;
        this.expectedErrorField = expectedErrorField;
        this.testDescription = testDescription;
    }

    // Constructor dùng cho Positive Test (Truyền 2 tham số)
    // TÊN CŨNG PHẢI LÀ SearchData
    public SearchData(String keyword, String testDescription) {
        this.keyword = keyword;
        this.testDescription = testDescription;
    }
}
package data;

public class BuildPCData {
    public String cpuKeyword;
    public String mainboardKeyword;
    public String ramKeyword;
    public String testDescription;

    public BuildPCData(String cpuKeyword, String mainboardKeyword, String ramKeyword, String testDescription) {
        this.cpuKeyword = cpuKeyword;
        this.mainboardKeyword = mainboardKeyword;
        this.ramKeyword = ramKeyword;
        this.testDescription = testDescription;
    }
}

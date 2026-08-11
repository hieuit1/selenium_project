package dataproviders;

import org.testng.annotations.DataProvider;
import data.BuildPCData;

public class BuildPCDataProvider {

    @DataProvider(name = "buildPCData")
    public static Object[][] getBuildPCData() {
        return new Object[][] {
            { new BuildPCData(
                "Intel Core i5-12400F",
                "ASUS PRIME B760M-K",
                "Kingston FURY Beast 8GB",
                "TC-BPC-01: Build PC with Intel Core i5, ASUS B760 Mainboard and Kingston RAM"
            )}
        };
    }
}

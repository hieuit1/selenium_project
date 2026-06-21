package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for taking screenshots
 */
public class ScreenshotUtil {

    /**
     * Take screenshot and save to file
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = "screenshots/" + fileName;

        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            new File("screenshots").mkdirs();
            File destFile = new File(filePath);
            org.apache.commons.io.FileUtils.copyFile(screenshotFile, destFile);
            LogUtil.info("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            LogUtil.error("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Take screenshot and attach to Allure report
     */
    public static void attachScreenshot(WebDriver driver, String screenshotName) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment(screenshotName, new FileInputStream(screenshotFile));
            LogUtil.info("Screenshot attached to Allure report");
        } catch (IOException e) {
            LogUtil.error("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }

    /**
     * Take screenshot on failure
     */
    public static void takeScreenshotOnFailure(WebDriver driver, String testName) {
        String filePath = takeScreenshot(driver, testName);
        if (filePath != null) {
            try {
                File screenshotFile = new File(filePath);
                Allure.addAttachment("Failed Screenshot", new FileInputStream(screenshotFile));
            } catch (IOException e) {
                LogUtil.error("Failed to attach failure screenshot: " + e.getMessage());
            }
        }
    }

    @Attachment(value = "Kết quả sau khi thực thi", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Kết quả sau khi thực thi", type = "image/png")
    public static byte[] attachRootScreenshot(WebDriver driver) {
        try {
            LogUtil.info("Đã đính kèm ảnh Kết quả sau khi thực thi vào root Allure.");
            // Allure sẽ tự động lấy byte[] này và tạo thành ảnh PNG trên report
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            LogUtil.error("Không thể đính kèm ảnh: " + e.getMessage());
            return new byte[0]; // Trả về mảng rỗng nếu lỗi để không làm sập chương trình
        }
    }
}

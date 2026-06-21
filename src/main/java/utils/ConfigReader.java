package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration reader utility
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            LogUtil.error("Failed to load config.properties: " + e.getMessage());
        }
    }

    /**
     * Get property value
     */
    public static String getProperty(String key) {
        String sysProp = System.getProperty(key);
        if (sysProp != null) {
            return sysProp;
        }

        String value = properties.getProperty(key);
        if (value == null) {
            LogUtil.warn("Property not found: " + key);
        }
        return value;
    }

    /**
     * Get property with default value
     */
    public static String getProperty(String key, String defaultValue) {
        String sysProp = System.getProperty(key);
        if (sysProp != null) {
            LogUtil.debug("System Property [" + key + "] = " + sysProp);
            return sysProp;
        }

        String value = properties.getProperty(key, defaultValue);
        LogUtil.debug("Property [" + key + "] = " + value);
        return value;
    }

    /**
     * Get base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    /**
     * Get browser type
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Get timeout
     */
    public static int getTimeout() {
        String timeout = getProperty("timeout", "10");
        return Integer.parseInt(timeout);
    }

    /**
     * Get headless mode setting
     */
    public static boolean isHeadlessMode() {
        String headless = getProperty("headless.mode", "false");
        return Boolean.parseBoolean(headless);
    }

    /**
     * Get screenshot on failure setting
     */
    public static boolean getScreenshotOnFailure() {
        String screenshot = getProperty("screenshot.on.failure", "true");
        return Boolean.parseBoolean(screenshot);
    }

}

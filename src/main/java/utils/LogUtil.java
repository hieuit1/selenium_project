package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Allure;

/**
 * Logging utility class using Log4j2
 */
public class LogUtil {

    private static final Logger logger = LogManager.getLogger(LogUtil.class);

    /**
     * Log info message
     */
    public static void info(String message) {
        logger.info(message);
        Allure.step(message);
    }

    /**
     * Log error message
     */
    public static void error(String message) {
        logger.error(message);
        Allure.step(message);
    }

    /**
     * Log debug message
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Log warning message
     */
    public static void warn(String message) {
        logger.warn(message);
        Allure.step(message);
    }

    /**
     * Log with exception
     */
    public static void error(String message, Throwable e) {
        logger.error(message, e);
        Allure.step(message + ": " + e.getMessage());
    }
}

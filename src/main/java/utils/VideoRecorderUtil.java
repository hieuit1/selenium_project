package utils;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

/**
 * Utility class for recording screen video during test execution
 */
public class VideoRecorderUtil {
    private static ScreenRecorder screenRecorder;
    private static String name;
    private static final String RECORDINGS_DIR = "./test-recordings/";

    /**
     * Start screen recording for a test
     */
    public static void startRecord(String methodName) throws Exception {
        try {
            name = methodName;

            // Create recordings directory if it doesn't exist
            File file = new File(RECORDINGS_DIR);
            if (!file.exists()) {
                boolean created = file.mkdirs();
                if (created) {
                    LogUtil.info("Created recordings directory: " + RECORDINGS_DIR);
                }
            }

            // Get screen dimensions
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);
            LogUtil.info("Screen size for recording: " + screenSize.width + "x" + screenSize.height);

            // Get graphics configuration
            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice().getDefaultConfiguration();

            // Configure screen recorder
            screenRecorder = new ScreenRecorder(
                    gc,
                    captureSize,
                    // File format
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    // Video format - AVI with TechSmith codec
                    new Format(MediaTypeKey, MediaType.VIDEO,
                            EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, 24,
                            FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    // Audio format
                    new Format(MediaTypeKey, MediaType.VIDEO,
                            EncodingKey, "black",
                            FrameRateKey, Rational.valueOf(30)),
                    null);

            // Start recording
            screenRecorder.start();
            LogUtil.info("Video recording started for test: " + methodName);

        } catch (Exception e) {
            LogUtil.error("Failed to start video recording: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Stop screen recording
     */
    public static void stopRecord() throws Exception {
        try {
            if (screenRecorder != null) {
                screenRecorder.stop();
                LogUtil.info("Video recording stopped. File saved as: " + RECORDINGS_DIR + name + ".avi");
            }
        } catch (IOException e) {
            LogUtil.error("IOException while stopping video recording: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            LogUtil.error("Error while stopping video recording: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get the path of the last recorded video
     */
    public static String getLastRecordingPath() {
        if (name != null) {
            return RECORDINGS_DIR + name + ".avi";
        }
        return null;
    }

    /**
     * Get the directory where recordings are stored
     */
    public static String getRecordingsDirectory() {
        return RECORDINGS_DIR;
    }
}
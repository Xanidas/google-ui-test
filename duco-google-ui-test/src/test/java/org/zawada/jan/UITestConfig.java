package org.zawada.jan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class UITestConfig {
    private static Properties properties;
    private static String screenshotPath;

    private static Logger logger = LogManager.getLogger(UITestConfig.class);

    static {

        try (InputStream props = UITestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            properties.load(props);
        } catch (IOException ex) {
            logger.error("Could not load properties", ex);
        }

        screenshotPath = properties.getProperty("screenshot.path");
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getScreenshotPath() {
        return screenshotPath;
    }
}

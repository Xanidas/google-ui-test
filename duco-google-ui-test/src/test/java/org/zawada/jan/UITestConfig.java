package org.zawada.jan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class UITestConfig {
    private static Properties properties;

    private static Logger logger = LogManager.getLogger(UITestConfig.class);
    
    public static Properties getProperties() {
        if (properties == null) {
            try(InputStream props = UITestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
                properties = new Properties();
                properties.load(props);
            } catch (IOException ex) {
                logger.error("Could not load properties", ex);
            }
        }
        return properties;
    }
}

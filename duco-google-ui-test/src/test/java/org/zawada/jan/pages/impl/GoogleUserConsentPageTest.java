package org.zawada.jan.pages.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.zawada.jan.utils.ScreenshotHelper;

public class GoogleUserConsentPageTest {
    public static WebDriver webDriver;
    static GoogleUserConsentPage googleUserConsentPage;
    private static Logger logger = LogManager.getLogger(GoogleUserConsentPageTest.class);
    private static Properties properties;

    @BeforeAll
    public static void setup(){
        try(InputStream props = GoogleUserConsentPageTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            properties.load(props);
        } catch (IOException ex) {
            logger.error("Could not load properties", ex);
        }
        webDriver = new SafariDriver();
        googleUserConsentPage = new GoogleUserConsentPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500L));
        webDriver.manage().window().maximize();
 
        webDriver.get(properties.getProperty("google.url"));
    }

    @AfterAll
    public static void cleanup() {
        webDriver.quit();
    }

    @Test
    public void checkTitle() {
        logger.info("Checking title of User Consent Page");
        ScreenshotHelper.takeScreenshot(webDriver, "preCheckTitle.png");

        assertEquals("Google",googleUserConsentPage.getTitle());

        ScreenshotHelper.takeScreenshot(webDriver, "postCheckTitle.png");
        logger.info("Finished checking title of User Consent Page");
    }

    @Test
    public void checkHeader() {
        logger.info("Checking header of User Consent Page");
        ScreenshotHelper.takeScreenshot(webDriver, "preCheckHeader.png");

        assertEquals("Before you continue to Google", googleUserConsentPage.getHeader());

        ScreenshotHelper.takeScreenshot(webDriver, "postCheckHeader.png");
        logger.info("Finished checking header of User Consent Page");
    }

}

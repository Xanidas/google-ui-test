package org.zawada.jan.pages.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.zawada.jan.UITestConfig;
import org.zawada.jan.utils.ScreenshotHelper;

public class GoogleUserConsentPageTest {
    public static WebDriver webDriver;
    static GoogleUserConsentPage googleUserConsentPage;
    private static Logger logger = LogManager.getLogger(GoogleUserConsentPageTest.class);
    private static Properties properties = UITestConfig.getProperties();
    private static String screenshotPath = UITestConfig.getScreenshotPath();

    @BeforeAll
    public static void setup(){
        webDriver = new SafariDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        googleUserConsentPage = new GoogleUserConsentPage(webDriver, wait);

        logger.info(webDriver.toString());
        
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
        webDriver.manage().window().maximize();

        webDriver.get(properties.getProperty("google.url"));
        googleUserConsentPage.acceptAll();
    }

    @AfterAll
    public static void cleanup() {
        webDriver.quit();
    }

    @BeforeEach
    public void logStartOfNewCase() {
        logger.info("===========================================");
    }

    @Test
    public void checkTitle() {
        logger.info("Checking title of User Consent Page");
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "preCheckTitle.png");

        assertEquals("Google",googleUserConsentPage.getTitle());

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "postCheckTitle.png");
        logger.info("Finished checking title of User Consent Page");
    }

    @Test
    public void checkHeader() {
        logger.info("Checking header of User Consent Page");
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "preCheckHeader.png");

        assertEquals("Before you continue to Google", googleUserConsentPage.getHeader());

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "postCheckHeader.png");
        logger.info("Finished checking header of User Consent Page");
    }

}

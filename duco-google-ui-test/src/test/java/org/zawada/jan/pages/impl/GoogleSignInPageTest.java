package org.zawada.jan.pages.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.zawada.jan.UITestConfig;
import org.zawada.jan.utils.ScreenshotHelper;


public class GoogleSignInPageTest {
    private static WebDriver webDriver;
    private static GoogleUserConsentPage googleUserConsentPage;
    private static GoogleSignInPage googleSignInPage;
    private static Logger logger = LogManager.getLogger(GoogleSignInPageTest.class);
    private static Properties properties = UITestConfig.getProperties();
    private static String screenshotPath = UITestConfig.getScreenshotPath();
    

    @BeforeAll
    public static void setup(){
        webDriver = new SafariDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        logger.info(webDriver.toString());

        googleUserConsentPage = new GoogleUserConsentPage(webDriver, wait);
        googleSignInPage = new GoogleSignInPage(webDriver, wait);
        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.get(properties.getProperty("google.url"));
        googleUserConsentPage.goToSignIn();
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
    @Order(1)
    public void checkTitle() {
        logger.info("Checking title of User Login Page");
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "preCheckSignInTitleTest.png");

        assertEquals("Sign in - Google Accounts",googleUserConsentPage.getTitle());

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "postCheckSignInTitleTest.png");
        logger.info("Finished checking title of User Login Page");
    }

    @Test
    @Order(2)
    public void testEmptyLoginIdInput() {
        logger.info("Checking empty Login Id input in User Login Page");
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "preCheckSignInTitleTest.png");
        
        googleSignInPage.clickNext();
        assertEquals("Enter an email or phone number",googleSignInPage.getEnterLoginIdLabel());

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "postCheckSignInTitleTest.png");
        logger.info("Finished checking empty Login Id input in User Login Page");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234"})
    @Order(3)
    public void testInvalidLoginIdInput(String login) {
        logger.info("Checking invalid Login Id input in User Login Page");
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "preCheckSignInTitleTest.png");
        
        googleSignInPage.clickNext();
        assertEquals("Enter a valid email or phone number",googleSignInPage.getInvalidLoginIdLabel());

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "postCheckSignInTitleTest.png");
        logger.info("Finished checking invalid Login Id input in User Login Page");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test"})
    @Order(3)
    public void testInvalidAccountInput(String login) {
        logger.info("Checking non existing account Login Id input in User Login Page");
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "preCheckSignInTitleTest.png");
        
        googleSignInPage.clickNext();
        assertEquals("Couldn’t find your Google Account",googleSignInPage.getInvalidAccountLabel());

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "postCheckSignInTitleTest.png");
        logger.info("Finished checking non existing account Login Id input in User Login Page");
    }
}

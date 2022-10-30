package org.zawada.jan.pages.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
    private static GoogleSearchMainPage googleSearchMainPage;
    private static GoogleSearchResultPage googleSearchResultPage;
    private static Logger logger = LogManager.getLogger(GoogleSearchTest.class);
    private static Properties properties = UITestConfig.getProperties();
    private static String screenshotPath = UITestConfig.getScreenshotPath();
    

    @BeforeAll
    public static void setup(){
        webDriver = new SafariDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        googleUserConsentPage = new GoogleUserConsentPage(webDriver);
        googleSearchMainPage = new GoogleSearchMainPage(webDriver, wait);
        googleSearchResultPage = new GoogleSearchResultPage(webDriver, wait);
        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.get(properties.getProperty("google.url"));
        googleUserConsentPage.acceptAll();
    }
}

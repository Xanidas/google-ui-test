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

@TestMethodOrder(OrderAnnotation.class)
public class GoogleSearchTest {
    private static WebDriver webDriver;
    static GoogleUserConsentPage googleUserConsentPage;
    static GoogleSearchMainPage googleSearchMainPage;
    static GoogleSearchResultPage googleSearchResultPage;
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

    @AfterAll
    public static void cleanup() {
        webDriver.quit();
    }

    @Test
    @Order(1)
    public void checkTitle() {
        logger.info("Checking title of User Consent Page");
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "preCheckTitle.png");

        assertEquals("Google",googleUserConsentPage.getTitle());

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, "postCheckTitle.png");
        logger.info("Finished checking title of User Consent Page");
    }

    @ParameterizedTest
    @ValueSource(strings = {"\"Poland\""})
    @Order(2)
    public void testExactSearch(String phrase) {
        String strippedPhrase = phrase.replaceAll("\"", "");
        logger.info("Checking results of exact search for phrase: ".concat(phrase));
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, strippedPhrase.concat("PreTest.png"));

        googleSearchMainPage.provideSearchPhrase(phrase);

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, strippedPhrase.concat("PostInput.png"));
        
        googleSearchMainPage.search();

        googleSearchResultPage.getSearchResults().forEach(searchResult -> {

            logger.debug(searchResult.getTitle().getText());
            logger.debug(searchResult.getText().getText());
            logger.debug(searchResult.getUrl().getText());

            assertTrue(searchResult.getTitle().getText().contains(strippedPhrase));
        });

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, strippedPhrase.concat("PostTest.png"));
        logger.info("Finished checking results of exact search for phrase: ".concat(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bigos", "EU", "test"})
    @Order(3)
    public void testConsequtiveSearch(String phrase) {
        logger.info("Checking results of exact search for phrase: ".concat(phrase));
        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, phrase.concat("PreTest.png"));

        googleSearchResultPage.provideSearchPhrase(phrase);

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, phrase.concat("PostInput.png"));
        
        googleSearchResultPage.search();

        googleSearchResultPage.getSearchResults().forEach(searchResult -> {

            logger.debug(searchResult.getTitle().getText());
            logger.debug(searchResult.getText().getText());
            logger.debug(searchResult.getUrl().getText());

            Boolean test = searchResult.getTitle().getText().contains(phrase) || searchResult.getText().getText().contains(phrase);

            assertTrue(test);
        });

        ScreenshotHelper.takeScreenshot(webDriver, screenshotPath, phrase.concat("PostTest.png"));
        logger.info("Finished checking results of exact search for phrase: ".concat(phrase));
    }

}

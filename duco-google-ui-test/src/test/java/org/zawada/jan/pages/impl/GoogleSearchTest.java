package org.zawada.jan.pages.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.zawada.jan.UITestConfig;
import org.zawada.jan.model.SearchResult;
import org.zawada.jan.utils.ScreenshotHelper;

public class GoogleSearchTest {
    private static WebDriver webDriver;
    static GoogleUserConsentPage googleUserConsentPage;
    static GoogleSearchMainPage googleSearchMainPage;
    static GoogleSearchResultPage googleSearchResultPage;
    private static Logger logger = LogManager.getLogger(GoogleSearchTest.class);
    private static Properties properties;
    private String screenshotPath;

    @BeforeAll
    public static void setup(){
        properties = UITestConfig.getProperties();
        
        webDriver = new SafariDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        googleUserConsentPage = new GoogleUserConsentPage(webDriver);
        googleSearchMainPage = new GoogleSearchMainPage(webDriver, wait);
        googleSearchResultPage = new GoogleSearchResultPage(webDriver, wait);
        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.get(properties.getProperty("google.url"));
        googleUserConsentPage.acceptAll();
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

    @ParameterizedTest
    @ValueSource(strings = {"\"Poland\""})
    public void testExactSearch(String phrase) {
        logger.info("Checking results of exact search for phrase: ".concat(phrase));
        ScreenshotHelper.takeScreenshot(webDriver, phrase.replaceAll("\"", "").concat("PreTest.png"));

        googleSearchMainPage.search(phrase);

        //WebElement nextLink = new WebDriverWait(webDriver, Duration.ofSeconds(10))
        //.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='Next']")));

        googleSearchResultPage.getSearchResults().forEach(searchResult -> {
            String strippedPhrase = phrase.replaceAll("\"", "");
            logger.info(searchResult.getTitle().getText());
            logger.info(searchResult.getText().getText());
            logger.info(searchResult.getUrl().getText());
            assertTrue(searchResult.getTitle().getText().contains(strippedPhrase));
        });

        ScreenshotHelper.takeScreenshot(webDriver, phrase.replaceAll("\"", "").concat("PostTest.png"));
        logger.info("Finished checking results of exact search for phrase: ".concat(phrase));
    }

}

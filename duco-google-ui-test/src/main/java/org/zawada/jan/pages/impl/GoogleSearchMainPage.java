package org.zawada.jan.pages.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.zawada.jan.pages.SearchPage;

/**
 * This is POM representing default search google page
 * @author: Jan Zawada
 */
public class GoogleSearchMainPage implements SearchPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    protected By searchInput = By.name("q");
    protected By searchBtn = By.name("btnK");
    protected By luckyBtn = By.name("btnI");
    protected By sugestionList = By.xpath("//*[@role='option']");
    protected By sugestionElement = By.xpath("//*[@role='option']/div/span");

    public GoogleSearchMainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public void search() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        driver.findElement(searchBtn).click();        
    }

    @Override
    public void searchLuckyStrike() {
        wait.until(ExpectedConditions.elementToBeClickable(luckyBtn));
        driver.findElement(luckyBtn).click();        
    }

    public List<WebElement> getSuggestionsList(String phrase) {
        wait.until(ExpectedConditions.elementToBeClickable(sugestionList));

        return driver.findElements(sugestionList);
    }

    @Override
    public void provideSearchPhrase(String phrase) {
        wait.until(ExpectedConditions.elementToBeClickable(searchInput));

        driver.findElement(searchInput).sendKeys(phrase);
        
    }
    
}

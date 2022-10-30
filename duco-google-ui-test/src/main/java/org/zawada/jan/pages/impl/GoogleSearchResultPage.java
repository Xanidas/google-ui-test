package org.zawada.jan.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.zawada.jan.model.SearchResult;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GoogleSearchResultPage extends GoogleSearchMainPage {

    protected By searchBtn = By.xpath("//*[@type='submit']");
    protected By resultStats = By.id("result-stats");
    protected By searchResults = By.cssSelector("#rso > div:nth-child(1) > div > div");
    protected By titleResult = By.xpath("//h3");
    protected By urlResult = By.xpath("//*[@data-header-feature='0']/div/div/div[1]/cite");
    protected By text = By.xpath("//*[@data-content-feature='1']/div");

    public GoogleSearchResultPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public void search(String phrase) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));

        driver.findElement(searchInput).sendKeys(phrase);
        driver.findElement(searchBtn).click();;
    }

    public List<WebElement> getSearchResult() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));

        return driver.findElements(searchResults);
    }

    public List<SearchResult> getSearchResults() {
        List<SearchResult> resultList = new ArrayList<> ();

        driver.findElements(searchResults).forEach(webElement -> {
            SearchResult sr = new SearchResult();
            sr.setTitle(webElement.findElement(titleResult));
            sr.setUrl(webElement.findElement(urlResult));
            sr.setText(webElement.findElement(text));
            
            resultList.add(sr);
        });

        return resultList;
    }

    
}
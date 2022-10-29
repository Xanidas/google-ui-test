package org.zawada.jan.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.zawada.jan.pages.SearchPage;

/**
 * This is POM representing default search google page
 * @author: Jan Zawada
 */
public class GoogleSearchMainPage implements SearchPage {
    protected WebDriver driver;
    
    protected By searchInput = By.name("q");
    protected By searchBtn = By.name("btnK");
    protected By luckyBtn = By.name("btnI");

    public GoogleSearchMainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public void search(String phrase) {
        driver.findElement(searchInput).sendKeys(phrase);
        driver.findElement(searchBtn).click();        
    }

    @Override
    public void searchLuckyStrike(String phrase) {
        driver.findElement(searchInput).sendKeys(phrase);
        driver.findElement(luckyBtn).click();        
    }
    
    
}

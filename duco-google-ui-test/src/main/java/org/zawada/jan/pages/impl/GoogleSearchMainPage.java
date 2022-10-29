package org.zawada.jan.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.zawada.jan.pages.SearchPage;

/**
 * This is POM representing default search google page
 * @author: Jan Zawada
 */
public class GoogleSearchMainPage implements SearchPage {
    private WebDriver driver;
    
    private By searchInput = By.name("q");
    private By searchBtn = By.name("btnK");
    private By luckyBtn = By.name("btnI");

    public GoogleSearchMainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public void search(String query) {
        driver.findElement(searchInput).sendKeys(query);
        driver.findElement(searchBtn).click();        
    }

    @Override
    public void searchLuckyStrike(String query) {
        driver.findElement(searchInput).sendKeys(query);
        driver.findElement(luckyBtn).click();        
    }
    
    
}

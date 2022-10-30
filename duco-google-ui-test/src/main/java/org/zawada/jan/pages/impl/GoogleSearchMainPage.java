package org.zawada.jan.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public GoogleSearchMainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public void search(String phrase) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));

        driver.findElement(searchInput).sendKeys(phrase);
        driver.findElement(searchBtn).click();        
    }

    @Override
    public void searchLuckyStrike(String phrase) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        
        driver.findElement(searchInput).sendKeys(phrase);
        driver.findElement(luckyBtn).click();        
    }
    
    
}

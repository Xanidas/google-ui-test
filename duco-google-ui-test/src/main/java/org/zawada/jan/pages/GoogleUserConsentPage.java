package org.zawada.jan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Author: Jan Zawada
 */
public class GoogleUserConsentPage {

    WebDriver driver;
    //When it is generally better to use other selectors dynamic name generation of html elements on google search makes it unsustainable
    By header = By.xpath("//h1");
    By acceptButton = By.xpath("//*[text()='Accept all']");
    By rejectButton = By.xpath("//*[text()='Reject all']");
    By signInButton = By.xpath("//*[text()='Sign in']");

    public GoogleUserConsentPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getHeader() {
        return driver.findElement(header).getText();
    }

    public void rejectAll() {
        driver.findElement(rejectButton).click();
    }

    public void acceptAll() {
        driver.findElement(acceptButton).click();
    }
}
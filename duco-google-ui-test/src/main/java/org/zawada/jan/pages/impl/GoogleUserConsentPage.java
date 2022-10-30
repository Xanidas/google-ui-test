package org.zawada.jan.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This is POM representing User consent Page
 * @author: Jan Zawada
 */
public class GoogleUserConsentPage {

    private WebDriver driver;
    //When it is generally better to use other selectors dynamic name generation of html elements on google search makes it unsustainable
    private By header = By.xpath("//h1");
    private By acceptButton = By.xpath("//*[text()='Accept all']");
    private By rejectButton = By.xpath("//*[text()='Reject all']");
    private By signInButton = By.xpath("//*[text()='Sign in']");

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

    public void goToSignIn() {
        driver.findElement(signInButton).click();
    }
}
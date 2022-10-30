package org.zawada.jan.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is POM representing User consent Page
 * @author: Jan Zawada
 */
public class GoogleUserConsentPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    //When it is generally better to use other selectors dynamic name generation of html elements on google search makes it unsustainable
    protected By header = By.xpath("//h1");
    protected By acceptButton = By.xpath("//*[text()='Accept all']");
    protected By rejectButton = By.xpath("//*[text()='Reject all']");
    protected By signInButton = By.xpath("//*[text()='Sign in']");

    public GoogleUserConsentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getHeader() {
        return driver.findElement(header).getText();
    }

    public void rejectAll() {
        wait.until(ExpectedConditions.elementToBeClickable(rejectButton));
        driver.findElement(rejectButton).click();
    }

    public void acceptAll() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptButton));
        driver.findElement(acceptButton).click();
    }

    public void goToSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        driver.findElement(signInButton).click();
    }
}
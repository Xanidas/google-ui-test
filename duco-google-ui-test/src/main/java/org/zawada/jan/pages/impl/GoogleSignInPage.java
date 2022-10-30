package org.zawada.jan.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is POM representing default search google page
 * @author: Jan Zawada
 */
public class GoogleSignInPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected By loginInput = By.id("identifierId");
    protected By nextBtn = By.xpath("//*[text()='Next']");
    protected By passwordInput = By.name("Passwd");
    protected By showPwdCheckbox = By.xpath("//*[@type='checkbox']");
    protected By enterLoginIdLabel = By.xpath("//*[text()='Enter an email or phone number']");
    protected By enterPwdLabel = By.xpath("//*[text()='Enter a password']");
    protected By invalidAccountLabel = By.xpath("//*[text()='Couldn’t find your Google Account']");
    protected By invalidLoginIdLabel = By.xpath("//*[text()='Enter a valid email or phone number']");

    public GoogleSignInPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void provideLoginId(String loginId) {
        wait.until(ExpectedConditions.elementToBeClickable(loginInput));
        driver.findElement(loginInput).sendKeys(loginId);
    }

    public void providePassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
        driver.findElement(nextBtn).click();
    }

    public void setShowPwdCheckbox(Boolean value) {
        wait.until(ExpectedConditions.elementToBeClickable(showPwdCheckbox));
        WebElement chkBox = driver.findElement(showPwdCheckbox);
        if ((value && chkBox.isEnabled()) || (!value && !chkBox.isEnabled())) {
            chkBox.click();
        }
    }

    public WebElement getEnterLoginIdLabel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterLoginIdLabel));
        return driver.findElement(enterLoginIdLabel);
    }

    public WebElement getEnterPwdLabel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterPwdLabel));
        return driver.findElement(enterPwdLabel);
    }

    public WebElement getInvalidAccountLabel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidAccountLabel));
        return driver.findElement(invalidAccountLabel);
    }

    public WebElement getInvalidLoginIdLabel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidLoginIdLabel));
        return driver.findElement(invalidLoginIdLabel);
    }
}

package org.zawada.jan.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This is POM representing default search google page
 * @author: Jan Zawada
 */
public class GoogleSignInPage {
    private WebDriver driver;
    private By loginInput = By.id("identifierId");
    private By nextBtn = By.xpath("//*[text()='Next']");
    private By passwordInput = By.name("Passwd");
    private By showPwdCheckbox = By.xpath("//*[@type='checkbox'");

    public GoogleSignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void provideLoginId(String loginId) {
        driver.findElement(loginInput).sendKeys(loginId);
        driver.findElement(nextBtn).click();
    }

    public void providePassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(nextBtn).click();
    }

    public void setShowPwdCheckbox(Boolean value) {
        WebElement chkBox = driver.findElement(showPwdCheckbox);
        if ((value && chkBox.isEnabled()) || (!value && !chkBox.isEnabled())) {
            chkBox.click();
        }
    }
}

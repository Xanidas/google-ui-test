package org.zawada.jan.model;

import org.openqa.selenium.WebElement;

public class SearchResult {

    private WebElement url;
    private WebElement title;
    private WebElement text;

    public WebElement getUrl() {
        return url;
    }

    public void setUrl(WebElement url) {
        this.url = url;
    }

    public WebElement getTitle() {
        return title;
    }

    public void setTitle(WebElement title) {
        this.title = title;
    }

    public WebElement getText() {
        return text;
    }

    public void setText(WebElement text) {
        this.text = text;
    }
    
}

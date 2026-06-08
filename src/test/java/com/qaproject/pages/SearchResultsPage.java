package com.qaproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "span._Omnvo")
    private WebElement searchKeywordLabel;

    @FindBy(css = "div.LwDgZ8.eq0K9s.tBcEQe")
    private List<WebElement> searchResults;

    @FindBy(css = "a.i2eZXn.paN7Fu")
    private WebElement currentPage;

    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean hasResults(){
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        return !searchResults.isEmpty();
    }

    public int getResultCount(){
        return searchResults.size();
    }

    public String getSearchKeyword(){
        return searchKeywordLabel.getText().replace("\"", "").trim();
    }

    public WebElement getFirstResult(){
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        return searchResults.get(0);
    }

    public void clickFirstResult(){
        getFirstResult().findElement(By.cssSelector("a.i2eZXn.paN7Fu")).click();
    }

    public boolean isOnPage(String pageNumber){
        return currentPage.getText().equals(pageNumber);
    }
}
package com.qaproject.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    //initializing all the different testing elements
    @FindBy(name = "q" )
    private WebElement searchBox;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = "img[alt='Chevron']")
    private WebElement chevronImage;

    @FindBy(xpath = "//a[contains(@href, 'viewcart')]")
    private WebElement cartIcon;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchFor(String keyword) {
        searchBox.clear();
        searchBox.sendKeys(keyword);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", searchButton);
    }


    public boolean isSearchBoxVisible(){
        return searchBox.isDisplayed();
    }

    public void clickChevron(){
        chevronImage.click();
    }

    public void clickCart() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cartIcon);
    }

    public String getTitle(){
        return driver.getTitle();
    }
}

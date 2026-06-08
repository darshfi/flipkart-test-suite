package com.qaproject.pages;

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

    @FindBy(css = "img[alt='cart']")
    private WebElement cartIcon;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //use and test the different elements
    public void searchFor(String keyword){
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchButton.click();
    }

    public boolean isSearchBoxVisible(){
        return searchBox.isDisplayed();
    }

    public void clickChevron(){
        chevronImage.click();
    }

    public void clickCart(){
        cartIcon.click();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}

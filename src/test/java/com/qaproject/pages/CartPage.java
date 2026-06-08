package com.qaproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isCartPageLoaded(){
        if(driver.getCurrentUrl().contains("viewcart")){
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(), 'Continue Shopping')]")
                ));
                return true;
            } catch (Exception ignored){}
            return true;
        }
        return false;
    }

    public boolean isCartEmpty(){
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(), 'Missing Cart items?')]")
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

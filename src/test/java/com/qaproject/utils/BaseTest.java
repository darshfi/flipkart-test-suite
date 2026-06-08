package com.qaproject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().browserVersion("148").setup();
        ChromeOptions options = new ChromeOptions();
        //get the chromium running for automations
        options.setBinary("/usr/bin/chromium");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--lang=en-IN");
        //get to the desired page
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.flipkart.com/");
        //wait for the page to load and make sure it is the right page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try{
            //see if there is any unnecessary popup and dismiss it
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(By.name("q")),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")),
                    ExpectedConditions.presenceOfElementLocated((By.cssSelector("img[alt='cart]"))),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[alt='Chevron']"))
            ));
            //if landed on an unexpected page, try to find a continue or submit button and click it
            try{
                driver.findElement(By.cssSelector("input[type='submit']")).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            } catch (Exception ignored) {}
        } catch (Exception ignored) {}
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}

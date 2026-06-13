package com.qaproject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public void setUp() {
        WebDriverManager.chromedriver().browserVersion("148").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--lang=en-IN");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        // NO implicit wait — we use explicit waits only, mixing both causes unpredictable behaviour
        driver.get("https://www.flipkart.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1 — wait for the page body to exist at all
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // Step 2 — dismiss login popup if it appears
        // "(//button)[2]" is the well-known close button for Flipkart's login modal
        try {
            WebElement closePopup = new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//button)[2]")
                    ));
            closePopup.click();
        } catch (Exception ignored) {
            // popup didn't show — that's fine, continue
        }

        // Step 3 — NOW wait for search box to be clickable (popup is gone)
        wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
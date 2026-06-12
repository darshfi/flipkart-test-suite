package com.qaproject.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qaproject.pages.CartPage;
import com.qaproject.pages.HomePage;
import com.qaproject.utils.BaseTest;
import com.qaproject.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.TestRunner.PriorityWeight.priority;

public class CartTest extends BaseTest {
    private HomePage homePage;
    private CartPage cartPage;

    @BeforeClass
    public void initPage(){
        ExtentReportManager.getInstance();
    }

    @Test(priority = 1)
    public void testCartPageLoads(){
        ExtentTest extentTest = ExtentReportManager.getInstance()
                .createTest("Cart Page Load Test");
        ExtentReportManager.setTest(extentTest);
        try{
            homePage = new HomePage(driver);
            homePage.clickCart();
            cartPage = new CartPage(driver);
            extentTest.log(Status.INFO,"Navigated to cart page");
            Assert.assertTrue(cartPage.isCartPageLoaded(),
                    "Cart page should load successfully");
            extentTest.log(Status.PASS,"Cart page loaded successfully");
        } catch(Exception e){
            extentTest.log(Status.FAIL,"Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2)
    public void testCartIsEmptyByDefault(){
        ExtentTest extentTest = ExtentReportManager.getInstance()
                .createTest("Empty Cart Test");
        ExtentReportManager.setTest(extentTest);
        try{
            homePage = new HomePage(driver);
            homePage.clickCart();
            cartPage = new CartPage(driver);
            Assert.assertTrue(cartPage.isCartEmpty(),
                    "Cart should be empty for a new session");
            extentTest.log(Status.PASS,"Cart is empty as expected");
        } catch(Exception e){
            extentTest.log(Status.FAIL,"Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDownReport(){
        ExtentReportManager.flush();
    }
}

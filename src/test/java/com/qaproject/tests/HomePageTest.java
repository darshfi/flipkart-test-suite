package com.qaproject.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qaproject.pages.HomePage;
import com.qaproject.utils.BaseTest;
import com.qaproject.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private HomePage homePage;
    // initialize/load the HTML file
    @BeforeTest
    public void initPage(){
        ExtentReportManager.getInstance();
    }
    // test if on home page using the title
    @Test(priority = 1)
    public void testHomePageTitle(){
        //create the test
        ExtentTest extentTest = ExtentReportManager.getInstance()
                .createTest("Home Page Title Test");
        ExtentReportManager.setTest(extentTest);
        try{
            homePage = new HomePage(driver);
            String title = homePage.getTitle();
            extentTest.log(Status.INFO, "Page Title: " + title);
            Assert.assertTrue(title.contains("Flipkart"),
                    "Title should contain 'Flipkart'");
            extentTest.log(Status.PASS,"Home Page title verified successfully");
        } catch(Exception e){
            extentTest.log(Status.FAIL,"Test failed: " + e.getMessage());
            throw e;
        }
    }
    // see if the search box has loaded up properly
    @Test(priority = 2)
    public void testSearchBoxVisible(){
        ExtentTest extentTest = ExtentReportManager.getInstance()
                .createTest("Search Box should be visible on home page");
        ExtentReportManager.setTest(extentTest);
        try{
            homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isSearchBoxVisible(),
                    "Search box should be visible on home page");
            extentTest.log(Status.PASS,"Search box is visible");
        } catch (Exception e){
            extentTest.log(Status.FAIL,"Test failed: " + e.getMessage());
            throw e;
        }
    }
    // check if the search box is working
    @Test(priority = 3)
    public void testSearchNavigation(){
        ExtentTest extentTest = ExtentReportManager.getInstance()
                .createTest("Search Navigation Test");
        ExtentReportManager.setTest(extentTest);
        try{
            homePage = new HomePage(driver);
            homePage.searchFor("laptop");
            extentTest.log(Status.INFO,"Searched for: laptop");
            Assert.assertTrue(driver.getCurrentUrl().contains("laptop"),
                    "URL should contain search keyword");
            extentTest.log(Status.PASS,"Search navigation works");
        } catch (Exception e){
            extentTest.log(Status.FAIL,"Test failed: " + e.getMessage());
            throw e;
        }
    }
    // send/write everything in the HTML file
    @AfterClass
    public void tearDownReport(){
        ExtentReportManager.flush();
    }
}

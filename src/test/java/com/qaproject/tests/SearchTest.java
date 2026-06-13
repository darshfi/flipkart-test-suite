package com.qaproject.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qaproject.pages.HomePage;
import com.qaproject.pages.SearchResultsPage;
import com.qaproject.utils.ExtentReportManager;
import com.qaproject.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest{
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @BeforeTest
    public void initPage(){
        ExtentReportManager.getInstance();
    }

    @DataProvider(name = "searchKeywords")
    public Object[][] searchKeywords(){
        return new Object[][]{
                {"laptop"},
                {"mobile phone"},
                {"headphones"}
        };
    }

    @Test(priority = 1, dataProvider = "searchKeywords")
    public void testSearchReturnResults(String keyword){
        ExtentTest extentTest = ExtentReportManager.getInstance()
                .createTest("Search Returns Results - " + keyword);
        ExtentReportManager.setTest(extentTest);
        try{
            homePage = new HomePage(driver);
            homePage.searchFor(keyword);
            searchResultsPage = new SearchResultsPage(driver);
            extentTest.log(Status.INFO,"Searched for: " + keyword);
            Assert.assertTrue(searchResultsPage.hasResults(),
                    "Search results should not be empty for: " + keyword);
            extentTest.log(Status.PASS,
                    "Found " + searchResultsPage.getResultCount() + " results for: " + keyword);
        } catch(Exception e){
            extentTest.log(Status.FAIL,"Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 3)
    public void testEmptySearchStaysOnHomePage() {
        ExtentTest extentTest = ExtentReportManager.getInstance()
                .createTest("Empty Search Test");
        ExtentReportManager.setTest(extentTest);
        try {
            homePage = new HomePage(driver);
            homePage.searchFor("");
            extentTest.log(Status.INFO, "Searched with empty string");
            // Check URL instead of title — URL is more reliable
            String currentUrl = driver.getCurrentUrl();
            extentTest.log(Status.INFO, "URL after empty search: " + currentUrl);
            Assert.assertTrue(
                    currentUrl.contains("flipkart.com") && !currentUrl.contains("search"),
                    "Should remain on Flipkart homepage after empty search. URL: " + currentUrl
            );
            extentTest.log(Status.PASS, "Empty search handled correctly");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDownReport(){
        ExtentReportManager.flush();
    }
}

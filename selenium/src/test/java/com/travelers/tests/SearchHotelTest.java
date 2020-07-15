package com.travelers.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.travelers.helpers.ExcelHelper;
import com.travelers.helpers.SeleniumHelper;
import com.travelers.helpers.TestListener;
import com.travelers.pages.HomePage;
import com.travelers.pages.ResultPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.travelers.helpers.SeleniumHelper.takeScreenshot;

@Listeners(TestListener.class)
public class SearchHotelTest extends BaseSeleniumTest {

    @Test
    public void searchHotelTest() throws IOException {

        ExtentTest test = reports.createTest("Search Hotel Test");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");
        HomePage homePage = new HomePage(driver);

        //MediaEntityBuilder entityBuilder = MediaEntityBuilder.createScreenCaptureFromPath(SeleniumHelper.takeScreenshot(driver));

        test.info("On PHP Travels Home Page", getScreenshot());

        homePage.setCityHotel("Dubai");
        homePage.setDateRange("09/11/2018", "09/13/2018");
        homePage.openTravellersModal();
        homePage.addAdult();
        homePage.addChild();
        homePage.performSearch();

        test.info("After performing search", getScreenshot());

        test.info("Checking hotel names", getScreenshot());

        ResultPage resultPage = new ResultPage(driver);

        List<String> hotelNames = resultPage.getHotelNames();

        //SeleniumHelper.takeScreenshot(driver);

        //Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        //Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth");

        test.info("Checking hotel prices", getScreenshot());
        List<String> prices = resultPage.getHotelPrices();
        Assert.assertEquals(prices.get(0),"$22");
        Assert.assertEquals(prices.get(1),"$50");
        Assert.assertEquals(prices.get(2),"$80");
        getScreenshot();
        //Assert.assertEquals(prices.get(3),"$151");
    }

}
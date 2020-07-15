package com.travelers.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.travelers.helpers.DriverFactory;
import com.travelers.helpers.DriverType;
import com.travelers.helpers.NoSuchDriverException;
import com.travelers.helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public abstract class BaseSeleniumTest {

    protected WebDriver driver;
    protected ExtentHtmlReporter reporter;
    protected ExtentReports reports;

    @BeforeTest
    public void setUpReporter() {
        reporter = new ExtentHtmlReporter("src//main//resources//reports//index.html");
        reports = new ExtentReports();
        reports.attachReporter(reporter);


    }

    @BeforeTest
    public void setUp() throws NoSuchDriverException {
        System.out.println("Wykonuje sie przed klasa");
        String driverPath = "C:\\Users\\damia\\IdeaProjects\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver3.exe";
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = DriverFactory.getDriver(DriverType.CHROME);
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Wykonuje sie po klasie");
        //driver.quit();

    }

    @AfterTest
    public void  tearDownReporter() {
        //reporter.flush();
        reports.flush();

    }

    MediaEntityModelProvider getScreenshot() throws IOException {
        return MediaEntityBuilder.createScreenCaptureFromPath(SeleniumHelper.takeScreenshot(driver)).build();
    }


}

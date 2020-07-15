package com.travelers.pages;

import com.travelers.helpers.SeleniumHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    @FindBy(xpath = "/html/body/div[5]/section/div[2]/div/div/div[2]/div/div[1]/form/div[1]/div/div[2]")
    private WebElement searchSpan;

    @FindBy(xpath = "/html/body/div[17]/div/input")
    private WebElement searchCityInput;

    @FindBy(xpath = "/html/body/div[5]/section/div[2]/div/div/div[2]/div/div[1]/form/div[2]/div/input")
    private WebElement checkInInput;

    @FindBy(xpath = "/html/body/div[5]/section/div[2]/div/div/div[2]/div/div[1]/form/div[3]/div/input")
    private WebElement checkOutInput;

    @FindBy(xpath = "/html/body/div[5]/section/div[2]/div/div/div[2]/div/div[1]/form/div[4]/div[1]/input")
    private WebElement travellersInput;

    @FindBy(xpath = "/html/body/div[5]/section/div[2]/div/div/div[2]/div/div[1]/form/div[4]/div[2]/div[1]/div[1]/div/div[2]/div/div/span[2]/button")
    private WebElement adultPlusBtn;

    @FindBy(xpath = "/html/body/div[5]/section/div[2]/div/div/div[2]/div/div[1]/form/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/span[2]/button")
    private WebElement childPlusBtn;

    @FindBy(xpath = "/html/body/div[5]/section/div[2]/div/div/div[2]/div/div[1]/form/div[5]/button")
    private WebElement searchBtn;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement selectResult;

    private SeleniumHelper helper;

    private WebDriver driver;

    private Logger log = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
        this.driver = driver;
    }

    public void setCityHotel(String cityName) {
        log.info("Setting city name ");
        searchSpan.click();
        searchCityInput.sendKeys(cityName);

        //By locationLabel = By.xpath("//div[@class='select2-result-label']");
        //helper.waitForElementToBeDisplayed(locationLabel);
        //WebElement element = driver.findElement(By.xpath("//div[@class='select2-result-label']"));
        helper.waitForElementToBeDisplayed(selectResult);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        searchCityInput.sendKeys(Keys.ENTER);
        log.info("City name is set ");
    }

    public void setDateRange (String checkInDate, String checkOutDate) {
        log.info("Setting date range ");
        checkInInput.sendKeys(checkInDate);
        checkOutInput.sendKeys(checkOutDate);
        checkOutInput.click();
        log.info("Date range is set ");
    }

    public void openTravellersModal() {
        log.info("Opening travellers modal");
        travellersInput.click();
        //helper.waitForElementToBeDisplayed(adultPlusBtn);
        log.info("Travellers modal is visible");
    }

    public void addAdult() {
        adultPlusBtn.click();
    }
    public void addChild() {
        childPlusBtn.click();
    }
    public void performSearch() {
        searchBtn.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

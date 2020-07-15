package com.travelers.pages;

import com.travelers.helpers.SeleniumHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultPage {

    @FindBy(xpath = "/html/body/div[5]/div[5]/div/div[3]/div[1]/div/table")
    private WebElement resultsTable;

    private SeleniumHelper helper;

    private Logger log = Logger.getLogger(HomePage.class);

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        helper = new SeleniumHelper(driver);
    }

    public List<String> getHotelNames() {
        log.info("Checking hotel names");
        List<String> hotelNames = new ArrayList<>();
        helper.waitForListOfWebElements(resultsTable.findElements(By.xpath(".//h4//b")));
        List<WebElement> hotelNameWebElements = resultsTable.findElements(By.xpath(".//h4//b"));
        for(WebElement hotelNameElement : hotelNameWebElements) {
            log.info(hotelNameElement.getText());
            System.out.println(hotelNameElement.getText());
            hotelNames.add(hotelNameElement.getText());
        }
        return hotelNames;
    }
    public List<String> getHotelPrices() {
        List<WebElement> hotelPrices = resultsTable.findElements(By.xpath("//div[contains(@class,'price_tab')]//b"));
        List<String> prices = hotelPrices.stream().map(element -> element.getText()).collect(Collectors.toList());
        return prices;
    }

}

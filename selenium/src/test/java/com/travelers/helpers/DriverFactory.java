package com.travelers.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;

public class DriverFactory {

    private static WebDriver driverInstance;

    public static WebDriver getDriver(DriverType driverType) throws NoSuchDriverException {

        if(driverInstance == null) {
            getSpecificDriver(driverType);
            //File driverExe = new File("src//main//resources//executables//drivers//chromedriver3.exe");
            //ChromeDriverService driverService = new ChromeDriverService.Builder()
                                                                       //.usingDriverExecutable(driverExe)
                                                                       //.usingAnyFreePort().build();
            //System.out.println("Zmienna zostanie zainicjalizowana");
            //String driverPath = "C:\\Users\\damia\\IdeaProjects\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver3.exe";
            //System.setProperty("webdriver.chrome.driver",driverPath);
            //driverInstance = new ChromeDriver(driverService);
            driverInstance.manage().window().maximize();
        }
        return driverInstance;
    }

    private static void getSpecificDriver(DriverType driverType) throws NoSuchDriverException {

        switch (driverType) {
            case IE:
                File ieExe = new File("src//main//resources//executables//drivers//IEDriverServer.exe");
                InternetExplorerDriverService ieService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(ieExe)
                        .usingAnyFreePort().build();
                driverInstance = new InternetExplorerDriver(ieService);
                break;
            case CHROME:
                File chromeExe = new File("src//main//resources//executables//drivers//chromedriver4.exe");
                ChromeDriverService chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromeExe)
                        .usingAnyFreePort().build();
                driverInstance = new ChromeDriver(chromeService);
                break;
            case FIREFOX:
                File firefoxExe = new File("src//main//resources//executables//drivers//geckodriver.exe");
                GeckoDriverService geckoDriverServiceService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(firefoxExe)
                        .usingAnyFreePort().build();
                driverInstance = new FirefoxDriver(geckoDriverServiceService);
                break;

            default:
                System.out.println("Brak danego drivera");
                throw new NoSuchDriverException();
        }
    }

}

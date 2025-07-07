package com.automation;

// Importing required Selenium and TestNG libraries
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleSearchTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://google.com");

    }

    @Test
    public  void SearchTest(){

        //create a Wait object

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        //wait for search box to appear

        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));





        //now we enter the search element into the search box
        searchBox.clear();
        searchBox.sendKeys("Veeva Systems");

        //submit

        searchBox.submit();

        //wait for results to appear

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));
        System.out.println("Seatch completed, Page title is: " +driver.getTitle());




    }

    @AfterClass

    public void tearDown(){
        driver.quit();
    }

}
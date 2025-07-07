package com.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class BaseTest {

    // ✅ Needed for thread-safe driver
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeClass
    @Parameters("browser")
    public void setUp() {
        System.out.println(">>> SETUP CALLED <<<");

        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");

        driver.set(new ChromeDriver(options));  // ✅ Set the driver in ThreadLocal
        getDriver().manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver.get();  // ✅ Access thread-local driver
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();     // ✅ Quit actual WebDriver
            driver.remove();        // ✅ Remove from ThreadLocal
        }
    }
}

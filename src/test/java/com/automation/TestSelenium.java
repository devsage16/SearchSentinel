package com.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenium {
    public static void main(String[] args) {

        // Set the path to ChromeDriver (VERY IMPORTANT)
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");

        // Create ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        // Open Google
        driver.get("https://www.google.com");

        // Print Title
        System.out.println("Page Title is: " + driver.getTitle());

        driver.quit();
    }
}

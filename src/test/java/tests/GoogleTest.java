package tests;

import com.automation.BaseTest;
import pages.GoogleSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTest {

    WebDriver driver;
    GoogleSearchPage googlePage;
    BaseTest BaseTest;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        googlePage = new GoogleSearchPage(driver);  // 🔥 create page object
    }

    @Test
    public void searchTest() {
        googlePage.enterSearchTerm("Veeva Systems");
        googlePage.waitForResults();
        System.out.println("Search Completed. Title: " + googlePage.getPageTitle());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

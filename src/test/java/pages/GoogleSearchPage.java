package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // 1️⃣ Constructor to receive driver object
    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 2️⃣ Locators
    private By searchBox = By.name("q");
    private By resultsSection = By.id("search");

    // 3️⃣ Actions / Methods

    // Type search term into the search box
    public void enterSearchTerm(String term) {
        WebElement box = wait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
        box.clear();
        box.sendKeys(term);
        box.submit();
    }

    // Wait for search results to appear
    public void waitForResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(resultsSection));
    }

    // Optional: Get Page Title after search
    public String getPageTitle() {
        return driver.getTitle();
    }
}

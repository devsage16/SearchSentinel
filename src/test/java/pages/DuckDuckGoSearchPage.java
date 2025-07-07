package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class DuckDuckGoSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public DuckDuckGoSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By searchBox = By.name("q");
    private By results = By.cssSelector("[data-testid='result-title-a']");

    // Actions
    public void enterSearchTerm(String searchTerm) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
        input.clear();
        input.sendKeys(searchTerm);
        input.submit();
    }

    public void waitForResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(results));
    }

    public List<String> getResultTitles() {
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(results));
        return elements.stream()
                .map(element -> {
                    try {
                        return element.getText();
                    } catch (org.openqa.selenium.StaleElementReferenceException e) {
                        return ""; // fallback to empty if stale
                    }
                })
                .filter(text -> !text.isBlank())
                .collect(Collectors.toList());
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}

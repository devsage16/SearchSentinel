package tests;

import com.automation.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.DuckDuckGoSearchPage;
import utils.FileLogger;
import utils.ReportManager;
import utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.util.List;

public class DuckDuckGoTest extends BaseTest {

    DuckDuckGoSearchPage duckPage;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        getDriver().get("https://duckduckgo.com/");
        duckPage = new DuckDuckGoSearchPage(getDriver());
    }

    @DataProvider(name = "searchQueries")
    public Object[][] provideSearchQueries() {
        return new Object[][]{
                {"openAI"},
                {"Veeva Systems"},
                {"Java Tutorials"},
                {"India News"},
                {"Veeva Vault"}
        };
    }

    @Test(dataProvider = "searchQueries")
    public void searchTest(String query) {
        test = ReportManager.getInstance().createTest("Search Test - " + query);
        try {
            duckPage.enterSearchTerm(query);
            test.log(Status.INFO, "Entered search term: " + query);

            duckPage.waitForResults();
            test.log(Status.INFO, "Waited for results to load.");

            List<String> titles = duckPage.getResultTitles();
            test.log(Status.INFO, "Fetched " + titles.size() + " result titles.");

            boolean found = titles.stream().anyMatch(title -> title.toLowerCase().contains(query.toLowerCase()));
            Assert.assertTrue(found, "❌ No result contained the search term: " + query);
            test.log(Status.PASS, "✅ At least one result contains the query.");

            FileLogger.writeResultsToFile(query, titles);
            test.log(Status.INFO, "Search results saved to file.");

        } catch (AssertionError | Exception e) {
            test.log(Status.FAIL, "❌ Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus() && test != null) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(getDriver(), result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        }

    }

    @AfterClass
    public void flushReport() {
        ReportManager.getInstance().flush();
    }
}

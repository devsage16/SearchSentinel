package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        // Format timestamp for file name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "screenshots/" + testName + "_" + timestamp + ".png";

        // Create the screenshots folder if it doesn’t exist
        try {
            Files.createDirectories(Paths.get("screenshots"));
        } catch (IOException e) {
            System.err.println("Could not create screenshots directory: " + e.getMessage());
        }

        // Take screenshot and save it
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get(fileName);

        try {
            Files.copy(srcFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("📸 Screenshot saved: " + destination.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
        }
        return timestamp;
    }
}

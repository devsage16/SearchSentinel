package utils; // Declares that this class belongs to the 'utils' package

// Import necessary classes for file handling and exceptions
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Utility class to log search results into a file.
 */
public class FileLogger {

    /**
     * Writes a list of search result titles to a text file.
     *
     * @param query   The search query, used to name the file.
     * @param results A list of result titles to write.
     */
    public static void writeResultsToFile(String query, List<String> results) {
        // Replace spaces with underscores in the query to make a valid file name
        String fileName = "results-" + query.replaceAll(" ", "_") + ".txt";

        // Create a Path object pointing to the file
        Path filePath = Paths.get(fileName);

        try {
            // Write the list of results to the file
            // - CREATE: creates file if it doesn't exist
            // - TRUNCATE_EXISTING: clears previous content if file already exists
            Files.write(filePath, results, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // Print confirmation message with absolute path to the file
            System.out.println("Search results saved to: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            // Handle any IO errors (e.g., file access issues)
            System.err.println("Failed to write results to file: " + e.getMessage());
        }
    }
}

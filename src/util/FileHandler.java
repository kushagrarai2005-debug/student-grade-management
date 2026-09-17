package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

/**
 * FileHandler - utility class for reading/writing CSV files.
 * Handles data persistence for all entities.
 */
public class FileHandler {

    /**
     * Writes lines to a file (overwrites existing content).
     * Creates parent directories if they don't exist.
     */
    public static boolean writeLines(String filePath, List<String> lines) {
        try {
            Path path = Paths.get(filePath);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, lines, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Reads all lines from a file.
     * Returns empty list if file doesn't exist.
     */
    public static List<String> readLines(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                return Collections.emptyList();
            }
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Checks if a file exists.
     */
    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
}

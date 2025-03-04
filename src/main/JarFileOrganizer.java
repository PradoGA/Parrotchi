package main;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class JarFileOrganizer {

    /**
     * Retrieves the directory of the JAR file or the current working directory
     * based on the execution context.
     *
     * @return The path to the JAR directory or current working directory.
     */
    public static String getJarDirectory() {
        try {
            // Get the URL of the code source location
            Class<?> contextClass = Game.class;
            URL url = contextClass.getProtectionDomain().getCodeSource().getLocation();

            // Handle potential null values from protection domain
            if (url == null) {
                return getFallbackDirectory("Unable to determine code source location");
            }

            File sourceFile;
            try {
                // Convert URL to a File object
                sourceFile = new File(url.toURI());
            } catch (URISyntaxException e) {
                // Handle invalid URI syntax
                return getFallbackDirectory("Invalid URI syntax: " + e.getMessage());
            }

            if (sourceFile.isDirectory()) {
                // Running in an IDE or development environment
                System.out.println("[DEBUG] IDE development mode: " + sourceFile);
                return Paths.get("").toAbsolutePath().toString();
            } else {
                // Running as a packaged executable JAR
                String parentPath = sourceFile.getParent();
                System.out.println("[DEBUG] Packaged executable mode: " + parentPath);
                return Paths.get(parentPath).normalize().toString();
            }
        } catch (SecurityException e) {
            // Handle security restrictions
            return getFallbackDirectory("Security restriction: " + e.getMessage());
        } catch (Exception e) {
            // Handle unexpected errors
            return getFallbackDirectory("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Provides a fallback directory path, typically the current working directory,
     * and logs the reason for the fallback.
     *
     * @param errorMessage The reason for the fallback.
     * @return The path to the current working directory.
     */
    private static String getFallbackDirectory(String errorMessage) {
        System.err.println("Falling back to working directory. Reason: " + errorMessage);
        return Paths.get("").toAbsolutePath().normalize().toString();
    }
}
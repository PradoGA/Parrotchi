package main;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class JarFileOrganizer {

    public static String getJarDirectory() {
        try {
            Class<?> contextClass = Game.class;
            URL url = contextClass.getProtectionDomain().getCodeSource().getLocation();

            // Handle potential null values from protection domain
            if (url == null) {
                return getFallbackDirectory("Unable to determine code source location");
            }

            File sourceFile;
            try {
                sourceFile = new File(url.toURI());
            } catch (URISyntaxException e) {
                return getFallbackDirectory("Invalid URI syntax: " + e.getMessage());
            }

            if (sourceFile.isDirectory()) {
                System.out.println("[DEBUG] IDE development mode: " + sourceFile);
                return Paths.get("").toAbsolutePath().toString();
            } else {
                String parentPath = sourceFile.getParent();
                System.out.println("[DEBUG] Packaged executable mode: " + parentPath);
                return Paths.get(parentPath).normalize().toString();
            }
        } catch (SecurityException e) {
            return getFallbackDirectory("Security restriction: " + e.getMessage());
        } catch (Exception e) {
            return getFallbackDirectory("Unexpected error: " + e.getMessage());
        }
    }

    private static String getFallbackDirectory(String errorMessage) {
        System.err.println("Falling back to working directory. Reason: " + errorMessage);
        return Paths.get("").toAbsolutePath().normalize().toString();
    }
}
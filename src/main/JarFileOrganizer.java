package main;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class JarFileOrganizer {

    public static String getJarDirectory()
    {
        try
        {
            // Get the URL of the JAR file or the class file
            URL url = Game.class.getProtectionDomain().getCodeSource().getLocation();
            System.out.println("URL of the JAR or class file: " + url);

            // Convert the URL to a File object
            File file = new File(url.toURI());

            // If the file is a directory (running in an IDE), return its path
            if (file.isDirectory())
            {
                System.out.println("Running in IDE. Using class directory: " + file.getAbsolutePath());
                return Paths.get(".").toAbsolutePath().toString();
            }

            // If the file is a JAR, return its parent directory
            String jarDirectory = file.getParent();
            System.out.println("Running from JAR. JAR directory: " + jarDirectory);
            return jarDirectory;

        } catch (URISyntaxException e)
        {
            // If something goes wrong, fall back to the current working directory
            String workingDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
            System.out.println("Error getting JAR directory. Falling back to working directory: " + workingDirectory);
            return workingDirectory;
        }
    }
}
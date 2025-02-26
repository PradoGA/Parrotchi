package responses;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.util.Random;

public class ParrotResponseParser {
    private ParrotResponses parrotResponses;

    public ParrotResponseParser(String filePath) {
        loadResponses(filePath);
    }

    private void loadResponses(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }
            System.out.println("Loading JSON file from: " + resourcePath);
            parrotResponses = mapper.readValue(inputStream, ParrotResponses.class);
            System.out.println("JSON file loaded successfully!");// Debug statement

            // Debug: Print the loaded responses
            if (parrotResponses != null && parrotResponses.getResponses() != null) {
                System.out.println("Loaded responses: " + parrotResponses.getResponses());

            } else {
                System.err.println("Responses map is null!"); // Debug statement
            }
        } catch (Exception e) {
            System.err.println("Error loading JSON file: " + e.getMessage()); // Debug statement
            e.printStackTrace();
        }
    }

    public String getRandomResponse(String category) {
        if (parrotResponses == null || parrotResponses.getResponses() == null) {

        }

        String[] categoryResponses = parrotResponses.getResponses().get(category);
        if (categoryResponses == null || categoryResponses.length == 0) {
            return "Sorry, I don't have a response for that.";
        }

        // Pick a random response from the category
        Random random = new Random();
        return categoryResponses[random.nextInt(categoryResponses.length)];
    }


}
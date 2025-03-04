package responses;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Random;

public class ParrotResponseParser {
    // Holds the responses loaded from the JSON file
    private ParrotResponses parrotResponses;

    // Constructor that initializes the parser with the given file path
    public ParrotResponseParser(String filePath) {
        loadResponses(filePath);
    }

    // Loads the responses from the specified resource path
    private void loadResponses(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }
            System.out.println("Loading JSON file from: " + resourcePath);
            // Deserialize JSON file into ParrotResponses object
            parrotResponses = mapper.readValue(inputStream, ParrotResponses.class);
            System.out.println("JSON file loaded successfully!");

            // Debug: Print the loaded responses
            if (parrotResponses != null && parrotResponses.getResponses() != null) {
                System.out.println("Loaded responses: " + parrotResponses.getResponses());
            } else {
                System.err.println("Responses map is null!");
            }
        } catch (Exception e) {
            System.err.println("Error loading JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Returns a random response from the specified category
    public String getRandomResponse(String category) {
        if (parrotResponses == null || parrotResponses.getResponses() == null) {
            return "Sorry, I don't have any responses loaded.";
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
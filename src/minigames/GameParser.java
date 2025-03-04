package minigames;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Random;

public class GameParser {

    private IrishPhrasesGame irishPhrasesGame;
    private int currentInt;

    // Constructor that initializes the GameParser with a file path
    public GameParser(String filePath) {
        loadPhrases(filePath);
    }

    // Loads phrases from a JSON file located at the given resource path
    private void loadPhrases(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + resourcePath + getClass().getClassLoader());
            }
            System.out.println("Loading Phrases JSON file from: " + resourcePath);
            irishPhrasesGame = mapper.readValue(inputStream, IrishPhrasesGame.class);
            System.out.println("JSON file loaded successfully!");

            // Debug: Print the loaded Phrases
            if (irishPhrasesGame != null && irishPhrasesGame.getPhrases() != null) {
                System.out.println("Loaded Phrases: " + irishPhrasesGame.getPhrases().length);
            } else {
                System.err.println("Phrases list is null!");
            }

        } catch (Exception e) {
            System.err.println("Error loading JSON file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Returns a random phrase from the loaded phrases
    public String getRandomPhrase() {
        currentInt = 0;
        if (irishPhrasesGame == null ||
                irishPhrasesGame.getPhrases() == null ||
                irishPhrasesGame.getPhrases().length == 0) {
            return "Sorry, the parrot is not available right now.";
        }

        Random random = new Random();
        int randomIndex = random.nextInt(irishPhrasesGame.getPhrases().length);
        currentInt = randomIndex;
        return irishPhrasesGame.getPhrases()[randomIndex].getPhrase();
    }

    // Returns the answer corresponding to the last retrieved phrase
    public String getRandomAnswer() {
        return irishPhrasesGame.getPhrases()[currentInt].getAnswer();
    }
}
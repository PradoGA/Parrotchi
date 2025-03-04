package main;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AIParrot class handles communication with OpenAI API to simulate a parrot's responses in a Tamagotchi game.
 */
public class AIParrot {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY =  ""; // Replace with your actual OpenAI API key

    /**
     * Generates a response from the AI Parrot based on user input and the pet's current state.
     *
     * @param userInput The user's input to the parrot.
     * @param pet The Tamagotchi pet object containing its state.
     * @return The AI-generated response.
     */
    public String talkToParrot(String userInput, Tamagotchi pet) {
        try {
            String prompt = createPrompt(userInput, pet);
            return sendRequestToOpenAI(prompt);
        } catch (Exception e) {
            Logger.getLogger(AIParrot.class.getName()).log(Level.SEVERE, null, e);
            return "Sorry, I'm having trouble responding. Please try again later.";
        }
    }

    /**
     * Creates a prompt for OpenAI API based on user input and the pet's state.
     *
     * @param userInput The user's input to the parrot.
     * @param pet The Tamagotchi pet object containing its state.
     * @return The formatted prompt string.
     */
    private String createPrompt(String userInput, Tamagotchi pet) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are a friendly parrot from Ireland, in a Tamagotchi game for kids. ");
        prompt.append("Your name is: ").append(pet.getName()).append(", ");
        prompt.append("Your current mood is: ").append(pet.getMoodManager().getCurrentMood()).append(". ");
        prompt.append("The user says: '").append(userInput).append("'. ");
        prompt.append("Respond as the parrot, considering your mood, use max 30 words, but focus on answering the user prompt");

        return prompt.toString();
    }

    /**
     * Sends a request to the OpenAI API and retrieves the response.
     *
     * @param prompt The prompt string to be sent to the API.
     * @return The AI-generated response.
     */
    private String sendRequestToOpenAI(String prompt) {
        try {
            // Set up the connection to the API
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            // Create the JSON payload using OpenAI's expected format
            String jsonInputString = String.format("{\"model\":\"gpt-4o\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}],\"temperature\":0.7}", prompt);

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return parseOpenAIResponse(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Please, can you repeat?";
        }
    }

    /**
     * Parses the response from the OpenAI API to extract the AI-generated content.
     *
     * @param jsonResponse The JSON response string from the API.
     * @return The extracted AI-generated content.
     */
    private String parseOpenAIResponse(String jsonResponse) {
        System.out.println(jsonResponse);
        try {
            // Parse JSON string to JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // Extract only the content part
            String content = jsonNode.path("choices").get(0).path("message").path("content").asText();

            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to parse response.";
        }
    }
}
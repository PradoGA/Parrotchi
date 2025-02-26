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

public class AIParrot {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "s"; // Replace with your actual OpenAI API key

    public String talkToParrot(String userInput, Tamagotchi pet) {
        try {
            String prompt = createPrompt(userInput, pet);
            return sendRequestToOpenAI(prompt);
        } catch (Exception e) {
            Logger.getLogger(AIParrot.class.getName()).log(Level.SEVERE, null, e);
            return "Sorry, I'm having trouble responding. Please try again later.";
        }
    }

    private String createPrompt(String userInput, Tamagotchi pet) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are a friendly parrot from Ireland, in a Tamagotchi game for kids. ");
        prompt.append("Your name is: ").append(pet.getName()).append(", ");
        prompt.append("Your current mood is: ").append(pet.getMoodManager().getCurrentMood()).append(". ");
        prompt.append("The user says: '").append(userInput).append("'. ");
        prompt.append("Respond as the parrot, considering your mood, use max 30 words, but focus in answer to the user prompt");

        return prompt.toString();
    }

    private String sendRequestToOpenAI(String prompt) {
        try {
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
            return "Sorry, I'm having trouble connecting to the server. Please try again later.";
        }
    }

    private String parseOpenAIResponse(String jsonResponse) {
            System.out.println(jsonResponse);
            try {
                // Parse JSON string to JsonNode
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);

                // Extract only the content part
                String content = jsonNode.path("choices").get(0).path("message").path("content").asText();

                // Print the content

                return content;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed to parse response.";


            }


    }
}

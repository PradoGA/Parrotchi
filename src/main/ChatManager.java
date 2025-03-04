package main;
import responses.ParrotResponseParser;
import java.util.ArrayList;
import java.util.Random;

public class ChatManager {

    // JSON categories for different responses
    private final String HAPPY_JSON = "happy_mood";
    private final String NEUTRAL_JSON = "neutral_mood";
    private final String ANGRY_JSON = "angry_mood";
    private final String SAD_JSON = "sad_mood";
    private final String HUNGRY_JSON = "hungry";
    private final String BORED_JSON = "bored";
    private final String ISOLATED_JSON = "feeling_lonely";
    private final String LOVE_JSON = "need_cuddles";
    private final String TIRED_JSON = "tired";
    private final String RANDOM_JSON = "random_comments";
    private final String GREETINGS_JSON = "greetings";
    private final String PET_JSON = "been_pet_response";

    // Parser to handle responses from a JSON file
    ParrotResponseParser parser;

    // Constructor initializes the parser with the responses JSON file
    public ChatManager() {
        this.parser = new ParrotResponseParser("responses.json");
    }

    // Method to show the current need response based on the pet's need status
    public String showCurrentNeedResponse(Tamagotchi pet) {
        ArrayList<String> possibleAnswers = new ArrayList<>();

        // Check each status of the pet and add a corresponding response
        if (pet.getHungryStatus()) {
            possibleAnswers.add(parser.getRandomResponse(HUNGRY_JSON));
        }
        if (pet.getLovelessStatus()) {
            possibleAnswers.add(parser.getRandomResponse(LOVE_JSON));
        }
        if (pet.getIsolateStatus()) {
            possibleAnswers.add(parser.getRandomResponse(ISOLATED_JSON));
        }
        if (pet.getBoredStatus()) {
            possibleAnswers.add(parser.getRandomResponse(BORED_JSON));
        }
        if (pet.getTirednessStatus()) {
            possibleAnswers.add(parser.getRandomResponse(TIRED_JSON));
        }
        if (possibleAnswers.isEmpty()) {
            possibleAnswers.add(parser.getRandomResponse(RANDOM_JSON));
        }

        // Pick a random response from the possible answers
        Random random = new Random();
        return possibleAnswers.get(random.nextInt(possibleAnswers.size()));
    }

    // Method to show the current status of the pet NOTE: In case i need it
    public String showCurrentStatus(Tamagotchi pet) {
        ArrayList<String> statuses = new ArrayList<>();

        // Check each status of the pet and add a random response for each status
        if (pet.getHungryStatus()) {
            statuses.add(parser.getRandomResponse(HUNGRY_JSON));
        }
        if (pet.getIsolateStatus()) {
            statuses.add(parser.getRandomResponse(ISOLATED_JSON));
        }
        if (pet.getLovelessStatus()) {
            statuses.add(parser.getRandomResponse(LOVE_JSON));
        }
        if (pet.getBoredStatus()) {
            statuses.add(parser.getRandomResponse(BORED_JSON));
        }
        if (pet.getTirednessStatus()) {
            statuses.add(parser.getRandomResponse(TIRED_JSON));
        }

        // If no statuses are found, return a random response
        if (statuses.isEmpty()) {
            return parser.getRandomResponse(RANDOM_JSON);
        }

        // Pick a random response from the possible statuses
        Random random = new Random();
        String response = statuses.get(random.nextInt(statuses.size()));

        return response;
    }

    // Method to show a response when the pet is petted
    public String showPetResponse() {
        return parser.getRandomResponse(PET_JSON);
    }

    // Method to show a greeting response from the pet
    public String showPetGreetings() {
        return parser.getRandomResponse(GREETINGS_JSON);
    }

    // Method to show a response for the pet's hungry status
    public String showHungryStatus() {
        return parser.getRandomResponse(HUNGRY_JSON);
    }

    // Method to show a response for the pet's love status
    public String showLoveStatus() {
        return parser.getRandomResponse(LOVE_JSON);
    }

    // Method to show a response for the pet's isolated status
    public String showIsolatedStatus() {
        return parser.getRandomResponse(ISOLATED_JSON);
    }

    // Method to show a response for the pet's bored status
    public String showBoredStatus() {
        return parser.getRandomResponse(BORED_JSON);
    }

    // Method to show a response for the pet's tired status
    public String showTiredStatus() {
        return parser.getRandomResponse(TIRED_JSON);
    }
}
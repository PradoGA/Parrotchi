package main;

import responses.ParrotResponseParser;

import java.util.ArrayList;
import java.util.Random;

public class ChatManager {

    //Json categories
    private final String HAPPY_JSON = "happy_mood";
    private final String NEUTRAL_JSON = "neutral_mood";
    private final String ANGRY_JSON = "angry_mood";
    private final String SAD_JSON = "sad_mood";
    private final String HUNGRY_JSON = "hungry";
    private final String BORED_JSON = "bored";
    private final String ISOLATED_JSON = "feeling_lonely";
    private final String LOVE_JSON =  "need_cuddles";
    private final String TIRED_JSON = "tired";
    private final String RANDOM_JSON = "random_comments";
    private final String GREETINGS_JSON = "greetings";
    private final String PET_JSON = "been_pet_response";


    ParrotResponseParser parser;


    public ChatManager()
    {
        this.parser = new ParrotResponseParser("responses.json");
    }

    public String showCurrentNeedResponse(Tamagotchi pet){
        ArrayList<String> possibleAnswers =  new ArrayList<>();
         if(pet.getHungryStatus())
         {
             possibleAnswers.add(parser.getRandomResponse(HUNGRY_JSON));
         }
         if(pet.getLovelessStatus())
         {
             possibleAnswers.add(parser.getRandomResponse(LOVE_JSON));
         }
         if(pet.getIsolateStatus())
         {
             possibleAnswers.add(parser.getRandomResponse(ISOLATED_JSON));
         }
         if (pet.getBoredStatus())
         {
             possibleAnswers.add(parser.getRandomResponse(BORED_JSON));
         }
        if(pet.getTirednessStatus())
        {
            possibleAnswers.add(parser.getRandomResponse(TIRED_JSON));
        }
         if(possibleAnswers.isEmpty())
         {
             possibleAnswers.add(parser.getRandomResponse(RANDOM_JSON));
         }

        // Pick a random response f
        Random random = new Random();

        return possibleAnswers.get(random.nextInt(possibleAnswers.size()));
    }


    public String showCurrentStatus(Tamagotchi pet)
    {
        //check the statuses of the pet and add 1 random responses for each status
        // and them choose una random of the possibles answers
        ArrayList<String> statuses = new ArrayList<>();
        if(pet.getHungryStatus())
        {
            statuses.add(parser.getRandomResponse(HUNGRY_JSON));
        }
        if(pet.getIsolateStatus())
        {
            statuses.add(parser.getRandomResponse(ISOLATED_JSON));
        }
        if(pet.getLovelessStatus())
        {
            statuses.add(parser.getRandomResponse(LOVE_JSON));
        }
        if(pet.getBoredStatus())
        {
            statuses.add(parser.getRandomResponse(BORED_JSON));
        }
        if(pet.getTirednessStatus())
        {
            statuses.add(parser.getRandomResponse(TIRED_JSON));
        }

        if (statuses.isEmpty())
        {
            return parser.getRandomResponse(RANDOM_JSON);
        }
        Random random = new Random();
        String response = statuses.get(random.nextInt(statuses.size()));


        return response;

    }

    public  String showPetResponse()
    {
        return parser.getRandomResponse(PET_JSON);
    }

    public  String showPetGreetings()
    {
        return parser.getRandomResponse(GREETINGS_JSON);
    }


    public String showHungryStatus()
    {
        return parser.getRandomResponse(HUNGRY_JSON);
    }

    public String showLoveStatus()
    {
        return parser.getRandomResponse(LOVE_JSON);
    }

    public String showIsolatedStatus()
    {
        return parser.getRandomResponse(ISOLATED_JSON);
    }
    public String showBoredStatus()
    {
        return parser.getRandomResponse(BORED_JSON);
    }

    public String showTiredStatus()
    {
        return parser.getRandomResponse(TIRED_JSON);
    }




}

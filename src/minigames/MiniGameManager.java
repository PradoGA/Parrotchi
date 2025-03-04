package minigames;

public class MiniGameManager {

    // Instance of GameParser to parse game data
    private GameParser gameParser;

    // Current phrase and answer being used in the game
    private String currentPhrase;
    private String currentAnswer;

    // Constructor initializing the game parser with the irishPhrases.json file
    public MiniGameManager() {
        this.gameParser = new GameParser("irishPhrases.json");
    }

    // Method to get a random phrase and its answer from the game parser
    public void getRandomPhrase() {
        this.currentPhrase = gameParser.getRandomPhrase();
        this.currentAnswer = gameParser.getRandomAnswer();
    }

    // Method to get the current phrase
    public String getCurrentPhrase() {
        System.out.println("Phrase: " + currentPhrase);
        return currentPhrase;
    }

    // Method to get the current answer
    public String getCurrentAnswer() {
        System.out.println("Answer: " + currentAnswer);
        return currentAnswer;
    }

    // Method to check if the user's answer is correct
    public boolean isCorrect(String userAnswer) {
        return userAnswer.equalsIgnoreCase(currentAnswer);
    }
}
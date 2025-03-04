package main;
import java.io.Serializable;

public class MoodManager implements Serializable {

    // Tracks the current mood of the pet
    private Moods currentMood = Moods.Happy;

    // Counter to determine mood changes
    private int moodCounter = 0;

    // Getter for moodCounter
    public int getMoodCounter() {
        return moodCounter;
    }

    // Resets the mood counter to zero and checks the current mood
    public void resetCounter() {
        this.moodCounter = 0;
        checkMood();
    }

    // Increases the mood counter by one and checks the current mood
    public void increaseCounter() {
        this.moodCounter++;
        System.out.println("ADD MOOD");
        checkMood();
    }

    // Decreases the mood counter by one (ensuring it doesn't go below zero) and checks the current mood
    public void decreaseCounter() {
        this.moodCounter = Math.max(0, this.moodCounter - 1);
        System.out.println("LESS MOOD");
        checkMood();
    }

    // Checks and updates the current mood based on the moodCounter value
    public void checkMood() {
        switch (this.moodCounter) {
            case 0:
                currentMood = Moods.Happy;
                break;
            case 1:
                currentMood = Moods.Neutral;
                break;
            case 2:
                currentMood = Moods.Angry;
                break;
            default:
                currentMood = Moods.Sad;
                if (moodCounter > 4) {
                    moodCounter = 4;
                    System.out.println("CHECK WHY IS OVER 4");
                }
        }

        System.out.println("The current PET MOOD IS: " + currentMood);
        Game.getGameInstance().getGuiNotifier().updateGUI();
    }

    // Getter for currentMood
    public Moods getCurrentMood() {
        return currentMood;
    }

}
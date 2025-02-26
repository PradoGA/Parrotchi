package main;

import java.io.Serializable;

public class MoodManager implements Serializable {



    private Moods currentMood = Moods.Happy;


    private int moodCounter = 0;



    public int getMoodCounter()
    {
        return moodCounter;
    }

    public void resetCounter()
    {
        this.moodCounter = 0;
        checkMood();
    }

    public void increaseCounter()
    {
        this.moodCounter++;
        System.out.println("ADD MOOD");
        checkMood();

    }
    public void decreaseCounter()
    {
        this.moodCounter = Math.max(0, this.moodCounter - 1);
        System.out.println("LESS MOOD");
        checkMood();
    }




    public void checkMood()
    {

        switch (this.moodCounter){
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
              if(moodCounter >4)
               {
                   moodCounter = 4;
                   System.out.println("CHECK WHY IS OVER 4");
               }
        }

        System.out.println("The current PET MOOD IS: " + currentMood);
        Game.getGameInstance().getGuiNotifier().updateGUI();
    }

    public Moods getCurrentMood()
    {
        return currentMood;
    }

}

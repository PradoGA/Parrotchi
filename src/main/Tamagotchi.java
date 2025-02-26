package main;

import java.io.Serializable;

public class Tamagotchi implements Serializable {



    private String name;

    private int hunger;
    private int hungerLimit = 5;
    private int feedCost = 1;
    private int loveless;
    private int lovelessLimit = 5;
    private int isolation;
    private int isolationLimit = 7;
    private int chatCost = 2;
    private int boredom;
    private int boredomLimit = 6;
    private int playCost= 3;
    private int tiredness;
    private int tirednessNotification = 15;
    private int tirednessLimit = 20;

    final private int GAME_OVER_LIMIT = 28;

    boolean isSleeping = false;
    boolean isHungry = false;
    boolean isLoveless = false;
    boolean isIsolated = false;
    boolean isBored = false;
    boolean isTired = false;

    private MoodManager moodManager;



    public Tamagotchi() {
        this.hunger = 0;
        this.loveless = 0;
        this.isolation = 0;
        this.boredom = 0;
        this.tiredness = 0;
        this.moodManager = new MoodManager();

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void increaseHunger() {
        this.hunger++;
        if(this.hunger >= GAME_OVER_LIMIT)
        {
            Game.getGameInstance().getMainGUI().gameOver("hungry");
            System.out.println("GAME OVER");
            return;
        }

        if(!isHungry)
        {
            if(this.hunger >= hungerLimit && this.hunger < GAME_OVER_LIMIT)
            {

                setHungryStatus(true);
            }
        }

        System.out.println("Pet is getting hungry. Hunger level: " + this.hunger);
    }

    public void increaseLoveless(){
        this.loveless++;
        if(this.loveless >= GAME_OVER_LIMIT)
        {
            Game.getGameInstance().getMainGUI().gameOver("loveless");
            System.out.println("GAMEEE OVER");
            return;
        }
        if(!isLoveless)
        {
            if(this.loveless >= lovelessLimit && this.loveless < GAME_OVER_LIMIT)
            {
                setLovelessStatus(true);
            }
        }

        System.out.println("Pet is feeling loveless. Loveless level: " + this.loveless);
    }
    public void increaseIsolation() {
        this.isolation++;
        if(this.isolation >= GAME_OVER_LIMIT)
        {
            Game.getGameInstance().getMainGUI().gameOver("lonely");
            System.out.println("GAMEEE OVER");
            return;
        }
        if(!isIsolated)
        {
            if(this.isolation >= isolationLimit && this.isolation < GAME_OVER_LIMIT)
            {

                setIsolationStatus(true);
            }
        }

        System.out.println("Pet is feeling Lonely. isolation level: " + this.isolation);
    }

    public void increaseBoredom()
    {
        this.boredom++;
        if(this.boredom >= GAME_OVER_LIMIT)
        {
            Game.getGameInstance().getMainGUI().gameOver("bored");
            System.out.println("GAMEEE OVER");
            return;
        }
        if(!isBored)
        {
            if(this.boredom >= boredomLimit && this.boredom < GAME_OVER_LIMIT)
            {

                setBoredomStatus(true);
            }
        }
        System.out.println("Pet is getting bored. Boredom level: " + this.boredom);
    }

    public void increaseTiredness(int value)
    {
        this.tiredness = this.tiredness + value;
        if(this.tiredness >= tirednessLimit)
        {
            setSleepingStatus(true);
            System.out.println("Sleep");
            Game.getGameInstance().getMainGUI().sleepBehaviour();
            System.out.println("Pet is getting tired. Tiredness level: " + this.tiredness);
            return;
        }

        if(!isTired)
        {
            if(this.tiredness >= tirednessNotification && this.tiredness < GAME_OVER_LIMIT)
            {

                setTirednessStatus(true);

            }
        }
        System.out.println("Pet is getting tired. Tiredness level: " + this.tiredness);
    }

    public void feed()
    {
        if(!isHungry)
        {
            System.out.println("YOUR PET IS NOT HUNGRY");


        }
        else
        {
            setHungryStatus(false);
            this.hunger = 0;
            System.out.println("Pet has been fed. Hunger level: " + this.hunger);
            increaseTiredness(feedCost);
        }

    }

    public void pet()
    {
        //Parrot can be pet in any moment
        if(isLoveless){
            setLovelessStatus(false);
        }
        this.loveless = 0;
        System.out.println("Pet has been pet. Loveless level: " + this.loveless);
    }

    public void chat()
    {
        if(!isIsolated)
        {
            System.out.println("YOUR PET Doesn't' want to talk");


        }
        else
        {
            setIsolationStatus(false);
            this.isolation = 0;
            System.out.println("Pet has been socializes. isolation level: " + this.isolation);
            increaseTiredness(chatCost);

        }


    }


    public void play() {
        //The player can play how much it wants
        if(isBored)
        {
            setBoredomStatus(false);
        }
        this.boredom = 0;
        System.out.println("Pet has played. Boredom level: " + this.boredom);
        increaseTiredness(playCost);
    }


    public void sleep()
    {
        setTirednessStatus(false);
        this.tiredness = 0;
        System.out.println("Pet is rested: " + this.tiredness);

    }



    public boolean getSleepingStatus()
    {
        return isSleeping;
    }

    public void setSleepingStatus(boolean value)
    {
        isSleeping = value;
    }

    public boolean getHungryStatus()
    {
        return isHungry;
    }

    public void setHungryStatus(boolean value)
    {
        isHungry = value;

        if(value == true)
        {
            moodManager.increaseCounter();
            Game.getGameInstance().getGuiNotifier().showHungryNotification();

        }
       else {
            moodManager.decreaseCounter();
            System.out.println("YOUR HAS BEEN FEED DECREASING COUNTER");
        }
    }

    public boolean getLovelessStatus()
    {
        return isLoveless;
    }

    public void setLovelessStatus(boolean value)
    {
        isLoveless = value;
        if(value == true)
        {
            moodManager.increaseCounter();
            System.out.println("YOUR PET IS FEELING lOVELESS");
            Game.getGameInstance().getGuiNotifier().showLoveNotification();
        }
        else {
            moodManager.decreaseCounter();
            System.out.println("YOUR PET IS lOVED");

        }
    }

    public boolean getIsolateStatus()
    {
        return isIsolated;
    }

    public void setIsolationStatus(boolean value)
    {
        isIsolated = value;
        if(value == true)
        {
            moodManager.increaseCounter();
            System.out.println("YOUR PET IS FEELING lONELY");
            Game.getGameInstance().getGuiNotifier().showSocialNotification();
        }
        else {
            moodManager.decreaseCounter();
            System.out.println("YOUR PET HAS SOCIALIZED");
        }
    }

     public void setBoredomStatus(boolean value)
     {
         isBored = value;
         if(value == true)
         {
             moodManager.increaseCounter();
             System.out.println("YOUR PET IS FEELING BORED");
             Game.getGameInstance().getGuiNotifier().showPlayNotification();
         }
         else {
             moodManager.decreaseCounter();
             System.out.println("YOUR PET HAS PLAYED");
         }
     }

     public boolean getBoredStatus()
     {
         return isBored;
     }
    public boolean getTirednessStatus()
    {
        return isTired;
    }

    public void setTirednessStatus(boolean value)
    {
        isTired = value;
        if(value == true)
        {
            moodManager.increaseCounter();
            System.out.println("YOUR PET IS FEELING TIRED");
            Game.getGameInstance().getGuiNotifier().showTiredNotification();
        }
        else {
            moodManager.decreaseCounter();
            System.out.println("YOUR PET HAS ENERGY");
            setSleepingStatus(false);
        }
    }

public MoodManager getMoodManager()
{
    return this.moodManager;
}


}

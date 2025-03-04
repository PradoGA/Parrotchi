package main;
import java.io.Serializable;

public class Tamagotchi implements Serializable {

    // Attributes representing the state of the Tamagotchi
    private String name;

    private int hunger;
    private final int HUNGER_LIMIT = 5;
    private int feedCost = 1;
    private int loveless;
    private final int LOVELESS_LIMIT = 5;
    private int isolation;
    private final int ISOLATION_LIMIT = 7;
    private int chatCost = 2;
    private int boredom;
    private final int BOREDOM_LIMIT = 6;
    private int playCost = 3;
    private int tiredness;
    private final int TIREDNESS_NOTIFICATION = 15;
    private final int TIREDNESS_LIMIT = 20;

    final private int GAME_OVER_LIMIT = 28;

    boolean isSleeping = false;
    boolean isHungry = false;
    boolean isLoveless = false;
    boolean isIsolated = false;
    boolean isBored = false;
    boolean isTired = false;

    private MoodManager moodManager;

    // Constructor initializing the Tamagotchi's state
    public Tamagotchi() {
        this.hunger = 0;
        this.loveless = 0;
        this.isolation = 0;
        this.boredom = 0;
        this.tiredness = 0;
        this.moodManager = new MoodManager();
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Method to increase hunger and check game over conditions
    public void increaseHunger() {
        this.hunger++;
        if (this.hunger >= GAME_OVER_LIMIT) {
            Game.getGameInstance().getMainGUI().gameOver("hungry");
            System.out.println("GAME OVER");
            return;
        }

        if (!isHungry) {
            if (this.hunger >= HUNGER_LIMIT && this.hunger < GAME_OVER_LIMIT) {
                setHungryStatus(true);
            }
        }

        System.out.println("Pet is getting hungry. Hunger level: " + this.hunger);
    }

    // Method to increase loveless and check game over conditions
    public void increaseLoveless() {
        this.loveless++;
        if (this.loveless >= GAME_OVER_LIMIT) {
            Game.getGameInstance().getMainGUI().gameOver("loveless");
            System.out.println("GAME OVER");
            return;
        }
        if (!isLoveless) {
            if (this.loveless >= LOVELESS_LIMIT && this.loveless < GAME_OVER_LIMIT) {
                setLovelessStatus(true);
            }
        }

        System.out.println("Pet is feeling loveless. Loveless level: " + this.loveless);
    }

    // Method to increase isolation and check game over conditions
    public void increaseIsolation() {
        this.isolation++;
        if (this.isolation >= GAME_OVER_LIMIT) {
            Game.getGameInstance().getMainGUI().gameOver("lonely");
            System.out.println("GAME OVER");
            return;
        }
        if (!isIsolated) {
            if (this.isolation >= ISOLATION_LIMIT && this.isolation < GAME_OVER_LIMIT) {
                setIsolationStatus(true);
            }
        }

        System.out.println("Pet is feeling Lonely. isolation level: " + this.isolation);
    }

    // Method to increase boredom and check game over conditions
    public void increaseBoredom() {
        this.boredom++;
        if (this.boredom >= GAME_OVER_LIMIT) {
            Game.getGameInstance().getMainGUI().gameOver("bored");
            System.out.println("GAME OVER");
            return;
        }
        if (!isBored) {
            if (this.boredom >= BOREDOM_LIMIT && this.boredom < GAME_OVER_LIMIT) {
                setBoredomStatus(true);
            }
        }
        System.out.println("Pet is getting bored. Boredom level: " + this.boredom);
    }

    // Method to increase tiredness and check for sleep conditions
    public void increaseTiredness(int value) {
        this.tiredness = this.tiredness + value;
        if (this.tiredness >= TIREDNESS_LIMIT) {
            setSleepingStatus(true);
            System.out.println("Sleep");
            Game.getGameInstance().getMainGUI().sleepBehaviour();
            System.out.println("Pet is getting tired. Tiredness level: " + this.tiredness);
            return;
        }

        if (!isTired) {
            if (this.tiredness >= TIREDNESS_NOTIFICATION && this.tiredness < GAME_OVER_LIMIT) {
                setTirednessStatus(true);
            }
        }
        System.out.println("Pet is getting tired. Tiredness level: " + this.tiredness);
    }

    // Method to feed the Tamagotchi
    public void feed() {
        if (!isHungry) {
            System.out.println("YOUR PET IS NOT HUNGRY");
        } else {
            setHungryStatus(false);
            this.hunger = 0;
            System.out.println("Pet has been fed. Hunger level: " + this.hunger);
            increaseTiredness(feedCost);
        }
    }

    // Method to pet the Tamagotchi
    public void pet() {
        if (isLoveless) {
            setLovelessStatus(false);
        }
        this.loveless = 0;
        System.out.println("Pet has been pet. Loveless level: " + this.loveless);
    }

    // Method to chat with the Tamagotchi
    public void chat() {
        if (!isIsolated) {
            System.out.println("YOUR PET Doesn't want to talk");
        } else {
            setIsolationStatus(false);
            this.isolation = 0;
            System.out.println("Pet has been socialized. isolation level: " + this.isolation);
            increaseTiredness(chatCost);
        }
    }

    // Method to play with the Tamagotchi
    public void play() {
        if (isBored) {
            setBoredomStatus(false);
        }
        this.boredom = 0;
        System.out.println("Pet has played. Boredom level: " + this.boredom);
        increaseTiredness(playCost);
    }

    // Method to let the Tamagotchi sleep
    public void sleep() {
        setTirednessStatus(false);
        this.tiredness = 0;
        System.out.println("Pet is rested: " + this.tiredness);
    }

    // Getter and Setter for sleeping status
    public boolean getSleepingStatus() {
        return isSleeping;
    }

    public void setSleepingStatus(boolean value) {
        isSleeping = value;
    }

    // Getter and Setter for hunger status
    public boolean getHungryStatus() {
        return isHungry;
    }

    public void setHungryStatus(boolean value) {
        isHungry = value;
        if (value == true) {
            moodManager.increaseCounter();
            Game.getGameInstance().getGuiNotifier().showHungryNotification();
        } else {
            moodManager.decreaseCounter();
            System.out.println("YOUR PET HAS BEEN FED DECREASING COUNTER");
        }
    }

    // Getter and Setter for loveless status
    public boolean getLovelessStatus() {
        return isLoveless;
    }

    public void setLovelessStatus(boolean value) {
        isLoveless = value;
        if (value == true) {
            moodManager.increaseCounter();
            System.out.println("YOUR PET IS FEELING LOVELESS");
            Game.getGameInstance().getGuiNotifier().showLoveNotification();
        } else {
            moodManager.decreaseCounter();
            System.out.println("YOUR PET IS LOVED");
        }
    }

    // Getter and Setter for isolation status
    public boolean getIsolateStatus() {
        return isIsolated;
    }

    public void setIsolationStatus(boolean value) {
        isIsolated = value;
        if (value == true) {
            moodManager.increaseCounter();
            System.out.println("YOUR PET IS FEELING LONELY");
            Game.getGameInstance().getGuiNotifier().showSocialNotification();
        } else {
            moodManager.decreaseCounter();
            System.out.println("YOUR PET HAS SOCIALIZED");
        }
    }

    // Setter for boredom status
    public void setBoredomStatus(boolean value) {
        isBored = value;
        if (value == true) {
            moodManager.increaseCounter();
            System.out.println("YOUR PET IS FEELING BORED");
            Game.getGameInstance().getGuiNotifier().showPlayNotification();
        } else {
            moodManager.decreaseCounter();
            System.out.println("YOUR PET HAS PLAYED");
        }
    }

    // Getter for boredom status
    public boolean getBoredStatus() {
        return isBored;
    }

    // Getter and Setter for tiredness status
    public boolean getTirednessStatus() {
        return isTired;
    }

    public void setTirednessStatus(boolean value) {
        isTired = value;
        if (value == true) {
            moodManager.increaseCounter();
            System.out.println("YOUR PET IS FEELING TIRED");
            Game.getGameInstance().getGuiNotifier().showTiredNotification();
        } else {
            moodManager.decreaseCounter();
            System.out.println("YOUR PET HAS ENERGY");
            setSleepingStatus(false);
        }
    }

    public MoodManager getMoodManager() {
        return this.moodManager;
    }
}
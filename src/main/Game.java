package main;

import Tasks.*;
import uiPackage.MainGUI;
import uiPackage.WelcomeGUI;
import java.util.Timer;


public class Game {
    private final static Game GAME_INSTANCE = new Game();



    private static Tamagotchi pet;
    private static Timer hungerTimer;
    private static Timer loveTimer;
    private static Timer chatTimer;
    private static Timer boredomTimer;
    private static Timer energyTimer;

    //NORMAL GAME TIME
    final static int HUNGER_TIME = 25000; //25 seconds
    final static int LOVE_TIME = 40000; //40 seconds
    final static int CHAT_TIME = 50000; //50 seconds
    final static int BOREDOM_TIME = 120000; //2 minute
    final static int ENERGY_TIME = 150000; //2.5 minutes


    //QUICK GAME TIME
    final static int Q_HUNGER_TIME = 12000; //12 seconds
    final static int Q_LOVE_TIME = 20000; //20 seconds
    final static int Q_CHAT_TIME = 30000; //30 seconds
    final static int Q_BOREDOM_TIME = 45000; //45 seconds
    final static int Q_ENERGY_TIME = 60000; //1 minute


   //UI
    private static MainGUI mainGUI;
    private static GUINotifier guiNotifier;



    private static WelcomeGUI welcomeGUI;




    public static Game getGameInstance()
    {
        return GAME_INSTANCE;
    }
    public static WelcomeGUI getWelcomeGUI()
    {
        return welcomeGUI;
    }

    public static void main(String[] args) {
        mainGUI = new MainGUI();
    welcomeGUI = new WelcomeGUI();
    welcomeGUI.openWelcomeGUI(welcomeGUI);
    guiNotifier = new GUINotifier(mainGUI);
    }

    public void closeGame()
    {
        // Stop the timers and close the scanner
        hungerTimer.cancel();
        loveTimer.cancel();
        boredomTimer.cancel();
        energyTimer.cancel();
    }

    public void exitGame()
    {
        System.exit(0);
    }

    public Tamagotchi getPet()
    {
        return pet;
    }

    public void setPet(Tamagotchi pet)
    {
        this.pet = pet;
    }


    public MainGUI getMainGUI()
    {
        return mainGUI;
    }

    public GUINotifier getGuiNotifier()
    {
        return guiNotifier;
    }

    public void startQuickGame()
    {
        pet = new Tamagotchi();
        hungerTimer = new Timer();
        loveTimer = new Timer();
        chatTimer = new Timer();
        boredomTimer = new Timer();
        energyTimer = new Timer();



        // Schedule tasks with different intervals
        hungerTimer.schedule(new HungerTask(pet), 0, Q_HUNGER_TIME); // Hunger updates every 5 seconds
        loveTimer.schedule(new LoveTask(pet), 0, Q_LOVE_TIME); // Loveless updates every 5 seconds
        chatTimer.schedule(new SocialTask(pet),0,Q_CHAT_TIME); // Social needs updates every 5 seconds
        boredomTimer.schedule(new BoredomTask(pet), 0, Q_BOREDOM_TIME); // Boredom updates every 10 seconds
        energyTimer.schedule(new EnergyTask(pet), 0, Q_ENERGY_TIME); // Energy updates every 3 seconds
        mainGUI.openMainGui(mainGUI, welcomeGUI);
        mainGUI.setNameLabel("Robert");
        pet.setName("Robert");


    }

    public void startNormalGame(String name)
    {
        pet = new Tamagotchi();
        pet.setName(name);
        hungerTimer = new Timer();
        loveTimer = new Timer();
        chatTimer = new Timer();
        boredomTimer = new Timer();
        energyTimer = new Timer();



        // Schedule tasks with different intervals
        hungerTimer.schedule(new HungerTask(pet), 0, HUNGER_TIME); // Hunger updates every 5 seconds
        loveTimer.schedule(new LoveTask(pet), 0, LOVE_TIME); // Loveless updates every 5 seconds
        chatTimer.schedule(new SocialTask(pet),0,CHAT_TIME); // Social needs updates every 5 seconds
        boredomTimer.schedule(new BoredomTask(pet), 0, BOREDOM_TIME); // Boredom updates every 10 seconds
        energyTimer.schedule(new EnergyTask(pet), 0, ENERGY_TIME);// Energy updates every 3 seconds

        mainGUI.openMainGui(mainGUI, welcomeGUI);
        mainGUI.setNameLabel(name);

    }

    public void startLoadedGame(Tamagotchi loadedPet)
    {
        this.pet = loadedPet;
        loadedPet.setName(loadedPet.getName());
        hungerTimer = new Timer();
        loveTimer = new Timer();
        chatTimer = new Timer();
        boredomTimer = new Timer();
        energyTimer = new Timer();



        // Schedule tasks with different intervals
        hungerTimer.schedule(new HungerTask(loadedPet), 0, Q_HUNGER_TIME); // Hunger updates every 5 seconds
        loveTimer.schedule(new LoveTask(loadedPet), 0, Q_LOVE_TIME); // Loveless updates every 5 seconds
        chatTimer.schedule(new SocialTask(loadedPet),0,Q_CHAT_TIME); // Social needs updates every 5 seconds
        boredomTimer.schedule(new BoredomTask(loadedPet), 0, Q_BOREDOM_TIME); // Boredom updates every 10 seconds
        energyTimer.schedule(new EnergyTask(loadedPet), 0, Q_ENERGY_TIME);// Energy updates every 3 seconds

        mainGUI.openMainGui(mainGUI,welcomeGUI);
        mainGUI.setNameLabel(loadedPet.getName());
        mainGUI.setMoodPanel();

    }



}
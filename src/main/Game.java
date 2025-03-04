 /*
  * This file is part of Parrotchi.
  *
  * Parrotchi is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program. If not, see <https://www.gnu.org/licenses/>.
  *
  * Note: This project was made in 10 days
  */
 package main;
 import Tasks.*;
 import uiPackage.MainGUI;
 import uiPackage.WelcomeGUI;
 import java.util.Timer;

 public class Game {
     // Singleton instance of the Game class
     private final static Game GAME_INSTANCE = new Game();

     // Instance variables for the Tamagotchi pet and various timers
     private static Tamagotchi pet;
     private static Timer hungerTimer;
     private static Timer loveTimer;
     private static Timer chatTimer;
     private static Timer boredomTimer;
     private static Timer energyTimer;

     // Time intervals for normal game mode
     final static int HUNGER_TIME = 20000; // 20 seconds
     final static int LOVE_TIME = 30000; // 30 seconds
     final static int CHAT_TIME = 40000; // 40 seconds
     final static int BOREDOM_TIME = 60000; // 1 minute
     final static int ENERGY_TIME = 100000; // 2.5 minutes

     // Time intervals for quick game mode
     final static int Q_HUNGER_TIME = 12000; // 12 seconds
     final static int Q_LOVE_TIME = 20000; // 20 seconds
     final static int Q_CHAT_TIME = 30000; // 30 seconds
     final static int Q_BOREDOM_TIME = 45000; // 45 seconds
     final static int Q_ENERGY_TIME = 60000; // 1 minute

     // UI components
     private static MainGUI mainGUI;
     private static GUINotifier guiNotifier;
     private static WelcomeGUI welcomeGUI;

     // Method to get the singleton instance of the Game class
     public static Game getGameInstance() {
         return GAME_INSTANCE;
     }

     // Method to get the WelcomeGUI instance
     public static WelcomeGUI getWelcomeGUI() {
         return welcomeGUI;
     }

     // Main method to start the game
     public static void main(String[] args) {
         mainGUI = new MainGUI();
         welcomeGUI = new WelcomeGUI();
         welcomeGUI.openWelcomeGUI(welcomeGUI);
         guiNotifier = new GUINotifier(mainGUI);
     }

     // Method to close the game and stop all timers
     public void closeGame() {
         hungerTimer.cancel();
         loveTimer.cancel();
         boredomTimer.cancel();
         energyTimer.cancel();
     }

     // Method to exit the game application
     public void exitGame() {
         System.exit(0);
     }

     // Getter for the Tamagotchi pet
     public Tamagotchi getPet() {
         return pet;
     }

     // Setter for the Tamagotchi pet
     public void setPet(Tamagotchi pet) {
         this.pet = pet;
     }

     // Getter for the MainGUI instance
     public MainGUI getMainGUI() {
         return mainGUI;
     }

     // Getter for the GUINotifier instance
     public GUINotifier getGuiNotifier() {
         return guiNotifier;
     }

     // Method to start a quick game with predefined intervals
     public void startQuickGame() {
         pet = new Tamagotchi();
         hungerTimer = new Timer();
         loveTimer = new Timer();
         chatTimer = new Timer();
         boredomTimer = new Timer();
         energyTimer = new Timer();

         // Schedule tasks with quick game intervals
         hungerTimer.schedule(new HungerTask(pet), 0, Q_HUNGER_TIME);
         loveTimer.schedule(new LoveTask(pet), 0, Q_LOVE_TIME);
         chatTimer.schedule(new SocialTask(pet), 0, Q_CHAT_TIME);
         boredomTimer.schedule(new BoredomTask(pet), 0, Q_BOREDOM_TIME);
         energyTimer.schedule(new EnergyTask(pet), 0, Q_ENERGY_TIME);

         mainGUI.openMainGui(mainGUI, welcomeGUI);
         mainGUI.setNameLabel("Robert");
         pet.setName("Robert");
     }

     // Method to start a normal game with user-provided pet name and normal intervals
     public void startNormalGame(String name) {
         pet = new Tamagotchi();
         pet.setName(name);
         hungerTimer = new Timer();
         loveTimer = new Timer();
         chatTimer = new Timer();
         boredomTimer = new Timer();
         energyTimer = new Timer();

         // Schedule tasks with normal game intervals
         hungerTimer.schedule(new HungerTask(pet), 0, HUNGER_TIME);
         loveTimer.schedule(new LoveTask(pet), 0, LOVE_TIME);
         chatTimer.schedule(new SocialTask(pet), 0, CHAT_TIME);
         boredomTimer.schedule(new BoredomTask(pet), 0, BOREDOM_TIME);
         energyTimer.schedule(new EnergyTask(pet), 0, ENERGY_TIME);

         mainGUI.openMainGui(mainGUI, welcomeGUI);
         mainGUI.setNameLabel(name);
     }

     // Method to start a game with a loaded pet
     public void startLoadedGame(Tamagotchi loadedPet) {
         this.pet = loadedPet;
         loadedPet.setName(loadedPet.getName());
         hungerTimer = new Timer();
         loveTimer = new Timer();
         chatTimer = new Timer();
         boredomTimer = new Timer();
         energyTimer = new Timer();

         // Schedule tasks with quick game intervals for the loaded pet
         hungerTimer.schedule(new HungerTask(loadedPet), 0, Q_HUNGER_TIME);
         loveTimer.schedule(new LoveTask(loadedPet), 0, Q_LOVE_TIME);
         chatTimer.schedule(new SocialTask(loadedPet), 0, Q_CHAT_TIME);
         boredomTimer.schedule(new BoredomTask(loadedPet), 0, Q_BOREDOM_TIME);
         energyTimer.schedule(new EnergyTask(loadedPet), 0, Q_ENERGY_TIME);

         mainGUI.openMainGui(mainGUI, welcomeGUI);
         mainGUI.setNameLabel(loadedPet.getName());
         mainGUI.setMoodPanel();
     }
 }
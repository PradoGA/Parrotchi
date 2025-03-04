package main;

import uiPackage.MainGUI;
import java.util.Timer;
import java.util.TimerTask;

public class GUINotifier {
    // Reference to the main GUI window
    private MainGUI mainGUI;

    // Manager for handling chat-related notifications
    private ChatManager chatManager = new ChatManager();

    // Timer for scheduling notification display
    private Timer notificationTimer;

    // Duration for which the notification is shown (in milliseconds)
    private int notificationTime = 2500;

    // Flag to indicate if a notification is currently being shown
    private boolean isShowingNotification = false;

    // Manager for playing sound notifications
    SoundManager soundManager;

    // Constructor to initialize the GUINotifier with the main GUI instance
    public GUINotifier(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        soundManager = SoundManager.getSoundManagerInstance();
    }

    // Private method to display a notification with the given status message
    private void showNotification(String status) {
        if (!mainGUI.isCanChangeMoodPanel() || isShowingNotification) {
            return;
        }
        if (notificationTimer != null) {
            notificationTimer.cancel();
        }
        isShowingNotification = true;
        mainGUI.cancelAllTimers();
        mainGUI.showNamePanel(false);
        soundManager.playNotificationSound();
        mainGUI.setChatLabelText(status);

        notificationTimer = new Timer();
        notificationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mainGUI.showNamePanel(true);
                mainGUI.setChatLabelText("");
                notificationTimer.cancel();
                notificationTimer = null;
                isShowingNotification = false;
            }
        }, notificationTime);
    }

    // Public method to display a "Hungry" notification
    public void showHungryNotification() {
        showNotification(chatManager.showHungryStatus());
    }

    // Public method to display a "Love" notification
    public void showLoveNotification() {
        showNotification(chatManager.showLoveStatus());
    }

    // Public method to display a "Social" notification
    public void showSocialNotification() {
        showNotification(chatManager.showIsolatedStatus());
    }

    // Public method to display a "Play" notification
    public void showPlayNotification() {
        showNotification(chatManager.showBoredStatus());
    }

    // Public method to display a "Tired" notification
    public void showTiredNotification() {
        showNotification(chatManager.showTiredStatus());
    }

    // Public method to update the GUI, specifically the mood panel
    public void updateGUI(){
        mainGUI.setMoodPanel();
    }
}
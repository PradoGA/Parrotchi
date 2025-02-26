package main;

import uiPackage.MainGUI;
import java.util.Timer;
import java.util.TimerTask;

public class GUINotifier {
    private MainGUI mainGUI;
    private ChatManager chatManager = new ChatManager();
    private Timer notificationTimer;
    private int notificationTime = 2500;
    private boolean isShowingNotification = false;
    SoundManager soundManager;

    public GUINotifier(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        soundManager = SoundManager.getSoundManagerInstance();
    }

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

    public void showHungryNotification() {
        showNotification(chatManager.showHungryStatus());
    }

    public void showLoveNotification() {
        showNotification(chatManager.showLoveStatus());
    }

    public void showSocialNotification() {
        showNotification(chatManager.showIsolatedStatus());
    }

    public void showPlayNotification() {
        showNotification(chatManager.showBoredStatus());
    }

    public void showTiredNotification() {
        showNotification(chatManager.showTiredStatus());
    }

    public void updateGUI() {
        mainGUI.setMoodPanel();
    }
}
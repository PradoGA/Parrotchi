package Tasks;
import uiPackage.ActionsGUI;
import java.util.TimerTask;

//Task timer for Mini game quiz
public class GameTask extends TimerTask {
    private ActionsGUI actionsGUI;
    private int timeLeftInMinutes = 60; // 1 minute = 60 seconds


    public GameTask(ActionsGUI actionsGUI)
    {
        this.actionsGUI = actionsGUI;
    }

    @Override
    public void run() {
        timeLeftInMinutes--;
        if (timeLeftInMinutes <= 0) {
            this.cancel();
            timeLeftInMinutes = 60; // Reset timer

            actionsGUI.cancelEndTimeMiniGame();
        }
        actionsGUI.setTimeLabel(String.valueOf(timeLeftInMinutes));
    }
}

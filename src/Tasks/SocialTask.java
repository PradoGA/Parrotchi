package Tasks;
import main.Game;
import main.Tamagotchi;
import java.util.TimerTask;

//Task timer for Chatting
public class SocialTask extends TimerTask {
    private Tamagotchi pet;

    public SocialTask(Tamagotchi pet)
    {
        this.pet = pet;
    }

    @Override
    public void run() {
        if(pet.getSleepingStatus() || Game.getGameInstance().getMainGUI().isGamePaused()) {return;}

        // Update pet's status
        pet.increaseIsolation();



    }

}

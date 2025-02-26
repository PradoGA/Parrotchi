package Tasks;

import main.Game;
import main.Tamagotchi;

import java.util.TimerTask;

public class EnergyTask extends TimerTask {

    private Tamagotchi pet;

    public EnergyTask(Tamagotchi pet)
    {
        this.pet = pet;
    }

    @Override
    public void run() {
        if(pet.getSleepingStatus() || Game.getGameInstance().getMainGUI().isGamePaused()) {return;}
        // Update pet's status
        pet.increaseTiredness(1);



    }
}

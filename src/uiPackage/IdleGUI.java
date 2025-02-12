package uiPackage;

import javax.swing.*;
import java.awt.*;

public class IdleGUI extends JPanel{
    private JPanel panel1;
    private JPanel idlePanel;
    private JPanel eatingPanel;

    private final String IDLE_IMAGE = "images/parrot/idleImage.png";
    private final String EATING_IMAGE= "images/parrot/eatingImage.png";
    private Image currentImage;





    public JPanel getIddlePanel()
    {
        return idlePanel;
    }

    public  JPanel getEatingPanel()
    {
        return eatingPanel;
    }


}

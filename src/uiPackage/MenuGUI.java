package uiPackage;

import main.Game;
import main.LocalFileManager;
import main.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MenuGUI extends JDialog {
    private JPanel contentPane;

    private JButton saveButton;
    private JPanel saveCredits;
    private JPanel defaultPanel;
    private JPanel savePanel;
    private JPanel creditsPanel;
    private JButton creditsButton;
    private JButton slot1Button;
    private JButton slot2Button;
    private JTextArea designAndProgrammingGabrielaTextArea;
    private JPanel tittle2;
    private JButton exitGameButton;
    private JButton musicButton;
    private JButton soundButton;
    private JButton maiMenuButton;
    private JButton buttonOK;

    CardLayout pauseCardLayout;
    SoundManager soundManager;

    //Card Names
    private final String DEFAULT_CARD = "defaultCard";
    private final String SAVE_CARD = "saveCard";
    private final String CREDITS_CARD = "creditsCard";

    public MenuGUI()
    {

        pauseCardLayout = (CardLayout) saveCredits.getLayout();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.soundManager = SoundManager.getSoundManagerInstance();


        //MUSIC Button
        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                soundManager.toggleBackgroundMusic();
            }
        });

        //CREDITS BUTTON
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                pauseCardLayout.show(saveCredits, CREDITS_CARD);
            }
        });


        //SAVE BUTTON PANEL
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                pauseCardLayout.show(saveCredits, SAVE_CARD);
            }
        });


        //Exit Game Button
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                Game.getGameInstance().closeGame();
                Game.getGameInstance().exitGame();
                 soundManager.toggleBackgroundMusic();
                 dispose();
            }
        });


        //SAVE SLOT 1
        slot1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                LocalFileManager localFileManager= new LocalFileManager();
                try
                {
                    localFileManager.backUpSlotOne(Game.getGameInstance().getPet());
                } catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });

        //SAVE SLOT 2
        slot2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                LocalFileManager localFileManager= new LocalFileManager();
                try
                {
                    localFileManager.backUpSlotTwo(Game.getGameInstance().getPet());
                } catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }

        });

        //SOUNDS BUTTON
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                soundManager.toggleCanPlaySounds();
            }
        });

        //MAIN MENU BUTTON
        maiMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                Game game =  Game.getGameInstance();
                 WelcomeGUI welcomeGUI = game.getWelcomeGUI();
                 welcomeGUI.openWelcomeGUI(welcomeGUI);
                 game.closeGame();
                game.getMainGUI().dispose();

                   dispose();
            }
        });
    }



    public void openMenuGUI(MenuGUI menuGUI, MainGUI  mainGUI)
    {
        menuGUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Match your existing sound management pattern
                SoundManager.getSoundManagerInstance().playButtonSound();

                // Mirror pause handling from configButton in MainGUI
                mainGUI.setPaused(false);
            }
        });

        menuGUI.setContentPane((menuGUI.contentPane));
        menuGUI.setTitle("Parrotchi - Your Feather Friend - Pause Menu");
        menuGUI.setSize(460,800);
        menuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuGUI.setResizable(false); // Add this line to prevent resizing


        // Center window on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = mainGUI.getX() ;
        int y = mainGUI.getY() ;
        menuGUI.setLocation(x, y);
        menuGUI.setVisible(true);
    }
}

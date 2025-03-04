package uiPackage;

import main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class WelcomeGUI extends JFrame {
    private JPanel contentPane;
    private JPanel basePanel;
    private JPanel topPanel;
    private JPanel tittlePanel;
    private JPanel buttonsPanel;
    private JPanel optionsPanel;
    private JButton quitButton;
    private JButton soundButton;
    private JLabel tittle;
    private JButton quickButton;
    private JButton newButton;
    private JButton loadButton;
    private JPanel quickGamePanel;
    private JButton quickPlayButton;
    private JTextPane quickGamePerfectForTextPane;
    private JPanel newGamePanel;
    private JTextArea normalGameModeChooseTextArea;
    private JTextField nameField;
    private JButton playNormalButton;
    private JButton gitHubButton;
    private JPanel loadPanel;
    private JButton slot1Button;
    private JButton slot2Button;
    private JPanel defaultPanel;
    private JButton buttonOK;


    //Card Names
    private final String QUICK_CARD = "quickCard";
    private final String NEW_CARD = "newCard";
    private final String LOAD_CARD = "loadCard";
    private final String DEFAULT_CARD = "defaultCard";
    CardLayout menuCardLayout;

    SoundManager soundManager;
    private Point initialClick;




    public WelcomeGUI()
    {
        menuCardLayout = (CardLayout) optionsPanel.getLayout();
        this.soundManager = SoundManager.getSoundManagerInstance();

        // Customize the borders to the buttons
        String hexColor = "#FF6F61"; // Red hex color
        Color customColor = Color.decode(hexColor);
        Insets margin = new Insets(8, 10, 8, 10);
        Dimension buttonSize = new Dimension(60, 40);

        ArrayList<JButton> remakeButtons = new ArrayList<>();

        remakeButtons.add(soundButton);
        remakeButtons.add(playNormalButton);
        remakeButtons.add(quickPlayButton);

        for(JButton button : remakeButtons)
        {

            button.setMargin(margin);
            button.setPreferredSize(buttonSize);
        }

        //QUICK GAME BUTTON
        quickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                menuCardLayout.show(optionsPanel, QUICK_CARD);

            }
        });


        //NEW GAME LISTENER
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                menuCardLayout.show(optionsPanel, NEW_CARD);
            }
        });

        //LOAD LISTENER
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                menuCardLayout.show(optionsPanel, LOAD_CARD);
            }
        });

        //PLAY NORMAL GAME MODE
        playNormalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                if(nameField.getText() == null || nameField.getText().isEmpty() || nameField.getText().length()< 2 ){
                    JOptionPane.showMessageDialog(null,"Insert a name","Missing Name",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    TutorialGUI tutorialGUI = new TutorialGUI();
                    tutorialGUI.setCurrentName(nameField.getText());
                    tutorialGUI.openTutorialGUI(tutorialGUI, Game.getWelcomeGUI(),1);
                    dispose();
                }

            }


        });


        // PLAY QUICK GAME BUTTON
        quickPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                JOptionPane.showMessageDialog(null,"ATTENTION:  This game mode is for demo purposes and isn't a full gameplay experience.","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
//
                TutorialGUI tutorialGUI = new TutorialGUI();
                tutorialGUI.openTutorialGUI(tutorialGUI, Game.getWelcomeGUI(),0);
                dispose();
            }
        });


        //QUIT BUTTON
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                System.exit(0);
            }
        });

        //SOUND BUTTON
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                soundManager.toggleBackgroundMusic();
            }
        });


        //LOAD SLOT 1 BUTTON
        slot1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                LocalFileManager localFileManager = new LocalFileManager();
                try
                {
                    Tamagotchi loadedPet = localFileManager.loadSlotOne();
                    int selection = JOptionPane.showConfirmDialog(null,"Load " + loadedPet.getName() + " The Parrot?","Confirm",JOptionPane.YES_NO_OPTION);
                    //selection :  0=yes, 1=no, 2=cancel
                    if(selection == 0 )
                    {
                        Game.getGameInstance().startLoadedGame(loadedPet);
                        System.out.println("PET LOADED: " + Game.getGameInstance().getPet());
                    }
                  else
                    {
                        loadedPet = null;
                    }


                } catch (IOException ex)
                {
                    System.out.println("NOT SAVED FILE FOUND");
                    JOptionPane.showMessageDialog(null,"Empty Slot","Missing File",JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex)
                {

                    throw new RuntimeException(ex);
                }
            }
        });

        //LOAD Second Slot
        slot2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                LocalFileManager localFileManager = new LocalFileManager();
                try
                {
                    Tamagotchi loadedSPet = localFileManager.loadSlotTwo();
                    int selection = JOptionPane.showConfirmDialog(null,"Load " + loadedSPet.getName() + "The Parrot?","Confirm",JOptionPane.YES_NO_OPTION);
                    //selection :  0=yes, 1=no
                    if(selection == 0 )
                    {
                        Game.getGameInstance().startLoadedGame(loadedSPet);
                        System.out.println("PET LOADED: " + Game.getGameInstance().getPet());
                    }
                    else
                    {
                        loadedSPet = null;
                    }


                } catch (IOException ex)
                {
                    System.out.println("NOT SAVED FILE FOUND");
                    JOptionPane.showMessageDialog(null,"Empty Slot","Missing File",JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex)
                {

                    throw new RuntimeException(ex);
                }
            }
        });


        //MOUSE LISTENER
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                initialClick = e.getPoint();
                super.mousePressed(e);

            }
        });
        contentPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e)
            {

                int thisX = WelcomeGUI.this.getLocation().x;
                int thisY = WelcomeGUI.this.getLocation().y;

                // Calculate new location
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int newX = thisX + xMoved;
                int newY = thisY + yMoved;

                // Move window
                WelcomeGUI.this.setLocation(newX, newY);
                super.mouseDragged(e);

            }
        });
    }

    public void openWelcomeGUI(WelcomeGUI welcomeGUI)
    {

        welcomeGUI.setContentPane((welcomeGUI.contentPane));
        welcomeGUI.setTitle("Parrotchi - Your Feather Friend");
        welcomeGUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        welcomeGUI.setResizable(false); // Add this line to prevent resizing
        welcomeGUI.setUndecorated(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenSize.height < 860)
        {
            welcomeGUI.setSize(460,700);
        }
        else {
            welcomeGUI.setSize(460,800);
        }


        // Center window on screen

        int x = (screenSize.width - welcomeGUI.getWidth()) / 2;
        int y = 0;
        welcomeGUI.setLocation(x, y);
        menuCardLayout.show(optionsPanel, DEFAULT_CARD);
        System.out.println(screenSize.height);
        welcomeGUI.setVisible(true);

    }


}

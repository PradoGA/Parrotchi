package uiPackage;

import main.Game;
import main.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TutorialGUI extends JDialog {
    private JPanel contentPane;
    private JButton skipButton;
    private JPanel cardsPanel;
    private JButton backButton;
    private JButton nextButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    private JButton buttonOK;

    CardLayout tutorialCardLayout;
    SoundManager soundManager;
    int currentCard = 1;
    int typeOfGame = -1; //0 quick, 1 normal
    String currentName;
    private Point initialClick;


    public TutorialGUI()
    {

        tutorialCardLayout = (CardLayout) cardsPanel.getLayout();
        this.soundManager = SoundManager.getSoundManagerInstance();

        backButton.setVisible(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);



        //SKIP BUTTON
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                if(typeOfGame == 0)
                {
                    Game.getGameInstance().startQuickGame();
                }
                if(typeOfGame == 1)
                {
                    Game.getGameInstance().startNormalGame(currentName);
                }
                dispose();
            }
        });

        //BACK BUTTON
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                currentCard--;
                if(currentCard==1)
                {
                    backButton.setVisible(false);
                }
                if(currentCard < 8)
                {
                    nextButton.setText("Next");
                }
                tutorialCardLayout.show(cardsPanel,"Card"+ String.valueOf(currentCard));
            }
        });

        //NEXT BUTTON
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                if(currentCard >= 8)
                {
                    if(typeOfGame == 0)
                    {
                        Game.getGameInstance().startQuickGame();
                    }
                    if(typeOfGame == 1)
                    {
                        Game.getGameInstance().startNormalGame(currentName);
                    }

                    dispose();
                }
                currentCard++;
                tutorialCardLayout.show(cardsPanel,"Card"+ String.valueOf(currentCard));
                if(currentCard == 8)
                {
                    nextButton.setText("Start Game");
                }
                if(currentCard>1)
                {
                    backButton.setVisible(true);
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

                int thisX = TutorialGUI.this.getLocation().x;
                int thisY = TutorialGUI.this.getLocation().y;

                // Calculate new location
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int newX = thisX + xMoved;
                int newY = thisY + yMoved;

                // Move window
                TutorialGUI.this.setLocation(newX, newY);
                super.mouseDragged(e);

            }
        });

    }

    public void openTutorialGUI(TutorialGUI tutorialGUI, WelcomeGUI welcomeGUI, int gameType)
    {
        this.typeOfGame = gameType;
        tutorialGUI.setContentPane((tutorialGUI.contentPane));
        tutorialGUI.setTitle("Parrotchi - Your Feather Friend - Tutorial");
        tutorialGUI.setSize(460,800);
        tutorialGUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        tutorialGUI.setResizable(false);
        tutorialGUI.setUndecorated(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = welcomeGUI.getX();
        int y = welcomeGUI.getY();
        tutorialGUI.setLocation(x, y);
        tutorialGUI.setVisible(true);



    }

    public void setCurrentName(String name)
    {
        this.currentName = name;
    }


}

package uiPackage;

import Tasks.GameTask;
import main.Game;
import main.SoundManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Timer;

public class ActionsGUI extends JPanel{
    private JPanel panel1;
    private JPanel idlePanel;
    private JPanel eatingPanel;
    private JPanel chattingPanel;
    private JPanel playingPanel;


    private JPanel PetPanel;

    private JButton cancelButton;
    private JButton sendButton;
    private JTextArea textArea1;
    private JPanel happyPanel;
    private JPanel neutralPanel;
    private SoundManager soundManager = new SoundManager();



    private JPanel sadPanel;
    private JPanel angryPanel;


    private JPanel sleepingPanel;
    private JLabel quizLabel;


    private JTextField quizField;
    private JButton cancelButton1;
    private JButton sendButton1;
    private JLabel timeLabel;

    private Timer gameTimer;




    public ActionsGUI()
    {


        //Send Message
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                String currentText;
                currentText = textArea1.getText();
                Game.getGameInstance().getMainGUI().sendMessage(currentText);
                textArea1.setText(" ");
                soundManager.playParrotSound();
            }
        });

        //CANCEL CHAT
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                Game.getGameInstance().getMainGUI().closeChat();
            }
        });
        quizField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e)
            {
                quizField.setText(Character.toString(quizField.getText().charAt(0)));
                super.focusGained(e);
            }
        });

        //GET ANSWER MINIGAME
        sendButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                String userAnswer = quizField.getText();
                Game.getGameInstance().getMainGUI().closeMinigame(userAnswer);
                if (gameTimer != null) {
                    gameTimer.cancel();
                }
            }
        });


        //CANCEL MINIGAME
        cancelButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                if (gameTimer != null) {
                    gameTimer.cancel();
                }
                Game.getGameInstance().getMainGUI().cancelMinigame();
            }
        });
    }

    public void cancelEndTimeMiniGame()
    {
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        JOptionPane.showMessageDialog(null,"Sorry, Run out if time","Ooops!",JOptionPane.INFORMATION_MESSAGE);
        Game.getGameInstance().getMainGUI().cancelMinigame();
    }

    public JPanel getIdlePanel()
    {
        return idlePanel;
    }

    public  JPanel getEatingPanel()
    {
        return eatingPanel;
    }

    public JPanel getChattingPanel()
    {
        return chattingPanel;
    }
    public JPanel getSleepingPanel()
    {
        return sleepingPanel;
    }


    public void setQuizLabelAndTimer(String quizLabel)
    {
        this.quizLabel.setText(quizLabel);
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        gameTimer = new Timer();
        gameTimer.schedule(new GameTask(this), 0,1000);

    }

    public void setTimeLabel(String time)
    {
        timeLabel.setText(time + " seconds");
    }



    public void setQuizField(String quizField)
    {
        this.quizField.setText(quizField);
    }


    public JPanel getPlayingPanel()
    {
        return playingPanel;
    }

    public JPanel getPetPanel()
    {
        return PetPanel;
    }

    public JPanel getHappyPanel()
    {
        return happyPanel;
    }

    public JPanel getNeutralPanel()
    {
        return neutralPanel;
    }

    public JPanel getSadPanel()
    {
        return sadPanel;
    }

    public JPanel getAngryPanel()
    {
        return angryPanel;
    }


}

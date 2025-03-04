package uiPackage;

import main.*;
import minigames.ExtractRedText;
import minigames.MiniGameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;



public class MainGUI extends JFrame{
    private JPanel panelMain;
    private JPanel basePanel;
    private JPanel topBar;
    private JSlider slider1;
    private JButton configButton;
    private JPanel bottomBar;
    private JButton feedButton;
    private JButton petButton;
    private JButton chatButton;
    private JButton playButton;
    private JPanel chatPanel;
    private JPanel spritePanel;
    private JPanel assetPanel;
    private JLabel chatLabel;
    private JLabel startImage;
    private JLabel moodLabel;
    private JButton questionButton;
    private JLabel nameLabel;


    SoundManager soundManager;
    Moods currentMood;
    AIParrot parrot;
    private ActionsGUI actionsGUI;
    private  CardLayout assetsCardLayout;
    ChatManager chatManager;
    MiniGameManager miniGameManager;
    TextFormatter textFormatter = new TextFormatter();
    private Timer questionTimer;
    private int questionTime = 3000;
    private Timer feedTimer;
    private int feedTime = 2500;
    private Timer petTimer;
    private int petTime = 2500;
    private Timer sleepingTimer;
    private int sleepTime = 12000;
    private Point initialClick;



    private boolean isPaused = false;




    //Cards Names
    private final String IDLE_PANEL = "Idle Panel";
    private final String BASE_PANEL = "Base Panel";
    private final String EATING_PANEL = "Eating Panel";
    private final String PET_PANEL = "Pet Panel";
    private final String CHATTING_PANEL = "Chatting Panel";
    private final String PLAYING_PANEL = "Playing Panel";
    private final String SLEEPING_PANEL = "Sleeping Panel";
    private final String BUBBLE_CARD = "bubbleCard";
    private final String NAME_CARD = "nameCard";
    private final String HAPPY_PANEL = "Happy Panel";
    private final String NEUTRAL_PANEL = "Neutral Panel";
    private final String ANGRY_PANEL = "Angry Panel";
    private final String SAD_PANEL = "Sad Panel";


    //emojis Images paths:
    // Emojis Images paths:
    private String happyImg = "assets/emojis/icons8-happy-64.png";
    private String neutralImg = "assets/emojis/icons8-neutral-64.png";
    private String angryImg = "assets/emojis/icons8-angry-64.png";
    private String sadImg = "assets/emojis/icons8-sad-64.png";


    boolean canChangeMoodPanel = true;


    public MainGUI() {



        // Initialize the Chat Manager
        chatManager =  new ChatManager();
        miniGameManager = new MiniGameManager();
      parrot = new AIParrot();
        setSoundManager();
        soundManager.playBackgroundMusic();

        showNamePanel(true);



        // Initialize the CardLayout and set it to the blankPanel
        assetsCardLayout = new CardLayout();
        spritePanel.setLayout(assetsCardLayout);
        actionsGUI = new ActionsGUI();
        spritePanel.add(assetPanel, BASE_PANEL);
        spritePanel.add(actionsGUI.getIdlePanel(),IDLE_PANEL);
        spritePanel.add(actionsGUI.getEatingPanel(),EATING_PANEL);
        spritePanel.add(actionsGUI.getPetPanel(),PET_PANEL);
        spritePanel.add(actionsGUI.getChattingPanel(),CHATTING_PANEL);
        spritePanel.add(actionsGUI.getPlayingPanel(),PLAYING_PANEL);
        spritePanel.add(actionsGUI.getHappyPanel(),HAPPY_PANEL);
        spritePanel.add(actionsGUI.getNeutralPanel(),NEUTRAL_PANEL);
        spritePanel.add(actionsGUI.getSadPanel(),SAD_PANEL);
        spritePanel.add(actionsGUI.getAngryPanel(),ANGRY_PANEL);
        spritePanel.add(actionsGUI.getSleepingPanel(), SLEEPING_PANEL);





        // Customize the borders to the buttons
        String hexColor = "#FF6F61"; // Red hex color
        Color customColor = Color.decode(hexColor);
        Insets margin = new Insets(18, 10, 18, 10);
        Dimension buttonSize = new Dimension(90, 90);
        Dimension configButtonSize = new Dimension(80 , 40);


        ArrayList<JButton> petsButtons= new ArrayList<>();
        petsButtons.add(feedButton);
        petsButtons.add(petButton);
        petsButtons.add(chatButton);
        petsButtons.add(playButton);


        for(JButton button : petsButtons)
        {
            button.setBorderPainted(true);
            button.setBorder(new RoundedBorder(customColor, 3));
            button.setMargin(margin);
            button.setPreferredSize(buttonSize);
        }

       configButton.setPreferredSize(configButtonSize);
        questionButton.setPreferredSize(configButtonSize);


        //FEED button
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundManager.playButtonSound();
                cancelAllTimers();
                showNamePanel(false);
                toggleButtons(false, feedButton);
                canChangeMoodPanel = false;

                if(! Game.getGameInstance().getPet().getHungryStatus())
                {

                    soundManager.playDontSound();
                    chatLabel.setText(textFormatter.FixBubbleText("I'm not hungry"));

                }
                else
                {
                    assetsCardLayout.show(spritePanel,EATING_PANEL);
                    Game.getGameInstance().getPet().feed();
                    soundManager.playThankYouSound();
                    chatLabel.setText(textFormatter.FixBubbleText("Yummy, thanks for the food "));
                }


                // Schedule the second setText to happen after 5 seconds (5000 ms)
                feedTimer = new Timer(feedTime, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Changing to idle status");
                        chatLabel.setText("");
                      //  chatLabel.setText(textFormatter.FixBubbleText(currentStatus));
                        toggleButtons(true, feedButton);
                        showNamePanel(true);
                        feedTimer.stop();
                        feedTimer = null;
                        canChangeMoodPanel = true;
                        setMoodPanel();
                    }
                });
                feedTimer.start();

            }
        });

            //PET BUTTON
        petButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canChangeMoodPanel = false;
                soundManager.playButtonSound();
                //Need to stop all the timers before
                cancelAllTimers();
                showNamePanel(false);
                toggleButtons(false, petButton);
                assetsCardLayout.show(spritePanel,PET_PANEL);
                Game.getGameInstance().getPet().pet();
                chatLabel.setText(textFormatter.FixBubbleText(chatManager.showPetResponse()));
                soundManager.playLoveSound();


                // Schedule the second setText to happen after 2.5 seconds (2500 ms)
                petTimer = new Timer(petTime, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Changing to idle status");
                        chatLabel.setText("");
                        toggleButtons(true, petButton);
                        showNamePanel(true);
                        petTimer.stop();
                        petTimer = null;
                        canChangeMoodPanel = true;
                        setMoodPanel();

                    }
                });
                petTimer.start();
            }
        });


        //MENU BUTTON
        configButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                soundManager.playButtonSound();
                setPaused(true);
                MenuGUI menuGUI = new MenuGUI();
                menuGUI.openMenuGUI(menuGUI, MainGUI.this);
//
//

            }
        });

        //CHAT BUTTON
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                soundManager.playButtonSound();
                cancelAllTimers();
                chatLabel.setText("What's the craic?");
                showNamePanel(false);
                toggleButtons(false, chatButton);
                canChangeMoodPanel = false;
                assetsCardLayout.show(spritePanel,CHATTING_PANEL);



            }
        });

        //PLAY BUTTON
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                canChangeMoodPanel = false;
                soundManager.playButtonSound();
                Game.getGameInstance().getPet().play();
                cancelAllTimers();
                showNamePanel(false);
                toggleButtons(false, playButton);
                assetsCardLayout.show(spritePanel,PLAYING_PANEL);
                soundManager.playPlaySound();
                soundManager.changeBackgroundMusic(true);

                miniGameManager.getRandomPhrase();
                String currentPhrase = miniGameManager.getCurrentPhrase();
                chatLabel.setText( currentPhrase);
                String answer =  miniGameManager.getCurrentAnswer().toUpperCase();

                String placeHolder = "_ ".repeat(answer.length() - 1);
                actionsGUI.setQuizField(answer.charAt(0) + placeHolder);
                ExtractRedText extractRedText = new ExtractRedText();
                String irishWord = extractRedText.extractText(currentPhrase);
                actionsGUI.setQuizLabelAndTimer("<html> <body style='width: 100px; text-align: center;'>Guess the Irish word: <h1><span style='color: red;'>"+ irishWord + "</span></h1>in English?</body> </html>");
            }
        });


        //CHECK STATUS BUTTON
        questionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                canChangeMoodPanel = false;
                soundManager.playHelloSound();
                cancelAllTimers();
                showNamePanel(false);
                toggleButtons(false, questionButton);
                String text = chatManager.showCurrentNeedResponse(Game.getGameInstance().getPet());
                chatLabel.setText(textFormatter.FixBubbleText(text));

                // Schedule the second setText to happen after 3 seconds (3000 ms)
                questionTimer = new Timer(questionTime, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showNamePanel(true);
                        System.out.println("Contando");
                        chatLabel.setText("");
                        questionTimer.stop();
                        questionTimer = null;
                        toggleButtons(true, questionButton);
                        canChangeMoodPanel = true;
                    }
                });
                questionTimer.start();



            }
        });

        //MOUSE LISTENER
        basePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                initialClick = e.getPoint();
                System.out.println("FIRST CLICK");
                super.mousePressed(e);
            }
        });
        basePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e)
            {

                int thisX = MainGUI.this.getLocation().x;
                int thisY = MainGUI.this.getLocation().y;

                // Calculate new location
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int newX = thisX + xMoved;
                int newY = thisY + yMoved;

                // Move window
                MainGUI.this.setLocation(newX, newY);
                super.mouseDragged(e);
                super.mouseDragged(e);
            }
        });
    }

    //SlEEP
    public void sleepBehaviour()
    {
        System.out.println("STARTING SLEEPING ");
        canChangeMoodPanel = false;
        setPaused(true);
        //Need to stop all the timers before
        cancelAllTimers();
        showNamePanel(true);
        toggleButtons(false, null);
        assetsCardLayout.show(spritePanel,SLEEPING_PANEL);
        soundManager.playSleepSound();


        sleepingTimer = new Timer(sleepTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getGameInstance().getPet().sleep();
                System.out.println("Changing to idle status");
                chatLabel.setText("");
                toggleButtons(true, null);
                showNamePanel(true);
                sleepingTimer.stop();
                sleepingTimer = null;
                canChangeMoodPanel = true;
                setPaused(false);
                setMoodPanel();

            }
        });
        sleepingTimer.start();
    }

    //CANCEL ALL UI TIMERS
    public void cancelAllTimers()
    {
        if(petTimer != null)
        {
            petTimer.stop();
        }
        if(feedTimer != null)
        {
            feedTimer.stop();
        }

        //Check if question timer is on
        if(questionTimer != null)
        {
            questionTimer.stop();
        }

        if(sleepingTimer !=null)
        {
            sleepingTimer.stop();
        }

    }



    public static void openMainGui(MainGUI myGui, WelcomeGUI welcomeGUI)
    {

        myGui.setContentPane((myGui.panelMain));
        myGui.setTitle("Parrotchi - Your Feather Friend");
        myGui.setSize(460,800);
        myGui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        myGui.setUndecorated(true);
        myGui.setResizable(false); // Add this line to prevent resizing


        // Center window on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = welcomeGUI.getX();
        int y = welcomeGUI.getY();
        myGui.setLocation(x, y);
        myGui.setVisible(true);
    }




    public void toggleButtons(boolean value, JButton currentButton)
    {

        configButton.setEnabled(value);
        feedButton.setEnabled(value);
        petButton.setEnabled(value);
        chatButton.setEnabled(value);
        playButton.setEnabled(value);
        questionButton.setEnabled(value);
        if(currentButton == null){return;}
        currentButton.setSelected(!value);
    }

    public void greet()
    {
        showNamePanel(false);
        String text = chatManager.showPetGreetings();
        chatLabel.setText(textFormatter.FixBubbleText(text));
    }
    public void sendMessage(String userInput)
    {
        String response = parrot.talkToParrot(userInput, Game.getGameInstance().getPet());
        chatLabel.setText(textFormatter.FixBubbleText(response));
        Game.getGameInstance().getPet().chat();

    }

    public void closeMinigame(String userAns)
    {
        if(miniGameManager.isCorrect(userAns))
        {
            soundManager.playCorrectSound();
            JOptionPane.showMessageDialog(null,"Congrats! your parrot had fun!","Correct!",JOptionPane.INFORMATION_MESSAGE);
            Game.getGameInstance().getPet().play();

        }
        else
        {
            soundManager.playFailSound();
            JOptionPane.showMessageDialog(null,"Sorry, Wrong Answer","Oh no!",JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println("Changing to idle status");

        chatLabel.setText("");
        toggleButtons(true,playButton);
        showNamePanel(true);
        soundManager.changeBackgroundMusic(false);
        canChangeMoodPanel = true;
        setMoodPanel();

    }

    public void cancelMinigame()
    {
        System.out.println("Changing to idle status");

        chatLabel.setText("");
        toggleButtons(true, playButton);
        showNamePanel(true);
        soundManager.changeBackgroundMusic(false);
        canChangeMoodPanel = true;
        setMoodPanel();
    }

    public void setChatLabelText(String string)
    {
        chatLabel.setText(textFormatter.FixBubbleText(string));
    }


    public void showNamePanel(boolean value)
    {

        if (chatPanel.getLayout() == null) {
            chatPanel.setLayout(new CardLayout());
        }
        CardLayout topCardLayout = (CardLayout) chatPanel.getLayout();
        if(value == true)
        {

           topCardLayout.show(chatPanel, NAME_CARD);
            System.out.println("CHANGING TO NAME CARD");


        }
        if(value == false)
        {
            topCardLayout.show(chatPanel, BUBBLE_CARD);
        }
    }

    public void setMoodPanel()
    {


            currentMood = Game.getGameInstance().getPet().getMoodManager().getCurrentMood();

            if(canChangeMoodPanel)
            {
                switch (currentMood)
                {
                    case Happy -> {
                        assetsCardLayout.show(spritePanel,HAPPY_PANEL);
                    }
                    case Neutral -> {
                        assetsCardLayout.show(spritePanel,NEUTRAL_PANEL);
                    }
                    case Angry -> {
                        assetsCardLayout.show(spritePanel,ANGRY_PANEL);
                    }
                    case Sad -> {
                        assetsCardLayout.show(spritePanel,SAD_PANEL);
                    }
                }
            }

        changeMoodLabel(currentMood);
        System.out.println("CURRENT MOOD UI CHANGED TO " + currentMood);
        System.out.println("MOOD COUNTER RECIVED: "  + Game.getGameInstance().getPet().getMoodManager().getMoodCounter());
    }
    public void changeMoodLabel(Moods currentMood)
    {


        switch (currentMood)
        {
            case Happy -> {

                moodLabel.setIcon(actionsGUI.getHappyIco().getIcon());
            }
            case Neutral -> {

                moodLabel.setIcon(actionsGUI.getNeutralIco().getIcon());
            }
            case Angry -> {

                moodLabel.setIcon(actionsGUI.getAngryIco().getIcon());
                soundManager.playAngrySound();
            }
            case Sad -> {
                moodLabel.setIcon(actionsGUI.getSadIco().getIcon());

            }
        }
        moodLabel.setText(currentMood.toString());

    }

    public void closeChat()
    {


        showNamePanel(true);
        toggleButtons(true, chatButton);
        chatLabel.setText(" ");
        canChangeMoodPanel = true;
        setMoodPanel();

    }



    public void setNameLabel(String name)
    {
        nameLabel.setText(name + " The Parrot");
    }

    private void setSoundManager()
    {
        this.soundManager = SoundManager.getSoundManagerInstance();
    }

    public boolean isCanChangeMoodPanel()
    {
        return canChangeMoodPanel;
    }


    public boolean isGamePaused()
    {
        return isPaused;
    }

    public void setPaused(boolean paused)
    {
        isPaused = paused;
    }

    public void gameOver(String status)
    {

        soundManager.playFailSound();
        chatLabel.setText(textFormatter.FixBubbleText("You didn't take care of me, BYE BYE"));
        JOptionPane.showMessageDialog(null,Game.getGameInstance().getPet().getName() + " Was too " +status+ " and decided to leave.","Your parrot is gone",JOptionPane.INFORMATION_MESSAGE);
        Game game =  Game.getGameInstance();
        WelcomeGUI welcomeGUI = game.getWelcomeGUI();
        welcomeGUI.openWelcomeGUI(welcomeGUI);
        game.closeGame();
        dispose();


    }

}




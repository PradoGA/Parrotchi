package uiPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private JLabel feedNotifier;
    private JLabel playNotifier;
    private JPanel chatPanel;
    private JPanel spritePanel;
    private JPanel assetPanel;
    private JLabel chatLabel;
    private JLabel startImage;


    private IdleGUI idleGUI;
    private  CardLayout cardLayout;


    //Cards Names
    private final String IDLE_PANEL = "Idle Panel";
    private final String BASE_PANEL = "Base Panel";
    private final String EATING_PANEL = "Eating Panel";


    public MainGUI() {
        // Initialize the CardLayout and set it to the blankPanel
        cardLayout = new CardLayout();
        spritePanel.setLayout(cardLayout);
        idleGUI= new IdleGUI();
        spritePanel.add(assetPanel, BASE_PANEL);
        spritePanel.add(idleGUI.getIddlePanel(),IDLE_PANEL);
        spritePanel.add(idleGUI.getEatingPanel(),EATING_PANEL);

        //Customizing slider
        // Customize the slider
        slider1 = new JSlider();
        slider1.setUI(new CustomSliderUI(slider1));
        slider1.revalidate();
        slider1.repaint();


        // Customize the borders to the buttons
        String hexColor = "#FF6F61"; // Red hex color
        Color customColor = Color.decode(hexColor);
        Insets margin = new Insets(18, 10, 18, 10);
        Dimension buttonSize = new Dimension(90, 90);
        Insets configMargin = new Insets(6, 6, 6, 6);
        Dimension configButtonSize = new Dimension(40 , 40);


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
        configButton.setBorderPainted(true);
        configButton.setBorder(new RoundedBorder(customColor, 3));
        configButton.setMargin(margin);
        configButton.setPreferredSize(configButtonSize);


        //test button
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(spritePanel,EATING_PANEL);
                System.out.println("Changing to idle status");
                System.out.println(cardLayout);
            }
        });

        petButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(spritePanel,IDLE_PANEL);
            }
        });
    }


    public void changeToIdlePanel()
    {

    }

    public static void openMainGui(MainGUI myGui)
    {

        myGui.setContentPane((myGui.panelMain));
        myGui.setTitle("Parrot Game");
        myGui.setSize(460,800);
        myGui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        myGui.setVisible(true);
    }



    // Custom border class for rounded corners
    private static class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final int thickness;

        public RoundedBorder(Color color, int thickness) {
            this.color = color;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRoundRect(x, y, width - 1, height - 1, 8, 8);
        }
    }

    // Custom Slider UI class
    private static class CustomSliderUI extends BasicSliderUI {
        private BufferedImage thumbImage;

        public CustomSliderUI(JSlider slider) {
            super(slider);
            try {
                thumbImage = ImageIO.read(new File("src/images/icons/icons8-heart-with-dog-paw-24.png")); // Replace with your image path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void paintThumb(Graphics g) {
            if (thumbImage != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int x = thumbRect.x + (thumbRect.width - thumbImage.getWidth()) / 2;
                int y = thumbRect.y + (thumbRect.height - thumbImage.getHeight()) / 2;
                g2d.drawImage(thumbImage, x, y, null);
                g2d.dispose();
            } else {
                super.paintThumb(g);
            }
        }
    }


}

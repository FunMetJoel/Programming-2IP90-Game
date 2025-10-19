package menu;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import gameEngine.GameCanvas;
import gameEngine.InputManager;
import gameObjects.GameScene.*;

/**
 * The menu shown before the game starts.
 */
public class StartMenu extends JLayeredPane implements ComponentListener {
    BackgroundImage background;
    JPanel mainPanel;
    boolean startClicked = false;

    public boolean getStartStatus() {
        return startClicked;
    }

    /**
     * Creates the menu object.
     */
    public StartMenu() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDoubleBuffered(true);
        this.setSize(getPreferredSize());
        this.addComponentListener(this);
        
        this.background = new BackgroundImage();
        this.add(background, Integer.valueOf(0));

        this.mainPanel = new JPanel();
        this.mainPanel.setOpaque(false);
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel, Integer.valueOf(1));

        // JLabel title = new AnimatedTitle().title;
        // title.setAlignmentX(CENTER_ALIGNMENT);
        // title.setForeground(new Color(5, 9, 245));

        AnimatedTitle title = new AnimatedTitle();
        title.setAlignmentX(CENTER_ALIGNMENT);

        // try {
        //     File fontFile = new File("assets/RushDriver-Italic.otf");
        //     Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(100f);
        //     title.setFont(font);
        // } catch(Exception e) {
        //     e.printStackTrace();
        // }

        this.mainPanel.add(title, Integer.valueOf(1));

        JPanel inputGroupPanel = new JPanel();
        inputGroupPanel.setOpaque(false);
        inputGroupPanel.setLayout(new GridLayout(3, 1, 0, 5));
        inputGroupPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        this.mainPanel.add(inputGroupPanel);

        JTextField seedInput = new JTextField("Seed");
        seedInput.setAlignmentX(CENTER_ALIGNMENT);
        seedInput.setHorizontalAlignment(JTextField.CENTER);
        inputGroupPanel.add(seedInput, Integer.valueOf(1));

        JButton newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(CENTER_ALIGNMENT);
        newGameButton.setBackground(new Color(88, 255, 10));
        newGameButton.setForeground(Color.WHITE);

        newGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            Border border = BorderFactory.createLineBorder(Color.GRAY, 8);
            public void mouseEntered(java.awt.event.MouseEvent e) {
                newGameButton.setBorder(border);
                newGameButton.setBorderPainted(true);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                newGameButton.setBorderPainted(false);
            }
        });
        

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startClicked = true;
                // System.out.println("START WAS CLICKED");
            }
        });

        inputGroupPanel.add(newGameButton, Integer.valueOf(1));

        JButton quitGameButton = new JButton("Quit Game");
        quitGameButton.setAlignmentX(CENTER_ALIGNMENT);
        quitGameButton.setBackground(new Color(232, 14, 14));
        quitGameButton.setForeground(Color.WHITE);

        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        inputGroupPanel.add(quitGameButton, Integer.valueOf(1));
        
        this.componentResized(null);
        this.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.background.setBounds(0, 0, this.getWidth(), this.getHeight());
        int padding = this.getWidth() / 10;
        this.mainPanel.setBounds(
            padding, padding, 
            getWidth() - 2 * padding, getHeight() - 2 * padding
        );
    }

    @Override
    public void componentHidden(ComponentEvent e) { }

    @Override
    public void componentMoved(ComponentEvent e) { }

    @Override
    public void componentShown(ComponentEvent e) { }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Main menu testing");

        StartMenu menu = new StartMenu();
        window.add(menu);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

package menu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartMenu extends JLayeredPane implements ComponentListener {
    BackgroundImage background;
    JPanel mainPanel;

    public StartMenu() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDoubleBuffered(true);
        this.setSize(getPreferredSize());
        this.addComponentListener(this);
        
        this.background = new BackgroundImage();
        this.background.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(background, Integer.valueOf(0));

        this.mainPanel = new JPanel();
        this.mainPanel.setOpaque(true);
        this.mainPanel.setBounds(this.getWidth()/10, this.getHeight()/10, this.getWidth() - 2*(this.getWidth()/10), this.getHeight() - 2*(this.getHeight()/10));
        this.add(mainPanel, Integer.valueOf(1));


        // JLabel title = new JLabel("NAME"); 
        // this.add(title, Integer.valueOf(1));


        // JTextField seedInput = new JTextField("Seed");
        // this.add(seedInput, Integer.valueOf(1));

        // JButton newGameButton = new JButton("New Game");
        // this.add(newGameButton, Integer.valueOf(1));

        this.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.background.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.mainPanel.setBounds(this.getWidth()/10, this.getHeight()/10, this.getWidth() - 2*(this.getWidth()/10), this.getHeight() - 2*(this.getHeight()/10));
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

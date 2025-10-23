package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * The menu shown before the game starts.
 */
public class StartMenu extends JLayeredPane implements ComponentListener {
    // BackgroundImage background;
    GradientBackground background;
    JPanel mainPanel;
    boolean startClicked = false;
    public int seed;

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
        
        this.background = new GradientBackground();
        this.add(background, Integer.valueOf(0));

        this.mainPanel = new JPanel();
        this.mainPanel.setOpaque(false);
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel, Integer.valueOf(1));

        AnimatedTitle title = new AnimatedTitle("COOL GAME");
        title.setAlignmentX(CENTER_ALIGNMENT);

        this.mainPanel.add(title, Integer.valueOf(1));

        JPanel inputGroupPanel = new JPanel();
        inputGroupPanel.setOpaque(false);
        inputGroupPanel.setLayout(new GridLayout(4, 1, 0, 10));
        inputGroupPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        this.mainPanel.add(inputGroupPanel);

        JTextField seedInput = new JTextField("Input custom seed");
        seedInput.setAlignmentX(CENTER_ALIGNMENT);
        seedInput.setHorizontalAlignment(JTextField.CENTER);
        seedInput.setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
        inputGroupPanel.add(seedInput, Integer.valueOf(1));

        seedInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (seedInput.getText().equals("Input custom seed")) {
                    seedInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (seedInput.getText().equals("")) {
                    seedInput.setText("Input custom seed");
                }
            }
        });

        ActionButton newGame = new ActionButton("New Game", new Color(2, 110, 36));
        inputGroupPanel.add(newGame, Integer.valueOf(1));

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startClicked = true;
                String textValue = seedInput.getText();

                try {
                    int newSeed = Integer.parseInt(textValue);
                    seed = newSeed;
                } catch (NumberFormatException exception) {
                    seed = 19;
                }

                System.out.println(textValue);
                // System.out.println("START WAS CLICKED");
            }
        });

        ActionButton quitGame = new ActionButton("Quit Game", new Color(130, 1, 18));
        inputGroupPanel.add(quitGame, Integer.valueOf(1));

        quitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        this.componentResized(null);
        this.setVisible(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartMenu.this.requestFocusInWindow();
            }
        });
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
}

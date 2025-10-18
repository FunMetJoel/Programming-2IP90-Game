package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AnimatedTitle extends JFrame{
    JLabel title;
    float titleSize = 80f;
    boolean isGrowing = true;

    public AnimatedTitle() {
        setPreferredSize(new Dimension(800, 150));
        setMinimumSize(new Dimension(800, 500)); 
        setMaximumSize(new Dimension(800, 500)); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        title = new JLabel("COOL GAME");
        try {
            File fontFile = new File("assets/RushDriver-Italic.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            title.setFont(font);
        } catch(Exception e) {
            e.printStackTrace();
        }

        add(title, BorderLayout.CENTER);

        Timer timer = new Timer(16, new ActionListener() {
            final float maxSize = 100f;
            final float minSize = 80f;
            final float step = 0.2f;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isGrowing) {
                    titleSize += step;
                    if (titleSize >= maxSize) {
                        isGrowing = false;
                    }
                } else {
                    titleSize -= step;
                    if (titleSize <= minSize) {
                        isGrowing = true;
                    }
                }

                title.setFont(title.getFont().deriveFont(titleSize));
                title.repaint();
            }
        });

        timer.start();
    }
}

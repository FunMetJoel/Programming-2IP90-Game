package menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**Class of the animated title in the menu.
 * 
 */
public class AnimatedTitle extends JPanel {
    JLabel title;
    String text;
    float titleSize = 80f;
    boolean isGrowing = true;

    /**Class constructor.
     * 
     */
    public AnimatedTitle(String text) {
        this.text = text;
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, 130));
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 130)); 
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130)); 
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        title = new JLabel(text, SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(5, 9, 245));


        try {
            File fontFile = new File("assets/RushDriver-Italic.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            title.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(title, BorderLayout.CENTER);

        Timer timer = new Timer(16, new ActionListener() {
            final float maxSize = 85f;
            final float minSize = 80f;
            final float step = 0.1f;

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

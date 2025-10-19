package menu;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class ActionButton extends JButton{
    JButton button;
    Color color;

    ActionButton(String name, Color color) {
        this.button = new JButton(name);
        this.color = color;
        this.button.setBackground(color);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Border border = BorderFactory.createLineBorder(Color.GRAY, 8);
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBorder(border);
                button.setBorderPainted(true);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBorderPainted(false);
            }
        });

        this.setAlignmentX(CENTER_ALIGNMENT);
        this.button.setForeground(Color.WHITE);
    }
}

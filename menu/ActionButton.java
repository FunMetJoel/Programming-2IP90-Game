package menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

import org.w3c.dom.events.MouseEvent;

public class ActionButton extends JButton{
    private Color color;
    private boolean hovered = false;
    private int originalY;
    private int hoverOffset = 5;

    ActionButton(String name, Color color) {
        super(name);
        this.color = color;

        setForeground(Color.WHITE);
        setBackground(color);

        setBorderPainted(false);
        setAlignmentX(CENTER_ALIGNMENT);

        addMouseListener(new java.awt.event.MouseAdapter() {
            Border border = BorderFactory.createLineBorder(Color.GRAY, 8);
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBorder(border);
                setBorderPainted(true);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                setBorderPainted(false);
            }
        });
    }
}
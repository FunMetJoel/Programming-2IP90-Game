package menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

import org.w3c.dom.events.MouseEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ActionButton extends JButton{
    Color borderColor;

    ActionButton(String name, Color color) {
        super(name);
        this.borderColor = color;

        setForeground(Color.WHITE);

        setBorderPainted(false);
        setContentAreaFilled(false);
        setAlignmentX(CENTER_ALIGNMENT);

        addMouseListener(new java.awt.event.MouseAdapter() {
            Border border = BorderFactory.createLineBorder(color, 3);
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBorder(border);
                setBorderPainted(true);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                setBorderPainted(false);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        Color color1 = new Color(0, 51, 102);
        Color color2 = new Color(0, 76, 153);
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color2);

        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);

        g2.dispose();

        super.paintComponent(g);
    }
}
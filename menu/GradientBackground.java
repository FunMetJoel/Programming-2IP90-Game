package menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GradientBackground extends JPanel{

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        Color start = new Color(0,0,60);
        Color end = new Color(0,0,130);

        GradientPaint gradient = new GradientPaint(0, 0, start, 0, height, end);

        graphics.setPaint(gradient);
        graphics.fillRect(0, 0, width, height);
    }
}

package menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**Class of the action button in the start menu.
 * 
 */
public class ActionButton extends JButton {
    Color borderColor;

    /**Class constructor.
     * 
     * @param name text inside of the button
     * @param color color of the border
     */
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
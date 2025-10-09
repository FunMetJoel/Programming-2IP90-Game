package menu;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundImage extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("SDF");
        Image backgroundTile = new ImageIcon("assets/grasstop.png").getImage();
        for (int i = 0; i < (this.getWidth()/backgroundTile.getWidth(null)) + 1; i++) {
            for (int j = 0; j < (this.getHeight()/backgroundTile.getHeight(null)) + 1; j++) {
                g.drawImage(backgroundTile, i*32, j*32, 32, 32, null);
            }
        }
        g.dispose();
    }
}

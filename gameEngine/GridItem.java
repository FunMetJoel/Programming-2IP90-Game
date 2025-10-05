package gameEngine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.BitSet;

import javax.swing.ImageIcon;

public class GridItem extends GameObject {

    static Image image = new ImageIcon("assets/grasstop.png").getImage();

    public GridItem(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
    }

    public Image getTexture(byte situation) {
        return image;
    }

    @Override
    public void paint(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        this.paint(graphics, centerScreenCords, scale, (byte) 0);
    }
    
    public void paint(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale, byte situation) {
        Vector2<Integer> screenPos = new Vector2<Integer>(
            (int) Math.round(centerScreenCords.x + (this.position.x * scale.x)),
            (int) Math.round(centerScreenCords.y + (this.position.y * scale.y))
        );

        graphics.drawImage(
            getTexture(situation),
            screenPos.x - (int) Math.round(scale.x * 0.5), 
            screenPos.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y),
            null
        );
    }

}

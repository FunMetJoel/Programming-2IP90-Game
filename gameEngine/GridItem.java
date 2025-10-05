package gameEngine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.BitSet;

import javax.swing.ImageIcon;

public abstract class GridItem extends GameObject {

    Image image = new ImageIcon("assets/grasstop.png").getImage();

    public GridItem(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
    }

    public abstract Image getTexture(byte situation);
    
    @Override
    public void paint(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        graphics.drawImage(
            getTexture((byte) 0),
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y),
            null
        );
    }

}

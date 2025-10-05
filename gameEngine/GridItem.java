package gameEngine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.BitSet;

import javax.swing.ImageIcon;

public abstract class GridItem extends GameObject {
    public boolean canEnter;

    public GridItem(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
    }

    public abstract Image getTexture(byte situation);

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

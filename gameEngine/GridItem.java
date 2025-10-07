package gameEngine;

import java.awt.Graphics;
import java.awt.Image;

/**
 * A tile in a Grid.
 */
public abstract class GridItem extends GameObject {
    public boolean canEnter;

    public GridItem(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
    }

    public abstract Image getTexture(byte situation);

    @Override
    public void paint(
        Graphics graphics, 
        Vector2<Integer> centerScreenCords, 
        Vector2<Double> scale
    ) {
        this.paint(graphics, centerScreenCords, scale, (byte) 0);
    }
    
    /**
     * Paints a tile with the correct context based on the tiles around it.
     * @param graphics the graphics context
     * @param centerScreenCords the screen coordinates of the center of the object
     * @param scale the scale of the element
     * @param situation which tiles of the same type it lays between
     */
    public void paint(
        Graphics graphics, 
        Vector2<Integer> centerScreenCords, 
        Vector2<Double> scale, 
        byte situation
    ) {
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

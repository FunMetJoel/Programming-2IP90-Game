package gameEngine;

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

}

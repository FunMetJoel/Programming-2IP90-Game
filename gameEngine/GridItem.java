package gameEngine;

import java.awt.Graphics2D;
import java.awt.Image;

import gameEngine.renderers.RegularShapeRenderer;
import gameEngine.renderers.ShapeRenderer;
import gameEngine.renderers.SpriteRenderer;

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

package gameEngine.renderers;

import gameEngine.GameObject;
import gameEngine.Renderer;
import gameEngine.Vector2;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A renderer for shapes.
 */
public class ShapeRenderer extends Renderer {

    public Shape shape;
    public Color fillColor;
    public Color borderColor;

    public ShapeRenderer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void render(
        Graphics2D[] graphics, 
        Vector2<Double> centerScreenCords, 
        Vector2<Double> screenScale
    ) {
        if (shape == null) {
            Logger logger = Logger.getLogger(SpriteRenderer.class.getName());
            logger.setLevel(Level.WARNING);
            logger.warning("Shape is null");
            return;
        }

        graphics[1].setColor(fillColor);
        graphics[1].fill(shape);

        graphics[1].setColor(borderColor);
        graphics[1].draw(shape);
    }
}

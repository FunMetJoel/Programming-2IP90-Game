package gameEngine.renderers;

import gameEngine.GameObject;
import gameEngine.Renderer;
import gameEngine.Vector2;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A renderer for simple shapes.
 */
public class RegularShapeRenderer extends Renderer {

    public Color fillColor;
    public Color borderColor;
    public RegularShapeRenderer.Shape shape = Shape.oval;

    /**
     * Different shapes that can be drawn by the class.
     */
    public enum Shape {
        rectangle,
        oval,
        // triangle,
        // pentagon,
        // hexagon
    }

    public RegularShapeRenderer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void render(
        Graphics2D[] graphics, 
        Vector2<Double> centerScreenCords, 
        Vector2<Double> screenScale
    ) {
        Vector2<Integer> upperCorner = getUpperCorner(centerScreenCords, screenScale).round();
        Vector2<Integer> lowerCorner = screenScale.round();
        
        switch (shape) {
            case rectangle -> {
                renderRect(graphics, upperCorner, lowerCorner);
            }

            case oval -> {
                renderOval(graphics, upperCorner, lowerCorner);
            }

            default -> {
                Logger logger = Logger.getLogger(SpriteRenderer.class.getName());
                logger.setLevel(Level.WARNING);
                logger.warning("Shape is null");
            }
        }
        
    }

    private void renderRect(
        Graphics2D[] graphics, Vector2<Integer> upperCorner, Vector2<Integer> lowerCorner
    ) {
        graphics[1].setColor(fillColor);
        graphics[1].fillRect(
            upperCorner.x, 
            upperCorner.y, 
            lowerCorner.x, 
            lowerCorner.y
        );

        graphics[1].setColor(borderColor);
        graphics[1].drawRect(
            upperCorner.x, 
            upperCorner.y, 
            lowerCorner.x, 
            lowerCorner.y
        );
    }

    private void renderOval(
        Graphics2D[] graphics, Vector2<Integer> upperCorner, Vector2<Integer> lowerCorner
    ) {
        graphics[1].setColor(fillColor);
        graphics[1].fillOval(
            upperCorner.x, 
            upperCorner.y, 
            lowerCorner.x, 
            lowerCorner.y
        );

        graphics[1].setColor(borderColor);
        graphics[1].drawOval(
            upperCorner.x, 
            upperCorner.y, 
            lowerCorner.x, 
            lowerCorner.y
        );
    }
}

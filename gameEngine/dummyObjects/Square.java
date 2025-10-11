package gameEngine.dummyObjects;

import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.RegularShapeRenderer;
import java.awt.Color;

/**
 * A square gameObject.
 */
public class Square extends GameObject {
    /**
     * Creates a new square with a position, scale, color and borderColor.
     * @param position the relative position of the circle
     * @param scale the relative scale of the circle
     * @param fill the color of the inside of the circle
     * @param border the color of the border of the circle
     */
    public Square(Vector2<Double> position, Vector2<Double> scale, Color fill, Color border) {
        super(position, scale);
        
        RegularShapeRenderer renderer = new RegularShapeRenderer(this);
        renderer.shape = RegularShapeRenderer.Shape.rectangle;
        renderer.fillColor = fill;
        renderer.borderColor = border;
        this.renderer = renderer;
    }
}

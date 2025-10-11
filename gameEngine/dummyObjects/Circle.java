package gameEngine.dummyObjects;

import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.RegularShapeRenderer;
import gameEngine.renderers.ShapeRenderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * A circle gameObject.
 */
public class Circle extends GameObject {
    /**
     * Creates a new circle with a position, scale, color and borderColor.
     * @param position the relative position of the circle
     * @param scale the relative scale of the circle
     * @param fill the color of the inside of the circle
     * @param border the color of the border of the circle
     */
    public Circle(Vector2<Double> position, Vector2<Double> scale, Color fill, Color border) {
        super(position, scale);
        
        RegularShapeRenderer renderer = new RegularShapeRenderer(this);
        renderer.shape = RegularShapeRenderer.Shape.oval;
        renderer.fillColor = fill;
        renderer.borderColor = border;
        this.renderer = renderer;
    }
}

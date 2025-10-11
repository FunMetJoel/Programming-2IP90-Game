package gameEngine.renderers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import gameEngine.GameObject;
import gameEngine.Renderer;
import gameEngine.Vector2;

public class RegularShapeRenderer extends Renderer{

    public Color fillColor;
    public Color borderColor;
    public RegularShapeRenderer.Shape shape;

    public enum Shape {
        rectangle,
        oval,
        triangle,
        pentagon,
        hexagon
    }

    public RegularShapeRenderer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void render(Graphics2D[] graphics, Vector2<Integer> centerScreenCords, Vector2<Double> screenScale) {
        Vector2<Integer> upperCorner = getUpperCorner(centerScreenCords, screenScale);
        Vector2<Integer> lowerCorner = screenScale.round();
        
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

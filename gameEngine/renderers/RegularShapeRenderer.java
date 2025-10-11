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

    public RegularShapeRenderer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void render(Graphics2D[] graphics, Vector2<Integer> centerScreenCords, Vector2<Double> screenScale) {
        graphics[1].setColor(fillColor);
        graphics[1].fillOval(
            centerScreenCords.x - (int) Math.round(gameObject.getScale().x * 0.5), 
            centerScreenCords.y - (int) Math.round(gameObject.getScale().y * 0.5), 
            (int) Math.round(gameObject.getScale().x), 
            (int) Math.round(gameObject.getScale().y)
        );

        graphics[1].setColor(borderColor);
        graphics[1].drawOval(
            centerScreenCords.x - (int) Math.round(gameObject.getScale().x * 0.5), 
            centerScreenCords.y - (int) Math.round(gameObject.getScale().y * 0.5), 
            (int) Math.round(gameObject.getScale().x), 
            (int) Math.round(gameObject.getScale().y)
        );
    }
}

package gameEngine.dummyObjects;

import gameEngine.GameObject;
import gameEngine.Vector2;
import java.awt.Color;
import java.awt.Graphics;

/**
 * A circle gameObject.
 */
public class Circle extends GameObject {

    Color fill;
    Color border;

    /**
     * Creates a new circle with a position, scale, color and borderColor.
     * @param position the relative position of the circle
     * @param scale the relative scale of the circle
     * @param fill the color of the inside of the circle
     * @param border the color of the border of the circle
     */
    public Circle(Vector2<Double> position, Vector2<Double> scale, Color fill, Color border) {
        super(position, scale);
        this.fill = fill;
        this.border = border;
    }

    @Override
    public void paint(
        Graphics[] graphics, 
        Vector2<Integer> centerScreenCords, 
        Vector2<Double> scale
    ) {
        graphics[1].setColor(this.fill);

        graphics[1].fillOval(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y)
        );

        graphics[1].setColor(this.border);
        graphics[1].drawOval(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y)
        );
    }
}

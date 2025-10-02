package gameEngine.dummyObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gameEngine.GameObject;
import gameEngine.Vector2;

public class Circle extends GameObject{

    Color fill;
    Color border;

    public Circle(Vector2<Double> position, Vector2<Double> scale, Color fill, Color border) {
        super(position, scale);
        this.fill = fill;
        this.border = border;
    }


    public void paint(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        graphics.setColor(this.fill);

        graphics.fillOval(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y)
        );

        graphics.setColor(this.border);
        graphics.drawOval(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y)
        );
    }
}

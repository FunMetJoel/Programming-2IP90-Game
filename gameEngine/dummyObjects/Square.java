package gameEngine.dummyObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gameEngine.GameObject;
import gameEngine.Vector2;

public class Square extends GameObject{

    Color fill;
    Color border;

    public Square(Vector2<Double> position, Vector2<Double> scale, Color fill, Color border) {
        gameObjects = new ArrayList<GameObject>();
        this.position = position;
        this.scale = scale;
        this.fill = fill;
        this.border = border;
    }


    public void paint(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        graphics.setColor(this.fill);

        graphics.fillRect(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y)
        );

        graphics.setColor(this.border);
        graphics.drawRect(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y)
        );
    }
}

package gameEngine;

import java.awt.*;
import java.util.ArrayList;

public class GameText extends GameObject {
    String text;
    Color color;

    public GameText(String text, Color color, Point position) {
        this.text = text;
        this.color = color;
        this.position = position;
    }


    public void paint(Graphics graphics) {
        graphics.setColor(color);

        // set Font
        graphics.setFont(new Font("Bold", 1, 20));

        // draw a string
        graphics.drawString(text, this.position.x, this.position.y);

        System.out.println(text);
    }
}

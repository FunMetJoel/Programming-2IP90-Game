package gameEngine.renderers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.File;

import gameEngine.GameObject;
import gameEngine.Renderer;
import gameEngine.Vector2;

public class TextRenderer extends Renderer {

    String textToRender;

    public TextRenderer(GameObject gameObject, String textToRender) {
        super(gameObject);
        this.textToRender = textToRender;
    }

    @Override
    public void render(Graphics2D[] graphics, Vector2<Double> centerScreenCords, Vector2<Double> screenScale) {
        // TODO Auto-generated method stub
        graphics[this.mainLayer].setColor(Color.WHITE);
        
        Font font = new Font("Arial", Font.BOLD, 72);;
        try {
            File fontFile = new File("assets/RushDriver-Italic.otf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch(Exception e) {
            font = new Font("Arial", Font.BOLD, 72);
        }
        font = font.deriveFont((float) screenScale.y.floatValue());
        graphics[this.mainLayer].setFont(font);
        FontMetrics metrics = graphics[this.mainLayer].getFontMetrics();
        int width = metrics.stringWidth(textToRender);

        if (width > screenScale.x) {
            font = font.deriveFont((((float) screenScale.x.floatValue()) * ((float) screenScale.y.floatValue()) / width));
            graphics[this.mainLayer].setFont(font);
            metrics = graphics[this.mainLayer].getFontMetrics();
            width = metrics.stringWidth(textToRender);
        }


        graphics[this.mainLayer].drawString(textToRender, centerScreenCords.round().x - (width / 2), centerScreenCords.round().y);
    }
}

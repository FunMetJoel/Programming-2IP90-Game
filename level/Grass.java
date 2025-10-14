package level;

import java.awt.Image;

import javax.swing.ImageIcon;

import gameEngine.GridItem;
import gameEngine.Vector2;
import gameEngine.renderers.SpriteRenderer;

public class Grass extends GridItem {
    static Image image = new ImageIcon("assets/grasstop.png").getImage();

    @Override
    public Image getTexture(byte situation) {
        return image;
    }

    public Grass(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
        this.canEnter = true;
        SpriteRenderer spriteRenderer = new SpriteRenderer(this);
        spriteRenderer.sprite = image;
        spriteRenderer.drawAntiLine = true;
        renderer = spriteRenderer;
    }
}

package level;

import java.awt.Image;

import javax.swing.ImageIcon;

import gameEngine.GridItem;
import gameEngine.Vector2;
import gameEngine.renderers.Ctm;
import gameEngine.renderers.SpriteRenderer;

public class Obstacle extends GridItem  {

    static Image image = new ImageIcon("assets/obstacle.png").getImage();

    static Ctm ctm = new Ctm(new ImageIcon("assets/CornerCtm.png").getImage());


    @Override
    public Image getTexture(byte situation) {
        return ctm.getTexture(situation);
    }


    public Obstacle(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
        this.canEnter = false;
        SpriteRenderer spriteRenderer = new SpriteRenderer(this);
        spriteRenderer.sprite = image;
        spriteRenderer.drawAntiLine = true;
        // renderer = spriteRenderer;
    }
    
}

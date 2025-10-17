package level;

import gameEngine.GridItem;
import gameEngine.Vector2;
import gameEngine.renderers.Ctm;
import gameEngine.renderers.SpriteRenderer;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Grass extends GridItem {
    static Image image = new ImageIcon("assets/grasstop.png").getImage();

    static Ctm ctm = new Ctm(new ImageIcon("assets/grassCtm.png").getImage());
    @Override
    public Image getTexture(byte situation) {
        return image;//ctm.getTexture(situation);
    }

    public Grass(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
        this.canEnter = true;
        SpriteRenderer spriteRenderer = new SpriteRenderer(this);
        spriteRenderer.sprite = image;
        spriteRenderer.drawAntiLine = true;
        // renderer = spriteRenderer;
    }
}

package level;

import gameEngine.GridItem;
import gameEngine.Vector2;
import gameEngine.renderers.Ctm;
import gameEngine.renderers.SpriteRenderer;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Grass extends GridItem {
    static Image image = new Ctm(new ImageIcon("assets/obstacleCtm.png").getImage()).getTexture((byte) 0);//new ImageIcon("assets/moss_block.png").getImage();

    static Ctm ctm = new Ctm(new ImageIcon("assets/obstacleCtm.png").getImage());
    @Override
    public Image getTexture(byte situation) {
        return ctm.getTexture(situation);
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

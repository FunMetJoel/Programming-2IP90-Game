package level;

import gameEngine.GridItem;
import gameEngine.Vector2;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A tile where you can walk over.
 */
public class Grass extends GridItem {
    static Image image = new ImageIcon("assets/grasstop.png").getImage();

    @Override
    public Image getTexture(byte situation) {
        return image;
    }

    /**
     * Creates a new grass tile.
     * @param position the position of the tile
     * @param scale the scale of the tile
     */
    public Grass(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
        this.canEnter = true;
    }
}

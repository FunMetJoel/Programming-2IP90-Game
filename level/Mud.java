package level;

import gameEngine.GridItem;
import gameEngine.Vector2;
import gameEngine.renderers.Ctm;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A tile with mud where you move slower.
 */
public class Mud extends GridItem {

    static Ctm ctm = new Ctm(new ImageIcon("assets/mudCtm.png").getImage());

    @Override
    public Image getTexture(byte situation) {
        return ctm.getTexture(situation);
    }

    /**
     * Creates new mud tile.
     * @param position the position of the tile
     * @param scale the scale of the tile
     */
    public Mud(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
        this.canEnter = true;
    }
}

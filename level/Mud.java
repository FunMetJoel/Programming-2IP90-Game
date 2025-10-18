package level;

import gameEngine.GridItem;
import gameEngine.Vector2;
import gameEngine.renderers.Ctm;
import gameEngine.renderers.SpriteRenderer;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Mud extends GridItem {

    static Ctm ctm = new Ctm(new ImageIcon("assets/mudCtm.png").getImage());
    @Override
    public Image getTexture(byte situation) {
        return ctm.getTexture(situation);
    }

    public Mud(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
        this.canEnter = true;
    }
}

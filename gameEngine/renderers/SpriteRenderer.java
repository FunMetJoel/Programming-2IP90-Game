package gameEngine.renderers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

import gameEngine.GameObject;
import gameEngine.Renderer;
import gameEngine.Vector2;

public class SpriteRenderer extends Renderer {

    public Image sprite;

    public SpriteRenderer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void render(Graphics2D[] graphics, Vector2<Integer> centerScreenCords, Vector2<Double> screenScale) {
        if (sprite == null) {
            Logger logger = Logger.getLogger(SpriteRenderer.class.getName());
            logger.setLevel(Level.WARNING);
            logger.warning("Sprite is null");
            return;
        }

        graphics[1].drawImage(
            sprite,
            centerScreenCords.x - (int) Math.round(screenScale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(screenScale.y * 0.5), 
            (int) Math.round(screenScale.x), 
            (int) Math.round(screenScale.y),
            null
        );
    }
}

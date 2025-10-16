package gameEngine.renderers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import gameEngine.GameObject;
import gameEngine.GridItem;
import gameEngine.Renderer;
import gameEngine.Vector2;

public class GridRenderer extends Renderer {
    public Image sprite;

    public Boolean drawAntiLine = false;

    private BufferedImage[] bakedMap;

    public GridRenderer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void render(
        Graphics2D[] graphics, 
        Vector2<Double> centerScreenCords, 
        Vector2<Double> screenScale
    ) {
        if (sprite == null) {
            Logger logger = Logger.getLogger(SpriteRenderer.class.getName());
            logger.setLevel(Level.WARNING);
            logger.warning("Sprite is null");
            return;
        }

        Vector2<Integer> upperCorner = getUpperCorner(centerScreenCords, screenScale).round();
        Vector2<Integer> lowerCorner = screenScale.round();
        

        graphics[mainLayer].drawImage(
            sprite,
            upperCorner.x, upperCorner.y, 
            lowerCorner.x, lowerCorner.y,
            null
        );

        if (!drawAntiLine) {
            return;
        }
        graphics[0].drawImage(
            sprite,
            upperCorner.addVector(new Vector2<Double>(-1.0, -1.0)).round().x, 
            upperCorner.addVector(new Vector2<Double>(-1.0, -1.0)).round().y, 
            lowerCorner.addVector(new Vector2<Double>(1.0, 1.0)).round().x, 
            lowerCorner.addVector(new Vector2<Double>(1.0, 1.0)).round().y,
            null
        );
    }

    public void bakeMap() {
        for (GameObject child : gameObject.children) {
            
        }
    }

    // public void renderChild
}

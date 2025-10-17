package gameEngine.renderers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import gameEngine.GameObject;
import gameEngine.Grid;
import gameEngine.GridItem;
import gameEngine.Renderer;
import gameEngine.Vector2;

public class GridRenderer extends Renderer {
    public Image sprite;

    public Boolean drawAntiLine = false;

    private BufferedImage[] bakedMap;
    private int hashedMap;

    public GridRenderer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void render(
        Graphics2D[] graphics, 
        Vector2<Double> centerScreenCords, 
        Vector2<Double> screenScale
    ) {
        if (hashedMap != hashCurrentMap()) {
            bakeMap();
            hashedMap = hashCurrentMap();
        }

        // TODO: Do not hardcode grid size
        Vector2<Integer> upperCorner = getUpperCorner(centerScreenCords, screenScale).round();
        Vector2<Integer> lowerCorner = screenScale.newScaledVector(51.0).round();
        graphics[1].drawImage(
            bakedMap[1],
            upperCorner.x,
            upperCorner.y,
            lowerCorner.x,
            lowerCorner.y,
            null
        );
    }

    public void bakeMap() {
        bakedMap = new BufferedImage[2];
        Graphics2D[] layers = new Graphics2D[bakedMap.length];
        for (int i = 0; i < bakedMap.length; i++) {
            bakedMap[i] = new BufferedImage(51 * 16, 51 * 16, BufferedImage.TYPE_INT_ARGB);
            layers[i] = bakedMap[i].createGraphics();
        }

        for (GameObject child : gameObject.children) {
            GridItem tile = (GridItem) child;
            Vector2<Integer> pos = tile.getPosition().round();
            byte situation = Ctm.getSituation((Grid) gameObject, pos);
            Image texture = tile.getTexture((byte) situation);
            layers[1].drawImage(
                texture, 
                pos.x * texture.getWidth(null), 
                pos.y * texture.getHeight(null), 
                texture.getWidth(null), 
                texture.getHeight(null), 
                null
            );
        }

        for (int i = 0; i < layers.length; i++) {
            layers[i].dispose();
        }

    }

    public int hashCurrentMap() {
        return gameObject.children.hashCode();
    }
}

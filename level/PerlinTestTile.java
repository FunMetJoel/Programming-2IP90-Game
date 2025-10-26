package level;

import gameEngine.GridItem;
import gameEngine.Vector2;
import gameEngine.renderers.RegularShapeRenderer;
import java.awt.Color;
import java.awt.Image;

/**
 * A tile to better debug the perlin noise.
 * A square that can render with a color between black and white depending on the value
 */
public class PerlinTestTile extends GridItem {

    /**
     * Creates a new perlin test tile.
     * @param position the position of the tile
     * @param scale the scale of the tile
     * @param value the value of the tile
     */
    public PerlinTestTile(Vector2<Double> position, Vector2<Double> scale, double value) {
        super(position, scale);
        this.canEnter = true;
        RegularShapeRenderer render = new RegularShapeRenderer(this);
        render.shape = RegularShapeRenderer.Shape.rectangle;
        int colorValue = Math.clamp((int) Math.round(value * 255.0), 0, 255);
        // System.err.println(colorValue);
        render.fillColor = new Color(colorValue, colorValue, colorValue);
        this.renderer = render;
    }

    @Override
    public Image getTexture(byte situation) {
        return null;
    }
}

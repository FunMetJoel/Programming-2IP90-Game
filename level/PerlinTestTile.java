package level;

import java.awt.Color;
import java.awt.Image;

import gameEngine.GridItem;
import gameEngine.Vector2;
import gameEngine.renderers.RegularShapeRenderer;

public class PerlinTestTile extends GridItem {


    public PerlinTestTile(Vector2<Double> position, Vector2<Double> scale, double value) {
        super(position, scale);
        this.canEnter = true;
        RegularShapeRenderer render = new RegularShapeRenderer(this);
        render.shape = RegularShapeRenderer.Shape.rectangle;
        int colorValue = Math.clamp((int) Math.round(value * 255.0), 0, 255);
        System.err.println(colorValue);
        render.fillColor = new Color(colorValue, colorValue, colorValue);
        this.renderer = render;
    }

    @Override
    public Image getTexture(byte situation) {
        return null;
    }
}

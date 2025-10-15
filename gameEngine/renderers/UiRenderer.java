package gameEngine.renderers;

import java.awt.Color;
import java.awt.Graphics2D;

import gameEngine.GameObject;
import gameEngine.Renderer;
import gameEngine.Vector2;

public class UiRenderer extends Renderer {
    public UiRenderer(GameObject gameObject) {
        super(gameObject);
        this.renderInCenter = true;
    }

    @Override
    public void render(Graphics2D[] graphics, Vector2<Double> centerScreenCords, Vector2<Double> screenScale,
            Vector2<Double> screenCenter) {
        
        Vector2<Integer> totalScreenScale = screenCenter.newScaledVector(2.0).round();
        render(
            graphics, 
            screenCenter.addVector(gameObject.getPosition().newScaledVector(totalScreenScale.newScaledVector(gameObject.getScale()))), 
            totalScreenScale.newScaledVector(gameObject.getScale())
        );
    }

    @Override
    public void render(Graphics2D[] graphics, Vector2<Double> centerScreenCords, Vector2<Double> screenScale) {
        // TODO Auto-generated method stub

        Vector2<Integer> upperCorner = getUpperCorner(centerScreenCords, screenScale).round();
        Vector2<Integer> lowerCorner = screenScale.round();

        graphics[1].setColor(Color.RED);
        graphics[1].fillRect(
            upperCorner.x, 
            upperCorner.y, 
            lowerCorner.x, 
            lowerCorner.y
        );
    }
}

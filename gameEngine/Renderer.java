package gameEngine;

import java.awt.Graphics2D;

/**
 * A renderer renders an object to the camera.
 */
public abstract class Renderer {

    protected GameObject gameObject;

    public boolean renderInCenter;
    public boolean constantScreenSize;
    public int mainLayer = 1;

    public Renderer(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * Renders the gameObject.
     * @param graphics the graphics contexts of the layers.
     * @param centerScreenCords the pixel coordinates of the center of the object.
     * @param screenScale the relative scale of the object (in pixels)
     */
    public abstract void render(
        Graphics2D[] graphics, 
        Vector2<Double> centerScreenCords,
        Vector2<Double> screenScale
    );

    public void render(
        Graphics2D[] graphics, 
        Vector2<Double> centerScreenCords,
        Vector2<Double> screenScale,
        Vector2<Double> screenCenter
    ) {
        if (!renderInCenter) {
            this.render(graphics, centerScreenCords, screenScale);
            return;
        }

        if (!constantScreenSize) {
            this.render(graphics, screenCenter, screenScale);
            return;
        }

        Vector2<Double> totalScreenScale = screenCenter.newScaledVector(2.0);
        Vector2<Double> newScale = totalScreenScale.newScaledVector(gameObject.getScale());
        Vector2<Double> newPosition = screenCenter.addVector(totalScreenScale.newScaledVector(gameObject.getPosition()));
        this.render(graphics, newPosition, newScale);
    }

    /**
     * Gets the topleft corner based on the center and scale.
     * @param centerScreenCords the center of the object on the screen
     * @param screenScale how big the object is on the screen
     * @return the pixel position of the corner of the object on screen
     */
    public static Vector2<Double> getUpperCorner(
        Vector2<Double> centerScreenCords, Vector2<Double> screenScale
    ) {
        return new Vector2<Double>(
            centerScreenCords.x - (screenScale.x * 0.5), 
            centerScreenCords.y - (screenScale.y * 0.5)
        );
    }
}

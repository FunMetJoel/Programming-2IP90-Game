package gameEngine;

import java.awt.Graphics2D;

/**
 * A renderer renders an object to the camera.
 */
public abstract class Renderer {

    protected GameObject gameObject;

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
        Vector2<Double> centerScreenCords, // TODO: This can be a double, and round on render.
        Vector2<Double> screenScale
    );

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

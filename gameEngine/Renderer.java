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
        Vector2<Integer> centerScreenCords, // TODO: This can also be a double, and then we can have it just round on render.
        Vector2<Double> screenScale
    );

    public static Vector2<Integer> getUpperCorner(Vector2<Integer> centerScreenCords, Vector2<Double> screenScale) {
        return new Vector2<Integer>(
            centerScreenCords.x - (int) Math.round(screenScale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(screenScale.y * 0.5)
        );
    }
}

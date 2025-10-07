package gameEngine;

import java.awt.*;
import java.util.ArrayList;

/**
 * An object that exist in the game world.
 */
public abstract class GameObject {
    protected ArrayList<GameObject> children;
    protected Vector2<Double> position = new Vector2<Double>(0.0, 0.0);
    protected Vector2<Double> scale = new Vector2<Double>(1.0, 1.0);

    /**
     * Paints the component to screen.
     * @param graphics the graphics context
     * @param centerScreenCords the screen coordinates of the center of the object
     * @param scale the scale of the element
     */
    public void paint(
        Graphics graphics, 
        Vector2<Integer> centerScreenCords, 
        Vector2<Double> scale
    ) {

    }

    /**
     * Runs in a loop.
     */
    public void update() {

    }

    /**
     * Creates a new empty gameObject.
     */
    public GameObject() {
        children = new ArrayList<GameObject>();
    }

    /**
     * Creates a gameObject with a specified position.
     * @param position the relative position of the gameObject
     */
    public GameObject(Vector2<Double> position) {
        children = new ArrayList<GameObject>();
        this.position = position;
    }

    /**
     * Creates a gameObject with a specified position and scale.
     * @param position the relative position of the gameObject
     * @param scale the scale of the gameObject
     */
    public GameObject(Vector2<Double> position, Vector2<Double> scale) {
        children = new ArrayList<GameObject>();
        this.position = position;
        this.scale = scale;
    }

    /**
     * Paints the gameObject and every child gameObject.
     * @param graphics the graphics context
     * @param centerScreenCords the center of the parent object in screen cords
     * @param scale the scale of the parent object
     */
    public void draw(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {

        Vector2<Double> newScale = new Vector2<Double>(
            scale.x * this.scale.x,
            scale.y * this.scale.y
        );

        Vector2<Integer> newCenterScreenCords = new Vector2<Integer>(
            (int) Math.round(centerScreenCords.x + (this.position.x * scale.x)),
            (int) Math.round(centerScreenCords.y + (this.position.y * scale.y))
        );

        this.paint(graphics, newCenterScreenCords, newScale);

        for (GameObject gameObject : children) {
            gameObject.draw(graphics, newCenterScreenCords, newScale);
        }
    }

    /**
     * Runs the update function of this object and every child object.
     */
    public void updateAll() {
        this.update();

        for (GameObject gameObject : children) {
            gameObject.updateAll();
        }
    }

    public Vector2<Double> getPosition() {
        return this.position;
    }
}

package gameEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An object that exist in the game world.
 */
public abstract class GameObject {
    protected ArrayList<GameObject> children;
    protected Vector2<Double> position = new Vector2<Double>(0.0, 0.0);
    protected Vector2<Double> scale = new Vector2<Double>(1.0, 1.0);
    protected ArrayList<Behavior> behaviors = new ArrayList<Behavior>();
    protected Renderer renderer;

    /**
     * Paints the component to screen.
     * @param graphics the graphics context
     * @param centerScreenCords the screen coordinates of the center of the object
     * @param scale the scale of the element
     */
    public void paint(
        Graphics2D[] graphics, 
        Vector2<Integer> centerScreenCords, 
        Vector2<Double> scale
    ) {

    }

    /**
     * Runs in a loop.
     * @deprecated use behaviors.
     */
    @Deprecated 
    public void update() {

    }

    /**
     * Sets up all behaviors.
     */
    private void setupBehaviors() {
        for (Behavior behavior : behaviors) {
            behavior.setup();
        }
    }

    /**
     * Updates the gameObjects behaviors.
     */
    private void updateBehaviors() {
        for (Behavior behavior : behaviors) {
            behavior.update();
        }
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
        this();
        this.position = position;
    }

    /**
     * Creates a gameObject with a specified position and scale.
     * @param position the relative position of the gameObject
     * @param scale the scale of the gameObject
     */
    public GameObject(Vector2<Double> position, Vector2<Double> scale) {
        this(position);
        this.scale = scale;
    }

    /**
     * Paints the gameObject and every child gameObject.
     * @param graphics the graphics context
     * @param centerScreenCords the center of the parent object in screen cords
     * @param scale the scale of the parent object
     */
    public void draw(
        Graphics2D[] graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale
    ) {
        Vector2<Double> newScale = this.scale.newScaledVector(scale);

        Vector2<Double> deltaPos = this.position.newScaledVector(scale);
        Vector2<Integer> newCenterScreenCords = centerScreenCords.addVector(deltaPos).round();

        // TODO: Remove this when the time is ready
        this.paint(graphics, newCenterScreenCords, newScale);
        
        if (this.renderer != null) {
            this.renderer.render(graphics, newCenterScreenCords, newScale);
        }

        for (GameObject gameObject : children) {
            gameObject.draw(graphics, newCenterScreenCords, newScale);
        }
    }

    /**
     * Runs the update function of this object and every child object.
     */
    public void updateAll() {
        // TODO: Remove this when the time is ready
        this.update();
        
        this.updateBehaviors();

        for (GameObject gameObject : children) {
            gameObject.updateAll();
        }
    }

    /**
     * Runs setup on all behavior's of this object and its children.
     */
    public void setupAll() {
        this.setupBehaviors();

        for (GameObject gameObject : children) {
            gameObject.setupAll();
        }
    }

    public Vector2<Double> getPosition() {
        return this.position.copy();
    }

    /**
     * Sets the position of the object.
     * @param x the x position
     * @param y the y position
     */
    public void setPosition(double x, double y) {
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * Sets the position of the object.
     * @param position the position
     */
    public void setPosition(Vector2<Double> position) {
        this.position = position;
    }

    public Vector2<Double> getScale() {
        return this.scale.copy();
    }

    public Behavior getBehavior(Class<? extends Behavior> searchClass) {
        for (Behavior behavior : behaviors) {
            if (searchClass.isInstance(behavior)) {
                return behavior;
            }
        }

        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.setLevel(Level.WARNING);
        logger.warning(this + " does not have a behavior of type " + searchClass.getName());
        return null;
    }

}

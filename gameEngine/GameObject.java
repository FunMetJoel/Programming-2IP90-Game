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
     * Runs the update function of this object and every child object.
     */
    public void updateAll() {
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

    public void addChild(GameObject gameObject) {
        this.children.add(gameObject);
    }

    public void removeChild(GameObject gameObject) {
        this.children.remove(gameObject);
    }

}

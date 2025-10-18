package gameEngine;

/**
 * A behavior of a gameObject.
 * Used to split up using the single responsibility principal.
 */
public abstract class Behavior {

    protected final GameObject gameObject;
    public boolean enabled = true;

    /**
     * Function that gets run at the start of the game.
     */
    public abstract void setup();

    /**
     * Function that runs every game tick.
     */
    public abstract void update();

    /**
     * Creates a new behavior instance.
     * @param gameObject the parent gameObject.
     */
    public Behavior(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}

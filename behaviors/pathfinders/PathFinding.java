package behaviors.pathfinders;

import behaviors.GridMovement;
import gameEngine.Behavior;
import gameEngine.GameObject;
import level.Level;

/**
 * A abstract pathfinding behavior that can be implemented as different algorithms.
 */
public abstract class Pathfinding extends Behavior {
    protected GridMovement target;
    protected GridMovement gridMovement;
    protected Level level;

    /**
     * Creates new pathfinding behavior.
     * @param gameObject the gameobject to add the behavior to
     * @param target the target of the pathfinding
     */
    public Pathfinding(GameObject gameObject, GridMovement target) {
        super(gameObject);
        this.target = target;
    }

    @Override
    public void setup() {
        this.gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);
        this.level = gridMovement.level;
    }

    @Override
    public void update() {
        if (!gridMovement.canMove()) {
            return;
        }

        move();
    }

    protected abstract void move();
}

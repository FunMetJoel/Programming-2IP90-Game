package behaviors.pathfinders;

import behaviors.GridMovement;
import gameEngine.Behavior;
import gameEngine.GameObject;
import level.Level;

public abstract class Pathfinding extends Behavior {
    protected GridMovement target;
    protected GridMovement gridMovement;
    protected Level level;

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

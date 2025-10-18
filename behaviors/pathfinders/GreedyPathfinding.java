package behaviors.pathfinders;

import behaviors.GridMovement;
import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameObjects.Player;
import level.Level;

public class GreedyPathfinding extends Behavior {
    public GridMovement gridMovement;
    public GridMovement target;
    Level level;


    public GreedyPathfinding(GameObject gameObject) {
        super(gameObject);
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

        Vector2<Integer> targetPos = target.getPosition();
        Vector2<Integer> currentPos = gridMovement.getPosition();

        if ((targetPos.y < currentPos.y) && level.canEnter(currentPos.x, currentPos.y - 1)) {
            gridMovement.move(0, -1);
        } else if ((targetPos.y > currentPos.y) && level.canEnter(currentPos.x, currentPos.y + 1)) {
            gridMovement.move(0, 1);
        } else if ((targetPos.x < currentPos.x) && level.canEnter(currentPos.x - 1, currentPos.y)) {
            gridMovement.move(-1, 0);
        } else if ((targetPos.x > currentPos.x) && level.canEnter(currentPos.x + 1, currentPos.y)) {
            gridMovement.move(1, 0);
        }
    }
}

package behaviors.pathfinders;

import behaviors.GridMovement;
import gameEngine.GameObject;
import gameEngine.Vector2;

/**
 * A simple path finding behavior that tries to minimize the distance to the player.
 */
public class GreedyPathfinding extends Pathfinding {

    public GreedyPathfinding(GameObject gameObject, GridMovement target) {
        super(gameObject, target);
    }

    @Override
    public void move() {
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

package behaviors;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.InputManager;
import java.awt.event.KeyEvent;

/**
 * A behavior of the player that moves it based on the keyboard input.
 */
public class PlayerController extends Behavior {
    GridMovement gridMovement;
    boolean horizontalFirst = false;

    public PlayerController(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void setup() {
        gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);
    }

    @Override
    public void update() {
        if (!gridMovement.canMove()) {
            return;
        }

        
        // TODO: This can be more neat right?
        if (horizontalFirst) {
            if (InputManager.isPressed(KeyEvent.VK_A) && gridMovement.canMoveBy(-1, 0)) {
                gridMovement.move(-1, 0);
                horizontalFirst = false;
            } else if (InputManager.isPressed(KeyEvent.VK_D) && gridMovement.canMoveBy(1, 0)) {
                gridMovement.move(1, 0);
                horizontalFirst = false;
            } else if (InputManager.isPressed(KeyEvent.VK_W) && gridMovement.canMoveBy(0, -1)) {
                gridMovement.move(0, -1);
                horizontalFirst = true;
            } else if (InputManager.isPressed(KeyEvent.VK_S) && gridMovement.canMoveBy(0, 1)) {
                gridMovement.move(0, 1);
                horizontalFirst = true;
            }
        } else {
            if (InputManager.isPressed(KeyEvent.VK_W) && gridMovement.canMoveBy(0, -1)) {
                gridMovement.move(0, -1);
                horizontalFirst = true;
            } else if (InputManager.isPressed(KeyEvent.VK_S) && gridMovement.canMoveBy(0, 1)) {
                gridMovement.move(0, 1);
                horizontalFirst = true;
            } else if (InputManager.isPressed(KeyEvent.VK_A) && gridMovement.canMoveBy(-1, 0)) {
                gridMovement.move(-1, 0);
                horizontalFirst = false;
            } else if (InputManager.isPressed(KeyEvent.VK_D) && gridMovement.canMoveBy(1, 0)) {
                gridMovement.move(1, 0);
                horizontalFirst = false;
            }
        }
    }
}

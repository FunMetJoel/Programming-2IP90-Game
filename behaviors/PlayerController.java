package behaviors;

import java.awt.event.KeyEvent;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.InputManager;

public class PlayerController extends Behavior {
    GridMovement gridMovement;

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

        if (InputManager.isPressed(KeyEvent.VK_W) && gridMovement.canMoveBy(0, -1)) {
            gridMovement.move(0, -1);
        } else if (InputManager.isPressed(KeyEvent.VK_S) && gridMovement.canMoveBy(0, 1)) {
            gridMovement.move(0, 1);
        } else if (InputManager.isPressed(KeyEvent.VK_A) && gridMovement.canMoveBy(-1, 0)) {
            gridMovement.move(-1, 0);
        } else if (InputManager.isPressed(KeyEvent.VK_D) && gridMovement.canMoveBy(1, 0)) {
            gridMovement.move(1, 0);
        }
    }
}

package behaviors;

import gameEngine.Behavior;
import gameEngine.GameObject;

public abstract class PlayerCollisionDetector extends Behavior {
    private GridMovement playerMovement;
    private GridMovement gridMovement;

    public PlayerCollisionDetector(GameObject gameObject, GridMovement playerMovement) {
        super(gameObject);
        this.playerMovement = playerMovement;
    }

    @Override
    public void setup() {
        this.gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);
    }

    @Override
    public void update() {
        if (playerMovement.getPosition().equals(gridMovement.getPosition())) {
            onCollide();
        }
    }

    abstract void onCollide();

}

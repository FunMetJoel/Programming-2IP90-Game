package behaviors;

import java.time.Duration;
import java.time.Instant;

import gameEngine.Behavior;
import gameEngine.GameObject;

public abstract class PlayerCollisionDetector extends Behavior {
    private GridMovement playerMovement;
    private GridMovement gridMovement;

    private Boolean collides = false;

    private Instant collisionInstant;

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
        
        if (!collides) {
            if (playerMovement.getPosition().equals(gridMovement.getPosition())) {
                onCollide();
                collides = true;
                collisionInstant = Instant.now();
            }
        } else {
            if (!playerMovement.getPosition().equals(gridMovement.getPosition())) {
                collides = false;
                onCollisionExit();
            } else {
                onCollisionStay(
                    ((double) Duration.between(collisionInstant, Instant.now()).toNanos()) / 1e9
                );
            }
        }
    }

    abstract void onCollide();

    abstract void onCollisionStay(double collisionTime);

    abstract void onCollisionExit();

}

package behaviors;

import gameEngine.GameObject;

public class Edible extends PlayerCollisionDetector {
    @Override
    void onCollide() {
        System.out.println("YAY");
    }

    public Edible(GameObject gameObject, GridMovement playerMovement) {
        super(gameObject, playerMovement);
    }
}

package behaviors;

import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;

/**
 * A collider for the enemy.
 */
public class EnemyCollider extends PlayerCollisionDetector {

    ScoreHolder scoreHolder;
    double lastHitTime = 0;

    /**
     * Creates new enemy collider behavior.
     * @param gameObject the gameObject to add the behavior to
     * @param playerMovement the movement script of the player to detect collisions.
     * @param scoreHolder the score holder to update the score when hit
     */
    public EnemyCollider(
        GameObject gameObject, GridMovement playerMovement, ScoreHolder scoreHolder
    ) {
        super(gameObject, playerMovement);
        this.scoreHolder = scoreHolder;
    }

    @Override
    void onCollide() {
        lastHitTime = -0.8;
    }

    @Override
    void onCollisionStay(double collisionTime) {
        // TODO Auto-generated method stub
        if (collisionTime - lastHitTime > 1.0) {
            scoreHolder.removeScore(1.0);
            lastHitTime = collisionTime;
        }
    }

    @Override
    void onCollisionExit() {
        // TODO Auto-generated method stub
        
    }
}

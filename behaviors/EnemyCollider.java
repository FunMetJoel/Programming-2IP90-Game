package behaviors;

import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;

/**
 * A collider for the enemy.
 */
public class EnemyCollider extends PlayerCollisionDetector {

    ScoreHolder scoreHolder;

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
        // TODO Auto-generated method stub
        scoreHolder.removeScore(1.0);
    }
}

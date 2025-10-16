package behaviors;

import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;

public class EnemyCollider extends PlayerCollisionDetector {

    ScoreHolder scoreHolder;

    public EnemyCollider(GameObject gameObject, GridMovement playerMovement, ScoreHolder scoreHolder) {
        super(gameObject, playerMovement);
        this.scoreHolder = scoreHolder;
    }

    @Override
    void onCollide() {
        // TODO Auto-generated method stub
        scoreHolder.removeScore(1.0);
    }
}

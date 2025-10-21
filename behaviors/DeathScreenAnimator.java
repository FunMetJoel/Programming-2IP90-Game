package behaviors;

import java.time.Duration;
import java.time.Instant;

import behaviors.managers.GameStateManager;
import behaviors.managers.GameStateManager.GameState;
import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameObjects.GameManager;

public class DeathScreenAnimator extends Behavior {

    GameManager gameManager;
    GameStateManager gameStateManager;
    Instant GameFinishedTime;

    public DeathScreenAnimator(GameObject gameObject, GameManager gameManager) {
        super(gameObject);
        this.gameManager = gameManager;
    }

    @Override
    public void setup() {
        this.gameStateManager = (GameStateManager) gameManager.getBehavior(GameStateManager.class);
    }

    @Override
    public void update() {
        if (!(gameStateManager.gameState == GameState.finished)) {
            return;
        }

        if (GameFinishedTime == null) {
            GameFinishedTime = Instant.now();
        }

        long duration = Duration.between(GameFinishedTime, Instant.now()).toMillis();
        double secondsPassed = ((double) duration / 1000.0);

        if (secondsPassed > 1.0 && secondsPassed < 1.25) {
            gameObject.setScale(new Vector2<Number>(0.75, 0.5).newScaledVector(secondsPassed - 1.0).newScaledVector(4.0));
        }
    }
}

package behaviors;

import java.time.Duration;
import java.time.Instant;

import behaviors.managers.GameStateManager;
import behaviors.managers.ScoreHolder;
import behaviors.managers.GameStateManager.GameState;
import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.TextRenderer;
import gameObjects.GameManager;

public class DeathScreenAnimator extends Behavior {

    GameManager gameManager;
    GameStateManager gameStateManager;
    ScoreHolder scoreHolder;
    Instant GameFinishedTime;
    TextRenderer scoreTextRenderer;

    public DeathScreenAnimator(GameObject gameObject, GameManager gameManager, TextRenderer scoreTextRenderer) {
        super(gameObject);
        this.gameManager = gameManager;
        this.scoreTextRenderer = scoreTextRenderer;
    }

    @Override
    public void setup() {
        this.gameStateManager = (GameStateManager) gameManager.getBehavior(GameStateManager.class);
        this.scoreHolder = (ScoreHolder) gameManager.getBehavior(ScoreHolder.class);
    }

    @Override
    public void update() {
        if (!(gameStateManager.gameState == GameState.finished)) {
            return;
        }

        if (GameFinishedTime == null) {
            GameFinishedTime = Instant.now();
            scoreTextRenderer.textToRender = "Score: " + Math.round(scoreHolder.getTotalScore());
        }

        long duration = Duration.between(GameFinishedTime, Instant.now()).toMillis();
        double secondsPassed = ((double) duration / 1000.0);

        if (secondsPassed > 1.0 && secondsPassed < 1.25) {
            gameObject.setScale(new Vector2<Number>(0.75, 0.5).newScaledVector(secondsPassed - 1.0).newScaledVector(4.0));
        }
    }
}

package behaviors;

import behaviors.managers.GameStateManager;
import behaviors.managers.GameStateManager.GameState;
import behaviors.managers.ScoreHolder;
import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.TextRenderer;
import gameObjects.GameManager;
import java.time.Duration;
import java.time.Instant;

/**
 * A behavior that animates the death screen popup.
 */
public class DeathScreenAnimator extends Behavior {

    GameManager gameManager;
    GameStateManager gameStateManager;
    ScoreHolder scoreHolder;
    Instant gameFinishedTime;
    TextRenderer scoreTextRenderer;

    int currentScoreNumber;

    /**
     * Creates the behavior.
     * @param gameObject the game object to add the behavior to
     * @param gameManager the game manager
     * @param scoreTextRenderer the text renderer to show the score
     */
    public DeathScreenAnimator(
        GameObject gameObject, GameManager gameManager, TextRenderer scoreTextRenderer
    ) {
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

        if (gameFinishedTime == null) {
            gameFinishedTime = Instant.now();
            scoreTextRenderer.textToRender = "Score: " + 0;
            currentScoreNumber = 0;
        }

        long duration = Duration.between(gameFinishedTime, Instant.now()).toNanos();
        double secondsPassed = ((double) duration / 1e9);

        if (secondsPassed > 1.0 && secondsPassed < 1.25) {
            gameObject.setScale(
                    new Vector2<Number>(0.75, 0.5)
                        .newScaledVector(secondsPassed - 1.0)
                        .newScaledVector(4.0)
            );
        }

        if (secondsPassed > 1.75 && secondsPassed < 2.75) {
            scoreTextRenderer.textToRender = 
                "Score: " 
                + Math.round(scoreHolder.getTotalScore() * (secondsPassed - 1.75));
        }
    }
}

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import gameEngine.GameCanvas;
import gameEngine.GameObject;
import level.Level;

/**
 * Object to manage the game, switch the level game state.
 */
public class GameManager extends GameObject {
    Player player;
    Level currentLevel;
    GameCanvas canvas;

    Instant levelStartedTime = Instant.now();

    public GameManager (Player player, GameCanvas canvas) {
        this.player = player;
        this.canvas = canvas;
    }

    public GameManager (Player player, Level currentLevel, GameCanvas canvas) {
        this.player = player;
        this.currentLevel = currentLevel;
        this.canvas = canvas;
    }

    public void newLevel() {
        newLevel(new Random().nextInt());
    }

    public void newLevel(int seed) {
        this.canvas.removeObject(currentLevel);
        this.currentLevel = new Level(seed);

        this.player.level = this.currentLevel;
        this.player.gridX = 0;
        this.player.gridY = 0;

        this.levelStartedTime = Instant.now();
    }

    @Override
    public void update() {
        long millisSinceStart = Duration.between(levelStartedTime, Instant.now()).toMillis();
        if (millisSinceStart < 3000) {
            canvas.cameraPosition.x = 0.0;
            canvas.cameraPosition.y = 0.0;
            double fixedTime = ((double) millisSinceStart - 2000.0) / 1000.0;
            if (millisSinceStart > 2000) {
                canvas.zoom = Math.pow(fixedTime, 4) - 3.75 * Math.pow(fixedTime, 3) + 3.625 * Math.pow(fixedTime, 2) + 0.125;
            } else {
                canvas.zoom = 0.125;
            }
        } else {
            canvas.zoom = 1;
            canvas.cameraPosition = player.getPosition();
        }

        player.screenMiddle.x = (canvas.getWidth() / 2);
        player.screenMiddle.y = (canvas.getHeight() / 2);
    }


}

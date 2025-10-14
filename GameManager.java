import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import behaviors.CameraManager;
import behaviors.GridMovement;
import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.Scene;
import level.Level;

/**
 * Object to manage the game, switch the level game state.
 */
public class GameManager extends GameObject {
    Player player;
    Level currentLevel;
    Scene scene;
    GameCanvas canvas;

    public GameManager (Player player, GameCanvas canvas, Scene scene) {
        this.player = player;
        this.canvas = canvas;
        this.scene = scene;

        this.behaviors.add(
            new CameraManager(this, player, canvas, scene)
        );
    }

    public GameManager (Player player, Level currentLevel, GameCanvas canvas, Scene scene) {
        this(player, canvas, scene);
        this.currentLevel = currentLevel;
    }

    public void newLevel() {
        newLevel(new Random().nextInt());
    }

    public void newLevel(int seed) {
        this.scene.removeObject(currentLevel);
        this.currentLevel = new Level(seed);

        // this.player.level = this.currentLevel;
        // this.player.gridX = 0;
        // this.player.gridY = 0;
        GridMovement gridMovement = (GridMovement) this.getBehavior(GridMovement.class);
        gridMovement.level = this.currentLevel;
        System.out.println(gridMovement.level);
        gridMovement.moveTo(0, 0);


        CameraManager cameraManager = (CameraManager) getBehavior(CameraManager.class);
        cameraManager.levelStartedTime = Instant.now();
    }
}

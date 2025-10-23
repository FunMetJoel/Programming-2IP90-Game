package gameObjects;

import behaviors.GridMovement;
import behaviors.managers.CameraManager;
import behaviors.managers.CanisterSpawner;
import behaviors.managers.EnemySpawner;
import behaviors.managers.GameStateManager;
import behaviors.managers.ScoreHolder;
import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.Scene;
import java.time.Instant;
import java.util.Random;
import level.Level;

/**
 * Object to manage the game, switch the level game state.
 */
public class GameManager extends GameObject {
    public Player player;
    Level currentLevel;
    Scene scene;
    GameCanvas canvas;

    /**
     * Creates the game manager.
     * @param player the player in the game
     * @param currentLevel the level of the game
     * @param canvas the canvas of the game
     * @param scene the scene of the game
     */
    public GameManager(Player player, Level currentLevel, GameCanvas canvas, Scene scene) {
        this.player = player;
        this.canvas = canvas;
        this.scene = scene;
        this.currentLevel = currentLevel;

        this.behaviors.add(
            new CameraManager(this, player, canvas, scene)
        );
        this.behaviors.add(
            new CanisterSpawner(
                this, 
                currentLevel, 
                (GridMovement) player.getBehavior(GridMovement.class)
            )
        );
        this.behaviors.add(
            new GameStateManager(this)
        );
        this.behaviors.add(
            new EnemySpawner(this, scene, currentLevel)
        );

        this.behaviors.add(new ScoreHolder(this));
    }

    // public GameManager (Player player, Level currentLevel, GameCanvas canvas, Scene scene) {
    //     this(player, canvas, scene);
    //     this.currentLevel = currentLevel;
    // }

    public void newLevel() {
        newLevel(new Random().nextInt());
    }

    // TODO: CAN this go?
    /**
     * Creates a new level.
     * @param seed the levels seed
     */
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

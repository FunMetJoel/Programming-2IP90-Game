package gameObjects;

import behaviors.managers.ScoreHolder;
import gameEngine.GameCanvas;
import gameEngine.Scene;
import gameEngine.Vector2;
import java.util.Random;
import level.Level;

/**
 * A scene in the game.
 */
public class GameScene extends Scene {
    public GameScene(GameCanvas gameCanvas) {
        this(new Random().nextInt(), gameCanvas);
    }

    /**
     * Creates the scene.
     */
    public GameScene(int seed, GameCanvas gameCanvas) {
        Level level = new Level(seed);
        addObject(level);

        Vector2<Integer> middlePosition = new Vector2<Integer>(
            level.gridSize / 2, 
            level.gridSize / 2
        );
        
        Player player = new Player(
            new Vector2<Double>((double) middlePosition.x, (double) middlePosition.y), 
            new Vector2<Double>(0.8, 0.8),
            level
        );
        player.level = level;

        addObject(player);

        GameManager gameManager = new GameManager(player, level, gameCanvas, this);
        addObject(gameManager);

        addUIObject(
            new MainUICanvas((ScoreHolder) gameManager.getBehavior(ScoreHolder.class))
        );

        addUIObject(
            new DeathScreenCanvas(gameManager)
        );
    }
}

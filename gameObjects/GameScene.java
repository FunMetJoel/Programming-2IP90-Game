package gameObjects;

import gameEngine.GameCanvas;
import gameEngine.Scene;
import gameEngine.Vector2;
import java.util.Random;

import behaviors.managers.ScoreHolder;
import level.Level;

public class GameScene extends Scene {
    public GameScene(GameCanvas gameCanvas) {
        this(new Random().nextInt(), gameCanvas);
    }

    public GameScene(int seed, GameCanvas gameCanvas) {
        Level level = new Level();
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
        player.camera = gameCanvas; // TODO: Is this needed???

        addObject(player);

        GameManager gameManager = new GameManager(player, level, gameCanvas, this);
        addObject(gameManager);

        Enemy enemy = new Enemy(new Vector2<Double>(10.0, 10.0), gameManager);
        addObject(enemy);
        addObject(new Enemy(new Vector2<Double>(20.0, 10.0), gameManager));

        // addObject(new EnergyBar());

        addUIObject(new MainUICanvas(gameCanvas, (ScoreHolder) gameManager.getBehavior(ScoreHolder.class)));
    }
}

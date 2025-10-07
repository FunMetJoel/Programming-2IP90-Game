import java.util.logging.Level;

import gameEngine.GameCanvas;
import gameEngine.GameObject;

/**
 * Object to manage the game, switch the level game state.
 */
public class GameManager extends GameObject {
    Player player;
    Level currentLevel;
    GameCanvas canvas;

    public GameManager (Player player, GameCanvas canvas) {
        this.player = player;
        this.canvas = canvas;
    }

    public GameManager (Player player, Level currentLevel, GameCanvas canvas) {
        this.player = player;
        this.currentLevel = currentLevel;
        this.canvas = canvas;
    }



}

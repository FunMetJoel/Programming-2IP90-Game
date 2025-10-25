package behaviors.managers;

import behaviors.GridMovement;
import behaviors.pathfinders.Pathfinding;
import gameEngine.Behavior;
import gameEngine.GameObject;
import gameObjects.Enemy;
import gameObjects.GameManager;
import gameObjects.Player;
import java.util.ArrayList;

/**
 * Behavior of game manager that manages the game state.
 */
public class GameStateManager extends Behavior {
    ScoreHolder scoreHolder;
    public GameState gameState = GameState.beforeStart;

    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public GameStateManager(GameObject gameObject) {
        super(gameObject);
    }

    /**
     * States a game can be in.
     */
    public enum GameState {
        beforeStart,
        inGame,
        finished
    }
    
    @Override
    public void setup() {
        // TODO Auto-generated method stub
        scoreHolder = (ScoreHolder) gameObject.getBehavior(ScoreHolder.class);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        if (scoreHolder.getScore() <= 0.0) {
            gameState = GameState.finished;  
            onGameFinished();
        } 
    }

    private void onGameFinished() {
        for (Enemy enemy : enemies) {
            Pathfinding pathfinding = (Pathfinding) enemy.getBehavior(Pathfinding.class);
            pathfinding.enabled = false;
        }
        Player player = ((GameManager) gameObject).player;
        GridMovement gridMovement = (GridMovement) player.getBehavior(GridMovement.class);
        gridMovement.enabled = false;
    }

}

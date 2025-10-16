package behaviors.managers;

import java.util.ArrayList;

import behaviors.GridMovement;
import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.Grid;
import gameObjects.Enemy;
import gameObjects.GameManager;

public class GameStateManager extends Behavior {
    ScoreHolder scoreHolder;
    GameState gameState = GameState.beforeStart;

    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public GameStateManager(GameObject gameObject) {
        super(gameObject);
    }

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
            for (Enemy enemy : enemies) {
                GridMovement gridMovement = (GridMovement) enemy.getBehavior(GridMovement.class);
                gridMovement.enabled = false;
            }
            GameManager gameManager = (GameManager) gameObject;
            GridMovement gridMovement = (GridMovement) gameManager.player.getBehavior(GridMovement.class);
            gridMovement.enabled = false;
        } 
    }
}

package gameObjects;

import behaviors.EnemyCollider;
import behaviors.GridMovement;
import behaviors.PlayerCollisionDetector;
import behaviors.SpeedManager;
import behaviors.managers.GameStateManager;
import behaviors.managers.ScoreHolder;
import behaviors.pathfinders.DijkstraPathfinding;
import behaviors.pathfinders.Pathfinding;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.SpriteRenderer;
import javax.swing.ImageIcon;
import level.Mud;

/**
 * A enemy that attacks the player.
 */
public class Enemy extends GameObject {
    public GameManager gameManager;

    public Enemy(Vector2<Double> position, GameManager gameManager) {
        this(position, gameManager, DijkstraPathfinding.class);
    }

    // TODO: Make pathfinding an abstract behavior
    /**
     * Creates a new enemy.
     * @param position the position of the enemy
     * @param gameManager the game manager 
     * @param pathFindingBehaviour the kind of path finding to use //TODO: Fix this/make this work
     */
    public Enemy(
        Vector2<Double> position, GameManager gameManager, 
        Class<? extends Pathfinding> pathFindingBehaviour    
    ) {
        super(position);

        this.gameManager = gameManager;

        SpriteRenderer spriteRenderer = new SpriteRenderer(this);
        spriteRenderer.sprite = new ImageIcon("assets/TempEnemy.png").getImage();
        renderer = spriteRenderer;

        GridMovement gridMovement = new GridMovement(this, this.gameManager.currentLevel);
        gridMovement.moveTo(position.round());
        this.behaviors.add(gridMovement);

        SpeedManager speedManager = new SpeedManager(this, 5.0);
        speedManager.addRule(Mud.class, 0.5);
        this.behaviors.add(speedManager);

        DijkstraPathfinding pathFinding = new DijkstraPathfinding(
            this, 
            (GridMovement) gameManager.player.getBehavior(GridMovement.class)
        );
        this.behaviors.add(pathFinding);
        
        PlayerCollisionDetector collider = new EnemyCollider(
            this, 
            (GridMovement) gameManager.player.getBehavior(GridMovement.class), 
            (ScoreHolder) gameManager.getBehavior(ScoreHolder.class)
        );
        this.behaviors.add(collider);


        GameStateManager gameStateManager = (GameStateManager) gameManager.getBehavior(GameStateManager.class);
        gameStateManager.enemies.add(this);
    }
}

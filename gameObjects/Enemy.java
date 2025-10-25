package gameObjects;

import behaviors.EnemyCollider;
import behaviors.GridMovement;
import behaviors.PlayerCollisionDetector;
import behaviors.SpeedManager;
import behaviors.managers.GameStateManager;
import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.SpriteRenderer;
import java.awt.Image;
import level.Mud;

/**
 * A enemy that attacks the player.
 */
public abstract class Enemy extends GameObject {
    public GameManager gameManager;

    /**
     * Creates a new enemy.
     * @param position the position of the enemy
     * @param gameManager the game manager 
     * @param sprite the sprite of the enemy
     */
    public Enemy(Vector2<Double> position, GameManager gameManager, Image sprite) {
        this(position, gameManager, sprite, 5.0);
    }

    public Enemy(Vector2<Double> position, GameManager gameManager, Image sprite, double defaultSpeed) {
        super(position);
        this.gameManager = gameManager;

        SpriteRenderer spriteRenderer = new SpriteRenderer(this);
        spriteRenderer.sprite = sprite;
        renderer = spriteRenderer;

        GridMovement gridMovement = new GridMovement(this, this.gameManager.currentLevel);
        gridMovement.moveTo(position.round());
        this.behaviors.add(gridMovement);

        SpeedManager speedManager = new SpeedManager(this, defaultSpeed);
        speedManager.addRule(Mud.class, 0.5);
        this.behaviors.add(speedManager);

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

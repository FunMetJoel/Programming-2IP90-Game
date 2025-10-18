package gameObjects;

import behaviors.EnemyCollider;
import behaviors.GridMovement;
import behaviors.PathFinding;
import behaviors.PlayerCollisionDetector;
import behaviors.SpeedManager;
import behaviors.managers.GameStateManager;
import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.SpriteRenderer;
import level.Mud;

import javax.swing.ImageIcon;

public class Enemy extends GameObject {
    public GameManager gameManager;

    public Enemy(Vector2<Double> position, GameManager gameManager) {
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

        PathFinding pathFinding = new PathFinding(this);
        pathFinding.level = gameManager.currentLevel;
        pathFinding.target = (GridMovement) gameManager.player.getBehavior(GridMovement.class);
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

    // TODO: Old simple code, mayby add enemy with this caractaristic?
    // public void handleMovement() {
    //     Level level = this.gameManager.currentLevel;
    //     Player player = this.gameManager.player;
    //     if ((player.gridY < this.gridY) && level.canEnter(gridX, gridY - 1)) {
    //         this.gridY -= 1;
    //     } else if ((player.gridY > this.gridY) && level.canEnter(gridX, gridY + 1)) {
    //         this.gridY += 1;
    //     } else if ((player.gridX < this.gridX) && level.canEnter(gridX - 1, gridY)) {
    //         this.gridX -= 1;
    //     } else if ((player.gridX > this.gridX) && level.canEnter(gridX + 1, gridY)) {
    //         this.gridX += 1;
    //     }

    //     this.position.x = (double) gridX;
    //     this.position.y = (double) gridY;
    // }
}

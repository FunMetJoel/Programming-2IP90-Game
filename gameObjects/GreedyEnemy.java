package gameObjects;

import behaviors.GridMovement;
import behaviors.pathfinders.GreedyPathfinding;
import gameEngine.Vector2;
import javax.swing.ImageIcon;

/**
 * A enemy that uses the greedy algorithm to pathfind.
 */
public class GreedyEnemy extends Enemy {
    public GreedyEnemy(Vector2<Double> position, GameManager gameManager) {
        super(
            position, 
            gameManager, 
            new ImageIcon("assets/RedEnemy.png").getImage(),
            7.5
        );

        this.behaviors.add(
            new GreedyPathfinding(
                this, 
                (GridMovement) gameManager.player.getBehavior(GridMovement.class)
            )
        );
    }
}

package gameObjects;

import behaviors.GridMovement;
import behaviors.pathfinders.DijkstraPathfinding;
import gameEngine.Vector2;
import javax.swing.ImageIcon;

/**
 * A enemy that uses Dijkstra's algorithm to pathfind.
 */
public class DijkstraEnemy extends Enemy {

    /**
     * Creates new dijkstra enemy.
     * @param position the position of the enemy
     * @param gameManager the gameManager to get information about the game
     */
    public DijkstraEnemy(Vector2<Double> position, GameManager gameManager) {
        super(
            position, 
            gameManager, 
            new ImageIcon("assets/BlueEnemy.png").getImage(),
            5.0
        );

        this.behaviors.add(
            new DijkstraPathfinding(
                this, 
                (GridMovement) gameManager.player.getBehavior(GridMovement.class)
            )
        );
    }
}

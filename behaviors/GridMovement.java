package behaviors;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.Vector2;
import java.time.Duration;
import java.time.Instant;


/**
 * A behavior of a object that moves trough a grid.
 */
public class GridMovement extends Behavior {

    private int gridX = 0;
    private int gridY = 0;
    
    public level.Level level;
    private Instant lastMovement = Instant.now();
    private Vector2<Integer> lastPosition = new Vector2<Integer>(gridX, gridY);
    public Double movementSpeed = 10.0;

    /**
     * Creates a new GridMovement instance.
     * @param gameObject the parent gameObject.
     */
    public GridMovement(GameObject gameObject, level.Level level) {
        super(gameObject);
        this.level = level;
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        double deltaTime = (double) Duration.between(lastMovement, Instant.now()).toMillis();

        if (deltaTime > (1000 / movementSpeed)) {
            gameObject.setPosition((double) gridX, (double) gridY);
            return;
        }

        Vector2<Double> movement = new Vector2<Integer>(gridX, gridY).subtractVector(lastPosition.toDouble());
        gameObject.setPosition(
            ((double) gridX - movement.x) + movementSpeed * (deltaTime / 1000) * movement.x, 
            ((double) gridY - movement.y) + movementSpeed * (deltaTime / 1000) * movement.y
        );

        
    }

    /**
     * Checks wether another move can be made.
     * @return if another move can be made.
     */
    public boolean canMove() {
        double deltaTime = (double) Duration.between(lastMovement, Instant.now()).toMillis();

        if (deltaTime < (1000 / movementSpeed)) {
            return false;
        }

        return true;
    }

    public boolean canMoveBy(int dx, int dy) {
        return canMoveTo(gridX + dx, gridY + dy);
    }

    public boolean canMoveTo(int x, int y) {
        return level.canEnter(x, y);
    }

    /**
     * Moves to a position.
     * @param x the x position to move to
     * @param y the y position to move to
     */
    public void moveTo(int x, int y) {
        this.lastPosition = new Vector2<Integer>(gridX, gridY);
        this.gridX = x;
        this.gridY = y;
        lastMovement = Instant.now();
    }

    /**
     * Moves to a position.
     * @param position the position to move to
     */
    public void moveTo(Vector2<Integer> position) {
        moveTo(position.x, position.y);
    }

    /**
     * Moves by some amount.
     * @param dx the x position to move to
     * @param dy the y position to move to
     */
    public void move(int dx, int dy) {
        moveTo(gridX + dx, gridY + dy);
    }

    public Vector2<Integer> getPosition() {
        Vector2<Integer> position =  new Vector2<Integer>(gridX, gridY);
        return position;
    }
}

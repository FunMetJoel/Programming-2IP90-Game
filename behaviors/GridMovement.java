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
        this.gridX = (int) Math.round(gameObject.getPosition().x);
        this.gridY = (int) Math.round(gameObject.getPosition().y);
    }

    /**
     * Creates a new GridMovement behavior.
     * @param gameObject the game object to add the behavior to
     * @param level the level to know if a tile is enterable
     * @param pos the starting position
     */
    public GridMovement(GameObject gameObject, level.Level level, Vector2<Integer> pos) {
        this(gameObject, level);
        gridX = pos.x;
        gridY = pos.y;
        lastPosition = new Vector2<Integer>(gridX, gridY);
    }

    @Override
    public void setup() {

    }

    @Override
    public void update() {
        double deltaTime = (double) Duration.between(lastMovement, Instant.now()).toNanos() / 1e6;

        if (deltaTime > (1000 / movementSpeed)) {
            gameObject.setPosition((double) gridX, (double) gridY);
            return;
        }

        interpolateMovement(movementSpeed * (deltaTime / 1000));
    }

    /**
     * Interpolates the movement between two points.
     * @param percent at what point between the two tiles the object is
     */
    public void interpolateMovement(double percent) {
        Vector2<Double> movement = new Vector2<Integer>(gridX, gridY)
                                        .subtractVector(lastPosition.toDouble());
        gameObject.setPosition(
            ((double) gridX - movement.x) + percent * movement.x, 
            ((double) gridY - movement.y) + percent * movement.y
        );
    }

    /**
     * Checks wether another move can be made.
     * @return if another move can be made.
     */
    public boolean canMove() {
        double deltaTime = (double) Duration.between(lastMovement, Instant.now()).toNanos() / 1e6;

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
        return new Vector2<Integer>(gridX, gridY);
    }
}

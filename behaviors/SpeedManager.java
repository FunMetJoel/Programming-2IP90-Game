package behaviors;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.GridItem;
import gameEngine.Vector2;
import java.util.ArrayList;

/**
 * Behavior that manages the speed a entity moves at.
 */
public class SpeedManager extends Behavior {
    GridMovement gridMovement;

    public double defaultMovementSpeed = 10.0;
    private ArrayList<SpeedRule> rules = new ArrayList<SpeedRule>();
    
    /**
     * Creates new behavior.
     * @param gameObject the game object to add the behavior to
     * @param defaultMovementSpeed the default movement speed
     */
    public SpeedManager(GameObject gameObject, double defaultMovementSpeed) {
        super(gameObject);
        this.gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);
        this.defaultMovementSpeed = defaultMovementSpeed;
    }

    @Override
    public void setup() {

    }

    @Override
    public void update() {
        Vector2<Integer> position = gridMovement.getPosition();
        GridItem gridItem = gridMovement.level.getTile(position.x, position.y);
        
        gridMovement.movementSpeed = getSpeed(gridItem.getClass());
    }

    // TODO: Make this a dictionary?
    private record SpeedRule(Class<? extends GridItem> tileClass, double multiplier) { }

    public void addRule(Class<? extends GridItem> tileClass, double multiplier) {
        this.rules.add(new SpeedRule(tileClass, multiplier));
    }

    public double getSpeed(Class<? extends GridItem> tileClass) {
        double speed = defaultMovementSpeed;
        for (SpeedRule speedRule : rules) {
            if (speedRule.tileClass == tileClass) {
                speed *= speedRule.multiplier;
            }
        }
        return speed;
    }
}

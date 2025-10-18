package behaviors;

import java.util.ArrayList;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.GridItem;
import gameEngine.Vector2;

public class SpeedManager extends Behavior {
    GridMovement gridMovement;

    public double defaultMovementSpeed = 10.0;
    private ArrayList<SpeedRule> rules = new ArrayList<SpeedRule>();
    
    public SpeedManager(GameObject gameObject, double defaultMovementSpeed) {
        super(gameObject);
        this.gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);
        this.defaultMovementSpeed = defaultMovementSpeed;
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        double speed = defaultMovementSpeed;
        Vector2<Integer> position = gridMovement.getPosition();
        GridItem gridItem = gridMovement.level.getTile(position.x, position.y);
        for (SpeedRule speedRule : rules) {
            if (speedRule.tileClass == gridItem.getClass()) {
                speed *= speedRule.multiplier;
            }
        }
        gridMovement.movementSpeed = speed;
    }

    // TODO: Make this a dictionary?
    private record SpeedRule(Class<? extends GridItem> tileClass, double multiplier) { }

    public void addRule(Class<? extends GridItem> tileClass, double multiplier) {
        this.rules.add(new SpeedRule(tileClass, multiplier));
    }
}

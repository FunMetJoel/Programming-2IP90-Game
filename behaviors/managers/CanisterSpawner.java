package behaviors.managers;

import behaviors.Edible;
import behaviors.GridMovement;
import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameObjects.Canister;
import java.util.ArrayList;
import java.util.Random;
import level.Level;

public class CanisterSpawner extends Behavior {

    Level level;
    GridMovement playerMovement;
    ArrayList<Canister> activeGameObjects = new ArrayList<Canister>();
    ScoreHolder scoreHolder;

    public CanisterSpawner(GameObject gameObject, Level level, GridMovement playerMovement) {
        super(gameObject);
        this.level = level;
        this.playerMovement = playerMovement;
    }

    @Override
    public void setup() {
        this.scoreHolder = (ScoreHolder) gameObject.getBehavior(ScoreHolder.class);
        // TODO Auto-generated method stub
        for (int i = 0; i < 5; i++) {
            Canister newCanister = new Canister(playerMovement, new Vector2<Integer>(0, 0), scoreHolder);
            activeGameObjects.add(newCanister);
            gameObject.addChild(newCanister);
            spawn(newCanister);
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        for (Canister canister : activeGameObjects) {
            if (!canister.isActive) {
                spawn(canister);
            }
        }
    }

    public void spawn(Canister canister) {
        int gridSize = level.gridSize;
        int x = Math.round(new Random().nextFloat() * (gridSize - 1));
        int y = Math.round(new Random().nextFloat() * (gridSize - 1));

        if (!level.canEnter(x, y)) { // Try a different position
            spawn(canister);
            return;
        }

        spawn(canister, x, y);
    }

    public void spawn(Canister canister, int x, int y) {
        canister.isActive = true;
        Edible edible = (Edible) canister.getBehavior(Edible.class);
        edible.isEaten = false;
        GridMovement gridMovement = (GridMovement) canister.getBehavior(GridMovement.class);
        gridMovement.moveTo(x, y);
        
    }
}

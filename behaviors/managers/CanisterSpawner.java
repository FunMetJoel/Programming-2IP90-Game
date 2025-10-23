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

/**
 * Behavior of manager to spawn canisters around the map.
 */
public class CanisterSpawner extends Behavior {

    Level level;
    GridMovement playerMovement;
    ArrayList<Canister> activeGameObjects = new ArrayList<Canister>();
    ScoreHolder scoreHolder;

    /**
     * Creates new spawner behavior.
     * @param gameObject the game object to add the behavior to
     * @param level the current level to know where to place the canisters
     * @param playerMovement the player movement to pass to the canisters
     */
    public CanisterSpawner(GameObject gameObject, Level level, GridMovement playerMovement) {
        super(gameObject);
        this.level = level;
        this.playerMovement = playerMovement;
    }

    @Override
    public void setup() {
        this.scoreHolder = (ScoreHolder) gameObject.getBehavior(ScoreHolder.class);
        for (int i = 0; i < 5; i++) {
            Canister newCanister = new Canister(
                playerMovement, new Vector2<Integer>(0, 0), scoreHolder
            );
            activeGameObjects.add(newCanister);
            gameObject.addChild(newCanister);
            spawn(newCanister);
        }
    }

    @Override
    public void update() {
        for (Canister canister : activeGameObjects) {
            if (!canister.isActive) {
                spawn(canister);
            }
        }
    }

    /**
     * Spawns a canister.
     * @param canister the canister to (re)spawn
     */
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

    /**
     * Spawns a canister at a specific location.
     * @param canister the canister to (re)spawn
     * @param x the new x position of the canister
     * @param y the new y position of the canister
     */
    public void spawn(Canister canister, int x, int y) {
        canister.isActive = true;
        Edible edible = (Edible) canister.getBehavior(Edible.class);
        edible.isEaten = false;
        GridMovement gridMovement = (GridMovement) canister.getBehavior(GridMovement.class);
        gridMovement.moveTo(x, y);
        
    }
}

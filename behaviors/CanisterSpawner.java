package behaviors;

import java.util.ArrayList;
import java.util.Random;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameObjects.Canister;
import level.Level;

public class CanisterSpawner extends Behavior {

    Level level;
    GridMovement playerMovement;
    ArrayList<Canister> activeGameObjects = new ArrayList<Canister>();

    public CanisterSpawner(GameObject gameObject, Level level, GridMovement playerMovement) {
        super(gameObject);
        this.level = level;
        this.playerMovement = playerMovement;
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 5; i++) {
            spawn();
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    public void spawn() {
        int gridSize = level.gridSize;
        int x = Math.round(new Random().nextFloat() * gridSize);
        int y = Math.round(new Random().nextFloat() * gridSize);
        spawn(x, y);
    }

    public void spawn(int x, int y) {
        Canister newCanister = new Canister(playerMovement, new Vector2<Integer>(x, y));
        this.activeGameObjects.add(newCanister);
        gameObject.addChild(newCanister);
    }
}

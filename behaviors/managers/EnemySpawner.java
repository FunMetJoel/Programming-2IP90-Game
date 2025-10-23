package behaviors.managers;

import gameEngine.Behavior;
import gameEngine.Scene;
import gameEngine.Vector2;
import gameObjects.Enemy;
import gameObjects.GameManager;

public class EnemySpawner extends Behavior {
    Scene scene;
    public EnemySpawner(GameManager gameObject, Scene scene) {
        super(gameObject);
        this.scene = scene;
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
        scene.instantiate(new Enemy(new Vector2<Double>(11.0, 11.0), (GameManager) gameObject));
        scene.instantiate(new Enemy(new Vector2<Double>(31.0, 11.0), (GameManager) gameObject));
        scene.instantiate(new Enemy(new Vector2<Double>(31.0, 31.0), (GameManager) gameObject));
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }
}

import java.awt.Color;
import java.awt.event.*;
import java.time.Instant;
import java.time.Duration;

import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.InputManager;
import gameEngine.Vector2;
import gameEngine.dummyObjects.Circle;

public class Player extends GameObject{

    GameCanvas camera;
    int gridX = 0;
    int gridY = 0;

    Instant lastMovement = Instant.now();

    Player(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);

        Circle circle = new Circle(new Vector2<Double>(0.0, 0.0), new Vector2<Double>(1.0, 1.0), Color.blue, Color.blue);

        children.add(circle);
    }

    @Override
    public void update() {
        
        handleMovement();

        camera.cameraPosition = position;
    }


    void handleMovement() {

        if (Duration.between(lastMovement, Instant.now()).toMillis() < 100) {
            return;
        }
        lastMovement = Instant.now();

        this.position = new Vector2<Double>((double) gridX, (double) gridY);

        if (InputManager.isPressed(KeyEvent.VK_W)) {
            this.gridY -= 1;
        }
        if (InputManager.isPressed(KeyEvent.VK_S)) {
            this.gridY += 1;
        }
        if (InputManager.isPressed(KeyEvent.VK_A)) {
            this.gridX -= 1;
        }
        if (InputManager.isPressed(KeyEvent.VK_D)) {
            this.gridX += 1;
        }

        Vector2<Double> direction = new Vector2<Double>(
            Math.signum(gridX - position.x), 
            Math.signum(gridY - position.y)
        );

        // this.position.

        this.position = new Vector2<Double>((double) gridX, (double) gridY);
    }
}

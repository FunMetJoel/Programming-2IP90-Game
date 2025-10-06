import java.awt.Color;
import java.awt.Graphics;
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

    public level.Level level;

    Instant lastMovement = Instant.now();

    Vector2<Double> direction = new Vector2<Double>(0.0, 0.0);

    Player(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);

        // Circle circle = new Circle(new Vector2<Double>(0.0, 0.0), new Vector2<Double>(1.0, 1.0), Color.blue, Color.blue);

        // children.add(circle);
    }

    public void paint(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        graphics.setColor(Color.red);

        graphics.fillOval(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y)
        );

        graphics.setColor(Color.BLACK);
        graphics.drawOval(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y)
        );
    }

    @Override
    public void update() {
        
        handleMovement();

        camera.cameraPosition.x = position.x;
        camera.cameraPosition.y = position.y;
    }


    void handleMovement() {

        double deltaTime = (double) Duration.between(lastMovement, Instant.now()).toMillis();

        this.position.x = ((double) gridX - direction.x) + (deltaTime / 100) * direction.x;
        this.position.y = ((double) gridY - direction.y) + (deltaTime / 100) * direction.y;

        if (deltaTime < 100) {
            return;
        }
        lastMovement = Instant.now();
        this.position.x = (double) gridX;
        this.position.y = (double) gridY;

        if (InputManager.isPressed(KeyEvent.VK_W)) {
            if (level.canEnter(gridX, gridY - 1)) {
                this.gridY -= 1;
            }
        }
        if (InputManager.isPressed(KeyEvent.VK_S)) {
            if (level.canEnter(gridX, gridY + 1)) {
                this.gridY += 1;
            }
        }
        if (InputManager.isPressed(KeyEvent.VK_A)) {
            if (level.canEnter(gridX - 1, gridY)) {
                this.gridX -= 1;
            }
        }
        if (InputManager.isPressed(KeyEvent.VK_D)) {
            if (level.canEnter(gridX + 1, gridY)) {
                this.gridX += 1;
            }
        }

        direction = new Vector2<Double>(
            Math.signum(gridX - position.x), 
            Math.signum(gridY - position.y)
        );
    }
}

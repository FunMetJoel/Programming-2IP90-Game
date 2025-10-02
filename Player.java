import java.awt.Color;
import java.awt.event.*;

import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.InputManager;
import gameEngine.Vector2;
import gameEngine.dummyObjects.Circle;

public class Player extends GameObject{

    GameCanvas camera;

    Player(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);

        Circle circle = new Circle(position, scale, Color.blue, Color.blue);

        children.add(circle);
    }

    @Override
    public void update() {
        if (InputManager.isPressed(KeyEvent.VK_W)) {
            this.position.y -= 0.000001;
        }
        if (InputManager.isPressed(KeyEvent.VK_S)) {
            this.position.y += 0.000001;
        }
        if (InputManager.isPressed(KeyEvent.VK_A)) {
            this.position.x -= 0.000001;
        }
        if (InputManager.isPressed(KeyEvent.VK_D)) {
            this.position.x += 0.000001;
        }

        camera.cameraPosition = this.position;
    }

}

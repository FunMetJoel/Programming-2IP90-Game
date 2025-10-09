import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.time.Instant;

import javax.swing.ImageIcon;

import java.time.Duration;

import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.InputManager;
import gameEngine.Vector2;
import gameEngine.dummyObjects.Circle;
import java.awt.Image;


public class Player extends GameObject{

    GameCanvas camera;
    int gridX = 0;
    int gridY = 0;

    public Vector2<Integer> screenMiddle = new Vector2<Integer>(0, 0);

    static Image image = new ImageIcon("assets/TempPlayer.png").getImage();

    public level.Level level;

    Instant lastMovement = Instant.now();

    Vector2<Double> direction = new Vector2<Double>(0.0, 0.0);

    Player(Vector2<Double> position, Vector2<Double> scale) {
        super(position, scale);
    }

    public void paint(Graphics[] graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        graphics[1].drawImage(
            image,
            screenMiddle.x - (int) Math.round(scale.x * 0.5), 
            screenMiddle.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y),
            null
        );
    }

    @Override
    public void update() {
        handleMovement();
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

        if (InputManager.isPressed(KeyEvent.VK_W) && level.canEnter(gridX, gridY - 1)) {
            this.gridY -= 1;
        } else if (InputManager.isPressed(KeyEvent.VK_S) && level.canEnter(gridX, gridY + 1)) {
            this.gridY += 1;
        } else if (InputManager.isPressed(KeyEvent.VK_A) && level.canEnter(gridX - 1, gridY)) {
            this.gridX -= 1;
        } else if (InputManager.isPressed(KeyEvent.VK_D) && level.canEnter(gridX + 1, gridY)) {
            this.gridX += 1;
        }

        direction = new Vector2<Double>(
            Math.signum(gridX - position.x), 
            Math.signum(gridY - position.y)
        );
    }
}

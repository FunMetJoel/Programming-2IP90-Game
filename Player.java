import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.time.Instant;

import javax.swing.ImageIcon;

import behaviors.GridMovement;

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

    Player(Vector2<Double> position, Vector2<Double> scale, level.Level level) {
        super(position, scale);
        this.behaviors.add(
            new GridMovement(this, level)
        );
    }

    public void paint(Graphics2D[] graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
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
        GridMovement gridMovement = (GridMovement) this.getBehavior(GridMovement.class);
        if (!gridMovement.canMove()) {
            return;
        }

        if (InputManager.isPressed(KeyEvent.VK_W) && gridMovement.canMoveBy(0, -1)) {
            gridMovement.move(0, -1);
        } else if (InputManager.isPressed(KeyEvent.VK_S) && gridMovement.canMoveBy(0, 1)) {
            gridMovement.move(0, 1);
        } else if (InputManager.isPressed(KeyEvent.VK_A) && gridMovement.canMoveBy(-1, 0)) {
            gridMovement.move(-1, 0);
        } else if (InputManager.isPressed(KeyEvent.VK_D) && gridMovement.canMoveBy(1, 0)) {
            gridMovement.move(1, 0);
        }
    }
}

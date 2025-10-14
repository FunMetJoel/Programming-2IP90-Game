import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.time.Instant;

import javax.swing.ImageIcon;

import behaviors.GridMovement;
import behaviors.PlayerController;

import java.time.Duration;

import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.InputManager;
import gameEngine.Vector2;
import gameEngine.dummyObjects.Circle;
import java.awt.Image;


public class Player extends GameObject{

    GameCanvas camera;

    public Vector2<Integer> screenMiddle = new Vector2<Integer>(0, 0);

    static Image image = new ImageIcon("assets/TempPlayer.png").getImage();

    public level.Level level;

    Player(Vector2<Double> position, Vector2<Double> scale, level.Level level) {
        super(position, scale);
        this.behaviors.add(new GridMovement(this, level));
        this.behaviors.add(new PlayerController(this));
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
}

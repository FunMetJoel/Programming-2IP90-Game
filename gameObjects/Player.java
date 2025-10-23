package gameObjects;

import behaviors.GridMovement;
import behaviors.PlayerController;
import behaviors.SpeedManager;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.SpriteRenderer;
import java.awt.Image;
import javax.swing.ImageIcon;
import level.Mud;

/**
 * The player character of the game.
 */
public class Player extends GameObject {

    public Vector2<Integer> screenMiddle = new Vector2<Integer>(0, 0);

    static Image image = new ImageIcon("assets/TempPlayer.png").getImage();

    public level.Level level;

    /**
     * Creates the player.
     * @param position the starting position of the player
     * @param scale the scale of the player
     * @param level the level the player is in
     */
    Player(Vector2<Double> position, Vector2<Double> scale, level.Level level) {
        super(position, scale);
        this.behaviors.add(new GridMovement(this, level));

        SpeedManager speedManager = new SpeedManager(this, 10.0);
        speedManager.addRule(Mud.class, 0.5);
        this.behaviors.add(speedManager);

        this.behaviors.add(new PlayerController(this));
        SpriteRenderer spriteRenderer = new SpriteRenderer(this);
        spriteRenderer.sprite = image;
        spriteRenderer.renderInCenter = true;
        renderer = spriteRenderer;
    }
}

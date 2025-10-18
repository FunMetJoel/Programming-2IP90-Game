package gameObjects;

import behaviors.GridMovement;
import behaviors.PlayerController;
import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.SpriteRenderer;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends GameObject {

    GameCanvas camera;

    public Vector2<Integer> screenMiddle = new Vector2<Integer>(0, 0);

    static Image image = new ImageIcon("assets/TempPlayer.png").getImage();

    public level.Level level;

    Player(Vector2<Double> position, Vector2<Double> scale, level.Level level) {
        super(position, scale);
        this.behaviors.add(new GridMovement(this, level));
        this.behaviors.add(new PlayerController(this));
        SpriteRenderer spriteRenderer = new SpriteRenderer(this);
        spriteRenderer.sprite = image;
        spriteRenderer.renderInCenter = true;
        renderer = spriteRenderer;
    }
}

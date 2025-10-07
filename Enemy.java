import java.awt.Graphics;
import java.awt.Image;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gameEngine.GameObject;
import gameEngine.Vector2;
import level.Level;

public class Enemy extends GameObject {
    static Image image = new ImageIcon("assets/TempEnemy.png").getImage();
    GameManager gameManager;
    int gridX;
    int gridY;

    Instant lastMovement = Instant.now();

    public Enemy(Vector2<Double> position, GameManager gameManager) {
        super(position);
        this.gridX = (int) Math.round(position.x);
        this.gridY = (int) Math.round(position.y);
        this.gameManager = gameManager;
    }

    @Override
    public void paint(Graphics[] graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        graphics[1].drawImage(
            image,
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y),
            null
        );
    }

    @Override
    public void update() {
        double deltaTime = (double) Duration.between(lastMovement, Instant.now()).toMillis();

        // this.position.x = ((double) gridX - direction.x) + (deltaTime / 100) * direction.x;
        // this.position.y = ((double) gridY - direction.y) + (deltaTime / 100) * direction.y;

        if (deltaTime < 200) {
            return;
        }
        lastMovement = Instant.now();

        Level level = this.gameManager.currentLevel;
        Player player = this.gameManager.player;
        if ((player.gridY < this.gridY) && level.canEnter(gridX, gridY - 1)) {
            this.gridY -= 1;
        } else if ((player.gridY > this.gridY) && level.canEnter(gridX, gridY + 1)) {
            this.gridY += 1;
        } else if ((player.gridX < this.gridX) && level.canEnter(gridX - 1, gridY)) {
            this.gridX -= 1;
        } else if ((player.gridX > this.gridX) && level.canEnter(gridX + 1, gridY)) {
            this.gridX += 1;
        }

        this.position.x = (double) gridX;
        this.position.y = (double) gridY;
    }
}

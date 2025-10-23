package gameObjects;

import java.awt.Color;

import org.w3c.dom.Text;

import behaviors.DeathScreenAnimator;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.RegularShapeRenderer;
import gameEngine.renderers.TextRenderer;

public class DeathScreenCanvas extends GameObject {
    
    public DeathScreenCanvas(GameManager gameManager) {
        RegularShapeRenderer renderer = new RegularShapeRenderer(this);
        renderer.fillColor = new Color(0, 0, 0, 100);
        renderer.shape = RegularShapeRenderer.Shape.rectangle;
        renderer.borderColor = new Color(0, 0, 0, 0);
        renderer.mainLayer = 0;
        this.renderer = renderer;

        GameObject gameOverText = new GameObject();
        gameOverText.renderer = new TextRenderer(gameOverText, "GAME OVER", Color.red);
        gameOverText.setScale(0.8, 0.25);
        gameOverText.setPosition(0.0, -0.3);
        this.addChild(gameOverText);

        GameObject scoreText = new GameObject();
        scoreText.renderer = new TextRenderer(scoreText, "Score: ...");
        scoreText.setScale(1, 0.10);
        scoreText.setPosition(0.0, 0.0);
        this.addChild(scoreText);

        this.scale = new Vector2<Double>(0.0, 0.0);

        this.behaviors.add(new DeathScreenAnimator(this, gameManager, (TextRenderer) scoreText.renderer));
    }
}

package gameObjects;

import java.awt.Color;

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

        GameObject GameOverText = new GameObject();
        GameOverText.renderer = new TextRenderer(GameOverText, "GAME OVER");
        GameOverText.setScale(0.8, 0.25);
        GameOverText.setPosition(0.0, -0.3);
        this.addChild(GameOverText);

        this.scale = new Vector2<Double>(0.0, 0.0);

        this.behaviors.add(new DeathScreenAnimator(this, gameManager));
    }
}

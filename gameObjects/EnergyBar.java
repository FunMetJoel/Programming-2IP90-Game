package gameObjects;


import behaviors.ScoreBarManager;
import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;
import gameEngine.renderers.RegularShapeRenderer;
import java.awt.Color;

/**
 * A bar that holds the energy of the player.
 */
public class EnergyBar extends GameObject {
    
    /**
     * Creates the energy bar.
     * @param scoreHolder the score holder that holds the score to display
     */
    public EnergyBar(ScoreHolder scoreHolder) {
        super();
        RegularShapeRenderer renderer = new RegularShapeRenderer(this);
        this.renderer = renderer;
        renderer.shape = RegularShapeRenderer.Shape.rectangle;
        renderer.fillColor = new Color(0, 0, 0, 150);
        renderer.borderColor = new Color(200, 0, 100, 100);

        this.scale.x = 0.5;
        this.scale.y = 0.05;
        this.position.y = - 0.45;
        

        this.behaviors.add(
            new ScoreBarManager(this, scoreHolder)
        );

        
    }
}

package gameObjects;
import java.awt.Color;

import behaviors.ScoreBarManager;
import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;
import gameEngine.renderers.RegularShapeRenderer;

public class EnergyBar extends GameObject {
    
    public EnergyBar(ScoreHolder scoreHolder) {
        super();
        RegularShapeRenderer renderer = new RegularShapeRenderer(this);
        this.renderer = renderer;
        renderer.shape = RegularShapeRenderer.Shape.rectangle;
        // renderer.borderColor = Color.PINK;
        renderer.fillColor = new Color(0, 0, 0, 150);
        renderer.borderColor = new Color(200, 0, 100, 100);
        // renderer.mainLayer = 3;ss
        // renderer.renderInCenter = true;
        // renderer.constantScreenSize = true;

        this.scale.x = 0.5;
        this.scale.y = 0.05;
        this.position.y = - 0.45;
        

        this.behaviors.add(
            new ScoreBarManager(this, scoreHolder)
        );

        
    }
}

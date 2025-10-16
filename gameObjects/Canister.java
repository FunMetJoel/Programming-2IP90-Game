package gameObjects;
import javax.swing.ImageIcon;

import behaviors.CanisterSpawner;
import behaviors.Edible;
import behaviors.GridMovement;
import behaviors.ScoreHolder;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.RegularShapeRenderer;
import gameEngine.renderers.SpriteRenderer;

public class Canister extends GameObject{
    public Canister(GridMovement playerMovement, Vector2<Integer> position, ScoreHolder scoreHolder){
        super();

        GameObject canisterSpriteObject = new GameObject();
        canisterSpriteObject.setScale(0.7, 0.7);
        SpriteRenderer renderer = new SpriteRenderer(canisterSpriteObject);
        renderer.sprite = new ImageIcon("assets/nether_star.png").getImage();
        canisterSpriteObject.renderer = renderer;
        this.addChild(canisterSpriteObject);

        this.behaviors.add(
            new GridMovement(this, null, position)
        );
        this.behaviors.add(
            new Edible(this, playerMovement, scoreHolder, canisterSpriteObject)
        );

        // this.renderer = new RegularShapeRenderer(this);


        
    }
}

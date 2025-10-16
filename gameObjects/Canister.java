package gameObjects;
import behaviors.Edible;
import behaviors.GridMovement;
import gameEngine.GameObject;
import gameEngine.Vector2;
import gameEngine.renderers.RegularShapeRenderer;

public class Canister extends GameObject{
    public Canister(GridMovement playerMovement, Vector2<Integer> position){
        super();
        this.behaviors.add(
            new GridMovement(this, null, position)
        );
        this.behaviors.add(
            new Edible(this, playerMovement)
        );

        this.renderer = new RegularShapeRenderer(this);
    }
}

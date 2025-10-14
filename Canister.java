import behaviors.Edible;
import behaviors.GridMovement;
import gameEngine.GameObject;

public class Canister extends GameObject{
    public Canister(GridMovement playerMovement){
        super();
        this.behaviors.add(
            new GridMovement(this, null)
        );
        this.behaviors.add(
            new Edible(this, playerMovement)
        );
    }
}

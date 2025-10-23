package behaviors;

import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;
import gameEngine.Vector2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * A behavior of a item that can be eaten.
 */
public class Edible extends PlayerCollisionDetector {

    ScoreHolder scoreHolder;
    public boolean isEaten = false;
    Timer animator;
    GameObject spriteObject;

    @Override
    void onCollide() {
        if (isEaten) {
            return;
        }

        scoreHolder.addScore(1.0);
        isEaten = true;

        ActionListener taskPerformer = new ActionListener() {
            int frame = 0;

            public void actionPerformed(ActionEvent evt) {
                frame++;
                spriteObject.setScale(0.8 + (double) frame * 0.3, 0.8 + (double) frame * 0.3);

                if (frame >= 4) {
                    spriteObject.setScale(0.8, 0.8);
                    gameObject.isActive = false;
                    animator.start();
                    ((Timer) evt.getSource()).stop();
                }
            }

            
        };
        Timer pickedUpAnimator = new Timer(5, taskPerformer);
        pickedUpAnimator.setRepeats(true);
        animator.stop();
        pickedUpAnimator.start();        
    }

    /**
     * Creates new edible behavior.
     * @param gameObject the gameObject to add the behavior to
     * @param playerMovement the movement script of the player to track its position
     * @param scoreHolder the score holder to edit the score
     * @param spriteObject the sprite of the gameObject
     */
    public Edible(
        GameObject gameObject, GridMovement playerMovement, 
        ScoreHolder scoreHolder, GameObject spriteObject
    ) {
        super(gameObject, playerMovement);
        this.scoreHolder = scoreHolder;
        this.spriteObject = spriteObject;

        int delay = 10; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            double direction = 1.0;

            public void actionPerformed(ActionEvent evt) {
                if (spriteObject.getPosition().y <= -0.2) {
                    direction = -1.0;
                } else if (spriteObject.getPosition().y >= 0.0) {
                    direction = 1.0;
                }
                
                Vector2<Double> newPosition = spriteObject.getPosition().addVector(
                    new Vector2<Double>(0.0, -0.01).newScaledVector(direction)
                );
                spriteObject.setPosition(newPosition);
            }
        };
        animator = new Timer(delay, taskPerformer);
        animator.setRepeats(true);
        animator.start();
    }

    @Override
    void onCollisionStay(double collisionTime) {
        // TODO Auto-generated method stub
    }

    @Override
    void onCollisionExit() {
        // TODO Auto-generated method stub
        
    }
}

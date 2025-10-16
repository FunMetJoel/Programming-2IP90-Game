package behaviors;

import gameEngine.GameObject;
import gameEngine.Vector2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import behaviors.managers.ScoreHolder;

public class Edible extends PlayerCollisionDetector {

    ScoreHolder scoreHolder;
    public boolean isEten = false;
    Timer animator;

    @Override
    void onCollide() {
        if (isEten) {
            return;
        }

        scoreHolder.addScore(1.0);
        System.out.println(scoreHolder.getScore());
        isEten = true;
        // TODO: Make this better
        gameObject.isActive = false;
    }

    public Edible(GameObject gameObject, GridMovement playerMovement, ScoreHolder scoreHolder, GameObject spriteObject) {
        super(gameObject, playerMovement);
        this.scoreHolder = scoreHolder;

        int delay = 10; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            double direction = 1.0;

            public void actionPerformed(ActionEvent evt) {
                if (spriteObject.getPosition().y <= -0.2) {
                    direction = -1.0;
                } else if (spriteObject.getPosition().y >= 0.0) {
                    direction = 1.0;
                }
                
                Vector2<Double> newPosition = spriteObject.getPosition().addVector(new Vector2<Double>(0.0, -0.01).newScaledVector(direction));
                spriteObject.setPosition(newPosition);
            }
        };
        animator = new Timer(delay, taskPerformer);
        animator.setRepeats(true);
        animator.start();
    }
}

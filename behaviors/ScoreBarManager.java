package behaviors;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.renderers.SpriteRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import behaviors.managers.ScoreHolder;

public class ScoreBarManager extends Behavior {

    GameObject[] partElements;
    int numberOfParts = 10;
    ScoreHolder scoreHolder;
    int lastFilledCount = 0;

    public ScoreBarManager(GameObject gameObject, ScoreHolder scoreHolder) {
        super(gameObject);
        this.scoreHolder = scoreHolder;
    }

    @Override
    public void setup() {
        partElements = new GameObject[numberOfParts];
        for (int i = 0; i < partElements.length; i++) {
            partElements[i] = new GameObject();
            GameObject spriteHolder = new GameObject();
            partElements[i].addChild(spriteHolder);

            SpriteRenderer spriteRenderer = new SpriteRenderer(spriteHolder);
            spriteRenderer.sprite = new ImageIcon("assets/nether_star.png").getImage();
            spriteHolder.renderer = spriteRenderer;

            partElements[i].setPosition(((double) (i - 4.5) / partElements.length), 0);
            partElements[i].setScale((1.0 / partElements.length), 1.0);

            gameObject.addChild(partElements[i]);
        }

        lastFilledCount = Long.valueOf(Math.round(scoreHolder.getScore())).intValue();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        double filled = Math.floor(scoreHolder.getScore());
        double filledPercent = scoreHolder.getScore() % 1.0;
        for (int i = 0; i < partElements.length; i++) { 
            if (i < filled) {
                partElements[i].setScale((1.0 / partElements.length), 1.0);
            } else if (i > filled) {
                partElements[i].setScale(0.0, 0.0);
            } else {
                partElements[i].setScale((filledPercent / partElements.length), filledPercent);
            }
        }

        if (filled > lastFilledCount && lastFilledCount >= 0) {
            gainAnimation(lastFilledCount);
            System.out.println(filled + ", " + lastFilledCount);
        }
        lastFilledCount = Long.valueOf(Math.round(filled)).intValue();

    }

    private void gainAnimation(int index) {
        ActionListener gainAnimation = new ActionListener() {
            int frame = 0;

            public void actionPerformed(ActionEvent evt) {
                frame++;
                GameObject sprite = partElements[index].children.get(0);

                double scale = -0.025 * frame * frame + 0.25 * (double) frame;
                sprite.setScale(1.0 + scale, 1.0 + scale);

                if (frame >= 10) {
                    sprite.setScale(1.0, 1.0);
                    ((Timer) evt.getSource()).stop();
                }
            }
        };
        Timer gainAnimator = new Timer(10, gainAnimation);
        gainAnimator.setRepeats(true);
        gainAnimator.start();    
    }


}

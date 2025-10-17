package behaviors.managers;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.renderers.RegularShapeRenderer;
import gameEngine.renderers.RegularShapeRenderer.Shape;

import java.awt.Color;

public class ScoreBarManager extends Behavior {

    GameObject[] partElements;
    int numberOfParts = 10;
    ScoreHolder scoreHolder;

    public ScoreBarManager(GameObject gameObject, ScoreHolder scoreHolder) {
        super(gameObject);
        this.scoreHolder = scoreHolder;
    }

    @Override
    public void setup() {
        partElements = new GameObject[numberOfParts];
        for (int i = 0; i < partElements.length; i++) {
            partElements[i] = new GameObject();

            // RegularShapeRenderer childRenderer = new RegularShapeRenderer(partElements[i]);
            // childRenderer.fillColor = Color.ORANGE;
            // partElements[i].renderer = childRenderer;
            
            // gameObject.addChild(partElements[i]);

            RegularShapeRenderer renderer = new RegularShapeRenderer(partElements[i]);
            renderer.fillColor = new Color(0, 0, 0, 0);
            renderer.borderColor = Color.BLACK;
            renderer.shape = Shape.rectangle;
            renderer.mainLayer = 1;
            renderer.renderInCenter = false;
            renderer.constantScreenSize = true;
            partElements[i].renderer = renderer;
            partElements[i].setPosition(((double) (i - 4.5) / partElements.length), 0);
            partElements[i].setScale((1.0 / partElements.length), 1.0);

            gameObject.addChild(partElements[i]);
        }
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
    }


}

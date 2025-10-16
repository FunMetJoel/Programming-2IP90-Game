package behaviors.managers;

import java.awt.Color;

import gameEngine.Behavior;
import gameEngine.GameObject;
import gameEngine.renderers.RegularShapeRenderer;

public class ScoreBarManager extends Behavior {

    GameObject[] partElements;
    int numberOfParts = 5;

    public ScoreBarManager(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void setup() {
        partElements = new GameObject[numberOfParts];
        for (int i = 0; i < partElements.length; i++) {
            partElements[i] = new GameObject();
            RegularShapeRenderer renderer = new RegularShapeRenderer(partElements[i]);
            renderer.fillColor = Color.red;
            renderer.mainLayer = 5;
            renderer.renderInCenter = false;
            renderer.constantScreenSize = true;
            partElements[i].renderer = renderer;
            partElements[i].setPosition(((double) i / partElements.length), 0);

            gameObject.addChild(partElements[i]);
            System.out.println(
                partElements[i].getPosition() + ", "
                + partElements[i].getScale()
            );
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }


}

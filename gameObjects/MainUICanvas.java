package gameObjects;

import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;
import gameEngine.renderers.RegularShapeRenderer;

public class MainUICanvas extends GameObject {
    public MainUICanvas(ScoreHolder scoreHolder) {
        addChild(new EnergyBar(scoreHolder));
    }
}

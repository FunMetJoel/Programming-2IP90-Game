package gameObjects;

import behaviors.managers.ScoreHolder;
import gameEngine.GameObject;

/**
 * The canvas with the main ui.
 */
public class MainUICanvas extends GameObject {
    public MainUICanvas(ScoreHolder scoreHolder) {
        addChild(new EnergyBar(scoreHolder));
    }
}

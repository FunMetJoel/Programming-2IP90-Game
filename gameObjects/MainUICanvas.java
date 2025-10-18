package gameObjects;

import behaviors.managers.ScoreHolder;
import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.renderers.RegularShapeRenderer;

public class MainUICanvas extends GameObject {
    public MainUICanvas(GameCanvas gameCanvas, ScoreHolder scoreHolder) {

        // renderer = new RegularShapeRenderer(this);

        addChild(new EnergyBar(scoreHolder));
    }
}

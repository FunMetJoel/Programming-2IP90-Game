package behaviors;

import gameEngine.Behavior;
import gameEngine.GameObject;

public class ScoreHolder extends Behavior {

    private double score = 0.0;

    public ScoreHolder(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    public double getScore() {
        return score;
    }

    public void addScore(double score) {
        this.score += score;
    }


}

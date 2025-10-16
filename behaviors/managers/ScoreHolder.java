package behaviors.managers;

import gameEngine.Behavior;
import gameEngine.GameObject;
import java.time.Duration;
import java.time.Instant;

public class ScoreHolder extends Behavior {

    private double score = 2.0;
    private double totalGrabedStore = 0.0;

    private Instant lastUpdate = Instant.now();

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
        double deltaPoints = (double) Duration.between(lastUpdate, Instant.now()).toMillis() * 0.0001;
        score = Math.max(score - deltaPoints, 0);
        lastUpdate = Instant.now();
        System.out.println(score + ", " + totalGrabedStore);
    }

    public double getScore() {
        return score;
    }

    public void addScore(double score) {
        this.score += score;
        this.totalGrabedStore += score;
    }

    public void removeScore(double score) {
        this.score -= score;
    }
}

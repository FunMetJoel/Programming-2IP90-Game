package behaviors.managers;

import gameEngine.Behavior;
import gameEngine.GameObject;
import java.time.Duration;
import java.time.Instant;

/**
 * A behavior for the gameManager that holds the score.
 */
public class ScoreHolder extends Behavior {

    private double score = 2.0;
    private double totalGrabbedStore = 0.0;

    private Instant lastUpdate = Instant.now();

    public ScoreHolder(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void setup() {
        
    }

    @Override
    public void update() {
        double deltaPoints = (double) Duration.between(lastUpdate, Instant.now()).toNanos() * 1e-10;
        score = Math.max(score - deltaPoints, 0);
        lastUpdate = Instant.now();
    }

    public double getScore() {
        return score;
    }

    public double getTotalScore() {
        return totalGrabbedStore;
    }

    /**
     * Adds points to the score.
     * @param score the amount of points to add.
     */
    public void addScore(double score) {
        this.score = Math.min(10, this.score + score);
        this.totalGrabbedStore += score;
    }

    public void removeScore(double score) {
        this.score -= score;
    }
}

package behaviors.managers;

import behaviors.managers.GameStateManager.GameState;
import gameEngine.Behavior;
import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.Scene;
import gameEngine.Vector2;

import java.time.Duration;
import java.time.Instant;

public class CameraManager extends Behavior {
    public Instant levelStartedTime;
    private Instant levelFinishedTime;
    GameCanvas camera;
    GameObject player;
    Scene scene;
    GameStateManager gameStateManager;


    public CameraManager(GameObject gameObject, GameObject player, GameCanvas camera, Scene scene) {
        super(gameObject);
        this.camera = camera;
        this.player = player;
        this.scene = scene;
    }
    
    @Override
    public void setup() {
        levelStartedTime = Instant.now();
        gameStateManager = (GameStateManager) gameObject.getBehavior(GameStateManager.class);
    }

    @Override
    public void update() {
        long millisSinceStart = Duration.between(levelStartedTime, Instant.now()).toMillis();
        if (millisSinceStart < 3000) {
            player.renderer.renderInCenter = false;
            double fixedTime = ((double) millisSinceStart - 2000.0) / 1000.0;
            if (millisSinceStart > 2000) {
                camera.zoom =  0.9 * (Math.pow(fixedTime, 4) - 3.75 * Math.pow(fixedTime, 3) + 3.625 * Math.pow(fixedTime, 2) + 0.125) + 0.1;
                camera.cameraPosition = new Vector2<Double>(25.0, 25.0).addVector(player.getPosition().addVector(new Vector2<Double>(-25.0, -25.0)).newScaledVector(fixedTime));
            } else {
                camera.zoom = 0.2125;
                camera.cameraPosition = new Vector2<Double>(25.0, 25.0);
            }
        } else {
            if (gameStateManager.gameState == GameState.finished) {
                if (levelFinishedTime == null) {
                    levelFinishedTime = Instant.now();
                    player.renderer.renderInCenter = false;
                }
                long millisSinceEnd = Duration.between(levelFinishedTime, Instant.now()).toMillis();
                double normalisedTime = 1.0 - ((double) millisSinceEnd) / 1000.0;
                if (millisSinceEnd < 1000) {
                    camera.zoom =  0.9 * (Math.pow(normalisedTime, 4) - 3.75 * Math.pow(normalisedTime, 3) + 3.625 * Math.pow(normalisedTime, 2) + 0.125) + 0.1;
                    camera.cameraPosition = new Vector2<Double>(25.0, 25.0).addVector(player.getPosition().addVector(new Vector2<Double>(-25.0, -25.0)).newScaledVector(normalisedTime));
                } else {
                    camera.zoom = 0.2125;
                    camera.cameraPosition = new Vector2<Double>(25.0, 25.0);
                }
            } else {
                camera.zoom = 1;
                camera.cameraPosition = player.getPosition();
                player.renderer.renderInCenter = true;
            }            
        }
    }
}

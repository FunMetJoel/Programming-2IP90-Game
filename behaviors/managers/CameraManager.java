package behaviors.managers;

import behaviors.managers.GameStateManager.GameState;
import gameEngine.Behavior;
import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.Vector2;
import java.time.Duration;
import java.time.Instant;

/**
 * Behavior that manages the camera position and zoom.
 */
public class CameraManager extends Behavior {
    public Instant levelStartedTime;
    private Instant levelFinishedTime;
    GameCanvas camera;
    GameObject player;
    GameStateManager gameStateManager;

    /**
     * Creates a camera manager.
     * @param gameObject the object to add the behavior to
     * @param player the player to track
     * @param camera the camera to move
     */
    public CameraManager(GameObject gameObject, GameObject player, GameCanvas camera) {
        super(gameObject);
        this.camera = camera;
        this.player = player;
    }
    
    @Override
    public void setup() {
        levelStartedTime = Instant.now();
        gameStateManager = (GameStateManager) gameObject.getBehavior(GameStateManager.class);
    }

    @Override
    public void update() {
        long millisSinceStart = Duration.between(levelStartedTime, Instant.now()).toNanos();
        if (millisSinceStart < 3e9) {
            player.renderer.renderInCenter = false;
            double fixedTime = ((double) millisSinceStart - 2e9) / 1e9;
            if (millisSinceStart > 2e9) { //TODO: Can I / should I simplify this function?
                camera.zoom =  0.9 * (Math.pow(fixedTime, 4) - 3.75 * Math.pow(fixedTime, 3) + 3.625 * Math.pow(fixedTime, 2) + 0.125) + 0.1;
                camera.cameraPosition = new Vector2<Double>(25.0, 25.0)
                    .addVector(
                        player.getPosition()
                            .addVector(new Vector2<Double>(-25.0, -25.0))
                            .newScaledVector(fixedTime)
                    );
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
                long nanosSinceEnd = Duration.between(levelFinishedTime, Instant.now()).toNanos();
                double normalizedTime = 1.0 - ((double) nanosSinceEnd) / 1e9;
                if (nanosSinceEnd < 1e9) {
                    camera.zoom =  0.9 * (Math.pow(normalizedTime, 4) - 3.75 * Math.pow(normalizedTime, 3) + 3.625 * Math.pow(normalizedTime, 2) + 0.125) + 0.1;
                    camera.cameraPosition = new Vector2<Double>(25.0, 25.0)
                        .addVector(
                            player.getPosition()
                                .addVector(new Vector2<Double>(-25.0, -25.0))
                                .newScaledVector(normalizedTime)
                        );
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

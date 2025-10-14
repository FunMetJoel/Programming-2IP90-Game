package gameEngine;

import java.util.ArrayList;

/**
 * A scene with gameObjects.
 */
public class Scene implements Runnable {
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    GameCanvas camera;

    public void run() {
        setup();
        while (true) {
            update();
            camera.repaint();
        }
    }

    public void setup() {
        for (GameObject gameObject: gameObjects) {
            gameObject.setupAll();
        }
    }

    /**
     * Run the update function of each element.
     */
    public void update() {
        for (GameObject gameObject: gameObjects) {
            gameObject.updateAll();
        }
    }

    public void setCamera(GameCanvas camera) {
        this.camera = camera;
    }

    /**
     * Adds an object to the canvas.
     * @param object the object to add
     */
    public void addObject(GameObject object) {
        gameObjects.add(object);
    }

    /**
     * Removes an object from the canvas.
     * @param object the object to remove
     */
    public void removeObject(GameObject object) {
        gameObjects.remove(object);
    }
}

package gameEngine;

import java.util.ArrayList;

/**
 * A scene with gameObjects.
 */
public class Scene implements Runnable {
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    ArrayList<GameObject> uiObjects = new ArrayList<GameObject>();
    GameCanvas camera;

    ArrayList<GameObject> bufferedNewObjects = new ArrayList<GameObject>();

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
        for (GameObject uiGameObject: uiObjects) {
            uiGameObject.setupAll();
        }
    }

    /**
     * Run the update function of each element.
     */
    public void update() {
        handleBufferedNewObjects();
        for (GameObject gameObject: gameObjects) {
            gameObject.updateAll();
        }
        for (GameObject gameObject : uiObjects) {
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

    public void instantiate(GameObject object) {
        bufferedNewObjects.add(object);
    }

    private void handleBufferedNewObjects() {
        ArrayList<GameObject> cloneBufferedObjects = new ArrayList<GameObject>(bufferedNewObjects);
        // System.out.print(bufferedNewObjects);
        for (GameObject gameObject : cloneBufferedObjects) {
            gameObjects.add(gameObject);
        }
        for (GameObject gameObject: cloneBufferedObjects) {
            gameObject.setupAll();
        }
        bufferedNewObjects = new ArrayList<GameObject>();
    }

    /**
     * Adds an object to the ui canvas.
     * @param object the object to add
     */
    public void addUIObject(GameObject object) {
        uiObjects.add(object);
    }

    /**
     * Removes an object from the canvas.
     * @param object the object to remove
     */
    public void removeObject(GameObject object) {
        gameObjects.remove(object);
    }
}

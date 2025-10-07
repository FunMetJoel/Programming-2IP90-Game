package gameEngine;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Object that contains and renders all gameObjects on the screen.
 */
public class GameCanvas extends JPanel implements Runnable {
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    public Vector2<Double> cameraPosition = new Vector2<Double>(0.0, 0.0);
    public double zoom = 1; // 1 pixel is 1 pixel

    /**
     * Creates a default game canvas.
     */
    public GameCanvas() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
    }

    /**
     * Thread to run the game.
     */
    public void run() {
        while (true) {
            update();
            repaint();
        }

    }

    /**
     * Paints all gameObjects.
     * @param graphics the graphics context
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        Vector2<Double> scale = new Vector2<Double>(
            zoom * this.getWidth() / 10.0, 
            zoom * this.getWidth() / 10.0
        );

        Vector2<Integer> centerScreenCords = new Vector2<Integer>(
            (int) Math.round((this.getWidth() / 2) - scale.x * cameraPosition.x),
            (int) Math.round((this.getHeight() / 2) - scale.y * cameraPosition.y)
        );
        
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(graphics2D, centerScreenCords, scale);
        }

        graphics2D.dispose();
    }

    /**
     * Run the update function of each element.
     */
    public void update() {
        for (GameObject gameObject: gameObjects) {
            gameObject.updateAll();
        }
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

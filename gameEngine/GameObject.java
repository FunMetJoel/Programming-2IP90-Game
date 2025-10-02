package gameEngine;

import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    protected ArrayList<GameObject> gameObjects;
    protected Vector2<Double> position = new Vector2<Double>(0.0,0.0);
    protected Vector2<Double> scale = new Vector2<Double>(1.0, 1.0);

    public GameObject() {
        gameObjects = new ArrayList<GameObject>();
    }

    public GameObject(Vector2<Double> position) {
        gameObjects = new ArrayList<GameObject>();
        this.position = position;
    }

    public GameObject(Vector2<Double> position, Vector2<Double> scale) {
        gameObjects = new ArrayList<GameObject>();
        this.position = position;
        this.scale = scale;
    }

    public void paint(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        
    }

    public void draw(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {

        Vector2<Double> newScale = new Vector2<Double>(
            scale.x * this.scale.x,
            scale.y * this.scale.y
        );

        Vector2<Integer> newCenterScreenCords = new Vector2<Integer>(
            (int) Math.round(centerScreenCords.x + (this.position.x * scale.x)),
            (int) Math.round(centerScreenCords.y + (this.position.y * scale.y))
        );

        this.paint(graphics, newCenterScreenCords, newScale);

        for (GameObject gameObject : gameObjects) {
            gameObject.draw(graphics, newCenterScreenCords, newScale);
        }
    }

    public void update() {

    }
}

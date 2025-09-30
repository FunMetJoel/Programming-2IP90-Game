package gameEngine;

import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    ArrayList<GameObject> gameObjects;
    Point position = new Point(0,0);
    Point scale = new Point(1, 1);

    public GameObject() {
        gameObjects = new ArrayList<GameObject>();
    }

    public GameObject(Point position) {
        gameObjects = new ArrayList<GameObject>();
        this.position = position;
    }

    public GameObject(Point position, Point scale) {
        gameObjects = new ArrayList<GameObject>();
        this.position = position;
        this.scale = scale;
    }

    public void paint(Graphics graphics) {

    }
}

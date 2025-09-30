package gameEngine;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameCanvas extends JFrame implements MouseListener, MouseMotionListener{
    Canvas c;
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public GameCanvas(String windowName) {
        super(windowName);

        Canvas canvas = new Canvas() {
            public void paint(Graphics graphics) {
                for (GameObject gameObject : gameObjects) {
                    gameObject.paint(graphics);
                }
            }
        };
        canvas.setBackground(Color.black);
        
        add(canvas);
        setSize(500, 500);
        setVisible(true);
    }
    public void run() {
        // TODO: start loops and stuff
    }

    public void addObject(GameObject object) {
        gameObjects.add(object);
        System.out.println("Printing Objects");
        for (GameObject gameObject : gameObjects) {
            System.out.println(object);
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }
}

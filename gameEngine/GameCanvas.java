package gameEngine;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class GameCanvas extends JPanel implements MouseListener, MouseMotionListener, Runnable {
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    Vector2<Double> cameraPosition = new Vector2<Double>(0.0, 0.0);
    double zoom = 1; // 1 pixel is 1 pixel

    public GameCanvas() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);

        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true) {
            update();
            repaint();
        }

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;

        Vector2<Double> scale = new Vector2<Double>(
            zoom * this.getWidth() / 10.0, 
            zoom * this.getWidth() / 10.0
        );

        Vector2<Integer> centerScreenCords = new Vector2<Integer>(
            (int) Math.round((this.getWidth() / 2) + scale.x * cameraPosition.x),
            (int) Math.round((this.getHeight() / 2) + scale.y * cameraPosition.y)
        );
        
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(graphics2D, centerScreenCords, scale);
        }

        graphics2D.dispose();
    }

    public void update() {
        // for (GameObject gameObject: gameObjects) {
        //     gameObject.update();
        // }
        // System.out.println(this.zoom);

        // this.zoom = zoom * 1;

        // cameraPosition.x -= 0.00001;
        // System.out.println(cameraPosition);
    }

    public void addObject(GameObject object) {
        gameObjects.add(object);
        System.out.println("Printing Objects");
        for (GameObject gameObject : gameObjects) {
            System.out.println(object);
        }
    }

    public void mouseClicked(MouseEvent e) {
        cameraPosition.x += 1;
        System.out.println(cameraPosition.x);
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

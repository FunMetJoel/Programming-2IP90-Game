import java.awt.Color;
import java.util.Scanner;

import javax.swing.JFrame;

import gameEngine.*;
import gameEngine.dummyObjects.Circle;
import gameEngine.dummyObjects.Square;
import gameObjects.GameScene;
import level.Level;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("HOW DO WE CALL OUR GAME");

        GameCanvas gameCanvas = new GameCanvas();
        window.add(InputManager.get());
        window.add(gameCanvas);

        Scene scene = new GameScene(666, gameCanvas);
        gameCanvas.setScene(scene);
        
        Thread thread = new Thread(scene);
        thread.start();

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

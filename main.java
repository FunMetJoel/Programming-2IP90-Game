import java.awt.Color;
import java.util.Scanner;

import javax.swing.JFrame;

import gameEngine.*;
import gameEngine.dummyObjects.Circle;
import gameEngine.dummyObjects.Square;
import level.Level;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("HOW DO WE CALL OUR GAME");

        GameCanvas gameCanvas = new GameCanvas();
        window.add(InputManager.get());

        window.add(gameCanvas);

        Level level = new Level();

        gameCanvas.addObject(
            level
        );

        Player player = new Player(
            new Vector2<Double>(0.0, 0.0), 
            new Vector2<Double>(0.8, 0.8)
        );
        player.level = level;

        gameCanvas.addObject(player);

        player.camera = gameCanvas;

        GameManager gameManager = new GameManager(player, level, gameCanvas);
        gameCanvas.addObject(gameManager);

        Enemy enemy = new Enemy(new Vector2<Double>(10.0, 10.0), gameManager);
        gameCanvas.addObject(enemy);
        
        Thread thread = new Thread(gameCanvas);
        thread.start();

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

import java.awt.Color;
import java.awt.Point;

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

        gameCanvas.addObject(
            new Circle(new Vector2<Double>(0.0, 0.0), new Vector2<Double>(1.0, 1.0), Color.red, null)
        );
        gameCanvas.addObject(
            new Circle(new Vector2<Double>(1.0, 0.0), new Vector2<Double>(0.8, 0.8), Color.red, null)
        );

        Player player = new Player(
            new Vector2<Double>(0.0, 0.0), 
            new Vector2<Double>(0.8, 0.8)
        );
        player.level = level;

        gameCanvas.addObject(player);

        player.camera = gameCanvas;

        
        Thread thread = new Thread(gameCanvas);
        thread.start();

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

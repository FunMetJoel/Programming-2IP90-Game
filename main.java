import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

import gameEngine.*;
import gameEngine.dummyObjects.Circle;
import gameEngine.dummyObjects.Square;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("HOW DO WE CALL OUR GAME");

        GameCanvas gameCanvas = new GameCanvas();
        window.add(InputManager.get());

        window.add(gameCanvas);

        for (int i = -30; i <= 30; i++) {
            for (int j = -30; j <= 30; j++) {
                Square square = new Square(
                    new Vector2<Double>((double) i, (double) j), 
                    new Vector2<Double>(1.0, 1.0),
                    new Color(1 * (i + 100), 1 * (j + 100), 0),
                    Color.white
                );
                gameCanvas.addObject(square);
            }
        }

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

        gameCanvas.addObject(player);
        player.camera = gameCanvas;

        
        Thread thread = new Thread(gameCanvas);
        thread.start();

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

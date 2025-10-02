import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

import gameEngine.*;
import gameEngine.dummyObjects.Square;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("HOW DO WE CALL OUR GAME");

        GameCanvas gameCanvas = new GameCanvas();
        window.add(gameCanvas);

        

        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                Square square = new Square(
                    new Vector2<Double>((double) i, (double) j), 
                    new Vector2<Double>(0.9, 0.9),
                    new Color(36 * (i + 4), 36 * (j + 4), 0),
                    Color.white
                );
                gameCanvas.addObject(square);
            }
        }
        
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

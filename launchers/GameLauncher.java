package launchers;

import gameEngine.GameCanvas;
import gameEngine.InputManager;
import gameEngine.Scene;
import gameObjects.GameScene;
import javax.swing.JFrame;

public class GameLauncher {

    public void launchGame() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("HOW DO WE CALL OUR GAME");

        GameCanvas gameCanvas = new GameCanvas();
        window.add(InputManager.get());
        window.add(gameCanvas);

        Scene scene = new GameScene(19, gameCanvas);
        gameCanvas.setScene(scene);
        
        Thread thread = new Thread(scene);
        thread.start();

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}

import gameEngine.GameCanvas;
import gameEngine.InputManager;
import gameEngine.Scene;
import gameObjects.GameScene;
import launchers.GameLauncher;
import launchers.StartMenuLauncher;
import menu.StartMenu;

import javax.swing.JFrame;


public class main {
    public static void main(String[] args) {



        // JFrame window = new JFrame();
        // window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window.setTitle("HOW DO WE CALL OUR GAME");

        // GameCanvas gameCanvas = new GameCanvas();
        // window.add(InputManager.get());
        // window.add(gameCanvas);

        // Scene scene = new GameScene(19, gameCanvas);
        // gameCanvas.setScene(scene);
        
        // Thread thread = new Thread(scene);
        // thread.start();

        // window.pack();

        // window.setLocationRelativeTo(null);
        // window.setVisible(true);

        StartMenuLauncher startMenu = new StartMenuLauncher();
        startMenu.createMenu();
        startMenu.launchStartMenu();

        StartMenu menu = startMenu.getMenu();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!menu.getStartStatus()) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                startMenu.closeStartMenu();
                new GameLauncher().launchGame();
            }
        }).start();
    }
}

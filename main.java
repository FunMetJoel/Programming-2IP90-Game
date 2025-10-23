import launchers.GameLauncher;
import launchers.StartMenuLauncher;
import menu.StartMenu;


/**
 * Runs all the code.
 */
public class Main {
    public static void main(String[] args) {

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

                new GameLauncher().launchGame(menu.seed);
            }
        }).start();
    }
}

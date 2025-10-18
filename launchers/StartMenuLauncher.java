package launchers;

import javax.swing.JFrame;

import menu.StartMenu;

public class StartMenuLauncher {
    JFrame window = new JFrame();
    StartMenu menu = new StartMenu();

    public StartMenu getMenu() {
        return menu;
    }
    
    public void createMenu() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Main menu testing");

        window.add(menu);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    public void launchStartMenu() {
        window.setVisible(true);
    }

    public void closeStartMenu() {
        window.setVisible(false);
    }
}

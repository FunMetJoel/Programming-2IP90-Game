package gameEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import javax.swing.JPanel;

/**
 * Detects keyboard input.
 */
public class InputManager extends JPanel implements KeyListener {
    private static InputManager instance = new InputManager();
    private HashSet<Integer> pressedKeyCodes = new HashSet<Integer>();

    /**
     * Gets the input manager.
     * @return the instance of the input manager.
     */
    public static InputManager get() {
        return instance;
    }

    /**
     * Creates a new Input manager.
     */
    private InputManager() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /**
     * Checks if a certain key is pressed.
     * @param keyCode the keycode to check is pressed
     * @return if the key is pressed
     */
    public static boolean isPressed(int keyCode) {
        return instance.pressedKeyCodes.contains(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeyCodes.add(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeyCodes.remove(keyCode);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}

package gameEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JPanel;

public class InputManager extends JPanel implements KeyListener {
    private static InputManager instance = new InputManager();
    private HashSet<Integer> pressedKeyCodes = new HashSet<Integer>();

    public static InputManager get() {
        return instance;
    }

    public InputManager() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public static boolean isPressed(int keyCode) {
        return instance.pressedKeyCodes.contains(keyCode);
    }

    // Implement the keyPressed method
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyCode) + ": " + keyCode);
        pressedKeyCodes.add(keyCode);
    }

    // Implement the keyReleased method
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Key Released: " + KeyEvent.getKeyText(keyCode));
        pressedKeyCodes.remove(keyCode);
    }

    // Implement the keyTyped method
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}

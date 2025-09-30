import java.awt.Color;
import java.awt.Point;

import gameEngine.*;

public class main {
    public static void main(String[] args) {
        // TODO: Run the game
        GameCanvas game = new GameCanvas("CoolGame");
        GameObject text = new GameText("Hoi", Color.PINK, new Point(0, 20));

        game.addObject(text);

        game.run();
    }
}

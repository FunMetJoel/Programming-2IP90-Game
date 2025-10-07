package level;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import gameEngine.Grid;
import gameEngine.Vector2;

public class Level extends Grid {
    public Level() {
        this(new Random().nextInt());
    }

    public Level(int seed) {
        // TODO: create level based on seed

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Vector2<Integer> newGridPos = getGridPos(i, j);
                Vector2<Double> newPos = new Vector2<Double>(
                    (double) newGridPos.x, 
                    (double) newGridPos.y
                );

                // TODO: Make this a better generator function
                if ((seed * new Random().nextInt()) % 5 != 0) {
                    setTile(
                        new Grass(newPos, scale), 
                        i, 
                        j
                    );
                } else {
                    setTile(
                        new Obstacle(newPos, scale), 
                        i, 
                        j
                    );
                }

                
            }
        }
    }
}

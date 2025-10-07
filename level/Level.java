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

    @Override
    public void paint(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        graphics.setColor(Color.DARK_GRAY);
        
        graphics.fillRect(
            centerScreenCords.x - (int) Math.round(scale.x * 0.5 * this.gridSize), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5 * this.gridSize), 
            (int) Math.round(scale.x * this.gridSize), 
            (int) Math.round(scale.y * this.gridSize)
        );
    }


}

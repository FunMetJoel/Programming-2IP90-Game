package level;

import gameEngine.Grid;
import gameEngine.Vector2;
import java.util.Random;

public class Level extends Grid {
    public Level() {
        this(new Random().nextInt());
    }

    public Level(int seed) {
        double frequency = 0.1;
        PerlinGrid perlin = new PerlinGrid(seed);
        double[][] noise = perlin.createGrid(gridSize, gridSize, frequency);
        double noiseAverage = perlin.findAverage(noise);
        String[][] visualOriginalPerlin = perlin.visualyRepresentedGrid(noiseAverage, noise);
        // double[][] newNoise = noise;

        // TODO: create level based on seed
        for (int i = 1; i < 3; i++) {
            PerlinGrid newPerlin = new PerlinGrid((seed * (i + 1)) % Integer.MAX_VALUE);
            double[][] newNoise = newPerlin.createGrid(gridSize, gridSize, frequency);
            double newNoiseAverage = newPerlin.findAverage(noise);
            String[][] visual = newPerlin.visualyRepresentedGrid(newNoiseAverage, newNoise);

            visualOriginalPerlin = newPerlin.combineGrid(visualOriginalPerlin, visual);
        }


        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Vector2<Integer> newGridPos = getGridPos(i, j);
                Vector2<Double> newPos = new Vector2<Double>(
                    (double) newGridPos.x, 
                    (double) newGridPos.y
                );

                // TODO: Make this a better generator function
                if (visualOriginalPerlin[i][j] != "*") {
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

                // setTile(new PerlinTestTile(newPos, scale, newNoise[i][j]), i, j);
            }
        }


        setTile(new Grass(new Vector2<Double>(25.0, 25.0), scale), 25, 25);
    }
}

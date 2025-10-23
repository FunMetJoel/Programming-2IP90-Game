package behaviors.managers;

import gameEngine.Behavior;
import gameEngine.Scene;
import gameEngine.Vector2;
import gameObjects.Enemy;
import gameObjects.GameManager;
import java.util.ArrayList;
import level.Level;

/**
 * Behavior of game manager to spawn enemy's on the map.
 */
public class EnemySpawner extends Behavior {
    Scene scene;
    Level level;


    /**
     * Creates a new enemy spawning behavior.
     * @param gameObject the game object to add the behavior to
     * @param scene the scene to spawn the enemies in
     * @param level the level to know where to spawn the enemies
     */
    public EnemySpawner(GameManager gameObject, Scene scene, Level level) {
        super(gameObject);
        this.scene = scene;
        this.level = level;
    }

    @Override
    public void setup() {
        int[][] distances = calculateDistanceMap();
        int highest = getHighestNonInf(distances);
        ArrayList<Vector2<Integer>> points = getPointsWithDistance(distances, (2 * highest) / 3);
        scene.instantiate(
            new Enemy(getCornerPoint(1, 1, points).toDouble(), (GameManager) gameObject)
        );
        scene.instantiate(
            new Enemy(getCornerPoint(-1, 1, points).toDouble(), (GameManager) gameObject)
        );
        scene.instantiate(
            new Enemy(getCornerPoint(-1, -1, points).toDouble(), (GameManager) gameObject)
        );
        scene.instantiate(
            new Enemy(getCornerPoint(1, -1, points).toDouble(), (GameManager) gameObject)
        );
    }

    @Override
    public void update() {

    }

    /**
     * Calculates the distances of each tile to the center of the map.
     * @return a list with the walking distance to the center of the map
     */
    private int[][] calculateDistanceMap() {
        int[][] distanceMap = new int[level.gridSize][level.gridSize];
        boolean[][] checkedMap = new boolean[level.gridSize][level.gridSize];

        for (int i = 0; i < distanceMap.length; i++) {
            for (int j = 0; j < distanceMap.length; j++) {
                distanceMap[i][j] = Integer.MAX_VALUE;
                checkedMap[i][j] = false;
            }
        }
        distanceMap[25][25] = 0;

        while (true) {
           
            Vector2<Integer> lowestPos = getLowestUncheckedPosition(distanceMap, checkedMap);
            if (lowestPos == null) {
                break;
            }

            checkedMap[lowestPos.x][lowestPos.y] = true;

            int x = lowestPos.x;
            int y = lowestPos.y;

            if (distanceMap[x][y] == Integer.MAX_VALUE) {
                continue;
            }

            if (level.canEnter(x + 1, y) && (distanceMap[x + 1][y] > distanceMap[x][y] + 1)) {
                distanceMap[x + 1][y] = distanceMap[x][y] + 1;
            }
            if (level.canEnter(x - 1, y) && (distanceMap[x - 1][y] > distanceMap[x][y] + 1)) {
                distanceMap[x - 1][y] = distanceMap[x][y] + 1;
            }
            if (level.canEnter(x, y + 1) && (distanceMap[x][y + 1] > distanceMap[x][y] + 1)) {
                distanceMap[x][y + 1] = distanceMap[x][y] + 1;
            }
            if (level.canEnter(x, y - 1) && (distanceMap[x][y - 1] > distanceMap[x][y] + 1)) {
                distanceMap[x][y - 1] = distanceMap[x][y] + 1;
            }

        }

        return distanceMap;
    }

    private Vector2<Integer> getLowestUncheckedPosition(int[][] valueMap, boolean[][] checkedMap) {
        int lowest = Integer.MAX_VALUE;
        Vector2<Integer> lowestPos = null;

        for (int i = 0; i < level.gridSize; i++) {
            for (int j = 0; j < level.gridSize; j++) {
                if (valueMap[i][j] > lowest) {
                    continue;
                }
                if (checkedMap[i][j]) {
                    continue;
                }
                lowest = valueMap[i][j];
                lowestPos = new Vector2<Integer>(i, j);
            }
        }

        return lowestPos;
    }

    private int getHighestNonInf(int[][] valueMap) {
        int highest = 0;
        for (int i = 0; i < valueMap.length; i++) {
            for (int j = 0; j < valueMap.length; j++) {
                if (valueMap[i][j] < highest) {
                    continue;
                }
                if (valueMap[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                highest = valueMap[i][j];
            }
        }
        return highest;
    }

    private ArrayList<Vector2<Integer>> getPointsWithDistance(int[][] valueMap, int distance) {
        ArrayList<Vector2<Integer>> points = new ArrayList<Vector2<Integer>>();

        for (int i = 0; i < valueMap.length; i++) {
            for (int j = 0; j < valueMap.length; j++) {
                if (valueMap[i][j] != distance) {
                    continue;
                }
                points.add(new Vector2<Integer>(i, j));
            }
        }

        return points;
    }

    private Vector2<Integer> getCornerPoint(int x, int y, ArrayList<Vector2<Integer>> points) {
        Vector2<Integer> point = points.get(0);

        for (Vector2<Integer> vector2 : points) {
            if ((point.x * x) + (point.y * y) < (vector2.x * x) + (vector2.y * y)) {
                continue;
            }
            point = vector2;
        }

        return point;
    }
}

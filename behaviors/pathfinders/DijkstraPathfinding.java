package behaviors.pathfinders;

import behaviors.GridMovement;
import gameEngine.GameObject;
import gameEngine.Vector2;

/**
 * A type of path finding using dijkstra's algorithm.
 */
public class DijkstraPathfinding extends Pathfinding {

    Vector2<Integer> nextMove = new Vector2<Integer>(0, 0);

    public DijkstraPathfinding(GameObject gameObject, GridMovement target) {
        super(gameObject, target);
    }

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void move() {
        try {
            int[][] costs = calculateCost();
            Vector2<Integer> movement = getMovement(costs);
            gridMovement.move(movement.x, movement.y);
        } catch (RuntimeException e) {
            System.err.println(e);
        }
        // calculateCost2();
        // gridMovement.move(nextMove.x, nextMove.y);
    }

    public Vector2<Integer> getMovement(int[][] costs) {
        Vector2<Integer> nextPosition = target.getPosition().round();
        Vector2<Integer> currentPosition = target.getPosition().round();
        final Vector2<Integer> arrayPos = gameObject.getPosition().round();
        
        while (
            (!(arrayPos.equals(nextPosition))) 
                && (costs[nextPosition.x][nextPosition.y] != 0)
        ) {
            currentPosition.x = nextPosition.x;
            currentPosition.y = nextPosition.y;
            int x = currentPosition.x;
            int y = currentPosition.y;
            int lowestValue = costs[x][y];

            if ((x + 1 < costs.length) && (costs[x + 1][y] <= lowestValue)) {
                lowestValue = costs[x + 1][y];
                nextPosition.x = x + 1;
            } else if ((x - 1 > 0) && (costs[x - 1][y] <= lowestValue)) {
                lowestValue = costs[x - 1][y];
                nextPosition.x = x - 1;
            } else if ((y + 1 < costs.length) && (costs[x][y + 1] <= lowestValue)) {
                lowestValue = costs[x][y + 1];
                nextPosition.y = y + 1; 
            } else if ((y - 1 > 0) && (costs[x][y - 1] <= lowestValue)) {
                lowestValue = costs[x][y - 1];
                nextPosition.y = y - 1; 
            }
        }
        return currentPosition.subtractVector(arrayPos.toDouble()).round();
    }

    public int[][] calculateCost() throws RuntimeException {
        int gridSize = level.gridSize;
        int[][] costs = new int[gridSize][gridSize];
        boolean[][] checked = new boolean[gridSize][gridSize];

        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs.length; j++) {
                costs[i][j] = Integer.MAX_VALUE;
                checked[i][j] = false;
            }
        }

        GridMovement gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);
        Vector2<Integer> arrayPos = gridMovement.getPosition();
        Vector2<Integer> targetArrayPos = target.getPosition();

        costs[arrayPos.x][arrayPos.y] = 0;

        while (costs[targetArrayPos.x][targetArrayPos.y] == Integer.MAX_VALUE) {
            Vector2<Integer> lowestValuePos = getLowestValuePosition(costs, checked);
            int x = lowestValuePos.x;
            int y = lowestValuePos.y;
            
            if (level.canEnter(x + 1, y) && !checked[x + 1][y]) {
                costs[x + 1][y] = Math.min(costs[x + 1][y], costs[x][y] + 1);
            }
            if (level.canEnter(x - 1, y) && !checked[x - 1][y]) {
                costs[x - 1][y] = Math.min(costs[x - 1][y], costs[x][y] + 1);
            }
            if (level.canEnter(x, y + 1) && !checked[x][y + 1]) {
                costs[x][y + 1] = Math.min(costs[x][y + 1], costs[x][y] + 1);
            }
            if (level.canEnter(x, y - 1) && !checked[x][y - 1]) {
                costs[x][y - 1] = Math.min(costs[x][y - 1], costs[x][y] + 1);
            }

            checked[x][y] = true;
        }

        return costs;
    }

    public Vector2<Integer> getLowestValuePosition(int[][] costs, boolean[][] checked) {
        int lowestValue = Integer.MAX_VALUE;
        Vector2<Integer> lowestValuePosition = null;

        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs.length; j++) {
                if (!checked[i][j] && (costs[i][j] < lowestValue)) {
                    lowestValue = costs[i][j];
                    lowestValuePosition = new Vector2<Integer>(i, j);
                }
            }
        }

        if (lowestValuePosition == null) {
            nextMove.x = 0;
            nextMove.y = 0;
            throw new RuntimeException("PathFinding not working");
        }

        return lowestValuePosition;
    }

    // /**
    //  * Calculates the cost and finds the next move to take.
    //  * TODO: Make this be multiple functions
    //  */
    // public void calculateCost2() {
    //     // TODO: Make this be part of the tile
    //     int movementCost = 1;

    //     // Set costs to really big
    //     for (int i = 0; i < costs.length; i++) {
    //         for (int j = 0; j < costs.length; j++) {
    //             costs[i][j] = Integer.MAX_VALUE;
    //             checked[i][j] = false;
    //         }
    //     }

    //     GridMovement gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);
    //     int gridX = gridMovement.getPosition().x;
    //     int gridY = gridMovement.getPosition().y;


    //     Vector2<Integer> enemyArrayPos = new Vector2<Integer>(gridX, gridY);
    //     Vector2<Integer> targetArrayPos = target.getPosition();

    //     costs[enemyArrayPos.x][enemyArrayPos.y] = 0;

    //     while (costs[targetArrayPos.x][targetArrayPos.y] == Integer.MAX_VALUE) {
    //         int lowestValue = Integer.MAX_VALUE;
    //         Vector2<Integer> lowestValuePosition = null;

    //         for (int i = 0; i < costs.length; i++) {
    //             for (int j = 0; j < costs.length; j++) {
    //                 if (!checked[i][j] && (costs[i][j] < lowestValue)) {
    //                     lowestValue = costs[i][j];
    //                     lowestValuePosition = new Vector2<Integer>(i, j);
    //                 }
    //             }
    //         }

    //         if (lowestValuePosition == null) {
    //             // System.out.println("-------------------");
    //             // for (int i = 0; i < costs.length; i++) {
    //             //     for (int j = 0; j < costs.length; j++) {
    //             //         if (costs[i][j] == Integer.MAX_VALUE) {
    //             //             System.out.print("inf");
    //             //         } else {
    //             //             System.out.print(String.format("%1$3s", costs[i][j]) );
    //             //         }
    //             //         System.out.print(" ");
    //             //     }
    //             //     System.out.println("");
    //             // }
    //             // System.out.println("-------------------");

    //             nextMove.x = 0;
    //             nextMove.y = 0;
    //             return;
    //             // throw new RuntimeException("PathFinding not working");
    //         }

    //         int x = lowestValuePosition.x;
    //         int y = lowestValuePosition.y;
            
    //         if (level.canEnter(x + 1, y) && !checked[x + 1][y]) {
    //             costs[x + 1][y] = Math.min(costs[x + 1][y], costs[x][y] + movementCost);
    //         }
    //         if (level.canEnter(x - 1, y) && !checked[x - 1][y]) {
    //             costs[x - 1][y] = Math.min(costs[x - 1][y], costs[x][y] + movementCost);
    //         }
    //         if (level.canEnter(x, y + 1) && !checked[x][y + 1]) {
    //             costs[x][y + 1] = Math.min(costs[x][y + 1], costs[x][y] + movementCost);
    //         }
    //         if (level.canEnter(x, y - 1) && !checked[x][y - 1]) {
    //             costs[x][y - 1] = Math.min(costs[x][y - 1], costs[x][y] + movementCost);
    //         }

    //         checked[x][y] = true;
    //     }

    //     // for (int i = 0; i < costs.length; i++) {
    //     //     for (int j = 0; j < costs.length; j++) {
    //     //         if (costs[i][j] == Integer.MAX_VALUE) {
    //     //             System.out.print("inf");
    //     //         } else {
    //     //             System.out.print(String.format("%1$3s", costs[i][j]) );
    //     //         }
    //     //         System.out.print(" ");
    //     //     }
    //     //     System.out.println("");
    //     // }
    //     // System.out.println("----------------");
        
    //     Vector2<Integer> nextPosition = new Vector2<Integer>(targetArrayPos.x, targetArrayPos.y);
    //     Vector2<Integer> currentPosition = new Vector2<Integer>(targetArrayPos.x, targetArrayPos.y);
    //     while (
    //         (!(enemyArrayPos.equals(nextPosition))) 
    //             && (costs[nextPosition.x][nextPosition.y] != 0)
    //     ) {
    //         currentPosition.x = nextPosition.x;
    //         currentPosition.y = nextPosition.y;
    //         int x = currentPosition.x;
    //         int y = currentPosition.y;
    //         int lowestValue = costs[x][y];

    //         if ((x + 1 < checked.length) && (costs[x + 1][y] <= lowestValue)) {
    //             lowestValue = costs[x + 1][y];
    //             nextPosition.x = x + 1;
    //         } else if ((x - 1 > 0) && (costs[x - 1][y] <= lowestValue)) {
    //             lowestValue = costs[x - 1][y];
    //             nextPosition.x = x - 1;
    //         } else if ((y + 1 < checked.length) && (costs[x][y + 1] <= lowestValue)) {
    //             lowestValue = costs[x][y + 1];
    //             nextPosition.y = y + 1; 
    //         } else if ((y - 1 > 0) && (costs[x][y - 1] <= lowestValue)) {
    //             lowestValue = costs[x][y - 1];
    //             nextPosition.y = y - 1; 
    //         }
    //     }
    //     nextMove.x = currentPosition.x - enemyArrayPos.x;
    //     nextMove.y = currentPosition.y - enemyArrayPos.y;
    // }

    // public void 
}

package behaviors;

import gameEngine.Behavior;
import gameEngine.GameCanvas;
import gameEngine.GameObject;
import gameEngine.Vector2;
import level.Level;

public class PathFinding extends Behavior {

    int[][] costs;
    boolean[][] checked;
    public GridMovement target;
    public GridMovement gridMovement;
    public Level level;
    Vector2<Integer> nextMove = new Vector2<Integer>(0, 0);

    public PathFinding(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void setup() {
        gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);

        int gridSize = level.gridSize;
        costs = new int[gridSize][gridSize];
        checked = new boolean[gridSize][gridSize];
    }

    @Override
    public void update() {
        // TODO: Move this to a different behavior
        if (!gridMovement.canMove()) {
            return;
        }

        calculateCost();
        gridMovement.move(nextMove.x, nextMove.y);
    }

    public void calculateCost() {
        // TODO: Make this be part of the tile
        int movementCost = 1;

        // Set costs to really big
        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs.length; j++) {
                costs[i][j] = Integer.MAX_VALUE;
                checked[i][j] = false;
            }
        }

        GridMovement gridMovement = (GridMovement) gameObject.getBehavior(GridMovement.class);
        int gridX = gridMovement.getPosition().x;
        int gridY = gridMovement.getPosition().y;


        Vector2<Integer> enemyArrayPos = level.getArrayPos(gridX, gridY);
        Vector2<Integer> targetArrayPos = level.getArrayPos(target.getPosition().x, target.getPosition().y);

        costs[enemyArrayPos.x][enemyArrayPos.y] = 0;

        while (costs[targetArrayPos.x][targetArrayPos.y] == Integer.MAX_VALUE) {
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
                // for (int i = 0; i < costs.length; i++) {
                //     for (int j = 0; j < costs.length; j++) {
                //         if (costs[i][j] == Integer.MAX_VALUE) {
                //             System.out.print("inf");
                //         } else {
                //             System.out.print(String.format("%1$3s", costs[i][j]) );
                //         }
                //         System.out.print(" ");
                //     }
                //     System.out.println("");
                // }
                nextMove.x = 0;
                nextMove.y = 0;
                return;
                // throw new RuntimeException("PathFinding not working");
            }

            int x = lowestValuePosition.x;
            int y = lowestValuePosition.y;
            
            if ((x + 1 < checked.length) && !checked[x + 1][y] && level.canEnterArrayPos(x + 1, y)) {
                costs[x + 1][y] = Math.min(costs[x + 1][y], costs[x][y] + movementCost);
            }
            if ((x - 1 > 0) && !checked[x - 1][y] && level.canEnterArrayPos(x - 1, y)) {
                costs[x - 1][y] = Math.min(costs[x - 1][y], costs[x][y] + movementCost);
            }
            if ((y + 1 < checked.length) && !checked[x][y + 1] && level.canEnterArrayPos(x, y + 1)) {
                costs[x][y + 1] = Math.min(costs[x][y + 1], costs[x][y] + movementCost);
            }
            if ((y - 1 > 0) && !checked[x][y - 1] && level.canEnterArrayPos(x, y - 1)) {
                costs[x][y - 1] = Math.min(costs[x][y - 1], costs[x][y] + movementCost);
            }

            checked[x][y] = true;
        }

        // for (int i = 0; i < costs.length; i++) {
        //     for (int j = 0; j < costs.length; j++) {
        //         if (costs[i][j] == Integer.MAX_VALUE) {
        //             System.out.print("inf");
        //         } else {
        //             System.out.print(String.format("%1$3s", costs[i][j]) );
        //         }
        //         System.out.print(" ");
        //     }
        //     System.out.println("");
        // }
        // System.out.println("----------------");
        
        Vector2<Integer> nextPosition = new Vector2<Integer>(targetArrayPos.x, targetArrayPos.y);
        Vector2<Integer> currentPosition = new Vector2<Integer>(targetArrayPos.x, targetArrayPos.y);
        while ((!(enemyArrayPos.equals(nextPosition))) && (costs[nextPosition.x][nextPosition.y] != 0)) {
            currentPosition.x = nextPosition.x;
            currentPosition.y = nextPosition.y;
            int x = currentPosition.x;
            int y = currentPosition.y;
            int lowestValue = costs[x][y];

            if ((x + 1 < checked.length) && (costs[x + 1][y] <= lowestValue)) {
                lowestValue = costs[x + 1][y];
                nextPosition.x = x + 1;
            } else if ((x - 1 > 0) && (costs[x - 1][y] <= lowestValue)) {
                lowestValue = costs[x - 1][y];
                nextPosition.x = x - 1;
            } else if ((y + 1 < checked.length) && (costs[x][y + 1] <= lowestValue)) {
                lowestValue = costs[x][y + 1];
                nextPosition.y = y + 1; 
            } else if ((y - 1 > 0) && (costs[x][y - 1] <= lowestValue)) {
                lowestValue = costs[x][y - 1];
                nextPosition.y = y - 1; 
            }
        }
        nextMove.x = currentPosition.x - enemyArrayPos.x;
        nextMove.y = currentPosition.y - enemyArrayPos.y;
    }


}

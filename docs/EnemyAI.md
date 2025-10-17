# Topic of choice: Pathfinding / enemy ai

## First code
The first code was a simple 'move towards player' function. It didn't think about how to reach the player, just that it needed to go towards it.
```Java
public void handleMovement() {
    Level level = this.gameManager.currentLevel;
    Player player = this.gameManager.player;
    if ((player.gridY < this.gridY) && level.canEnter(gridX, gridY - 1)) {
        this.gridY -= 1;
    } else if ((player.gridY > this.gridY) && level.canEnter(gridX, gridY + 1)) {
        this.gridY += 1;
    } else if ((player.gridX < this.gridX) && level.canEnter(gridX - 1, gridY)) {
        this.gridX -= 1;
    } else if ((player.gridX > this.gridX) && level.canEnter(gridX + 1, gridY)) {
        this.gridX += 1;
    }

    this.position.x = (double) gridX;
    this.position.y = (double) gridY;
}
```

This caused the enemy to get stuck behind a obstacle when it had to walk back first to be able to reach the player


## Second code
Then we trought about a algorithm that would calculate the cost to go to each position, and when it reaches the player, backtracks to know the correct route. This code worked quite well. It is not efficient nor neat to look at, but it did do its job very well (after some bugfixing)
```Java
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
```

## The problem with enemies with the same algorithms
We noticed that when you have a lot of enemies using the same algoritm, they will stack and move as one:
![Multiple enemies on top of each other](/docs/img/image.png)
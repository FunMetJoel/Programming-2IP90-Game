import java.awt.Graphics;
import java.awt.Image;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gameEngine.GameObject;
import gameEngine.Vector2;
import level.Level;

public class Enemy extends GameObject {
    static Image image = new ImageIcon("assets/TempEnemy.png").getImage();
    GameManager gameManager;
    int gridX;
    int gridY;

    int costs[][];
    boolean checked[][];
    boolean route[][];

    Instant lastMovement = Instant.now();

    public Enemy(Vector2<Double> position, GameManager gameManager) {
        super(position);
        this.gridX = (int) Math.round(position.x);
        this.gridY = (int) Math.round(position.y);
        this.gameManager = gameManager;
        
        int gridSize = gameManager.currentLevel.gridSize;
        costs = new int[gridSize][gridSize];
        checked = new boolean[gridSize][gridSize];

        calculateCost();
    }

    public void calculateCost() {
        // TODO: Make this be part of the tile
        int movementCost = 1;

        Player player = this.gameManager.player;
        Level level = this.gameManager.currentLevel;

        // Set costs to realy big
        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs.length; j++) {
                costs[i][j] = Integer.MAX_VALUE;
            }
        }

        Vector2<Integer> EnemyArrayPos = gameManager.currentLevel.getArrayPos(gridX, gridY);
        Vector2<Integer> TargetArrayPos = gameManager.currentLevel.getArrayPos(player.gridX, player.gridY);

        costs[EnemyArrayPos.x][EnemyArrayPos.y] = 0;

        while (costs[TargetArrayPos.x][TargetArrayPos.y] == Integer.MAX_VALUE) {
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
                for (int i = 0; i < costs.length; i++) {
                    for (int j = 0; j < costs.length; j++) {
                        if (costs[i][j] == Integer.MAX_VALUE) {
                            System.out.print("inf");
                        } else {
                            System.out.print(String.format("%1$3s", costs[i][j]) );
                        }
                        System.out.print(" ");
                    }
                    System.out.println("");
                }

                throw new RuntimeException("PathFinding not working");
            }

            int x = lowestValuePosition.x;
            int y = lowestValuePosition.y;
            
            if ((x+1 < checked.length) && !checked[x+1][y] && level.canEnterArrayPos(x+1, y)) {
                costs[x+1][y] = Math.min(costs[x+1][y], costs[x][y] + movementCost);
            }
            if ((x-1 > 0) && !checked[x-1][y] && level.canEnterArrayPos(x-1, y)) {
                costs[x-1][y] = Math.min(costs[x-1][y], costs[x][y] + movementCost);
            }
            if ((y+1 < checked.length) && !checked[x][y+1] && level.canEnterArrayPos(x, y+1)) {
                costs[x][y+1] = Math.min(costs[x][y+1], costs[x][y] + movementCost);
            }
            if ((y-1 > 0) && !checked[x][y-1] && level.canEnterArrayPos(x, y-1)) {
                costs[x][y-1] = Math.min(costs[x][y-1], costs[x][y] + movementCost);
            }

            checked[x][y] = true;
        }

        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs.length; j++) {
                if (costs[i][j] == Integer.MAX_VALUE) {
                    System.out.print("inf");
                } else {
                    System.out.print(costs[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
        
    }

    @Override
    public void paint(Graphics[] graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {
        graphics[1].drawImage(
            image,
            centerScreenCords.x - (int) Math.round(scale.x * 0.5), 
            centerScreenCords.y - (int) Math.round(scale.y * 0.5), 
            (int) Math.round(scale.x), 
            (int) Math.round(scale.y),
            null
        );
    }

    @Override
    public void update() {
        double deltaTime = (double) Duration.between(lastMovement, Instant.now()).toMillis();

        // this.position.x = ((double) gridX - direction.x) + (deltaTime / 100) * direction.x;
        // this.position.y = ((double) gridY - direction.y) + (deltaTime / 100) * direction.y;

        if (deltaTime < 200) {
            return;
        }
        lastMovement = Instant.now();

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
}

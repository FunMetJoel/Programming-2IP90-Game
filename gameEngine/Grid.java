package gameEngine;

import java.awt.Graphics;

public class Grid extends GameObject {
    public final int gridSize = 51;
    GridItem[][] gridData = new GridItem[gridSize][gridSize];

    public Grid() {
        // // TODO: remove this, this is only for testing
        // for (int i = 0; i < gridSize; i++) {
        //     for (int j = 0; j < gridSize; j++) {
        //         Vector2<Integer> newGridPos = getGridPos(i, j);
        //         Vector2<Double> newPos = new Vector2<Double>(
        //             (double) newGridPos.x, 
        //             (double) newGridPos.y
        //         );

        //         System.out.println(newPos);

        //         gridData[i][j] = new GridItem(newPos, scale);
        //     }
        // }
    }

    public boolean canEnter(int gridX, int gridY) {
        Vector2<Integer> arrayPosition = getArrayPos(gridX, gridY);
        if (arrayPosition.x < 0 || arrayPosition.y < 0) {
            return false;
        }
        if (arrayPosition.x > gridSize || arrayPosition.y > gridSize) {
            return false;
        }
        return gridData[arrayPosition.x][arrayPosition.y].canEnter;
    }

    protected Vector2<Integer> getArrayPos(int gridX, int gridY) {
        return new Vector2<Integer>(
            gridX + gridSize / 2, 
            gridY + gridSize / 2
        );
    }

    protected Vector2<Integer> getGridPos(int arrayX, int arrayY) {
        return new Vector2<Integer>(
            arrayX - gridSize / 2, 
            arrayY - gridSize / 2
        );
    }

    public void setTile(GridItem tile, int arrayX, int arrayY) {
        gridData[arrayX][arrayY] = tile;
    }

    @Override
    public void draw(Graphics graphics, Vector2<Integer> centerScreenCords, Vector2<Double> scale) {

        Vector2<Double> newScale = new Vector2<Double>(
            scale.x * this.scale.x,
            scale.y * this.scale.y
        );

        Vector2<Integer> newCenterScreenCords = new Vector2<Integer>(
            (int) Math.round(centerScreenCords.x + (this.position.x * scale.x)),
            (int) Math.round(centerScreenCords.y + (this.position.y * scale.y))
        );

        this.paint(graphics, newCenterScreenCords, newScale);

        for (GridItem[] row : gridData) {
            for (GridItem gridItem : row) {
                if (gridItem == null) {
                    continue;
                }
                gridItem.paint(graphics, newCenterScreenCords, newScale, (byte) 0);
            }
        }
    }
}

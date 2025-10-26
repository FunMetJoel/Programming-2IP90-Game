package gameEngine;

import gameEngine.renderers.GridRenderer;

/**
 * A grid of GridItem tiles.
 */
public class Grid extends GameObject {
    public final int gridSize = 51;
    GridItem[][] gridData = new GridItem[gridSize][gridSize];

    public Grid() {
        this.renderer = new GridRenderer(this);
    }

    /**
     * Checks if an object can enter a tile.
     * @param gridX the x coordinate of the tile
     * @param gridY the y coordinate of the tile
     * @return if the tile is enterable
     */
    public boolean canEnter(int gridX, int gridY) {
        if (!inBounds(gridX, gridY)) {
            return false;
        }
        return gridData[gridX][gridY].canEnter;
    }

    /**
     * Checks if a tile is in de bounds of the grid.
     * @param gridX the x position of the tile
     * @param gridY the y position of the tile
     * @return if the position is in bounds
     */
    public boolean inBounds(int gridX, int gridY) {
        if (gridX < 0 || gridY < 0) {
            return false;
        }
        if (gridX >= gridSize || gridY >= gridSize) {
            return false;
        }
        return true;
    }

    /**
     * Check if an array position is enterable, not a obstacle and not outside the grid.
     * @param arrayX the array X position of the tile
     * @param arrayY the array Y position of the tile
     * @return if the tile is enterable
     */
    public boolean canEnterArrayPos(int arrayX, int arrayY) {
        if (arrayX < 0 || arrayY < 0) {
            return false;
        }
        if (arrayX >= gridSize || arrayY >= gridSize) {
            return false;
        }
        return gridData[arrayX][arrayY].canEnter;
    }

    /**
     * Sets a tile to a certain position.
     * @param tile the tile to set it to
     * @param arrayX the x position of the tile set
     * @param arrayY the y position of the tile set
     */
    public void setTile(GridItem tile, int arrayX, int arrayY) {
        children.remove(gridData[arrayX][arrayY]);
        gridData[arrayX][arrayY] = tile;
        children.add(tile);
    }

    /**
     * gets a tile.
     * @param x the x cord of the tile
     * @param y the y cord of the tile
     * @return the tile
     */
    public GridItem getTile(int x, int y) {
        if (x < 0 || x >= this.gridSize) {
            return null;
        }
        if (y < 0 || y >= this.gridSize) {
            return null;
        }
        return gridData[x][y];
    }
}

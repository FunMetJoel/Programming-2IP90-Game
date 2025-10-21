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
        Vector2<Integer> arrayPosition = getArrayPos(gridX, gridY);
        if (arrayPosition.x < 0 || arrayPosition.y < 0) {
            return false;
        }
        if (arrayPosition.x >= gridSize || arrayPosition.y >= gridSize) {
            return false;
        }
        return gridData[arrayPosition.x][arrayPosition.y].canEnter;
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
     * Calculates the position in the grid array from the worldPosition of the tile.
     * @param gridX the world pos X of the tile
     * @param gridY the world pos Y of the tile
     * @return The x and y of the array object
     */
    public Vector2<Integer> getArrayPos(int gridX, int gridY) {
        return new Vector2<Integer>(gridX, gridY);
        // TODO: remove when fully implemented
        // return new Vector2<Integer>(
        //     gridX + gridSize / 2, 
        //     gridY + gridSize / 2
        // );
    }

    /**
     * Gets the world position from a array element.
     * @param arrayX the x in the array
     * @param arrayY the y in the array
     * @return The world x and y of the tile
     */
    protected Vector2<Integer> getGridPos(int arrayX, int arrayY) {
        return new Vector2<Integer>(arrayX, arrayY);
        // TODO: remove when fully implemented
        // return new Vector2<Integer>(
        //     arrayX - gridSize / 2, 
        //     arrayY - gridSize / 2
        // );
    }

    public void setTile(GridItem tile, int arrayX, int arrayY) {
        children.remove(gridData[arrayX][arrayY]);
        gridData[arrayX][arrayY] = tile;
        children.add(tile);
    }

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

package gameEngine;

import java.awt.Graphics;

public class Grid extends GameObject {
    int gridSize = 51;
    GridItem[][] gridData = new GridItem[gridSize][gridSize];

    public Grid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Vector2<Double> newPos = new Vector2<Double>(
                    (double) i - gridSize / 2, 
                    (double) j - gridSize / 2
                );

                System.out.println(newPos);

                gridData[i][j] = new GridItem(newPos, scale);
            }
        }
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

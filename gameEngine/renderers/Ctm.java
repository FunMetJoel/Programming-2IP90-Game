package gameEngine.renderers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gameEngine.Grid;
import gameEngine.GridItem;
import gameEngine.Vector2;

public class Ctm {
    public Image[] images = new Image[47];

    public Ctm(Image spriteSheet) {
        BufferedImage bufferedImage = new BufferedImage(
            spriteSheet.getWidth(null), spriteSheet.getHeight(null), 
            BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.drawImage(spriteSheet, 0, 0, null);
        graphics.dispose();

        unpackSpriteSheet(bufferedImage);
    }

    private void unpackSpriteSheet(BufferedImage spriteSheet) {
        for (int i = 0; i < images.length; i++) {
            int x = i % 12;
            int y = i / 12;
            System.out.println(x + ", " + y);
            images[i] = spriteSheet.getSubimage(x * 16, y * 16, 16, 16);
        }
    }

    public Image getTexture(byte situation) {
        final boolean NW = ((situation >> 0) & 1) == 1;
        final boolean N = ((situation >> 1) & 1) == 1;
        final boolean NE = ((situation >> 2) & 1) == 1;
        final boolean E = ((situation >> 3) & 1) == 1;
        final boolean SE = ((situation >> 4) & 1) == 1;
        final boolean S = ((situation >> 5) & 1) == 1;
        final boolean SW = ((situation >> 6) & 1) == 1;
        final boolean W = ((situation >> 7) & 1) == 1;

        // All connected
        if (NW & N & NE & E & SE & S & SW & W) {
            return images[26];
        } 
                
        // Sides
        else if (E & SE & S & SW & W) {
            return images[14];
        } else if (E & NE & N & NW & W) {
            return images[38];
        } else if (N & NE & E & SE & S) {
            return images[25];
        } else if (N & NW & W & SW & S) {
            return images[27];
        }

        // Sides with 1 diagonal missing
        else if (E & S & SW & W) {
            return images[29];
        } else if (E & NE & N & W) {
            return images[40];
        } else if (N & E & SE & S) {
            return images[28];
        } else if (N & NW & W & S) {
            return images[41];
        } else if (E & SE & S & W) {
            return images[31];
        } else if (E & N & NW & W) {
            return images[42];
        } else if (N & NE & E & S) {
            return images[30];
        } else if (N & W & SW & S) {
            return images[43];
        }

        // Sides with 2 diagonals missing
        else if (E & S & W) {
            return images[7];
        } else if (E & N & W) {
            return images[18];
        } else if (N & E & S) {
            return images[6];
        } else if (N & W & S) {
            return images[19];
        }

        // Corners
        else if (N & E & NE) {
            return images[37];
        } else if (N & W & NW) {
            return images[39];
        } else if (S & E & SE) {
            return images[13];
        } else if (S & W & SW) {
            return images[15];
        }

        // Corner without diagonal
        else if (N & E) {
            return images[16];
        } else if (N & W) {
            return images[17];
        } else if (S & E) {
            return images[4];
        } else if (S & W) {
            return images[5];
        }

        // Bar
        else if (S & N) {
            return images[24];
        } else if (E & W) {
            return images[2];
        }

        // Single connection
        else if (E) {
            return images[1];
        } else if (W) {
            return images[3];
        } else if (S) {
            return images[12];
        } else if (N) {
            return images[36];
        }
        
        return images[0];
    }

    public static byte getSituation(Grid grid, Vector2<Integer> tile) {
        byte situation = (byte) 0;
        GridItem center = grid.getTile(tile.x, tile.y);
        if (isSameClass(center, grid.getTile(tile.x - 1, tile.y - 1))) {
            situation |= 1 << 0;
        }
        if (isSameClass(center, grid.getTile(tile.x, tile.y - 1))) {
            situation |= 1 << 1;
        }
        if (isSameClass(center, grid.getTile(tile.x + 1, tile.y - 1))) {
            situation |= 1 << 2;
        }
        if (isSameClass(center, grid.getTile(tile.x + 1, tile.y))) {
            situation |= 1 << 3;
        }
        if (isSameClass(center, grid.getTile(tile.x + 1, tile.y + 1))) {
            situation |= 1 << 4;
        }
        if (isSameClass(center, grid.getTile(tile.x, tile.y + 1))) {
            situation |= 1 << 5;
        }
        if (isSameClass(center, grid.getTile(tile.x - 1, tile.y + 1))) {
            situation |= 1 << 6;
        }
        if (isSameClass(center, grid.getTile(tile.x - 1, tile.y))) {
            situation |= 1 << 7;
        }

        return situation;
    }

    private static boolean isSameClass(GridItem tile, GridItem otherTile) {
        if (otherTile == null) {
            return false;
        }
        return tile.getClass().equals(otherTile.getClass());
    }
}

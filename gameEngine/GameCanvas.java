package gameEngine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Object that contains and renders all gameObjects on the screen.
 */
public class GameCanvas extends JPanel {
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    public Vector2<Double> cameraPosition = new Vector2<Double>(0.0, 0.0);
    public double zoom = 1; // 1 pixel is 1 pixel

    private Scene scene;

    /**
     * Creates a default game canvas.
     */
    public GameCanvas() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setDoubleBuffered(true);
        this.setSize(getPreferredSize());
        this.setBackground(Color.black);
    }

    /**
     * Paints all gameObjects.
     * @param graphics the graphics context
     */
    public void paintComponent(Graphics graphics) {

        int width = this.getWidth();
        int height = this.getHeight();
        
        // Create layers
        BufferedImage[] bufferedImages = new BufferedImage[5];
        Graphics2D[] graphicsLayers = new Graphics2D[bufferedImages.length];
        for (int i = 0; i < bufferedImages.length; i++) {
            bufferedImages[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            graphicsLayers[i] = bufferedImages[i].createGraphics();
        }

        Vector2<Double> scale = new Vector2<Double>(
            zoom * width / 15.0, 
            zoom * width / 15.0
        );

        Vector2<Integer> centerScreenCords = new Vector2<Double>(
            (width / 2) - scale.x * cameraPosition.x,
            (height / 2) - scale.y * cameraPosition.y
        ).round();
        
        for (GameObject gameObject : scene.gameObjects) {
            gameObject.draw(graphicsLayers, centerScreenCords, scale);
        }

        drawToScreen(graphics, bufferedImages);
    }

    public void drawToScreen(Graphics graphics, BufferedImage[] bufferedImages) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        for (BufferedImage bufferedImage: bufferedImages) {
            graphics2D.drawImage(bufferedImage, null, 0, 0);
        }

        graphics2D.dispose();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.camera = this;
    }
}

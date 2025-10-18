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
    static final int totalLayers = 6;
    static final int uiLayers = 2;

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
        BufferedImage[] bufferedImages = new BufferedImage[totalLayers];
        Graphics2D[] graphicsLayers = new Graphics2D[totalLayers - uiLayers];
        Graphics2D[] graphicsUiLayers = new Graphics2D[uiLayers];
        for (int i = 0; i < totalLayers; i++) {
            bufferedImages[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            if (i < totalLayers - uiLayers) {
                graphicsLayers[i] = bufferedImages[i].createGraphics();
            } else {
                graphicsUiLayers[i - (totalLayers - uiLayers)] = bufferedImages[i].createGraphics();
            }
            
        }

        Vector2<Double> screenScale = new Vector2<Double>(
            (double) width, 
            (double) height    
        );

        Vector2<Double> scale = new Vector2<Double>(
            zoom * width / 15.0, 
            zoom * width / 15.0
        );

        Vector2<Double> centerScreenCords = new Vector2<Double>(
            (width / 2) - scale.x * cameraPosition.x,
            (height / 2) - scale.y * cameraPosition.y
        );

        Vector2<Double> centerScreen = new Vector2<Double>(
            width * 0.5, 
            height * 0.5
        );

        renderAll(scene.gameObjects, graphicsLayers, centerScreenCords, scale, centerScreen);
        renderAll(scene.uiObjects, graphicsUiLayers, centerScreen, screenScale, centerScreen);

        drawToScreen(graphics, bufferedImages);
    }

    /**
     * Renders all the gameObjects to screen.
     * @param graphics the graphics context
     * @param centerScreenCords the center of the parent object in screen cords
     * @param scale the scale of the parent object
     */
    private void renderAll(
        ArrayList<GameObject> gameObjects, Graphics2D[] graphics, Vector2<Double> centerScreenCords, Vector2<Double> scale, Vector2<Double> screenCenter
    ) {
        for (GameObject gameObject : gameObjects) {
            renderAll(gameObject, graphics, centerScreenCords, scale, screenCenter);
        }
    }

    /**
     * Renders a gameObject and all its children.
     * @param gameObject the gameObject to render
     * @param graphics the graphics context
     * @param centerScreenCords the center of the parent object in screen cords
     * @param scale the scale of the parent object
     */
    private void renderAll(
        GameObject gameObject, 
        Graphics2D[] graphics, Vector2<Double> centerScreenCords, Vector2<Double> scale, Vector2<Double> screenCenter
    ) {
        Vector2<Double> newScale = gameObject.scale.newScaledVector(scale);
        Vector2<Double> deltaPos = gameObject.position.newScaledVector(scale);
        Vector2<Double> newCenterScreenCords = centerScreenCords.addVector(deltaPos);
        
        if (gameObject.renderer != null) {
            gameObject.renderer.render(graphics, newCenterScreenCords, newScale, screenCenter);
        }

        for (GameObject child : gameObject.children) {
            if (!child.isActive) {
                return;
            }
            renderAll(child, graphics, newCenterScreenCords, newScale, screenCenter);
        }
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

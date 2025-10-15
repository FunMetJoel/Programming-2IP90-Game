import gameEngine.GameObject;
import gameEngine.renderers.RegularShapeRenderer;
import gameEngine.renderers.UiRenderer;

public class EnergyBar extends GameObject {
    
    public EnergyBar() {
        super();
        RegularShapeRenderer renderer = new RegularShapeRenderer(this);
        this.renderer = renderer;
        renderer.shape = RegularShapeRenderer.Shape.rectangle;

        this.scale.x = 0.5;
        this.scale.y = 0.05;
        this.position.y = - 0.45;
        this.renderer.mainLayer = 3;

        this.renderer.renderInCenter = true;
        this.renderer.constantScreenSize = true;
    }
}

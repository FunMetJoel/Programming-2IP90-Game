package level;

public class VectorCreator {
    float x;
    float y;

    public VectorCreator(float x, float y) {
        this.x = x;
        this.y = y;
    }

    float dotProduct(float otherX, float otherY) {
        return x * otherX + y * otherY;
    }
}

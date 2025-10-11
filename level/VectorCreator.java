package level;

public class VectorCreator {
    double x;
    double y;

    public VectorCreator(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double dotProduct(double otherX, double otherY) {
        return x * otherX + y * otherY;
    }
}

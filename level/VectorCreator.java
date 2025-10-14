package level;

public class VectorCreator {
    double x;
    double y;

    public VectorCreator(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double dotProduct(double otherX, double otherY) {
        return this.x * otherX + this.y * otherY;
    }

    double dotProductVector(VectorCreator v) {
        return x * v.x + y * v.y;
    }
}

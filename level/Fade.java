package level;

public class Fade {
    double x;
    
    public Fade(double x) {
        this.x = x;
    }

    public double calculateFade() {
        // double value;
        // value = ((6 * x - 15) * x + 10) * x * x * x;
        // return value;
        return ((6 * x - 15) * x + 10) * x * x * x;
    }
}

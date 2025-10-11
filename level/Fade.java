package level;

public class Fade {
    float x;
    
    public Fade(float x) {
        this.x = x;
    }

    public float calculateFade() {
        return ((6 * x - 15) * x + 10) * x * x * x;
    }
}

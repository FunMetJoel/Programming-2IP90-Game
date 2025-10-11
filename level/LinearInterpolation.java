package level;

public class LinearInterpolation {
    float factor;
    float start;
    float end;
    
    public LinearInterpolation(float factor, float start, float end) {
        this.factor = factor;
        this.start = start;
        this.end = end;
    }

    public float doInteroplation(){
        float value = start + factor * (end - start);

        return value;
    }
}

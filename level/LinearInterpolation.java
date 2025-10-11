package level;

public class LinearInterpolation {
    double factor;
    double start;
    double end;
    
    public LinearInterpolation(double factor, double start, double end) {
        this.factor = factor;
        this.start = start;
        this.end = end;
    }

    public double doInteroplation(){
        double value = start + factor * (end - start);

        return value;
    }
}

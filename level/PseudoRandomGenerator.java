package level;


//TODO javadoc
public class PseudoRandomGenerator {
    int seed;
    double value;

    public PseudoRandomGenerator(int seed) {
        this.seed = seed;
        this.value = seed % Integer.MAX_VALUE;
    }

    public double generatePseudoRandom() {
        value = value * 16807;
        value = value % Integer.MAX_VALUE;

        return (value - 1) / Integer.MAX_VALUE;
    }

    public int getSeed() {
        return seed;
    }
}
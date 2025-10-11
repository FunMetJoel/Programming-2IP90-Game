package level;


//TODO javadoc
public class PseudoRandomGenerator {
    int seed;
    float value;

    public PseudoRandomGenerator(int seed) {
        this.seed = seed;
        this.value = seed % Integer.MAX_VALUE;
    }


    /**Generates a pseudo-random number for the noise to be bounded to a seed.
     * 
     * @param seed an initial seed for the generator
     * @return float in range (0, 1]
     */
    public float generatePseudoRandom() {
        value = value * 16807;
        value = value % Integer.MAX_VALUE;

        return (value - 1) / Integer.MAX_VALUE;
    }

    public int getSeed() {
        return seed;
    }
}
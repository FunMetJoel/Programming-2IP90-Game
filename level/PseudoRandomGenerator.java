package level;


/**Handles generation of pseudo-random numbers based on Lehmer random number generator.
 * 
 */
public class PseudoRandomGenerator {
    int seed;
    double value;

    /**Class constructor.
     * 
     */
    public PseudoRandomGenerator(int seed) {
        this.seed = seed;
        this.value = seed % Integer.MAX_VALUE;
    }

    /**Generates a pseudo-random number based on Lehmer random number generator.
     * 
     * @return a pseudi-random number
     */
    public double generatePseudoRandom() {
        value = value * 16807;
        value = value % Integer.MAX_VALUE;

        return (value - 1) / Integer.MAX_VALUE;
    }

    /**Getter method to get a seed.
     * 
     * @return seed
     */
    public int getSeed() {
        return seed;
    }
}
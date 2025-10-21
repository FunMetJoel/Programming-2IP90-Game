package level;

/**Makes the transitions smoother, based on a function developed by Perlin.
 * 
 */
public class Fade {
    
    /**Calculates the fade value.
     * 
     * @param x value of the argument
     * @return value of the fade function of the given argument
     */
    public static double calculateFade(double x) {
        return ((6 * x - 15) * x + 10) * x * x * x;
    }
}

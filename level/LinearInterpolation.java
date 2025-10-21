package level;

/**Does linear interoplation of two values and a factor.
 * 
 */
public class LinearInterpolation {
    
    /**Computes the interpolation and calculates the value.
     * 
     * @param factor interpolation factor
     * @param start first value
     * @param end second value
     * @return value of the interpolation
     */
    public static double doInteroplation(double factor, double start, double end){
        double value = start + factor * (end - start);
        return value;
    }
}

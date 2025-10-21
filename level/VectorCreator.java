package level;

/**Handles vector creation and operations on vectors.
 * 
 */
public class VectorCreator {
    double x;
    double y;

    /**Class constructor.
     * 
     */
    public VectorCreator(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**Calculates the dot product of the current vector and another vector.
     * 
     * @param otherX x value of the other vector
     * @param otherY y value of the other vector
     * @return dot product value
     */
    double dotProduct(double otherX, double otherY) {
        return this.x * otherX + this.y * otherY;
    }


    /**Calculates the dot product of the current vector and another vector.
     * 
     * @param v other VectorCreator
     * @return dot product value
     */
    double dotProductVector(VectorCreator v) {
        return x * v.x + y * v.y;
    }
}

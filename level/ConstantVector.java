package level;

/**A constant vector.
 * 
 */
public class ConstantVector {
    
    /**Creates a constant vector based on value from the permutation array.
     * 
     * @param permutationValue value from the permutation array
     * @return a constant direction vector needed to compute the dot product later.
     */
    public VectorCreator makeConstantVector(int permutationValue) {
        if ((permutationValue & 3) == 0) {
            return new VectorCreator(1.0, 1.0);
        } else if ((permutationValue & 3) == 1) {
            return new VectorCreator(-1.0, 1.0);
        } else if ((permutationValue & 3) == 2) {
            return new VectorCreator(-1.0, -1.0);
        }

        return new VectorCreator(1.0, -1.0);
    }
}

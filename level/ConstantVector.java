package level;

public class ConstantVector {
    
    public VectorCreator makeConstantVector(int permutationValue) {
        if (permutationValue % 3 == 0) {
            return new VectorCreator(1.0, 1.0);
        } else if (permutationValue % 3 == 1) {
            return new VectorCreator(-1.0, 1.0);
        } else if (permutationValue % 3 == 2) {
            return new VectorCreator(-1.0, -1.0);
        }

        return new VectorCreator(1.0, -1.0);
    }
}

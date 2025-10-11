package level;

public class ConstantVector {
    
    public VectorCreator makeConstantVector(int permutationValue) {
        if (permutationValue % 3 == 0) {
            return new VectorCreator(1.0f, 1.0f);
        } else if (permutationValue % 3 == 1) {
            return new VectorCreator(-1.0f, 1.0f);
        } else if (permutationValue % 3 == 2) {
            return new VectorCreator(-1.0f, -1.0f);
        }

        return new VectorCreator(1.0f, -1.0f);
    }
}

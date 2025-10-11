package level;

public class Noise {
    float x;
    float y;
    
    public Noise(float x, float y) {
        this.x = x;
        this.y = y;
    }

    float calculateNoise() {
        final int indexX = (int) Math.floor(x) & 255;
        final int indexY = (int) Math.floor(y) & 255;

        final double distX = x - Math.floor(x);
        final double distY = y - Math.floor(y);

        final VectorCreator upperRightCorner = new VectorCreator(distX - 1.0, distY - 1.0);
        final VectorCreator upperLeftCorner = new VectorCreator(distX, distY - 1.0);
        final VectorCreator lowerRightCorner = new VectorCreator(distX - 1.0, distY);
        final VectorCreator lowerLeftCorner = new VectorCreator(distX, distY);

        PermutationArrayGeneration permutation = new PermutationArrayGeneration();
        int[] permutationArray = permutation.getPermutatedArray();

        final int valueUpperRight = permutationArray[permutationArray[indexX + 1] + indexY + 1];
        final int valueUpperLeft = permutationArray[permutationArray[indexX] + indexY + 1];
        final int valueLowerRight = permutationArray[permutationArray[indexX + 1] + indexY];
        final int valueLowerLeft = permutationArray[permutationArray[indexX] + indexY];

        final double dotUpperRight = upperLeftCorner.dotProduct(valueLowerRight, valueLowerLeft)
    }
}

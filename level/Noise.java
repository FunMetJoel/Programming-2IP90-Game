package level;

/**Handles the final noise computation based on Ken Perlin's algorithm.
 * 
 */
public class Noise {
    
    /**Calculates the noise value based on Ken Perlin's algorithm.
     * 
     * @param permutationArray permutation array
     * @param x x paramether of a point
     * @param y y paramether of a point
     * @return value of the noise in a specific point
     */
    double calculateNoise(int[] permutationArray, double x, double y) {
        final int indexX = (int) Math.floor(x) & 255;
        final int indexY = (int) Math.floor(y) & 255;

        System.out.println(x + " " + indexX + " | " + y + " " + indexY);

        final double distX = x - Math.floor(x);
        final double distY = y - Math.floor(y);

        final VectorCreator upperRightCorner = new VectorCreator(distX - 1.0, distY - 1.0);
        final VectorCreator upperLeftCorner = new VectorCreator(distX, distY - 1.0);
        final VectorCreator lowerRightCorner = new VectorCreator(distX - 1.0, distY);
        final VectorCreator lowerLeftCorner = new VectorCreator(distX, distY);

        final int valueUpperRight = permutationArray[(permutationArray[indexX + 1] + indexY + 1) % 256];
        final int valueUpperLeft = permutationArray[(permutationArray[indexX] + indexY + 1) % 256];
        final int valueLowerRight = permutationArray[(permutationArray[indexX + 1] + indexY) % 256];
        final int valueLowerLeft = permutationArray[(permutationArray[indexX] + indexY) % 256];

        final VectorCreator constantUpperRight = new ConstantVector().makeConstantVector(valueUpperRight);
        final VectorCreator constantUpperLeft = new ConstantVector().makeConstantVector(valueUpperLeft);
        final VectorCreator constantLowerRight = new ConstantVector().makeConstantVector(valueLowerRight);
        final VectorCreator constantLowerLeft = new ConstantVector().makeConstantVector(valueLowerLeft);

        final double dotUpperRight = upperRightCorner.dotProductVector(constantUpperRight);
        final double dotUpperLeft = upperLeftCorner.dotProductVector(constantUpperLeft);
        final double dotLowerRight = lowerRightCorner.dotProductVector(constantLowerRight);
        final double dotLowerLeft = lowerLeftCorner.dotProductVector(constantLowerLeft);

        final double horizontalFade = Fade.calculateFade(distX);
        final double verticalFade = Fade.calculateFade(distY);

        final double firstInterpolation = LinearInterpolation.doInteroplation(verticalFade, dotLowerLeft, dotUpperLeft);
        final double secondInterpolation = LinearInterpolation.doInteroplation(verticalFade, dotLowerRight, dotUpperRight);
        final double noiseValue = LinearInterpolation.doInteroplation(horizontalFade, firstInterpolation, secondInterpolation);

        return noiseValue;
    }

    public static void main(String[] args) {
        // new Noise(23.532, 14.463, 12345).calculateNoise();
    }
}

package level;

public class Noise {
    
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

        // final int valueUpperRight = permutationArray[permutationArray[indexX + 1] + indexY + 1];
        // final int valueUpperLeft = permutationArray[permutationArray[indexX] + indexY + 1];
        // final int valueLowerRight = permutationArray[permutationArray[indexX + 1] + indexY];
        // final int valueLowerLeft = permutationArray[permutationArray[indexX] + indexY];

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

        final double horizontalFade = new Fade(distX).calculateFade();
        final double verticalFade = new Fade(distY).calculateFade();

        final double firstInterpolation = new LinearInterpolation(verticalFade, dotLowerLeft, dotUpperLeft).doInteroplation();
        final double secondInterpolation = new LinearInterpolation(verticalFade, dotLowerRight, dotUpperRight).doInteroplation();

        final double noiseValue = new LinearInterpolation(horizontalFade, firstInterpolation, secondInterpolation).doInteroplation();

        // System.out.println(noiseValue);

        return noiseValue;
    }

    public static void main(String[] args) {
        // new Noise(23.532, 14.463, 12345).calculateNoise();
    }
}

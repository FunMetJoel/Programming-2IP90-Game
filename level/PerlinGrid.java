package level;

public class PerlinGrid {
    int seed;

    public PerlinGrid(int seed) {
        this.seed = seed;
    }


    double[][] createGrid(int x, int y) {
        double[][] perlinArray = new double[x][y];
        Noise noise = new Noise();
        PermutationArrayGeneration generator = new PermutationArrayGeneration(seed);
        generator.createIndicies();
        generator.fillInitialArray();
        generator.shuffleArray();
        int[] permutations = generator.getPermutatedArray();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                double noiseValue = noise.calculateNoise(permutations, i * 0.01, j * 0.01);
                noiseValue += 1.0;
                noiseValue /= 2;

                perlinArray[i][j] = noiseValue;
            }
        }

        return perlinArray;
    }

    public static void main(String[] args) {
        PerlinGrid grid = new PerlinGrid(12132144);
        double[][] array = grid.createGrid(50, 50);
    }
}
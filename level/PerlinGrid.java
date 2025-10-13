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

    double findAverage(double[][] array) {
        double sum = 0.0;
        int x = array.length;
        int y = array[0].length;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sum += array[i][j];
                // System.out.println("Element: " + array[i][j] + "    Sum: " + sum);
            }
        }   

        // System.out.println("Average:" + sum / (x * y * 1.0) + "    " + sum);
        return sum / (x * y * 1.0);
    }

    String[][] visualyRepresentedGrid(double average, double[][] grid) {
        int x = grid.length;
        int y = grid[0].length;

        String[][] visual = new String[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] >= average) {
                    visual[i][j] = "*";
                } else {
                    visual[i][j] = " ";
                }
            }
        }

        return visual;
    }

    void printStringArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        PerlinGrid grid = new PerlinGrid(12132144);
        double[][] array = grid.createGrid(200, 200);

        double average = grid.findAverage(array);

        String[][] vis = grid.visualyRepresentedGrid(average, array);

        grid.printStringArray(vis);
    }
}
package level;

public class PerlinGrid {
    int seed;

    public PerlinGrid(int seed) {
        this.seed = seed;
    }


    double[][] createGrid(int x, int y, double frequency) {
        double[][] perlinArray = new double[x][y];
        Noise noise = new Noise();
        PermutationArrayGeneration generator = new PermutationArrayGeneration(seed);
        generator.createIndicies();
        generator.fillInitialArray();
        generator.shuffleArray();
        int[] permutations = generator.getPermutatedArray();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                double noiseValue = noise.calculateNoise(permutations, i * frequency, j * frequency);
                // double noiseValue = noise.calculateNoise(permutations, i, j);
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

    String[][] combineGrid(String[][] firstGrid, String[][] secondGrid) {
        String[][] betterGrid = new String[firstGrid.length][firstGrid[0].length];

        for (int i = 0; i < firstGrid.length; i++) {
            for (int j = 0; j < firstGrid[0].length; j++) {
                if (firstGrid[i][j] == "*" && secondGrid[i][j] == "*") {
                    betterGrid[i][j] = "*";
                } else {
                    betterGrid[i][j] = " ";
                }
            }
        }
        return betterGrid;
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
        PerlinGrid gridOne = new PerlinGrid(16890);
        double[][] arrayOne = gridOne.createGrid(50, 50, 0.01);
        double averageOne = gridOne.findAverage(arrayOne);
        String[][] visOne = gridOne.visualyRepresentedGrid(averageOne, arrayOne);

        PerlinGrid gridTwo = new PerlinGrid(6523);
        double[][] arrayTwo = gridTwo.createGrid(50, 50, 0.01);
        double averageTwo = gridTwo.findAverage(arrayTwo);
        String[][] visTwo = gridTwo.visualyRepresentedGrid(averageTwo, arrayTwo);

        String[][] combinedGrid = gridOne.combineGrid(visOne, visTwo);

        gridOne.printStringArray(combinedGrid);
    }
}
package level;

import java.util.ArrayList;

public class PermutationArrayGeneration {
    PseudoArrayGeneration generation;

    int size = 256;

    int[] permutations = new int[size];

    ArrayList<Double> randomNumbers;

    ArrayList<Integer> indicies = new ArrayList<Integer>();

    public PermutationArrayGeneration(int seed) {
        this.generation = new PseudoArrayGeneration(seed);
        this.randomNumbers = generation.getArray();
    }

    public void fillInitialArray() {
        for (int i = 0; i < 256; i++) {
            permutations[i] = i;
            // System.out.println(permutations.get(i));
        }
    }

    public void createIndicies() {
        for (int i = 0; i < randomNumbers.size(); i++) {
            double number = randomNumbers.get(i) * 1000;
            int index = (int) Math.round(number) % 256;
            indicies.add(index);
        }
    }

    public void shuffleArray() {
        for (int i = permutations.length - 1; i > 0; i -= 1) {
            int index = indicies.get(i);
            int tmp = permutations[i];
            permutations[i] = permutations[index];
            permutations[index] = tmp;
        }
    }

    public int[] getPermutatedArray() {
        return permutations;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        String arrayRepresentation = "";

        for (int i = 0; i < permutations.length; i++) {
            arrayRepresentation += permutations[i] + "\n";
        }

        return arrayRepresentation;
    }

    public static void main(String[] args) {
        PermutationArrayGeneration generation = new PermutationArrayGeneration(4462);
        generation.createIndicies();
        generation.fillInitialArray();
        generation.shuffleArray();
        System.out.println(generation);
    }
}

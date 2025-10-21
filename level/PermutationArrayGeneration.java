package level;

import java.util.ArrayList;

/**Handles methods generating a permutation array needed for later computations.
 * 
 */
public class PermutationArrayGeneration {
    PseudoArrayGeneration generation;

    int size = 256;

    int[] permutations = new int[size];

    ArrayList<Double> randomNumbers;

    ArrayList<Integer> indicies = new ArrayList<Integer>();

    /**Constructor of the class.
     * 
     * @param seed seed for random number generator
     */
    public PermutationArrayGeneration(int seed) {
        this.generation = new PseudoArrayGeneration(seed);
        this.randomNumbers = generation.getArray();
    }

    /**Fill the initial array with numbers from 0 to 255.
     * 
     */
    public void fillInitialArray() {
        for (int i = 0; i < 256; i++) {
            permutations[i] = i;
            // System.out.println(permutations.get(i));
        }
    }

    /**Transforms the random number array into an array of int indicies.
     * 
     */
    public void createIndicies() {
        for (int i = 0; i < randomNumbers.size(); i++) {
            double number = randomNumbers.get(i) * 1000;
            int index = (int) Math.round(number) & 255;
            indicies.add(index);
        }
    }

    /**Shuffles the initial permutation array with random indicies.
     * 
     */
    public void shuffleArray() {
        for (int i = permutations.length - 1; i > 0; i -= 1) {
            int index = indicies.get(i);
            int tmp = permutations[i];
            permutations[i] = permutations[index];
            permutations[index] = tmp;
        }
    }

    /**Getter method to get permutation array.
     * 
     * @return permutation array
     */
    public int[] getPermutatedArray() {
        return permutations;
    }

    /**Getter method to get the size of permutation array.
     * 
     * @return size of permutation array
     */
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
        PermutationArrayGeneration generation = new PermutationArrayGeneration(2462);
        generation.createIndicies();
        generation.fillInitialArray();
        generation.shuffleArray();
        System.out.println(generation);
    }
}

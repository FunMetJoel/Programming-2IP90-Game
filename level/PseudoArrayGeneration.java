package level;

import java.util.ArrayList;

/**Handles generation of an array list of pseudo-random numbers.
 * 
 */
public class PseudoArrayGeneration {
    ArrayList<Double> pseudoArray = new ArrayList<Double>();
    PseudoRandomGenerator prg;

    public PseudoArrayGeneration(int seed) {
        this.prg = new PseudoRandomGenerator(seed);
    }

    /**Generates the array list of pseudo random numbers.
     * 
     */
    void generateArray() {
        for (int i = 0; i < 256; i++) {
            
            double nextElement = prg.generatePseudoRandom();

            pseudoArray.add(nextElement);
        }
    }

    /**Getter method to get the array list of pseudo-random numbers.
     * 
     * @return array list of pseudo-random numbers
     */
    ArrayList<Double> getArray() {
        generateArray();
        return pseudoArray;
    }


    @Override
    public String toString() {
        String arrayRepresentation = "";

        for (int i = 0; i < pseudoArray.size(); i++) {
            arrayRepresentation += pseudoArray.get(i) + "\n";
        }

        return arrayRepresentation;
    }

    public static void main(String[] args) {
        PseudoArrayGeneration generate = new PseudoArrayGeneration(1234);
        generate.generateArray();
        System.out.println(generate);
    }
}
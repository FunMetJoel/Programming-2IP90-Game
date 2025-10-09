package level;

import java.util.ArrayList;

public class PseudoArrayGeneration {
    ArrayList<Float> pseudoArray = new ArrayList<Float>();
    PseudoRandomGenerator prg = new PseudoRandomGenerator(1234);

    int seed = prg.getSeed();

    void generateArray() {
        for (int i = 0; i < 256; i++) {
            
            float nextElement = prg.generatePseudoRandom();

            pseudoArray.add(nextElement);
        }
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
        PseudoArrayGeneration generate = new PseudoArrayGeneration();
        generate.generateArray();
        System.out.println(generate);
    }
}
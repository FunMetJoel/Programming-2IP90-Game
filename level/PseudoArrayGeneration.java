package level;

import java.util.ArrayList;

public class PseudoArrayGeneration {
    ArrayList<Double> pseudoArray = new ArrayList<Double>();
    PseudoRandomGenerator prg;

    public PseudoArrayGeneration(int seed) {
        this.prg = new PseudoRandomGenerator(seed);
    }

    void generateArray() {
        for (int i = 0; i < 256; i++) {
            
            double nextElement = prg.generatePseudoRandom();

            pseudoArray.add(nextElement);
        }
    }

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
        PseudoArrayGeneration generate = new PseudoArrayGeneration(1254325334);
        generate.generateArray();
        System.out.println(generate);
    }
}
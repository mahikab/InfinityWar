package avengers;

import java.util.ArrayList;

//java.util.*;
/**
 * Given a Set of Edges representing Vision's Neural Network, identify all of the 
 * vertices that connect to the Mind Stone. 
 * List the names of these neurons in the output file.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * MindStoneNeighborNeuronsInputFile name is passed through the command line as args[0]
 * Read from the MindStoneNeighborNeuronsInputFile with the format:
 *    1. v (int): number of neurons (vertices in the graph)
 *    2. v lines, each with a String referring to a neuron's name (vertex name)
 *    3. e (int): number of synapses (edges in the graph)
 *    4. e lines, each line refers to an edge, each line has 2 (two) Strings: from to
 * 
 * Step 2:
 * MindStoneNeighborNeuronsOutputFile name is passed through the command line as args[1]
 * Identify the vertices that connect to the Mind Stone vertex. 
 * Output these vertices, one per line, to the output file.
 * 
 * Note 1: The Mind Stone vertex has out degree 0 (zero), meaning that there are 
 * no edges leaving the vertex.
 * 
 * Note 2: If a vertex v connects to the Mind Stone vertex m then the graph has
 * an edge v -> m
 * 
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut:
 *     StdOut.setFile(outputfilename);
 *     //Call StdOut.print() for EVERY neuron (vertex) neighboring the Mind Stone neuron (vertex)
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/MindStoneNeighborNeurons mindstoneneighborneurons.in mindstoneneighborneurons.out
 *
 * @author Yashas Ravi
 * 
 */


public class MindStoneNeighborNeurons {
    
    public static void main (String [] args) {
        
    	if ( args.length < 2 ) {
            StdOut.println("Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>");
            return;
        }
    	
        String mindStoneNeighborNeuronsInputFile = args[0];
        String mindStoneNeighborNeuronsOutputFile = args[1];

        StdIn.setFile(mindStoneNeighborNeuronsInputFile);

        int brainNeurons = StdIn.readInt();
        String[] neurons = new String[brainNeurons];

        for (int i = 0; i < brainNeurons; i++){
            neurons[i] = StdIn.readString();
        }

        int x = StdIn.readInt();
        int[][] arrayMatrix = new int[brainNeurons][brainNeurons];


        for (int i = 0; i < x; i++){
            String start = StdIn.readString(), to = StdIn.readString(); //from, to
            int row = -1;
            int col = -1;

            for (int j = 0; j < brainNeurons; j++){
                if (neurons[j].equals(start))
                    row = j;
                if (neurons[j].equals(to))
                    col = j;
                if (row != -1 && col != -1)
                    break;
                
            }
            arrayMatrix[row][col] = 1;

        }


        int y = -1;

        for (int r = 0; r < brainNeurons; r++){
            int tony = 0;

            for (int c = 0; c < brainNeurons; c++){
                if (arrayMatrix[r][c] == 1)
                    tony++;
            }
            if (tony == 0){
                y = r;
                break;
            }
        }


        StdOut.setFile(mindStoneNeighborNeuronsOutputFile);

        for (int i = 0; i < brainNeurons; i++) {
            if (arrayMatrix[i][y] == 1)
                StdOut.println(neurons[i]);
        }
        
    }
}

package avengers;

import java.util.ArrayList;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {
	 
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }
        
        String predictThanosSnapInputFile = args[0];
        String predictThanosSnapOutputFile = args[1];
        
        StdIn.setFile(predictThanosSnapInputFile);

        long seed = StdIn.readLong();
        StdRandom.setSeed(seed);
        int p = StdIn.readInt();

        boolean[][] ChrisEvans = new boolean[p][p];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                int edge = StdIn.readInt();
                if (edge == 1) 
                    ChrisEvans[i][j] = true;
            }
        }

        ArrayList<Integer> avengers = new ArrayList<Integer>();
        for (int i = 0; i < p; i++) {
            if (StdRandom.uniform() <= 0.5) 
                avengers.add(i);
            
            }
            boolean isConnected = Connection(ChrisEvans, avengers);
            StdOut.setFile(predictThanosSnapOutputFile);
            StdOut.print(isConnected);
            }
        
        public static boolean Connection(boolean[][] Max, ArrayList<Integer> avengers) {
            boolean connect = true;
            boolean[] array2 = new boolean[Max.length];
            for (int i = 0; i < Max.length; i++) {
                if (!avengers.contains(i)) {
                    methodADD(Max, i, array2, avengers);
                    break;
                }
            }
            for (int i = 0; i < array2.length; i++) {
                if (!avengers.contains(i)) {
                    if (!array2[i]) {
                        connect = false;
                        break;
                    }
                }
            }
                return connect;
        }
           
        public static void methodADD(boolean[][] Max, int vertex, boolean[] array2, ArrayList<Integer> AVENGERS) {
            array2[vertex] = true;
            for (int i = 0; i < Max.length; i++) {
                if (Max[vertex][i] && !array2[i] && !AVENGERS.contains(i)) 
                        methodADD(Max, i, array2, AVENGERS);
            }
        }        

}

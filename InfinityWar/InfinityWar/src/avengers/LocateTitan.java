package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:f
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }


        String locateTitanInputFile = args[0];
        String locateTitanOutputFile = args[1];


    	   // Set the input file.
        StdIn.setFile(locateTitanInputFile);

        int g = StdIn.readInt();
        Double[] ChrisEvans = new Double[g];

           for (int i = 0; i < g; i++) {
               int j = StdIn.readInt();
               ChrisEvans[i] = StdIn.readDouble();
           }

        int[][] adjMatrix = new int[g][g];

           for (int i = 0; i < g; i++) {
               for (int j = 0; j < g; j++) {
                   adjMatrix[i][j] = StdIn.readInt();
               }
           }


           for (int i = 0; i < g; i++) {
               for (int j = 0; j < g; j++) {
                   int l = (int)(adjMatrix[i][j]/(ChrisEvans[i] * ChrisEvans[j]));
                   adjMatrix[i][j] = l;
               }
           }

           // Use Dijkstra's Algorithm 
           int[] dist = new int[g];
           boolean[] dij = new boolean[g];

        for (int i = 0; i < dist.length; i++){
            if (i == 0)
                dist[i] = 0;
            else  
                dist[i] = Integer.MAX_VALUE;
        }

           for (int i = 0; i < g - 1; i++) {
               int minVertex = minVertex(dist, dij);
               dij[minVertex] = true;

               for (int j = 0; j < g; j++) {
                   if (adjMatrix[minVertex][j] != 0 && dij[j] == false) {
                       int newDist = dist[minVertex] + adjMatrix[minVertex][j];
                       if (newDist < dist[j]) 
                           dist[j] = newDist;
                       
                   }
               }
           }
           // Set the output file.
           StdOut.setFile(locateTitanOutputFile);
           StdOut.print(dist[g - 1]);
       }
    
      
       public static int minVertex(int[] dist, boolean[] dij) {
           int mini = Integer.MAX_VALUE;
           int index = -1;

           for (int i = 0; i < dist.length; i++) {
               if (dist[i] < mini && dij[i] == false){
                    mini = dist[i];
                    index = i;
               }
           }
           return index;
   

    }
}

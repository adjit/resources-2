// (C) Prof. Lixin Tao, Pace University, September 2003
public class ExhaustiveSearch {

  // Enumerate all solutions, and return the one with the smallest cut size
  // Return the best partition through bestPartition[]
  // Utilities object u is shared by all algorithms
  public int run(int bestPartition[], Utilities u) {
    int vertexNumber = u.getVertexNumber();   // Retrieve vertex number in the graph
    int halfVertexNumber = vertexNumber/2;     
    int p[] = new int[vertexNumber];          // Allocate space for current partition
    int bestCost = Integer.MAX_VALUE;         // Initialize bestCost to an impossible large value
    // Create a generator that will systematically return half of values in
    // {0, 1, 2, ..., vertexNumber-1}
    CombinationGenerator g = new CombinationGenerator(vertexNumber, halfVertexNumber);
    while (g.hasMore()) {   // Loop as long as there are more combinations to enumerate
      int c[] = g.nextCombination();  // c[] contains the selected vertices
      // First asign all vertices to the left partition
      for (int i = 0; i < vertexNumber; i++)      
        p[i] = 0;
      // Then reassign the selected vertices to the right partition
      for (int i = 0; i < halfVertexNumber; i++)  
        p[c[i]] = 1;
      int cost = u.cutSize(p);        // Evaluate the cut size of partition p
      if (cost < bestCost) {          // If p improves the best seen so far, record it
        bestCost = cost;
        u.copyArray(p, bestPartition);
      }
    }
    return bestCost;
  }

  // main() is only used when you run "java ExhaustiveSearch [file name]"
  public static void main(String args[]) {
    Utilities u = new Utilities();                    // Create a Utilities object
    String fileName = "graph10.txt";                  // Default data file name
    if (args.length == 1)
      fileName = args[0];                             // Use command-line file name
    u.readGraph(fileName);
    //u.printGraph();                                 // Print out the parsed graph data
    int bestPartition[] = new int[u.getVertexNumber()]; // Allocate space for best partition
    ExhaustiveSearch es = new ExhaustiveSearch();
    u.startRun();                                     // Mark the start of run
    int bestCost = es.run(bestPartition, u);          // Run Exhaustive Search
    u.endRun();                                       // Mark the end of run
    // Print out results
    u.reportResult("Exhaustive search", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Exhaustive search", bestCost, bestPartition);        
  }
}
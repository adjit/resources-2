// (C) Prof. Lixin Tao, Pace University, September 2003
public class HillClimbing {
  
  // Use Hill-Climbing to find and return the smallest cut size
	// Return the best partition through bestPartition[]
  // Utilities object u is shared by all algorithms
  public int run(int bestPartition[], Utilities u) {
    int vertexNumber = u.getVertexNumber();   // Retrieve vertex number in the graph
    u.randomPartition(bestPartition);         // Generate random initial solution
    int bestCost = u.cutSize(bestPartition);  // Record best cost seen so far
    int neighbor[] = new int[vertexNumber];   // Allocate space for a neighbor solution
    // Loop terminates if we see 100 successive non-improving neighbors
    // This is an efficient approximation for hill-climbing
    for (int i = 0; i < 100; i++) {                           
      u.copyArray(bestPartition, neighbor);
      u.randomSwap(neighbor);                 // neighbor[] is now a neighbor of bestPartition[]
      int newCost = u.cutSize(neighbor);
      if (newCost < bestCost) {               // If new cost is the best seen so far, record it
        u.copyArray(neighbor, bestPartition);
        bestCost = newCost;
        i = 0;                                // retry 100 times for improvement
      }
    }
    return bestCost;
  }

  // main() is only used when you run "java HillClimbing [file name]"
  public static void main(String args[]) {
    Utilities u = new Utilities();                    // Create a Utilities object
    String fileName = "graph10.txt";                  // Default data file name
    if (args.length == 1)
      fileName = args[0];                             // Use command-line file name
    u.readGraph(fileName);
    //u.printGraph();                                 // Print out the parsed graph data
    int bestPartition[] = new int[u.getVertexNumber()]; // Allocate space for best partition
    HillClimbing hc = new HillClimbing();
    u.startRun();                                     // Mark the start of run
    int bestCost = hc.run(bestPartition, u);          // Run Hill-Climbing
    u.endRun();                                       // Mark the end of run
    // Print out results
    u.reportResult("Hill-climbing", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Hill-climbing", bestCost, bestPartition);        
  }
}
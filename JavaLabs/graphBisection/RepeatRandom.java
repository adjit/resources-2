// (C) Prof. Lixin Tao, Pace University, September 2003
public class RepeatRandom {

  // Generate count random partitions, and return the one with the smallest cut size
	// Return the best partition through bestPartition[]
  // Utilities object u is shared by all algorithms
  public int run(int bestPartition[], int count, Utilities u) {
    int vertexNumber = u.getVertexNumber();   // Retrieve vertex number in the graph
    int p[] = new int[vertexNumber];          // Allocate space for current partition
    int bestCost = Integer.MAX_VALUE;         // Initialize bestCost to an impossible large value
    for (int i = 0; i < count; i++) {
      u.randomPartition(p);            // Populate p[] with a random partition
      int newCutSize = u.cutSize(p);   // Evaluate the cut size of p[]
      if (newCutSize < bestCost) {     // If p[] is the best partition seen so far, record it
        bestCost = newCutSize;
        u.copyArray(p, bestPartition);
      }
    }
    return bestCost;
  }

  // main() is only used when you run "java RepeatRandom [file name]"
  public static void main(String args[]) {
    Utilities u = new Utilities();                    // Create a Utilities object
    String fileName = "graph10.txt";                  // Default data file name
    if (args.length == 1)
      fileName = args[0];                             // Use command-line file name
    u.readGraph(fileName);
    //u.printGraph();                                 // Print out the parsed graph data
    int bestPartition[] = new int[u.getVertexNumber()]; // Allocate space for best partition
    RepeatRandom rr = new RepeatRandom();
    u.startRun();                                     // Mark the start of run
    int bestCost = rr.run(bestPartition, 100, u);     // Run "repeat random 100 times"
    u.endRun();                                       // Mark the end of run
    // Print out results
    u.reportResult("Repeat 100 times", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Repeat 100 times", bestCost, bestPartition);        
  }
}
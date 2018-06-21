// (C) Prof. Lixin Tao, Pace University, September 2003
import java.util.Random;

public class SimulatedAnnealing {
  
  // Use simulated annealing to find and return the smallest cut size
  // Return the best partition through bestPartition[]
  // Utilities object u is shared by all algorithms
  public int run(int bestPartition[], Utilities u) {
    int vertexNumber = u.getVertexNumber();   // Retrieve vertex number in the graph
    Random r = u.getRandom();                 // Retrieve Random object
    int p[] = new int[vertexNumber];          // Allocate space for current partition

    u.randomPartition(p);                     // Generate random initial solution
    int currentCost = u.cutSize(p);           // Find out its cost
    int bestCost = currentCost;               // p[] is the best partition seen so far
    u.copyArray(p, bestPartition);            // Record it
    int neighbor[] = new int[vertexNumber];   // Allocate space for a neighbor solution
    double t = 10.0;         // Initial temperature; parameter for adjustment
    while (t > 0.01) {       // While not frozen; parameter for adjustment
      for (int l = 0; l < 50; l++) {  // 1000 is parameter for adjustment
        u.copyArray(p, neighbor);
        u.randomSwap(neighbor);         // neighbor[] is now a neighbor of p[]
        int newCost = u.cutSize(neighbor);
        int delta = newCost - currentCost;
        // Probability to accept a worsening neighbor
        double acceptProbability = Math.exp(-delta/t);
        // If the neighbor is better, take it as new current solution
        // Otherwise take it with probability acceptProbability
        if ((delta <= 0) || (r.nextDouble() < acceptProbability)) {
          // Accept the neighbor
          u.copyArray(neighbor, p);
          currentCost = newCost;
          // If the new solution is the best seen so far, record it
          if (currentCost < bestCost) {  
            bestCost = currentCost;
            u.copyArray(p, bestPartition);
          }
        }
      }
			t = 0.95*t;   // Reduce temperature
    }
    return bestCost;
  }

  // main() is only used when you run "java SimulatedAnneaing [file name]"
  public static void main(String args[]) {
    Utilities u = new Utilities();                    // Create a Utilities object
    String fileName = "graph10.txt";                  // Default data file name
    if (args.length == 1)
      fileName = args[0];                             // Use command-line file name
    u.readGraph(fileName);
    //u.printGraph();                                 // Print out the parsed graph data
    int bestPartition[] = new int[u.getVertexNumber()]; // Allocate space for best partition
    SimulatedAnnealing sa = new SimulatedAnnealing();
    u.startRun();                                     // Mark the start of run
    int bestCost = sa.run(bestPartition, u);          // Run Simulated Annealing
    u.endRun();                                       // Mark the end of run
    // Print out results
    u.reportResult("Simulated annealing", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Simulated annealing", bestCost, bestPartition);        
  }
}
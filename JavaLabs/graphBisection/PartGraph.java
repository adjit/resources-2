// (C) Prof. Lixin Tao, Pace University, September 2003
// Sample implementation for Graph Bisection
import java.io.*;
import java.util.StringTokenizer;
import java.util.Random;

// A driver class to run all graph bisection algorithms on the same graph data file
public class PartGraph {
  
  static public void main(String args[]) {
    Utilities u = new Utilities();                      // Create a Utilities object
    String fileName = "graph10.txt";                    // Default data file name
    if (args.length == 1)
      fileName = args[0];                               // Use command-line file name
    u.readGraph(fileName);                              // Read in graph data file
    //u.printGraph();                                   // Print out the parsed graph data
    int bestPartition[] = new int[u.getVertexNumber()]; // Allocate space for best partition
    int bestCost;                                       // Cut size of bestPartition[]

    // Run Exhaustive Search only if vertex number is less than 21
    if (u.getVertexNumber() < 21) {   
      ExhaustiveSearch es = new ExhaustiveSearch();
      u.startRun();                                // Mark the start of run
      bestCost = es.run(bestPartition, u);         // Run Exhaustive Search
      u.endRun();                                  // Mark the end of run
      // Print out results
      u.reportResult("Exhaustive search", bestCost, bestPartition); 
      // Append results in file costs.txt
      u.appendBestPartition("Exhaustive search", bestCost, bestPartition);   
    }
    
    RepeatRandom rr = new RepeatRandom();
    u.startRun();                                  // Mark the start of run
    bestCost = rr.run(bestPartition, 100, u);      // Run "repeat random 100 times"
    u.endRun();                                    // Mark the end of run
    // Print out results
    u.reportResult("Repeat 100 times", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Repeat 100 times", bestCost, bestPartition);   
    
    HillClimbing hc = new HillClimbing();
    u.startRun();                                  // Mark the start of run
    bestCost = hc.run(bestPartition, u);           // Run Hill-Climbing
    u.endRun();                                    // Mark the end of run
    // Print out results
    u.reportResult("Hill-climbing", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Hill-climbing", bestCost, bestPartition);   

    SimulatedAnnealing sa = new SimulatedAnnealing();
    u.startRun();                                  // Mark the start of run
    bestCost = sa.run(bestPartition, u);           // Run Simulated Annealing
    u.endRun();                                    // Mark the end of run
    // Print out results
    u.reportResult("Simulated annealing", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Simulated annealing", bestCost, bestPartition);   

    TabuSearch ts = new TabuSearch();
    u.startRun();                                   // Mark the start of run
    bestCost = ts.run(bestPartition, u);            // Run Tabu Search
    u.endRun();                                     // Mark the end of run
    // Print out results
    u.reportResult("Tabu search", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Tabu search", bestCost, bestPartition);
    
    GeneticAlgorithm ga = new GeneticAlgorithm();
    u.startRun();                                  // Mark the start of run
    bestCost = ga.run(bestPartition, u);           // Run Genetic Algorithm
    u.endRun();                                    // Mark the end of run
    // Print out results             
    u.reportResult("Genetic algorithm", bestCost, bestPartition); 
    // Append results in file costs.txt
    u.appendBestPartition("Genetic algorithm", bestCost, bestPartition); 
  }
}
// (C) Prof. Lixin Tao, Pace University, September 2003
import java.io.*;
import java.util.StringTokenizer;
import java.util.Random;

// Provide utility methods used by multiple algorithms
public class Utilities {
  private int vertexNumber;      // Number of vertices in the current graph
  private int adj[][] = null;    // Adjacency matrix for the current graph
  private Random r = null;       // Shared random number generator
  private String fileName;       // Graph data file name

  private long startTime;        // Run start time in milliseconds from 12AM 1970 
  private long endTime;          // Run end time in milliseconds from 12AM 1970 

  // Class constructor
  public Utilities() {  
    // Initialize random number generator with current time
    // so each run will use different random numbers
    long randomSeed = System.currentTimeMillis();
    r = new Random(randomSeed);
  }

  // Getter for Random object r
  public Random getRandom() {    
    return r;
  }

  // Getter for vertex number
  public int getVertexNumber() {
    return vertexNumber;
  }

  // Getter for the adjacency matrix adj[][]
  public int[][] getAdjacencyMatrix() {
    return adj;
  } 
  
  // Make a time stamp for run start time
  public void startRun() {
    startTime = System.currentTimeMillis();
  }

  // Make a time stamp for run end time
  public void endRun() {
    endTime = System.currentTimeMillis();
  }

  // Return elaped time for the recent run in milliseconds
  public long elapsedTime() {
    return endTime - startTime;
  }

  // Generic way to report run results
  public void reportResult(String message, int bestCost, int bestPartition[]) {
    System.out.println(message + ":  file = " + fileName + "   cut size = " + bestCost);
    System.out.println("Run time = " + elapsedTime() + " milliseconds");
    printPartition(bestPartition);
    System.out.println("----------------------------------------------");
  }

  // Print the partition in p[]
  public void printPartition(int p[]) {
    for (int i = 0; i < p.length; i++)
      System.out.print(i + ":" + p[i] + " ");
    System.out.println();
  }

  // Objective (cost) function: cut size of a partition p
  public int cutSize(int p[]) {
    int sum = 0;
    for (int i = 1; i < vertexNumber; i++) {
      for (int j = 0; j < i; j++) { 
        if ((adj[i][j] == 1) && (p[i] != p[j])) 
          sum++;
      }
    }
    return sum;
  }

  // Generate in p[] a random feasible partition (with balanced vertex distribution)
  public void randomPartition(int p[]) {     // p[i] = 0: vertex i is in left partition
                                             // p[i] = 1: vertex i is in right partition
    for (int i = 0; i < vertexNumber; i++)   // Put all vertices in left partition 
      p[i] = 0;   
    int count = vertexNumber/2;              // Number of vertices that should be in right partition
    while (count > 0) {                      // Move count vertices from left partition to right one
      int j = r.nextInt(vertexNumber);       // j is a random vertex
      if (p[j] == 0) {                       // If j is still in left partition, move it
        p[j] = 1;
        count--;
      }
    }
  }

  // Copy from[] into to[]
  public void copyArray(int from[], int to[]) {
    for (int i = 0; i < from.length; i++)
      to[i] = from[i];
  }

  // Randomly swap two vertices, one on each side of partition p[]
  public void randomSwap(int p[]) {
    while (true) {
      int x = r.nextInt(vertexNumber);   // Randomly choose two vertices x and y
      int y = r.nextInt(vertexNumber);
      if (p[x] != p[y]) {                // If x and y are from different partitions, swap them
        swap(p, x, y);                   // and return
        return;
      }
    }
  }

  // Swap two vertices in p[] at positions i and j
  public void swap(int p[], int i, int j) {
    int temp = p[i];
    p[i] = p[j];
    p[j] = temp;
  }
  
  // Read in graph data from file fileName
  public void readGraph(String fileName) {
    BufferedReader file = null;
    StringTokenizer st;  // A generic reference for a String tokenizer
    String line;         // Current line read from the file
    String token;        // Current token on the current line
    int v;               // Current integer represented by token

    this.fileName = fileName; 
    try {
      file = new BufferedReader(new FileReader(fileName));
      // Read vertex number, and create the adjacency array for the graph
      line = file.readLine().trim();
      vertexNumber = Integer.parseInt(line);
      // Vertices are named 0, 1, 2, ..., vertexNumber-1
      adj = new int[vertexNumber][vertexNumber];  // Allocate space for the adjacency array
      // Clean up the main diagonal line
      for (int i = 0; i < vertexNumber; i++)
        adj[i][i] = 0;
      // Read in edges from lower left triangle of the adjacency matrix
      for (int i = 1; i < vertexNumber; i++) {
        line = file.readLine();
        st = new StringTokenizer(line);
        for (int j = 0; j < i; j++) {
          token = st.nextToken().trim();
          v = Integer.parseInt(token);
          adj[i][j] = adj[j][i] = v;      // The edges are bi-directional
        }
      }
    }
    catch (Exception e) {}
    finally {
      try {
        if (file != null) file.close();
      }
      catch (Exception e) {}
    }
  }

  // Print out the contents of a graph data file without parsing; for debugging only
  public void viewGraph(String fileName) {
    BufferedReader file = null;
    try {
      file = new BufferedReader(new FileReader(fileName));
      String line = file.readLine();
      System.out.println(line);
      while ((line = file.readLine()) != null) {
        System.out.println(line);
      }
    }
    catch (Exception e) {}
    finally {
      try {
        if (file != null) file.close();
      }
      catch (Exception e) {}
    }
  }

  // Print out parsed graph data; for debugging only
  public void printGraph() {
    System.out.println("-------------Graph Contents--------------");
    System.out.println("Vertex number: " + vertexNumber);
    System.out.println("Adjacency matrix:");
    for (int i = 0; i < vertexNumber; i++) {
      for (int j = 0; j < vertexNumber; j++)
        System.out.print(adj[i][j] + " ");
      System.out.println();
    }
    System.out.println();
    System.out.println("Edges:");
    for (int i = 0; i < vertexNumber-1; i++)
      for (int j = i+1; j < vertexNumber; j++)
        if (adj[i][j] > 0)
          System.out.print("(" + i + ", " + j +")");
    System.out.println();
    System.out.println("-----------------------------------------");
  }

  // Append the best cost to file costs.txt for off-line analysis
  public void appendBestPartition(String message, int bestCost, int bestPartition[]) {
    try {
      FileWriter fw = new FileWriter("costs.txt", true);
      PrintWriter out = new PrintWriter(fw);
      out.println(message + ":   file = " + fileName + "   cut size = " + bestCost);
      out.println("Run time = " + elapsedTime() + " milliseconds");
      for (int i = 0; i < vertexNumber; i++)
        out.print(i + ":" + bestPartition[i] + "|");
      out.println();
      out.println("-----------------------------------------------------------");
      out.close();
    }
    catch (Exception e){}
  }
}
// (C) Prof. Lixin Tao, Pace University, September 2003
// Generate random graphs for graph bisection
import java.io.*;
import java.util.Random;

public class GenGraph {

  static int vertexNumber = 10;
  static int expectedDegree;
  static String outputFileName = "graph10.txt";         // Default output file name
  static Random r = null;
  
  public static void main(String args[]) {
    if (args.length > 0) 
      vertexNumber = Integer.parseInt(args[0].trim());  // Get vertex number from command-line
    if (args.length > 1)
      outputFileName = args[1];                         // Get file name from command-line
    // If vertexNumber is odd, promote it to the next even integer
    if (2*(vertexNumber/2) != vertexNumber)           
      vertexNumber++;                 
    // Calculate the expected number of edges incident to each vertex
    expectedDegree = vertexNumber/3;  

    long randomSeed = System.currentTimeMillis();       // Get milliseconds from 12AM 1970
    r = new Random(randomSeed);   // Use current time as random generator seed
    PrintWriter pw = null;
    File file = new File(outputFileName);
    /*
    // Use the following code if you don't want to rewrite existing files
    if (file.exists()) {
      System.out.println("File " + outputFileName + " already exists,"
                         + " delete or rename it, rerun the program");
      System.exit(0);
    }
    */
    try {
      pw = new PrintWriter(new FileOutputStream(file), true);
      genRandomGraph(pw, vertexNumber, expectedDegree);   // Generate and write out random graph
    }
    catch (IOException e) {
      System.out.println(e.getMessage());
    }
    finally {
      if (pw != null) pw.close();
    }
  }

  // Write to stream out the data of a random graph with vertexNumber vertices 
  // and each vertex has on the average expectedDegree neighbors
  static void genRandomGraph(PrintWriter out, int vertexNumber, int expectedDegree) {
    double p =((double)expectedDegree)/(vertexNumber - 1); // Probability to generate an edge
    try {
      out.println(vertexNumber);
      // Generate random edges
      for (int i = 1; i < vertexNumber; i++) {
        for (int j = 0; j < i; j++) {
          int value = 0;
          if (r.nextDouble() < p) // Generate an edge with probability p
            value = 1;
          out.print(value + " ");
        }
        out.println();
      }
    }
    catch (Exception e) {}
  }
}
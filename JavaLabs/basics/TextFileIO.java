import java.io.*;
import java.util.Random;

public class TextFileIO {
  public static void main(String[] args) throws IOException {
    File file = new File("temp.txt");
    if (!file.exists()) {    
      // if file temp.txt does not exist, create an output stream to it
      PrintWriter output = new PrintWriter(new FileWriter(file));

      // generate 100 random integers
      Random r = new Random();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++)
          output.printf("%4d", r.nextInt(100));
        output.println();
      }
      
      // close the output stream
      output.close();
    }
    
    // open an input stream from file temp.txt
    BufferedReader input =
      new BufferedReader(new FileReader("temp.txt"));
    
    int total = 0;
    String line = null;
    while ((line = input.readLine()) != null) {
      String[] token = line.trim().split("\\s+");
      for (int i = 0; i < token.length; i++) 
        total += Integer.parseInt(token[i]);
    }
    System.out.println("Total is " + total);
    
    // close input stream
    input.close();
  }
}
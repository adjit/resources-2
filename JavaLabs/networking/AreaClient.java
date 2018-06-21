// (C) Dr. Lixin Tao, Pace University
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

// Usage: java AreaClient [serverURL [portNbr]]
// If your provide port number, you must also provide server URL
public class AreaClient {
  public static void main(String[] args) {
    String serverURL = "localhost"; // default server URL
    int portNbr = 8000;             // default server port number
    if (args.length > 0)
      serverURL = args[0];
    if (args.length > 1) {
      try {
        portNbr = Integer.parseInt(args[1]);
      } catch (Exception e) {
        // ignore the wrong port number
      }
    }		
    try {
      // Create a socket to connect to the server
      System.out.println("Connecting to " + serverURL + ":" + portNbr);
      Socket connectToServer = new Socket(serverURL, portNbr);
      // Create a buffered input stream to receive data
      // from the server
      BufferedReader isFromServer = new BufferedReader(
        new InputStreamReader(connectToServer.getInputStream()));
      // Create a buffered output stream to send data to the server
      PrintWriter osToServer =
        new PrintWriter(connectToServer.getOutputStream(), true);
      // Continuously send radius and receive area from the server
      // Terminate the session by typing an illegal number or 0
      System.out.println("Successfully connected to the server");
      System.out.println("Terminate the session by typing an illegal number or 0");
      while (true) {
        // Read a radius from an input dialog
        String s = JOptionPane.showInputDialog("Please enter a radius");
        double radius;
        try {
          radius = Double.parseDouble(s.trim());
        }
        catch (Exception e) {
          break;
        }								
        if (radius == 0)  // if radius is 0, terminate the session
          break;				
        // Send the radius to the server
        osToServer.println(radius);
        // Get area from the server
        StringTokenizer st = new StringTokenizer(isFromServer.readLine());
        // Convert string to double
        double area = Double.parseDouble(st.nextToken());
        // Print area on the console
        System.out.println("Radius = " + radius + "; Area (from the server): " + area);
      }
    }
    catch (IOException e)  {
      System.err.println(e);
    }
    System.exit(0);
  }
}

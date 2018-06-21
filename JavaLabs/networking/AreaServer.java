// (C) Dr. Lixin Tao, Pace University
import java.io.*;
import java.net.*;
import java.util.*;

public class AreaServer  {

  // maximum number of clients the server can serve simultaneously
  static final int MAX_THREAD_LIMIT = 10; 

  public static void main(String[] args)  { 
    int portNbr = 8000; // default port number
    if (args.length > 0) { // user provides port number; adopt it
      try {
        portNbr = Integer.parseInt(args[0]);
      } catch (Exception e) {
        System.out.println("Usage: java AreaServer [portNbr]");
      }
    }	  
    try  {
      // Create a server socket at the specified port
      ServerSocket serverSocket = new ServerSocket(portNbr);
      System.out.println("Server is running at port number " + portNbr);
      // Create a thread group to hold all active threads serving the clients
      ThreadGroup g = new ThreadGroup("servingThreads");
      // As long as there are less than MAX_THREAD_LIMIT active threads,
      // wait for new client service requests;
      // otherwise sleep for 1000 ms and and check again
      while (true) {
        if (g.activeCount() >= MAX_THREAD_LIMIT) { // thread number limit reached
          try { 
            Thread.sleep(1000);   // 1000 can be adjusted; sleep for 1000 ms
          } catch (InterruptedException e) {}
            // resume the while loop from beginning to check number of active threads again
            continue; 
        }
        // Start listening for connections on the server socket
        Socket connectToClient = serverSocket.accept();
        // If execution reaches here, accept() has returned by responding to a client request
        // A connectToClient holds a new socket responsible for communicating
        // with the corresponding socket on the client side
        Thread thread = new Thread(g, new Server(connectToClient));
        thread.start(); // lauch a new thread dedicated to serve the new client
      }
    } catch (Exception e) {
      System.err.println("Server launch failed");
      System.err.println(e);
    }
    System.exit(0);
  }
}
	
// Each new thread running class Server serves one client
class Server extends Thread {

  Socket connectToClient;

  public Server(Socket connectToClient) {
    this.connectToClient = connectToClient;
  }
  
  public void run() {
    PrintWriter osToClient; // output stream for sending data to client
    BufferedReader isFromClient; // input stream for data from client
    try {
      // Create a buffered reader stream to get data from the client
      isFromClient = new BufferedReader(new
      	InputStreamReader(connectToClient.getInputStream()));
      // Create a buffered writer stream to send data to the client
      osToClient = new PrintWriter(connectToClient.getOutputStream(), true);
    } catch (IOException e) { // networking error
      System.err.println("Networking error, shutting down serving thread");
      return;
    }
    // Continuously read from the client and process it,
    // and send result back to the client
    while (true) {
      // Read a line and create a string tokenizer
      StringTokenizer st;
      try {
        st = new StringTokenizer(isFromClient.readLine());
      } catch (IOException e) {
        System.err.println("Networking error, shutting down serving thread");
        return;
      }
      // Retrieve the first token, which should represent a double
      String value = st.nextToken();
      double radius;
      try {
        // Convert string to double
        radius = Double.parseDouble(value);
      } catch (Exception e) { // Value from client is not a number
        System.out.println("Client sent illegal value: " + value);
        return; // exit from this thread; stop serving this client
      }
      // Compute area
      double area = radius*radius*Math.PI;
      // Send the result to the client
      osToClient.println(area);
      // Print radius and result to the console
      System.out.println("Radius (from client): " + radius + "; Area found: " + area);
    }
  }
}

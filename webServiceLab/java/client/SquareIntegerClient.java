import localhost.axis.SquareIntegerServer_jws.*;

public class SquareIntegerClient {
  public static void main(String[] args) throws Exception {
    int value = 0; // value to be squared
    // The program expects to receive an integer on command-line
    // Program quits if there is no such integer
    if (args.length == 1)  // there is one command-line argument 
      value = Integer.parseInt(args[0]);  // parse the string form of integer to an int
    else {
      System.out.println("Usage: java SquareIntegerClient  [integer]");
      System.exit(-1);  // terminate the program
    }
    // Get the proxy factory
    SquareIntegerServerServiceLocator factory =
      new SquareIntegerServerServiceLocator();
    // Generate the Web service proxy object
    SquareIntegerServer proxy = factory.getSquareIntegerServer();
    // Access the Web service
    int result = proxy.square(value); // invoke server method to square value
    System.out.println("Square of " + value + " is " + result);
  }
}
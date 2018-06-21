import edu.pace.csis.*;

public class HelloWorldClient {
  public static void main(String[] args) throws Exception {
    String name = ""; // name to be sent to the Web service
    // The program expects to receive a string name on command-line
    // If you have spaces inside the name, enclose the entire name inside double quotes
    // Program quits if there is no such name string
    if (args.length == 1)  // there is one command-line argument 
      name = args[0];  
    else {
      System.out.println("Usage: java HelloWorldClient  \"[your name]\"");
      System.exit(-1);  // terminate the program
    }
    // Get the proxy factory
    ServiceLocator factory = new ServiceLocator();
    // Generate the Web service proxy object
    ServiceSoap proxy = factory.getServiceSoap();
    // Invoke Web service method to retrieve a personalized message
    String message = proxy.hello(name); 
    System.out.println(message);
  }
}
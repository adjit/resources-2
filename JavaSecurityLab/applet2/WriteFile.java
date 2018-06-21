import java.awt.*;
import java.applet.*;
import java.io.*;

public class WriteFile extends Applet {
  public void paint(Graphics g) {
    try {
      String fileName = System.getProperty("user.home") +
		    System.getProperty("file.separator") +       //   / or \
		    "data.txt";
      File f = new File(fileName);
      PrintWriter output = new PrintWriter(new FileWriter(f), true); // auto-flush
      output.println(new java.util.Date() + ": Pace University Java Tutorial");
      g.drawString("Data were successfully written to file \"" + fileName + "\"", 10, 10);
    }
    catch (SecurityException e) {
      g.drawString("WriteFile: security exception is caught", 10, 10);
      e.printStackTrace();  // print error messages to terminal window for debugging
    }
    catch (IOException ioe) {
      g.drawString("WriteFile: I/O exception is caught", 10, 10);
    }
  }
}



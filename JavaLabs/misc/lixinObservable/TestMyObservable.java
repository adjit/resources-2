package lixinObservable;
// (C) Prof. Lixin Tao, Pace Univerity, February 2005
// This project shows the essence of class Observable and interface Observer.
// By my class MyObservable and interface MyObserver, you can understand the
// inside work of java.util.Observerable and java.util.Observer, and has a better
// understanding of the essence of the event-driven programming paradigm.

import java.util.Observer;
import java.util.Observable;
import javax.swing.*;

public class TestMyObservable {
  public static void main(String args[]) {
    // Create an information source
    InforSource source = new InforSource();
    for (int i = 1; i <= 5; i++) 
      // Create 5 information users (observers), and register them with observable source
      source.addObserver(new InforUser(i)); 
    // Run the information source n a new thread
    Thread t = new Thread(source);
    t.start(); 
  }
}

class InforSource extends MyObservable implements Runnable {
  public void run() {
    while (true) {  // The code will run forever until the user stops it
      // The information source will repeatedly generates messages 1, 2, ..., 100
      for (int i = 1; i <= 100; i++) { 
        Object o = "Message " + i;
        // Tell the observable that new information (event) has arrived
        setChanged();  
        // Let the observable to broadcast new information to all registered observers
        notifyObservers(o);  
        // Add a pause of 1000 milliseconds
        try {
          Thread.sleep(1000);
        }
        catch (Exception e) {}
      }
    }
  };
}

class InforUser implements MyObserver {
  private JTextField textField = new JTextField(10);

  public InforUser(int i) {
    // Create a new window with the specified title
    JFrame f = new JFrame("Information Observer " + i);
    // Set up so the application will shutdown when the user clicks on the window icon X
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Set the window size
    f.setSize(300, 100);
    // Position the windows at (x, y) locations
    f.setLocation(100, 100+i*100);
    // Add the text field to the window
    f.getContentPane().add(textField);
    // Make the window visible
    f.setVisible(true);
  }
  
  // Callback method declared in interface Observer
  public void update(MyObservable o, Object arg) {
    textField.setText((String)arg);
  }
}
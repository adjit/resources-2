import javax.swing.JOptionPane;

public class InputDialog {
  public static void main(String[] args) {
    String input = 
      JOptionPane.showInputDialog(null, "Enter an integer");
    int n = Integer.parseInt(input);
    String message = "Input value is " + n + "\n"
                     + "Square of " + n + " is " + n*n;
    JOptionPane.showMessageDialog(null, message);
    System.exit(0);
  }
}
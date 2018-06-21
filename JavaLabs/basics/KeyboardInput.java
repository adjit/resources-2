import java.util.*;

public class KeyboardInput {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Please enter an integer: ");
    int n1 = keyboard.nextInt();
    System.out.println("You have just entered integer " + n1);
    System.out.print("Please enter a floating-point number: ");
    double d = keyboard.nextDouble();
    System.out.println("You have just entered floating-point number " + d);
    System.out.print("Please enter a string containing no spaces: ");
    String s = keyboard.next();
    System.out.println("You have just entered a string \"" + s + "\"");
    s = keyboard.nextLine(); // consume the "\n" on the current line
    System.out.print("Please enter a few strings: ");
    s = keyboard.nextLine();
    System.out.println("You have just entered line \"" + s + "\"");
    System.out.print("Please enter two integers on the same line: ");
    n1 = keyboard.nextInt();
    int n2 = keyboard.nextInt();
    System.out.println("You have just entered integers " + n1 + " and " + n2);
  }
}
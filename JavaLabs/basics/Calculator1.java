public class Calculator1 {
  public static void main(String[] args) {
    //for (int i = 0; i < args.length; i++)
    //  System.out.println(args[i]);
    if (args.length != 3) {
      System.out.println("Usage: java  Calculator1  double  op  double\n" +
                         "where op can be +, -, x, or /");
      System.exit(-1);                      
    }
    double d1 = Double.parseDouble(args[0]);
    double d2 = Double.parseDouble(args[2]);
    char op = args[1].charAt(0);
    double result = 0;
    if (op == '+')
      result = d1 + d2;
    else if (op == '-')
      result = d1 - d2;
    else if (op == 'x')
      result = d1 * d2;
    else if (op == '/')
      result = d1 / d2;
    else {
      System.out.println("Error: accepted operators are +, -, x, and /");
      System.exit(-1);
    }
    System.out.println(d1 + " " + op + " " + d2 + " = " + result);
  }
}
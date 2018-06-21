public class Calculator3 {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Usage: java  Calculator3  double  op  double\n" +
                         "where op can be +, -, x, or /");
      System.exit(-1);                      
    }
    double d1 = 0.0, d2 = 0.0;
    try {
      d1 = Double.parseDouble(args[0]);
      d2 = Double.parseDouble(args[2]);
    }
    catch (Exception e) {
      // System.out.println(e);
      System.out.println("Error: at least one of the operands is not a number");
      System.exit(-2);
    }
    /*
    finally    {
     System.out.println("Finally clause executed. ");
    }
    */
    char op = args[1].charAt(0);
    double result = 0;
    switch (op) {
      default:
        System.out.println("Error: accepted operators are +, -, x, and /");
        System.exit(-3);
      case '+': 
        result = d1 + d2;
        break;
      case '-':
        result = d1 - d2;
        break;
      case 'x':
      case 'X':
        result = d1 * d2;
        break;
      case '/':
        result = d1 / d2;
        break;
    }
    System.out.println(d1 + " " + op + " " + d2 + " = " + result);
  }
}
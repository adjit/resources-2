public class Calculator2 {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Usage: java  Calculator2  double  op  double\n" +
                         "where op can be +, -, x, or /");
      System.exit(-1);                      
    }
    double d1 = Double.parseDouble(args[0]);
    double d2 = Double.parseDouble(args[2]);
    char op = args[1].charAt(0);
    double result = 0;
    switch (op) {
      case '+': 
        result = d1 + d2;
        break;
      case '-':
        result = d1 - d2;
        break;
      case 'x':
        result = d1 * d2;
        break;
      case '/':
        result = d1 / d2;
        break;
      default:
        System.out.println("Error: accepted operators are +, -, x, and /");
        System.exit(-1);
    }
    System.out.println(d1 + " " + op + " " + d2 + " = " + result);
  }
}
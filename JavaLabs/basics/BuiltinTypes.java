public class BuiltinTypes {
  public static void main(String[] args) {
    // Variable declaration and initialization
    int i = 0; 
    char c = 'c';
    String s = "Hello";
    double d = 1.414;
    boolean isOK = true;
    // Convert int string into int
    i = Integer.parseInt("12");
    System.out.println("String \"12\" is converted into int " + i);
    // Convert double string into double
    d = Double.parseDouble("3.14");
    System.out.println("String \"3.14\" is converted into double " + d);
    // Get a char from a string
    c = s.charAt(0);
    System.out.println("The first character of \"" + s + "\" is '" + c + "'");
    // Convert a boolean string into boolean
    isOK = Boolean.parseBoolean("false");
    System.out.println("isOK now has value " + isOK);
    // int remainder operator %
    System.out.println("3 % 5 = " + (3 % 5));
    System.out.println("5 % 2 = " + (5 % 2));
    System.out.println("10 % 2 = " + (10 % 2));
    // Integer division and implicit type promotion
    System.out.println("5/2 = " + (5/2));
    System.out.println("5.0/2 = " + (5.0/2));
    // Convert int into double, implicitly
    i = 12;
    d = i; 
    System.out.println("d has now value " + d);
    // Convert double into int, explicitly with type casting
    i = (int)d;
    System.out.println("i has now value " + i);
    // Conversion between int and char
    c = 'a';
    System.out.println("Unicode value for 'a' is " + (int)c);
    c = (char)(c + 1);
    System.out.println("c now has value '" + c + "'");
    // Relationship expressions and boolean
    i = 5;
    isOK = (i == 5); // equal to
    System.out.println("(1) isOK = " + isOK);
    isOK = (i <= 5); // less than or equal to
    System.out.println("(2) isOK = " + isOK);
    isOK = (i >= 6); // greater than or equal to
    System.out.println("(3) isOK = " + isOK);
    isOK = (i != 6); // not equal to
    System.out.println("(4) isOK = " + isOK);
    isOK = !isOK;    // negation
    System.out.println("(5) isOK = " + isOK);
    isOK = (i >= 0) && (i <= 6);  // Are two conditions both true?
    System.out.println("(6) isOK = " + isOK);
    isOK = (i < 2) || (i > 6);    // At least one of the conditions is true?
    System.out.println("(7) isOK = " + isOK);
    // Common Math functions
    System.out.println("abs(-2) = " + Math.abs(-2));
    System.out.println("ceil(1.5) = " + Math.ceil(1.5));
    System.out.println("ceil(2) = " + Math.ceil(2));
    System.out.println("ceil(-1.5) = " + Math.ceil(-1.5));
    System.out.println("floor(1.5) = " + Math.floor(1.5));
    System.out.println("floor(2) = " + Math.floor(2));
    System.out.println("floor(-1.5) = " + Math.floor(-1.5));
    System.out.println("exp(1.0) = " + Math.exp(1.0));
    System.out.println("log(10.0) = " + Math.log(10.0));
    System.out.println("max(1, 2) = " + Math.max(1, 2));
    System.out.println("min(1, 2) = " + Math.min(1, 2));
    System.out.println("pow(2.0, 3.0) = " + Math.pow(2.0, 3.0));
    System.out.println("sqrt(2.0) = " + Math.sqrt(2.0));
    System.out.println("random() = " + Math.random());
  }
}
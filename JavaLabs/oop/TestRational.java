public class TestRational {
  public static void main(String[] args) {
    Rational x = new Rational(1, 2);
    System.out.println(x);
    Rational y, z;
    y = new Rational(1, 3);
    z = x.add(y);
    System.out.println(x + " + " + y + " = " + z);
    System.out.println(x + " x " + y + " = " + x.multiply(y));
  }
}

class Rational {
  private int numerator;
  private int denominator;
  
  public Rational(int numerator, int denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }
  
  public Rational() {
    this(0, 1);
  }
  
  public int getNumerator() {
    return numerator;
  }
  
  public void setNumerator(int numerator) {
    this.numerator = numerator;
  }
  
  public int getDenominator() {
    return denominator;
  }
  
  public void setDenominator(int denominator) {
    this.denominator = denominator;
  }
  
  public Rational add(Rational o) {
    int n = numerator*o.getDenominator() + o.numerator*denominator;
    int d = denominator * o.getDenominator();
    return new Rational(n, d);
  }
  
  public Rational multiply(Rational o) {
    return new Rational(numerator * o.getNumerator(),
                        denominator * o.getDenominator());
  }
  
  public String toString() {
    return "(" + numerator + ", " + denominator + ")";
  }
}
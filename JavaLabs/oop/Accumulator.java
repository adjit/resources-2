public class Accumulator {
  private int value = 0;
  
  public Accumulator() {} // optional
  
  public void add(int v) {
    value = value + v;
  }
 
  public int getValue() {
    return value;
  }
  
  public void setValue(int value) {
    this.value = value;
  }

  public static void main(String[] args) {
    Accumulator a = new Accumulator();
    System.out.println(a.getValue());
    a.setValue(10);
    System.out.println(a.getValue());
    a.add(5);
    System.out.println(a.getValue());
  }
}

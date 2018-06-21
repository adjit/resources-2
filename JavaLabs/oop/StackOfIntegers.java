public class StackOfIntegers {
  private int[] elements;
  private int size; // initially size has default 0

  /** Construct a stack with the default capacity 16 */
  public StackOfIntegers() {
    this(16);
  }

  /** Construct a stack with the specified maximum capacity */
  public StackOfIntegers(int capacity) {
    elements = new int[capacity];
  }

  /** Push a new integer into the top of the stack */
  public int push(int value) {
    if (size >= elements.length) {
      int[] temp = new int[elements.length * 2];
      System.arraycopy(elements, 0, temp, 0, elements.length);
      elements = temp;
    }

    return elements[size++] = value;
  }

  /** Return and remove the top element from the stack */
  public int pop() {
    return elements[--size];
  }

  /** Test whether the stack is empty */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Return the number of elements in the stack */
  public int size() {
    return size;
  }
  
  /** Testing driver for class StackOfInteger */
  public static void main(String[] args) {
    StackOfIntegers s1 = new StackOfIntegers(3);
    for (int i = 0; i < 20; i++)
      s1.push(i);
    System.out.println("There are " + s1.size() + " values in stack s1");
    while (!s1.isEmpty()) 
      System.out.print(s1.pop() + " ");
    System.out.println();
    
    // Test Java Collection Framework's class Stack of Object
    java.util.Stack<Integer> s2 = new java.util.Stack<Integer>();
    for (int i = 0; i < 20; i++)
      s2.push(i + 20);
    System.out.println("There are " + s2.size() + " values in stack s2");
    while (!s2.isEmpty()) 
      System.out.print(s2.pop() + " ");
    System.out.println();
  }
}

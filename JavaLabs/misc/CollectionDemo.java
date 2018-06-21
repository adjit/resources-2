// (C) Dr. Lixin Tao, Pace University
// This class provides a demo of the important data structures of
//   Java Collection framework, including Vector, ArrayList, Stack, Hashtable.
// It also shows the usage of Arrays.sort() for Object sorting.
import java.util.*;

public class CollectionDemo {

  public static void main(String arg[]) {
    CollectionDemo o = new CollectionDemo();
    o.VectorDemo();    
    o.ArrayListDemo();
    o.StackDemo();
    o.HashtableDemo();
  }
  
  // Vector is an array of Objects with variable size.
  // Class Vector is roughly equivalent to ArrayList, except that it is synchronized
  // It should be used when the data could be accessed by multiple objects simultaneously
  // Its basic operations include add(Object), add(int, Object), iterator(), remove(int),
  //   size(), toArray()
  // Iterator is a Java interface supports methods hasNext() and next()
  void VectorDemo() {
    System.out.println("-----Vector demo-----");
    Vector<String> vector = new Vector<String>();  // create an empty vector
    for (int k = 0; k < 5; k++)
      vector.add("Item " + k); // add String objects to the end of vector
    vector.add(3, "Inserted value"); // insert a value at position 3, values from position 3 move right
    Iterator i = vector.iterator();  // retrieve an Iterator for vector
    // An Iterator is a list of references for the objects in a data structure,
    //  with a read pointer initialized to before the first value
    while (i.hasNext()) // does i still have unvisited values?
      System.out.println(i.next());  // if yes, retrieve the next one
    System.out.println("vector[3] = " + vector.get(3));  // retrive value at position 3
    vector.remove(3);  // remove value at position 3
    vector.add("A"); // add "A" to the end for showing off sorting later
    System.out.println("vector has now " + vector.size() + " elements");
    Object[] array = vector.toArray();  // return list values as an array of Objects
    Arrays.sort(array);  // sort values in array
    for (int k = 0; k < array.length; k++)
      System.out.println("array[" + k + "] = " + array[k]);
  }
  
  // Class ArrayList is roughly equivalent to Vector, except that it is unsynchronized
  // It should be used when only one object will access it a time, and efficiency is important
  // Its basic operations include add(Object), add(int, Object), iterator(), remove(int),
  //   size(), toArray()
  // Iterator is a Java interface supports methods hasNext() and next()
  void ArrayListDemo() {
    System.out.println("-----ArrayList demo-----");
    ArrayList<String> list = new ArrayList<String>();  // create an empty list
    for (int k = 0; k < 5; k++)
      list.add("Item " + k);  // add String objects to the end of the list
    list.add(3, "Inserted value"); // insert a value at position 3, values from position 3 move right
    Iterator i = list.iterator();  // retrieve an Iterator for the list
    // An Iterator is a list of references for the objects in a data structure,
    //  with a read pointer initialized to before the first value
    while (i.hasNext())  // does i still have unvisited values?
      System.out.println(i.next());  // if yes, retrieve the next one
    System.out.println("list[3] = " + list.get(3));  // retrive value at position 3
    list.remove(3); // remove value at position 3
    list.add("A"); // add "A" to the end for showing off sorting later
    System.out.println("list has now " + list.size() + " elements");
    Object[] array = list.toArray();  // return list values as an array of Objects
    Arrays.sort(array);  // sort values in array
    for (int k = 0; k < array.length; k++)
      System.out.println("array[" + k + "] = " + array[k]);
  }
  
  // Stack is a First-In-Last-Out data structure for Objects
  // Its most important methods are push(Object), pop(), and isEmpty()
  void StackDemo() {
    System.out.println("-----Stack demo-----");
    Stack<String> s = new Stack<String>();
    for (int i = 1; i < 5; i++) {
      System.out.println("Pushing " + i); 
      s.push(""+i);  // pushing String objects
    }
    while (!s.isEmpty()) 
      System.out.println("popping " + s.pop());
  }
  
  // A hashtable maintains a set of values associated with their keys.
  // Both keys and values must be objects of any type.
  // To insert a (key, value) pair in the table h, use "h.put(key, value);"
  // To retrieve value associated with key in table h, use "h.get(key)".
  void HashtableDemo() {
    System.out.println("-----Hashtable demo-----");
    Hashtable<String, Integer> h = new Hashtable<String, Integer>();
    // Count occurrence number for each string in words[]
    // The words in words[] are used as keys, their associated values are their
    //   occurrences
    String[] words = new String[]{"a", "b", "a", "c", "b", "c", "b"};
    for (int i = 0; i < words.length; i++) {
      // If a word is not in table yet, insert object 1 in the table: it has been seen once
      Object o = h.get(words[i]); // retrieved value is always of type Object
      if (o == null) {
        h.put(words[i], new Integer(1));
        continue;  // resume from for loop header
      }
      // Otherwise, get the current count (value) for the word, increase it by 1,
      //   and save it as new value for the word
      Integer iv = (Integer)o;  // cast Object to Integer
      int v = iv.intValue();  // get Integer's primitive int value
      h.put(words[i], new Integer(v+1)); // increase value by 1, save it back in table
    }
    Integer iv = (Integer)h.get("b");  // retrieve count for "b"
    System.out.println("\"a\" occurred " + iv + " times");
  }
}
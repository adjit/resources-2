// Author: Lixin Tao, CSIS, Pace University, 9/5/2006
// Class BitArray implements a new data type as a sequence of binary bits.
// Java has limited support for bit operations. This class overcomes this
// problem. It is one of the utility classes for cipher algorithm 
// implementations.
// You can run this class aone to learn the function of exlusive-or, by typing
// "java  BitArray  abc  def" (you can change the last two strings to any
// strings, but they must be of the same length
public class BitArray {
  private boolean[] value = null; // bit sequence is implemented as bool array
  
  // Default constructor
  public BitArray() {}
  
  // Create a new BitArray object that is a copy of b
  public BitArray(BitArray b) {
    this.value = new boolean[b.value.length];
    for (int i = 0; i < b.value.length; i++)
      this.value[i] = b.value[i];
  }
  
  // Create a new BitArray object with value specified by boolean array b[]
  public BitArray(boolean[] b) {
    value = b;
  }
  
  // Create a new BitArray object with value specified by byte b
  public BitArray(byte b) {
    setValue(b);
  }
  
  // Create a new BitArray object with value specified by bytes in 
  // byte array a[]
  public BitArray(byte[] a) {
    setValue(a);
  }
  
  // Create a new BitArray object with value specified by string s
  public BitArray(String s) {
     this(s.getBytes());
  }
  
  // Create a new BitArray object for a bit sequence of length 'length'
  // and all the bits being initialized as 0 or 1 depending whether
  // initialValue is 0 or not
  public BitArray(int length, byte initialValue) {
    value = new boolean[length];
    for (int i = 0; i < length; i++) 
      value[i] = (initialValue == 0) ? false : true;
  }
  
  // Return a boolean array representing byte b (true for 1, false for 0)
  public boolean[] byte2booleanArray(byte b) {
    boolean[] v = new boolean[8];
    v[0] = (b >=0) ? false : true;
    byte mask = (byte)0x40; // 0100 0000
    for (int i = 1; i < 8; i++) {
      v[i] = ((b & mask) > 0);  // &: bit-wise logical AND
      mask = (byte)(mask >> 1); // shift 1 to right by one position
    }
    return v;
  }
  
  // Return length of the bit sequence represented by this object
  public int getLength() {
    return value.length;
  }
  
  // Reset the value of this object to represent bits in byte b
  public void setValue(byte b) {
    value = byte2booleanArray(b);
  }
  
  // Set the value of this object to represent bits in bytes of array a[]
  public void setValue(byte[] a) {
    value = new boolean[8 * a.length]; // one byte has 8 bits
    int destIndex = 0;  
    for (int i = 0; i < a.length; i++) {
      boolean[] temp = byte2booleanArray(a[i]);
      for (int j = 0; j < 8; j++)
        value[destIndex++] = temp[j];
    }
  }
  
  // Return a new BitArray object that holds bits of the current object
  // with index values from 'from' to 'to'
  public BitArray range(int from, int to) {
    int length = to - from + 1;
    BitArray b = new BitArray(length, (byte)0);
    for (int i = 0; i < length; i++)
      b.value[i] = this.value[from+i];
    return b;
  }
  
  // Return a byte array represented by the current object
  // Assume bit sequence length is multiple of 8
  public byte[] getBytes() {
    if ((value == null) || ((value.length % 8) != 0)) {
      System.out.println("getBytes(): bit length is not multiple of 8");
      System.exit(1);
    }
    int byteNumber = value.length/8;
    byte[] b = new byte[byteNumber]; 
    int k = 0;
    for (int i = 0; i < byteNumber; i++) {
      int v = 0;
      int sign = (value[k++]) ? -1 : 1;
      for (int j = 0; j < 7; j++) {
        v = 2*v + ((value[k++]) ? 1 : 0);
      }
      if (sign == -1 && v == 0)
        b[i] = (byte)0x80;  // 1000 0000
      else if (sign == -1)
        b[i] = (byte)(0x80 | v);
      else
        b[i] = (byte)v;
    }
    return b;
  }
  
  // Return a string represented by this BitArray object. Its length must
  // must be a multiple of 8
  public String getString() {
    return new String(getBytes());
  }
  
  // Retrieve bit value at the specified index value
  public byte getBit(int index) {
    if (index < 0 || index >= value.length) {
      System.out.println("getBit(): Invalid index " + index);
      System.exit(2);
    }
    int b = (value[index]) ? 1 : 0;
    return (byte)b;
  }
  
  // Set bit value at the specified index value to 0 or 1
  // depending on whether b is 0 or not
  public void setBit(int index, byte b) {
    if (index < 0 || index >= value.length) {
      System.out.println("setBit(): Invalid index " + index);
      System.exit(2);
    }
    value[index] = (b == 0) ? false : true;
  }
  
  // Return a new BitArray object that representing the exlusive-or of
  // the current object with o
  // The current object and o must haev the same length
  public BitArray exclusiveOR(BitArray o) {
    if (o.value.length != this.value.length) {
      System.out.println("exclusiveOR(): lengths differ");
      System.exit(3);
    }
    BitArray v = new BitArray();
    v.value = new boolean[this.value.length];
    for (int i = 0; i < v.value.length; i++)
      v.value[i] = o.value[i] ^ this.value[i]; // Boolean XOR
    return v;
  }
  
  // Transform bit string into a BitArray object
  public static BitArray getBitArrayFromBitString(String s) throws Exception {
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c != '0' && c != '1')
        throw new Exception("Input to getBitArrayFromBitString() must be a binary bit sequence");
    }
    boolean[] b = new boolean[s.length()];
    for (int i = 0; i < b.length; i++) {
      if (s.charAt(i) == '0')
        b[i] = false;
      else if (s.charAt(i) == '1')
        b[i] = true;
    }
    return new BitArray(b);
  }
  
  // Print bit sequence of this object
  public void print() {
    for (int i = 0; i < value.length; i++) 
      System.out.print((value[i]) ? 1 : 0);
  }
  
  // This main() method is only for testing purpose
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Usage: java BitArray string1 string2");
      System.exit(1);
    }
    if (args[0].length() != args[1].length()) {
      System.out.println("The two input strings must be of the same length");
      System.exit(2);
    }
    BitArray b1 = new BitArray(args[0]);
    BitArray b2 = new BitArray(args[1]);
    BitArray b3 = b1.exclusiveOR(b2);
    BitArray b4 = b3.exclusiveOR(b2);
    System.out.print("b1:               "); b1.print(); System.out.println();
    System.out.print("b2:               "); b2.print(); System.out.println();
    System.out.print("b1 xor b2:        "); b3.print(); System.out.println();
    System.out.print("b1 xor b2 xor b2: "); b4.print(); System.out.println();
    b1 = new BitArray((byte)-15);
    b2 = new BitArray((byte)14);
    b3 = b1.exclusiveOR(b2);
    b4 = b3.exclusiveOR(b2);
    System.out.print("b1:               "); b1.print(); System.out.println();
    System.out.print("b2:               "); b2.print(); System.out.println();
    System.out.print("b1 xor b2:        "); b3.print(); System.out.println();
    System.out.print("b1 xor b2 xor b2: "); b4.print(); System.out.println();
  }
}


// Copyright Prof. Lixin Tao, CSIS, Pace University, ltao@pace.edu, 9/5/2006
// This class implements Simplified DES described in file "C-SDES.pdf"
public class S_DES {

  private SecurityUtilities u = new SecurityUtilities();
  private BitArray key = null; // input key
  private BitArray[] derivedKeys = new BitArray[2];

  // Default constructor
  public S_DES() {}
  
  // Constructor that sets the key
  public S_DES(String keyBitString) throws Exception {
    setKey(keyBitString);
  }

  // Flag for debugging output
  // Assign "false" to avoid excessive printing
  static boolean isDebugging = false; 

  // Set a new key value, and generate the two new derived keys
  public void setKey(String keyBitString) throws Exception {
    // Make sure keyBitString is made of 10 binary bits
    if (keyBitString == null || keyBitString.trim().equals(""))
      throw new Exception("Empty key");
    if (keyBitString.length() > 10)
      keyBitString = keyBitString.substring(0, 9); // only use the first 10 bits
    else if (keyBitString.length() < 10) // if shorter than 10, at 0s at end
      keyBitString = keyBitString + "0000000000".substring(0, 10-keyBitString.length()-1);
    for (int i = 0; i < keyBitString.length(); i++) {
      char c = keyBitString.charAt(i);
      if (c != '0' && c != '1')
        throw new Exception("Key must be a binary bit sequence");
    }
    if (isDebugging) {
      System.out.println("setKey(" + keyBitString + ")");
    }
    this.key = BitArray.getBitArrayFromBitString(keyBitString);
    
    // This section generates 8-bit derived keys key1 and key2
    BitArray permutedKey = p10(key);
    if (isDebugging) {
      System.out.print("Permuted key: ");
      permutedKey.print(); 
      System.out.println();
    }
    BitArray shiftedKey1 = ls_1(permutedKey);
    if (isDebugging) {
      System.out.print("Left-shift key by 1 position:  ");
      shiftedKey1.print(); 
      System.out.println();
    }
    BitArray key1 = p8(shiftedKey1);
    if (isDebugging) {
      System.out.print("key1: "); 
      key1.print(); 
      System.out.println();
    }
    BitArray shiftedKey2 = ls_1(ls_1(shiftedKey1));
    if (isDebugging) {
      System.out.print("Left-shift key by 2 positions: ");
      shiftedKey2.print(); 
      System.out.println();
    }
    BitArray key2 = p8(shiftedKey2);
    if (isDebugging) {
      System.out.print("key2: "); 
      key2.print(); 
      System.out.println();
    }
    this.derivedKeys[0] = key1;
    this.derivedKeys[1] = key2;
  }
  
  // Encrypt/decrypt the input byte b depending on whether
  // isDecoding is false, and return the resulting byte
  public byte desTransform(BitArray b, boolean isDecoding) {
    // Decide the key order depending on it is for encoding or decoding
    BitArray[] keys = new BitArray[2];
    if (isDecoding) {
      keys[0] = derivedKeys[1];
      keys[1] = derivedKeys[0];
    }
    else {
      keys[0] = derivedKeys[0];
      keys[1] = derivedKeys[1];
    }
      
    if (isDebugging) {
      if (isDecoding)
        System.out.print("Decode byte: "); 
      else
        System.out.print("Encode byte: "); 
      b.print(); 
      System.out.println();
    }
    b = ip(b);        
    if (isDebugging) {
      System.out.print("Apply IP: "); 
      b.print(); 
      System.out.println();
    }
    b = fk(b, keys[0]);
    b = sw(b);  
    if (isDebugging) {
      System.out.print("Apply SW: "); 
      b.print(); 
      System.out.println();  
    }
    b = fk(b, keys[1]);
    b = ip_1(b);  
    if (isDebugging) {
      System.out.print("Apply IP-1: "); b.print(); System.out.println();      
    } 
    byte outputByte = b.getBytes()[0]; 
    if (isDebugging) {
      System.out.print("Output byte: " ); 
      u.printByte(outputByte); 
      System.out.println();   
    }
    return outputByte;
  }
    
  // Encrypt/decrypt the input bytes in b1[] depending on whether
  // isDecoding is false, and return the resulting byte array
  public byte[] desTransform(byte[] b1, boolean isDecoding) {       
    byte[] b2 = new byte[b1.length];
    for (int i = 0; i < b1.length; i++) {
      BitArray b = new BitArray(b1[i]);
      b2[i] = desTransform(b, isDecoding); 
    }    
    return b2;
  }
    
  // Apply function f_k on input byte b with the specified key, 
  // and return the resulting bit sequence (byte)
  BitArray fk(BitArray b, BitArray key) {
    if (isDebugging) {
      System.out.print("Apply f_k on byte "); 
      b.print();
      System.out.print(" with key "); 
      key.print();
      System.out.println();
    }
    int[][] s0 = {{1, 0, 3, 2}, {3, 2, 1, 0}, {0, 2, 1, 3}, {3, 1, 3, 2}};
    int[][] s1 = {{0, 1, 2, 3}, {2, 0, 1, 3}, {3, 0, 1, 0}, {2, 1, 0, 3}};
    BitArray b_ep = ep(b);
    if (isDebugging) {
      System.out.print("Apply E/P: "); 
      b_ep.print(); 
      System.out.println();
    }
    BitArray xor = b_ep.exclusiveOR(key);
    if (isDebugging) {
      System.out.print("E/P result XOR with Key: "); 
      xor.print(); 
      System.out.println();
    }
    int s0_row = intValue(xor.getBit(0), xor.getBit(3));
    int s0_column = intValue(xor.getBit(1), xor.getBit(2));
    int s1_row = intValue(xor.getBit(4), xor.getBit(7));
    int s1_column = intValue(xor.getBit(5), xor.getBit(6));
    int leftValue = s0[s0_row][s0_column];
    int rightValue = s1[s1_row][s1_column];
    BitArray b_combined = combineLeftRight(leftValue, rightValue);
    if (isDebugging) {
      System.out.print("S-box output: "); 
      b_combined.print(); 
      System.out.println();
    }
    BitArray b_p4 = p4(b_combined);
    if (isDebugging) {
      System.out.print("Apply P4: "); 
      b_p4.print(); 
      System.out.println();
    }
    BitArray left = b.range(0,3); // a copy of left 4 bits
    BitArray leftXorF = left.exclusiveOR(b_p4);
    // copy new left back into b
    for (int i = 0; i < 4; i++)
      b.setBit(i, leftXorF.getBit(i));
    if (isDebugging) {
      System.out.print("F_key output: "); 
      b.print(); 
      System.out.println(); 
    } 
    return b;
  }
  
  // Apply P4 permutation on b_combined and return the result
  BitArray p4(BitArray b_combined) {
    int[] P4 = {1, 3, 2, 0};
    // create a bit sequence with value 0000
    BitArray b_p4 = new BitArray(4, (byte)0);
    for (int i = 0; i < 4; i++)
      b_p4.setBit(i, b_combined.getBit(P4[i]));
    return b_p4;
  }
  
  // Combine the output values of S0 and S1 to return a single value
  BitArray combineLeftRight(int leftValue, int rightValue) {
    BitArray v = new BitArray(4, (byte)0); // reserve space
    switch (leftValue) {
      case 0: v.setBit(0, (byte)0); v.setBit(1, (byte)0); break;
      case 1: v.setBit(0, (byte)0); v.setBit(1, (byte)1); break;
      case 2: v.setBit(0, (byte)1); v.setBit(1, (byte)0); break;
      case 3: v.setBit(0, (byte)1); v.setBit(1, (byte)1); break;
    }
    switch (rightValue) {
      case 0: v.setBit(2, (byte)0); v.setBit(3, (byte)0); break;
      case 1: v.setBit(2, (byte)0); v.setBit(3, (byte)1); break;
      case 2: v.setBit(2, (byte)1); v.setBit(3, (byte)0); break;
      case 3: v.setBit(2, (byte)1); v.setBit(3, (byte)1); break;
    }
    return v;
  }
  
  // Return an integer represented by the binary bits (b1, b2)
  int intValue(byte b1, byte b2) {
    int v = 0;
    if (b2 != 0) 
      v = 1;
    if (b1 != 0)
      v += 2;
    return v;
  }
  
  // Apply the E/P transformation on the right-half of b and return the result
  BitArray ep(BitArray b) {
    int[] m = {7, 4, 5, 6, 5, 6, 7, 4};
    BitArray b_ep = new BitArray(b); 
    for (int i = 0; i < 8; i++)
      b_ep.setBit(i, b.getBit(m[i]));
    return b_ep;
  }
  
  // Swap the left and right halves of bit sequence b, and return the result
  BitArray sw(BitArray b) {
    int[] SW = {4, 5, 6, 7, 0, 1, 2, 3};
    BitArray b1 = new BitArray(b);
    for (int i = 0; i < 8; i++)
      b1.setBit(i, b.getBit(SW[i]));
    return b1;
  }
  
  // Apply the initial permutation IP on bit sequence b and return the result
  BitArray ip(BitArray b) {
    int[] IP = {1, 5, 2, 0, 3, 7, 4, 6};
    BitArray b1 = new BitArray(b);
    for (int i = 0; i < 8; i++)
      b1.setBit(i, b.getBit(IP[i]));
    return b1;
  }

  // Apply the inverse of initial permutation IP on bit sequence b and 
  // return the result  
  BitArray ip_1(BitArray b) {
    int[] IP_1 = {3, 0, 2, 4, 6, 1, 7, 5};
    BitArray b1 = new BitArray(b);
    for (int i = 0; i < 8; i++)
      b1.setBit(i, b.getBit(IP_1[i]));
    return b1;
  }
  
  // Apply P10 permutation on key and return the result
  BitArray p10(BitArray key) {
    int[] P10 = {2, 4, 1, 6, 3, 9, 0, 8, 7, 5};
    BitArray permutedKey = new BitArray(key);
    for (int i = 0; i < 10; i++) 
      permutedKey.setBit(i, key.getBit(P10[i]));
    return permutedKey;
  }
  
  // Apply circular left-shift LS-1 on key by one position and 
  // return the result
  BitArray ls_1(BitArray key) {
    int[] LS_1 = {1, 2, 3, 4, 0, 6, 7, 8, 9, 5};
    BitArray shiftedKey = new BitArray(key);
    for (int i = 0; i < 10; i++)
      shiftedKey.setBit(i, key.getBit(LS_1[i]));
    return shiftedKey;
  }
  
  // Apply P8 permutation on key10bits and return the result
  BitArray p8(BitArray key10bits) {
    int[] P8 = {5, 2, 6, 3, 7, 4, 9, 8};
    BitArray key8bits = new BitArray(8, (byte)0); 
    for (int i = 0; i < 8; i++)
      key8bits.setBit(i, key10bits.getBit(P8[i]));
    return key8bits;
  }
}
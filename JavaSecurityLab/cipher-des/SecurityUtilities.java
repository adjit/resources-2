// Copyright Prof. Lixin Tao, CSIS, Pace University, ltao@pace.edu, 9/5/2006
// This class contains utility methods used by other classes for major algorithms
// so they don't need be repeated in those classes. You don't need to understand
// this class. But you may want to read the comment for the methods to see their
// function.
import java.util.*;
import java.io.*;

class SecurityUtilities {

  // For each input byte in b1[], exlusive-or it with a corresponding key in 
  // a key sequence to generate an output byte in b2[]
  public byte[] randTransform(byte[] b1, long key) {
    // Create a random number generator with key as its seed.
    // All Java random number generators created with the same seed will
    // always generate the same fixed sequence of (psudo-random) numbers. 
    // We take them as a sequence of keys derived by the input augument key.
    Random randomGenerator = new Random(key);
    // Generate random bytes in r[] as derived keys
    byte[] r = new byte[b1.length];
    randomGenerator.nextBytes(r);
    byte[] b2 = new byte[b1.length];
    for (int i = 0; i < b1.length; i++)
      b2[i] = (byte)(b1[i] ^ r[i]);  // "^" is the exlusive-or operator
      // Let a and b be bits or Boolean values. (a ^ b) is 1 or true
      // if and only if a and b have different value.
      // If a and b are bytes, exclusive-or is applied to corresponding bits.
    return b2;
  }
  
  // Convert a string s into an array of bytes and return it
  public byte[] stringToBytes(String s) {
    byte[] b = new byte[s.length()];
    for (int i = 0; i < s.length(); i++)
      b[i] = (byte)s.charAt(i);
    return b;
  }
  
  // Return an array of characters converted from bytes in b[]
  public char[] bytesToChars(byte[] b) {
    char[] c = new char[b.length];
    for (int i = 0; i < b.length; i++)
      c[i] = (char)b[i];
    return c;
  }
  
  // Print bit strings for each byte in byteArray[], 5 strings on each line
  public void printBytes(byte[] byteArray) {
    int i = 0;
    int length = byteArray.length;
    while (i < length) {
      printByte(byteArray[i]);
      System.out.print("  ");
      i = i + 1;
      if ((i % 5) == 0)
        System.out.println();
    }
    if ((i % 5) != 0)
      System.out.println();
  }
  
  // Print bit string for byte b.
  public void printByte(byte b) {
    if (b < 0)
      System.out.print(1);
    else
      System.out.print(0);
    byte mask = (byte)0x40; // 0100 0000
    for (int i = 0; i < 7; i++) {
      if ((b & mask) > 0)  // &: bit-wise logical AND
        System.out.print(1);
      else 
        System.out.print(0);
      mask = (byte)(mask >> 1); // shift 1 to right by one position
    }
  }
  
  // Return an array of bytes from the file with name in inputFilName
  public byte[] readBytesFromFile(String inputFileName) {
    DataInputStream dis = null;
    int length = 0;
    byte[] b = null;
    try {
      File inputFile = new File(inputFileName);
      length = (int)inputFile.length();
      dis = new DataInputStream(new FileInputStream(inputFile));
      b = new byte[length];
      for (int i = 0; i < length; i++)
        b[i] = dis.readByte();
    }
    catch (Exception e) {
      System.out.println(e);
    }
    finally {
      try {
        dis.close();
      }
      catch (Exception ioe) {}
    }
    return b;
  }
  
  // Write bytes in b[] into the file with name in outputFileName
  public void writeBytesIntoFile(byte[] b, String outputFileName) {
    DataOutputStream dos = null;
    try {
      dos = new DataOutputStream(new FileOutputStream(new File(outputFileName)));
      for (int i = 0; i < b.length; i++)
        dos.writeByte(b[i]);
    }
    catch (Exception e) {
      System.out.println(e);
    }
    finally {
      try {
        dos.close();
      }
      catch (Exception ioe) {}
    }
  }
}
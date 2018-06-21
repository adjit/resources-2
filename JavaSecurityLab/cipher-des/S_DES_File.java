// Copyright Prof. Lixin Tao, CSIS, Pace University, ltao@pace.edu, 9/5/2006
// This class encodes or decodes an input file with Simple DES and generates 
// an output file
// To compile this program, use "javac S_DES_File.java". Make sure you have 
// files S_DES_File.java, S_DES.java, BitArray.java and SecurityUtilities.java
// in the current directory.
// To encrypt the plaintext in file "plaintext" (text or binary) into a binary file 
// "encrypted" with key "1010101010", which is a sequence of 10 or less 
// binary bits, use "java S_DES_File plaintext encrypted 1010101010"
// To decrypt the resulting file "encrypted" back to plaintext file 
// "decrypted", use 
// "java S_DES_File encrypted decrypted 1010101010 decode"
public class S_DES_File {
  public static void main(String[] args) {
    String inputFileName = "", outputFileName = "";
    String key = null;
    boolean isDecoding = false;
    S_DES des = null;
    try {
      inputFileName = args[0];
      outputFileName = args[1];
      key = args[2];
      des = new S_DES(key);
    }
    catch (Exception e) {
      // System.out.println(e);
      System.out.println("usage: java S_DES_File inputFile outputFile key-bits [decode]");
      System.exit(1);
    }
    if (args.length > 3) 
      isDecoding = true;
    SecurityUtilities u = new SecurityUtilities();
    byte[] b1 = u.readBytesFromFile(inputFileName);
    byte[] b2 = des.desTransform(b1, isDecoding);
    u.writeBytesIntoFile(b2, outputFileName);
  }
}
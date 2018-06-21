import java.io.*;
import java.security.*;
import java.security.spec.*;

// Import a private key from a file, generate signature of the input data with the private key,
// and write out the signature in file "signature".
class GenerateSignature2 {
  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("Usage: java GenerateSignature2 privateKeyFile fileToSign");
      System.exit(-1);
    }
    // Import the encoded private key from file args[0]
    FileInputStream keyfis = new FileInputStream(args[0]);
    // create a byte array as large as the file contents
    byte[] encKey = new byte[keyfis.available()];  
    keyfis.read(encKey);
    keyfis.close();
    // use file contents to create a private key specification object
    PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(encKey);
    // create a key factory using algorithm DSA implemented by SUN
    KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
    // recover private key from its specification
    PrivateKey priv = keyFactory.generatePrivate(privKeySpec);
    // Create a Signature object and initialize it with algorithm SHA1withDSA
    // implemented by SUN.
    Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
    dsa.initSign(priv); // set the private key
    // Read in data, up to 1024 bytes a time, to generate fingerprint
    // args[1]: second command-line argument, data file name
    FileInputStream fis = new FileInputStream(args[1]); // open data file
    BufferedInputStream bufin = new BufferedInputStream(fis); // provide buffer for data input
    byte[] buffer = new byte[1024];
    int len;
    while (bufin.available() != 0) { // while there are still unread data
      len = bufin.read(buffer);      // read up to 1024 bytes, set actual length in len
      dsa.update(buffer, 0, len);    // apply hash to the recent data segment
    };
    bufin.close();
    // Generate a signature by encoding the fingerprint with the private key
    byte[] realSig = dsa.sign();    
    // Save the signature in file "signature"
    FileOutputStream sigfos = new FileOutputStream("signature");
    sigfos.write(realSig);
    sigfos.close();
  };
}


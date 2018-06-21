import java.io.*;
import java.security.*;
import java.security.spec.*;

// Import public key and signature from the files, and use them to authenticate 
// the author and validate the contents of the data file
class VerifySignature {
  public static void main(String[] args) throws Exception {
    if (args.length != 3) {
      System.out.println("Usage: java VerifySignature publicKeyFile signatureFile dataFile");
      System.exit(-1);
    }
    // import encoded public key
    FileInputStream keyfis = new FileInputStream(args[0]);
    // create a byte array as large as the file contents
    byte[] encKey = new byte[keyfis.available()];  
    keyfis.read(encKey);
    keyfis.close();
    // use file contents to create a public key specification object
    X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
    // create a key factory using algorithm DSA implemented by SUN
    KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
    // recover public key from its specification
    PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
    // input the signature bytes
    FileInputStream sigfis = new FileInputStream(args[1]);
    byte[] sigToVerify = new byte[sigfis.available()]; 
    sigfis.read(sigToVerify );
    sigfis.close();
    // create a Signature object using algorithm SHA1withDSA implemented by SUN
    Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
    // initialize the Signature object with the public key
    sig.initVerify(pubKey);
    // Read in data, up to 1024 bytes a time, to generate fingerprint
    // args[2]: third command-line argument, data file name
    FileInputStream datafis = new FileInputStream(args[2]); // open data file
    BufferedInputStream bufin = new BufferedInputStream(datafis); // provide buffer for data input
    byte[] buffer = new byte[1024]; 
    int len;
    while (bufin.available() != 0) { // while there are still unread data
      len = bufin.read(buffer);      // read up to 1024 bytes, set actual length in len
      sig.update(buffer, 0, len);    // apply hash to the recent data segment
    };
    bufin.close();
    // decode the input signature into fingerprint with the public key,
    // compare it with the fingerprint just created above.
    boolean verifies = sig.verify(sigToVerify);
    System.out.println("signature verifies: " + verifies);
  }
}



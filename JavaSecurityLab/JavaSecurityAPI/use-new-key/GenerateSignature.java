import java.io.*;
import java.security.*;

// Generate a pair of public/private keys, generate signature of the input data with the private key,
// write out the signature and the public key in files "signature" and "public-key".
class GenerateSignature {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: java GenerateSignature fileToSign");
      System.exit(-1);
    }
    // Generate a key pair
    // set key generator algorithm DSA implemented by SUN
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN"); 
    // set random number algorithm SHA1PRNG implemented by SUN
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN"); 
    keyGen.initialize(1024, random); // set key length be 1024 bytes
    KeyPair pair = keyGen.generateKeyPair(); // generate key pair
    PrivateKey priv = pair.getPrivate();
    PublicKey pub = pair.getPublic();
    // Create a Signature object and initialize it with algorithm SHA1withDSA
    // implemented by SUN.
    Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
    dsa.initSign(priv); // set the private key
    // Read in data, up to 1024 bytes a time, to generate fingerprint
    // args[0]: first command-line argument, data file name
    FileInputStream fis = new FileInputStream(args[0]); // open data file
    BufferedInputStream bufin = new BufferedInputStream(fis); // provide buffer for data input
    byte[] buffer = new byte[1024];
    while (bufin.available() != 0) { // while there are still unread data
      int len = bufin.read(buffer);  // read up to 1024 bytes, set actual length in len
      dsa.update(buffer, 0, len);    // apply hash to the recent data segment
    };
    bufin.close();
    // Generate a signature by encoding the fingerprint with the private key
    byte[] realSig = dsa.sign();    
    // Save the signature in file "signature"
    FileOutputStream sigfos = new FileOutputStream("signature");
    sigfos.write(realSig);
    sigfos.close();
    // Save the public key in file "public-key"
    byte[] key = pub.getEncoded();
    FileOutputStream keyfos = new FileOutputStream("public-key");
    keyfos.write(key);
    keyfos.close();
  };
}


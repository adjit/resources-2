import java.io.*;
import java.security.*;
import java.security.spec.*;

// Import a private key from a keystore, generate signature of the input data with the private key,
// and write out the signature in file "signature".
class GenerateSignature3 {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: java GenerateSignature3 fileToSign");
      System.exit(-1);
    }
    // Import the private key and certificate with alias "PaceKey" from keystore "../../PaceKeystore"
    KeyStore ks = KeyStore.getInstance("JKS"); // get an empty keystore object
    FileInputStream ksfis = new FileInputStream("../../PaceKeystore"); // open keystore file
    BufferedInputStream ksbufin = new BufferedInputStream(ksfis);  // load keystore contents in buffer
    // load keystore contents into the keystore object; supply keystore password
    ks.load(ksbufin, "PaceUniversity".toCharArray()); 
    // retrieve private key for alias "PaceKey" with key password "Seidenberg"
    PrivateKey priv = (PrivateKey)ks.getKey("PaceKey", "Seidenberg".toCharArray());
    // Retreive the corresponding certificate and save it in a file
    java.security.cert.Certificate cert = ks.getCertificate("PaceKey");
    byte[] encodedCert = cert.getEncoded(); // encode the certificate for I/O
    // save the certificate in a file named "certificate" */
    FileOutputStream certfos = new FileOutputStream("certificate");
    certfos.write(encodedCert);
    certfos.close();    
    // Create a Signature object and initialize it with algorithm SHA1withDSA
    // implemented by SUN.
    Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
    dsa.initSign(priv); // set the private key
    // Read in data, up to 1024 bytes a time, to generate fingerprint
    // args[0]: first command-line argument, data file name
    FileInputStream fis = new FileInputStream(args[0]); // open data file
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


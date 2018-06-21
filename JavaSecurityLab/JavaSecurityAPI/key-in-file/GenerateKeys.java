import java.io.*;
import java.security.*;

// Generate a pair of public/private keys, and save them in files "public-key" and "private-key".
class GenerateKeys {
  public static void main(String[] args) throws Exception {
    // Generate a key pair
    // set key generator algorithm DSA implemented by SUN
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN"); 
    // set random number algorithm SHA1PRNG implemented by SUN
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN"); 
    keyGen.initialize(1024, random); // set key length be 1024 bytes
    KeyPair pair = keyGen.generateKeyPair(); // generate key pair
    PrivateKey priv = pair.getPrivate();
    PublicKey pub = pair.getPublic();
    // Save the public key in file "public-key"
    byte[] key = pub.getEncoded();
    FileOutputStream keyfos = new FileOutputStream("public-key");
    keyfos.write(key);
    keyfos.close();
    // Save the private key in file "private-key"
    key = priv.getEncoded();
    keyfos = new FileOutputStream("private-key");
    keyfos.write(key);
    keyfos.close();
  };
}
// Print values of selected Java system properties
class GetProperties {
  public static void main(String[] args) {
    String s;
    try {
      s = System.getProperty("os.name", "not specified");
      System.out.println("  Your operating system is: " + s);
      s = System.getProperty("java.version", "not specified");
      System.out.println("  Your Java version is: " + s);
      s = System.getProperty("user.home", "not specified");
      System.out.println("  Your user home directory is: " + s);
      s = System.getProperty("java.home", "not specified");
      System.out.println("  Your JRE installation directory is: " + s);
      s = System.getProperty("java.ext.dirs", "not specified");
      System.out.println("  Your Java extension directories are: " + s);
    } catch (Exception e) {
      System.err.println("Caught exception: " + e);
    }
  }
}


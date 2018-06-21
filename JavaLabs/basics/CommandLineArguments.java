public class CommandLineArguments {
  public static void main(String[] args) {
    for (int i = 0; i < args.length; i++) {
      System.out.println(args[i]);
    }
    // Sorting strings in args[]
    java.util.Arrays.sort(args);
    int j = 0;
    while (j < args.length) {
      System.out.println(args[j]);
      j++;
    }
  }
}
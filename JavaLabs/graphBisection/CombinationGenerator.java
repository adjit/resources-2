// Prof. Lixin Tao, Pace University, September 9, 2003

// A class to generate all r combination sets of base set
// {0, ..., n-1} in lexicographic order, where n and r are
// positive integers, and r <= n
public class CombinationGenerator {
  private int n;                 // Size of the base set
  private int r;                 // Size of a combination set
  private boolean isFirstTime;   // Flag, true iff nextCombination() is
                                 // called the first time
  private int p[];               // Buffer for returning the next combination

  // Constructor for generating r combinations out of {0, ..., n-1}
  public CombinationGenerator(int n, int r) {
    if (r > n || n < 1) 
      throw new IllegalArgumentException();
    this.n = n;
    this.r = r;
    isFirstTime = true;
    p = new int[r];
  }

  // return true iff there are more combinations to return
  public boolean hasMore() {
    // return false iff p[] = {n-r, n-(r-1), ..., n-1} (last combination)
    int i = r - 1;
    while ((i >= 0) && (p[i] == n - r + i)) 
      i--;
    return (i >= 0);
  }

  // Generate next combination in lexicographic order
  // Algorithm from Rosen' Discrete Mathematics, p. 286
  public int[] nextCombination() {
    if (isFirstTime) {
      isFirstTime = false;
      for (int i = 0; i < r; i++)  // Generate the smallest combination
        p[i] = i;
      return p;
    }
    int i = r - 1;
    while (p[i] == n - r + i) 
      i--;
    p[i] = p[i] + 1;
    for (int j = i + 1; j < r; j++) 
      p[j] = p[i] + j - i;
    return p;
  }

  // Normally not called; provide a demo of how to use this class
  public static void main(String[] args) {
    String[] elements = {"a", "b", "c", "d", "e", "f", "g"};
    int[] indices;
    // Generate all 3-combinations of {"a", "b", "c", "d", "e", "f", "g"}
    CombinationGenerator x = new CombinationGenerator(elements.length, 3);
    StringBuffer combination;
    int count = 0;   // count the number of generated combinations
    while (x.hasMore()) {
      count++;
      combination = new StringBuffer();
      indices = x.nextCombination();
      for (int i = 0; i < indices.length; i++)
        combination.append(elements[indices[i]]);
      System.out.println(combination.toString ());
    }
    System.out.println(count + " combinations have been generated");
  }
}


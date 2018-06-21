// (C) Dr. Lixin Tao, Pace University
// This program shows two ways to split a string into tokens, 
// Tokens are longest substrings of a string that don't contain white space
import java.util.StringTokenizer;

public class StringTokenizerDemo {

  public static void main(String args[]) {
    String words = "a b c1 c2 d1 d2 d3";
    System.out.println("Approach 1: use class StringTokenizer");
    StringTokenizer st = new StringTokenizer(words);
    // A StringTokenizer allows you read tokens from left to right, one a time
    while (st.hasMoreTokens()) {  // Do we have more tokens to return?
       System.out.println(st.nextToken()); // return and print the next token in the string
    }
    System.out.println("Approach 2: use method String.split() (preferred approach)");
    // "\s" is the regular expression for white space; "\\s" is used because "\" is
    // an escape character.
    // change "\\s" to change the separation character between tokens
    String[] token = words.split("\\s+"); 
    for (int i = 0; i < token.length; i++)
      System.out.println(token[i]);
  }
}
import java.util.Stack;

public class BalancedScopes {
  String good = "{()[()()]}(())";
  String bad = "{[(])}";
  
  public static void main(String[] args) {
    new BalancedScopes();
  }
  
  /** Testing for balanced scopes with {}, [], and () */
  public BalancedScopes() {
    if (testBalance(good)) 
      System.out.println("\"" + good + "\" has balanced scopes.");
    else
      System.out.println("\"" + good + "\" does not have balanced scopes.");
      
    if (testBalance(bad)) 
      System.out.println("\"" + bad + "\" has balanced scopes.");
    else
      System.out.println("\"" + bad + "\" does not have balanced scopes.");
  }
  
  boolean testBalance(String v) {
    Stack<Character> s = new Stack<Character>();
    for (int i = 0; i < v.length(); i++) {
      char c = v.charAt(i); 
      if ((c == '{') || (c == '[') || (c == '(')) {
        s.push(c);
        continue;
      }
      char topChar = s.pop();
      if ((c == '}' && topChar != '{') ||
          (c == ']' && topChar != '[') ||
          (c == ')' && topChar != '(') 
         )
        return false;
    }
    return true;
  }
}

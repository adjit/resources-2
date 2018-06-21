public class MethodCallReturnOrder {
  public static void main(String[] args) {
    System.out.println("Enter main()");
    a();
    System.out.println("Leave main()");
  }

  static void a() {
    System.out.println("Enter a()");
    b();
    System.out.println("Leave a()");
  }

  static void b() {
    System.out.println("Enter b()");
    System.out.println("Leave b()");
  }
}
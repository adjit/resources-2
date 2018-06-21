// Dr. Lixin Tao, Pace University
// This program shows how to use class Random 
import java.util.Random;

public class RandomNumbers
{
  public static void main(String[] args)
  {
    Random r = new Random();
    // generate integers between 1 and 10 inclusive
    for (int i = 0; i < 5; i++)
    {
      int num = r.nextInt(10) + 1;
      System.out.print(num + " ");
    }
    System.out.println();
    // generate doubles between 0 and 1
    for (int i = 0; i < 5; i++)
    {
      double num = r.nextDouble();
      System.out.print(num + " ");
    }
  }
}
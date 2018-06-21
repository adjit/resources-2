public class TestThreads {
  public static void main(String[] args)  {
    // Create threads
    PrintChar printA = new PrintChar('a', 100);
    PrintChar printB = new PrintChar('b', 100);
    Thread print100 = new Thread(new PrintNum(100));
    // Start threads
    print100.start();
    printA.start();
    printB.start();
  }
}

class PrintChar extends Thread {
  private char charToPrint;  // The character to print
  private int times;  // The times to repeat

  public PrintChar(char c, int t)  {
    charToPrint = c;
    times = t;
  }

  public void run() {
    for (int i=0; i < times; i++) {
      System.out.print(charToPrint);
      try { Thread.sleep(5); } catch(Exception e){}
    }
  }
}

class PrintNum implements Runnable {
  private int lastNum;

  // Construct a thread for print 1, 2, ... i
  public PrintNum(int n)  {
    lastNum = n;
  }
  
  public void run() {
    for (int i=1; i <= lastNum; i++) {
      System.out.print(" " + i);
      try { Thread.sleep(5); } catch(Exception e){}
    }
  }
}




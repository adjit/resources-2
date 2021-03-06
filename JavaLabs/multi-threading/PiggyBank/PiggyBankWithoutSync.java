
public class PiggyBankWithoutSync {
  private PiggyBank bank = new PiggyBank();
  private Thread[] thread = new Thread[100];

  public static void main(String[] args) {
    PiggyBankWithoutSync test = new PiggyBankWithoutSync();
    System.out.println("Final balance is " + test.bank.getBalance());
  }

  public PiggyBankWithoutSync() {
    ThreadGroup g = new ThreadGroup("group"); // generate a new thread group
    boolean done = false;

    for (int i = 0; i < 100; i++) { 
      // create a new thread with label "t", and add the thread to thread group g
      thread[i] = new Thread(g, new AddAPennyThread(), "t");
      thread[i].start();
    }
    // wait for all the 100 new threads terminate
    while (!done)
      if (g.activeCount() == 0)  // if there are active threads, continue looping
        done = true;
  }

  class AddAPennyThread extends Thread {
    public void run() {
      int newBalance = bank.getBalance(); // retrieve the old balance
      newBalance++;  // add a penny to the old balance
      
      try {
        Thread.sleep(5); // purposely add a delay before writing back balance
      }
      catch (InterruptedException e) { // a sleeping thread could be interrupted
        System.out.println(e);         // by another thread
      }
      bank.setBalance(newBalance);
    }
  }
}

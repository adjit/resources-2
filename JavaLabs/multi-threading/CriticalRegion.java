class BankAccount {
	private int balance = 0;

	public int getBalance() { return balance;	}
		public void setBalance(int balance) { this.balance = balance; }
	}
	
	public class CriticalRegion {
	  private BankAccount account = new BankAccount();
	  private Thread[] thread = new Thread[100];
	
	  public static void main(String[] args) {
	    CriticalRegion test = new CriticalRegion();
	    System.out.println("Final balance is " + test.account.getBalance());
	  }
	
	  public CriticalRegion() {
	    // generate a new thread group
	    ThreadGroup g = new ThreadGroup("group");  
	    boolean done = false;
	
	    for (int i = 0; i < 100; i++) {
	      // create a new thread and add the thread to thread group g
	      thread[i] = new Thread(g, new AddADollarThread());
	      thread[i].start();
	    }
	    // wait for all the 100 new threads terminate
	    while (!done) {
	      if (g.activeCount() == 0)  // if there are active threads, continue 
	        done = true;             // looping
	    }
	  }
	
	  /* synchronized */ void deposit(int sum) {
	    int newBalance = account.getBalance(); // retrieve the old balance
	    newBalance += sum;  // add sum to the old balance
	   
	    try {
	      Thread.sleep(1);  // purposely add a delay before writing 
	                        // back balance
	    }
	    catch (InterruptedException e) { // a sleeping thread could be 
	      System.out.println(e);         // interrupted by another thread
	    }
	    account.setBalance(newBalance);
	  }
	
	  class AddADollarThread extends Thread {
	    public void run() {
	      deposit(1);
	  }
	}
}

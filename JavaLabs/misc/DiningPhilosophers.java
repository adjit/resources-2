// @Prof. Lixin Tao, Pace University, 2002
import java.util.*;

public class DiningPhilosophers {
  public static void main(String[] args) {
    Monitor m = new Monitor();
    for (int i = 0; i < 5; i++) {
      Philosopher p = new Philosopher(i, m);
      p.start();
    }
  }
}

class Philosopher extends Thread {
  private Monitor monitor;
  private int me;
  private Random r = new Random();

  public Philosopher(int i, Monitor m) {
    me = i;
    monitor = m;
  }
  
  public void run() {
    while (true) {
      try {
        monitor.pickup(me); 
        Thread.sleep(r.nextInt(1000)); // eat for random amount of milliseconds
        monitor.putdown(me);
        Thread.sleep(r.nextInt(1000)); // think for random amount of milliseconds       
      }
      catch (InterruptedException e) {}     
    }
  }
}

class Monitor {
  private final int thinking = 0;
  private final int hungry   = 1;
  private final int eating   = 2;
  private int[] state = new int[5];

  public Monitor() {
    for (int i = 0; i < 5; i++) 
      state[i] = thinking;
  }

  synchronized public void pickup(int i)
      throws InterruptedException {
    state[i] = hungry;
    test(i);
    while (state[i] != eating)
      wait();
    System.out.print(i + " start, ");   
  }

  synchronized public void putdown(int i) {
    state[i] = thinking;
    System.out.print(i + " stop, ");   
    test((i+4) % 5);
    test((i+1) % 5);
  }

  private void test(int i) {
    if ((state[(i+4) % 5] != eating) &&  // Is the left neighbor eating?
         (state[i] == hungry) &&
         (state[(i+1) % 5] != eating)) { // Is the right neighbor eating?
      state[i] = eating;
      notifyAll();
    }
  }
}
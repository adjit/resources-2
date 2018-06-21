package lixinObservable;
// (C) Prof. Lixin Tao, Pace University, February 2005
// Class MyObservable simulates class java.util.Observable for better understanding
// the logic of the event-driven programming paradigm.

import java.util.Vector;
import java.util.Enumeration;

public class MyObservable {
  private Vector observers = new Vector(); // maintain a list of registered observers
  private boolean hasChanged = false;
  
  // Adds an observer to the set of observers for this object, provided that it is 
  // not the same as some observer already in the set.
  public void addObserver(MyObserver o) {
    if (!observers.contains(o))
      observers.add(o);
  }
  
  // Deletes an observer from the set of observers of this object.
  public void deleteObserver(MyObserver o) {
    observers.remove(o);
  }
  
  // Clears the observer list so that this object no longer has any observers.
  public void deleteObservers() {
    observers.clear();
  }
  
  // Returns the number of observers of this Observable object.
  public int countObservers() {
    return observers.size();
  }
  
  // Indicate that this object has no longer changed, or that it has already 
  // notified all of its observers of its most recent change, so that the hasChanged 
  // method will now return false.
  protected void clearChanged() {
    hasChanged = false;
  }
  
  // Tests if this object has changed.
  public boolean hasChanged() {
    return hasChanged;
  }
  
  // If this object has changed, as indicated by the hasChanged method, 
  // then notify all of its observers and then call the clearChanged method 
  // to indicate that this object has no longer changed.
  public void notifyObservers() {
    notifyObservers(null);
  }
  
  // If this object has changed, as indicated by the hasChanged method, then 
  // notify all of its observers and then call the clearChanged method to 
  // indicate that this object has no longer changed.
  public void notifyObservers(Object arg) {
    if (!hasChanged()) return;
    for (Enumeration e = observers.elements(); e.hasMoreElements();)
      ((MyObserver)e.nextElement()).update(this, arg);
    clearChanged();
  }
  
  //  Marks this Observable object as having been changed; 
  //  the hasChanged method will now return true.
  protected void setChanged() {
    hasChanged = true;
  }
}
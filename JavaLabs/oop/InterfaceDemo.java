// (C) Dr. Lixin Tao, Pace University
//  This program presents some features of Java interface.
//  Java interface consists of a set of public method signatures with no implementations.
//  A class implementing an interface means the class promises to implement 
//  all methods declared in the interface.
//  The name of an interface can be used to declare variables that can hold references
//  to any objects instantiated from classes that implement the same interface; only
//  the methods listed in the interface can be called from such variables.
//  Interface is used for systematically processing objects of mixed classes
public class InterfaceDemo {
  public static void main(String arg[]) {
    Pet pets[] = new Pet[2]; // creates an array of 3 Pet references
    pets[0] = new Cat("Pussy");    // since Cat implements interface Pet
    pets[1] = new Dog("Champion"); // since Dog implements interface Pet
    
    for (int i = 0; i < pets.length; i++)
      pets[i].talk();
      
    Cat c = (Cat)pets[0];  // cast a Pet type object to a Cat object
    c.hello();
    // pets[0].hello();    // this line must be commented off; pets[0] has type Pet,
                           // only methods listed in interface Pet can be called from it.
  }
}

interface Pet {
  void talk();   // all pets must implement method talk()
}

class Cat implements Pet {
  String name;

  public Cat(String name) {
    this.name = name;
  }
  
  public void talk() {  // this method is required by the interface Pet
    System.out.println("I am cat " + name + ", meow.");
  }
  
  public void hello() { // this is a method not required by interface Pet
    System.out.println("Cat " + name + " says hello to you.");
  }  
}

class Dog implements Pet {
  String name;

  public Dog(String name) {
    this.name = name;
  }
  
  public void talk() {  // this method is required by the interface Pet
    System.out.println("I am dog " + name + ", wow.");
  }
  
  public void hello() { // this is a method not required by interface Pet
    System.out.println("Dog " + name + " says hello to you.");
  }  
}
// (C) Dr. Lixin Tao, Pace University
//  This program presents some features of Java inheritance and polymorphism.
//  Java interface consists of a set of public method signatures with no implementations.
//  An abstract method (a method signature) in a class promises that it must be 
//  implemented by all of its concrete derived classes.
//  A class containing one or more abstract methods must be declared as an abstract class. 
//  An abstract base class can be used to declare reference variables, which can hold
//  references of objects of any class derived from the base class; only the methods
//  of the base class can be called from such reference variables; if there are multiple
//  implementations for a method, the implementation closer to the object's class 
//  (bottom of inheritance chain) will be invoked. This is so called polymorphism.

public class AbstractMethods {
  public static void main(String arg[]) {
    Pet pets[] = new Pet[2]; // creates an array of 3 Pet references
    pets[0] = new Cat("Pussy");    // since Cat is a derived class of Pet
    pets[1] = new Dog("Champion"); // since Dog is a derived class of Pet
    
    for (int i = 0; i < pets.length; i++)
      pets[i].talk();
      
    Cat c = (Cat)pets[0];  // cast a Pet type object to a Cat object
    c.hello();
    // pets[0].hello();    // this line must be commented off; pets[0] has type Pet,
                           // only methods listed in class Pet can be called from it.
    c.helloFromParent();   // indirectly call a method of the base class
  }
}

abstract class Pet {
  abstract public void talk();   // all pets must implement method talk()
  public void hello() {
    System.out.println("Pet parent says hello to you.");
  }
}

class Cat extends Pet {
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
  
  public void helloFromParent() {
    super.hello();  // call hello() of the super (base) class
  }
}

class Dog extends Pet {
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
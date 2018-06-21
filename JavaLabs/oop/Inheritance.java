public class Inheritance {
  public static void main(String[] args) {
    Cat c = new Cat("Cathy");
    Dog d = new Dog("Champion");
    c.talk();
    d.talk();
    System.out.println("I am " + c + ", my name is " + c.getName());
    System.out.println("I am " + d + ", my name is " + d.getName());
    // polymorphism demo: the version of toString() used is the one 
    // declared in the subclasses
    Pet[] p = new Pet[2];
    p[0] = c;
    p[1] = d;
    for (int i = 0; i < p.length; i++)
      System.out.println("I am " + p[i] + ", my name is " + p[i].getName());
    // p[0].talk(); // this will fail, since Pet has no method talk()
    for (int i = 0; i < p.length; i++) {
      if (p[i] instanceof Cat) // Is p[i] an instance of Cat?
        ((Cat)p[i]).talk();    // Cast p[i] back into a reference for Cat
    }
  }
}

class Pet {
  private String name;
  
  public Pet(String n) {
    this.name = n;
    System.out.println("Pet(" + n + ")");
  }
  
  public String getName() {
    return name;
  }
  
  public String toString() {
    return "Pet(" + name + ")";
  }
}

class Cat extends Pet {
  public Cat(String name) {
    super(name);  // call superclass constructor
    System.out.println(this + ", I am also " + super.toString());
  }

  public void talk() {
    System.out.println("I like fish.");
  }
  
  public String toString() { // override toString() method in superclass
    return "Cat(" + getName() + ")";
  }
}

class Dog extends Pet {
  public Dog(String name) {
    super(name);  // call superclass constructor
    System.out.println(this + ", I am also " + super.toString());
  }

  public void talk() {
    System.out.println("I like sports.");
  }
  
  public String toString() { // override toString() method in superclass
    return "Dog(" + getName() + ")";
  }
}
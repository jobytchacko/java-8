/*
Before Java 8, interfaces could have only abstract methods. The implementation of these methods has to be provided in a separate class.
        So, if a new method is to be added in an interface, then its implementation code has to be provided in the class implementing the same interface.
        To overcome this issue,Java 8 has introduced the concept of default methods
        which allow the interfaces to have methods with implementation without affecting the classes that implement the interface.

    Interfaces can have default methods with implementation in Java 8 on later.
    Interfaces can have static methods as well, similar to static methods in classes.
    Default methods were introduced to provide backward compatibility for old interfaces so that they can have new methods without affecting existing code
*/


interface DefualtMethodInterface{

    public void square(int a);

    // default method
    default void show() {
        System.out.println("Default Method Executed");
    }

    // default static method

    static void showStatic(){
        System.out.println("Default static method!!!!!");
    }

}

public class DefaultMethods implements DefualtMethodInterface{
    // implementation of square abstract method
    public void square(int a)
    {
        System.out.println(a*a);
    }

    public static void main(String args[])
    {
        DefualtMethodInterface d = new DefaultMethods();
        d.square(4);

        // default method executed
        d.show();

        DefualtMethodInterface.showStatic();
    }

}



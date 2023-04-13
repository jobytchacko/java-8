/*

Every Java Programmer is familiar with NullPointerException. It can crash your code.
And it is very hard to avoid it without using too many null checks.
So, to overcome this, Java 8 has introduced a new class Optional in java.util package.
It can help in writing a neat code without using too many null checks. By using Optional,
we can specify alternate values to return or alternate code to run.
This makes the code more readable because the facts which were hidden are now visible to the developer.

*/

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        testNullPointerCheck();
        testOptional();

        /*Optional is a container object which may or may not contain a non-null value.
        You must import java.util package to use this class. If a value is present, isPresent() will return true and get() will return the value.
        Additional methods that depend on the presence or absence of a contained value are provided, such as orElse() which returns a default value
        if the value is not present, and ifPresent() which executes a block of code if the value is present. This is a value-based class, i.e their instances are :

        Final and immutable (though may contain references to mutable objects).
        Considered equal solely based on equals(), not based on reference equality(==).
        Do not have accessible constructors.*/


        Integer value1 = null;
        Integer value2 = new Integer(10);

        //Optional.ofNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.ofNullable(value1);

        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);
        System.out.println(sumTotal(a,b));


        //Few other examples

        // creating a string array
        String[] str = new String[5];

        // Setting value for 2nd index
        str[2] = "Hello, Welcome";

        // It returns a non-empty Optional
        Optional<String> value = Optional.of(str[2]);

        // It returns value of an Optional.
        // If value is not present, it throws
        // an NoSuchElementException
        System.out.println(value.get());

        // It returns hashCode of the value
        System.out.println(value.hashCode());

        // It returns true if value is present,
        // otherwise false
        System.out.println(value.isPresent());

    }

    private static Integer sumTotal(Optional<Integer> a, Optional<Integer> b) {
        //Optional.isPresent - checks the value is present or not

        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.orElse - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();
        return value1 + value2;
    }

    private static void testOptional() {
        String[] words = new String[10];
        Optional<String> wordCheckNull = Optional.ofNullable(words[5]);
        if(wordCheckNull.isPresent()){
            String word = words[5].toLowerCase();
            System.out.print(word);
        } else {
            System.out.println("Word is null");
        }
    }

    private static void testNullPointerCheck() {
        try {
            String[] words = new String[10];
            String word = words[5].toLowerCase();
            System.out.print(word);
        }catch (Exception ex){
            System.out.println("Exception is thrown :"+ex.getMessage());
        }

    }


    /*

    Static Methods:
    Static methods are the methods in Java that can be called without creating an object of the class.
    They are referenced by the class name itself or reference to the object of that class.

    Important Points: Since Static methods belong to the class, they can be called to without creating the object of the class.
    Below given are some important points regarding Static Methods :

    Static method(s) are associated with the class in which they reside i.e. they can be called even without creating an instance of the class.
    They are designed with the aim to be shared among all objects created from the same class.
    Static methods can not be overridden. But can be overloaded since they are resolved using static binding by the compiler at compile time.
    */


    /*

    Instance Methods:

    Instance methods are methods that require an object of its class to be created before it can be called.
    To invoke an instance method, we have to create an Object of the class within which it is defined.

    Important Points:
    Instance Methods can be called within the same class in which they reside or from the different classes defined either in the same package or
    other packages depending on the access type provided to the desired instance method. Below given are some important points regarding Instance Methods :

    Instance method(s) belong to the Object of the class, not to the class i.e. they can be called after creating the Object of the class.
    Every individual object created from the class has its own copy of the instance method(s) of that class.
    They can be overridden since they are resolved using dynamic binding at run time.
    */

    /*
    Concrete Methods: A concrete method means, the method has a complete definition but can be overridden in the inherited class.
    If we make this method final, then it can not be overridden. Declaring method or class “final” means its implementation is complete.
    It is compulsory to override abstract methods. Concrete Methods can be overridden in the inherited classes if they are not final.
    */



}

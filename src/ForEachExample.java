/*
In Java, the Collection interface has Iterable as its super interface. And this interface has a new API starting with Java 8:
        void forEach(Consumer<? super T> action)
Simply put, the Javadoc of forEach states that it “performs the given action for each element
of the Iterable until all elements have been processed or the action throws an exception.”

And so, with forEach, we can iterate over a collection and perform a given action on each element, like any other Iterator.

For instance, consider a for-loop version of iterating and printing a Collection of Strings:


*/

import java.util.*;
import java.util.function.Consumer;

public class ForEachExample {

    public static void main(String[] args) {
        List<String> namess = Arrays.asList("Alex", "Ran", "Shan", "Raj");

        //Normal Usage of for each
        namess.forEach(s -> {
            System.out.println(s);
        });

        //By passing consumer

        Consumer<String> namessConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Through Consumer-- "+s);
            }
        };

        namess.forEach(namessConsumer);

        //Through Method reference
        System.out.println("Through Method Reference");
        namess.forEach(System.out::println);


        //Any iterable of type Collection — list, set, queue etc. — has the same syntax for using forEach.

        //Therefore, as we have seen, we can iterate elements of a list this way:
        List<String> names = Arrays.asList("Larry", "Steve", "James");
        names.forEach(System.out::println);


        //And a set is similar:
        Set<String> uniqueNames = new HashSet<>(Arrays.asList("Larry", "Steve", "James"));
        uniqueNames.forEach(System.out::println);


        //Finally, let's look at a Queue that is also a Collection:
        Queue<String> namesQueue = new ArrayDeque<>(Arrays.asList("Larry", "Steve", "James"));
        namesQueue.forEach(System.out::println);


        /*Iterating Over a Map Using Map's forEach
        Maps are not Iterable, but they do provide their own variant of forEach that accepts a BiConsumer.
        Java 8 introduces a BiConsumer instead of Consumer in Iterable's forEach so that an action can be performed on both the key and value of a Map simultaneously.

        */
        //Let's create a Map with these entries:

        Map<Integer, String> namesMap = new HashMap<>();
        namesMap.put(1, "Larry");
        namesMap.put(2, "Steve");
        namesMap.put(3, "James");
        //Next, let's iterate over namesMap using Map's forEach:
        namesMap.forEach((key, value) -> System.out.println(key + " " + value));
        //As we can see here, we've used a BiConsumer to iterate over the entries of the Map:
        //(key, value) -> System.out.println(key + " " + value)


        //Iterating Over a Map by Iterating entrySet
        //We can also iterate the EntrySet of a Map using Iterable's forEach.
        //Since the entries of a Map are stored in a Set called EntrySet, we can iterate that using a forEach:
        namesMap.entrySet().forEach(entry -> System.out.println(
                entry.getKey() + " " + entry.getValue()));



        //Foreach vs For-Loop

        /*From a simple point of view, both loops provide the same functionality: loop through elements in a collection.

        The main difference between them is that they are different iterators. The enhanced for-loop is an external iterator, whereas the new forEach method is internal.*/

        //Internal Iterator — forEach
        /*
                This type of iterator manages the iteration in the background and leaves the programmer to just code what is meant to be done with the elements of the collection.
                The iterator instead manages the iteration and makes sure to process the elements one-by-one.
        */

        //Let's see an example of an internal iterator:

        names.forEach(name -> System.out.println(name));
        //In the forEach method above, we can see that the argument provided is a lambda expression. This means that the method only needs to know what is to be done,
        // and all the work of iterating will be taken care of internally.

        //External Iterator — for-loop
       /* External iterators mix what and how the loop is to be done.
        Enumerations, Iterators and enhanced for-loop are all external iterators (remember the methods iterator(), next() or hasNext()?).
        In all these iterators, it's our job to specify how to perform iterations.
        */
        //Consider this familiar loop:

        for (String name : names) {
            System.out.println(name);
        }

        /*Alhough we are not explicitly invoking hasNext() or next() methods while iterating over the list,
        the underlying code that makes this iteration work uses these methods.
         This implies that the complexity of these operations is hidden from the programmer, but it still exists.


        Contrary to an internal iterator in which the collection does the iteration itself,
        here we require external code that takes every element out of the collection.
*/

    }





}

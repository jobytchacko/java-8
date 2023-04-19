/*
Java 8 introduced a new method called as parallelSort() in java.util.Arrays Class. It uses Parallel Sorting of array elements

Algorithm of parallelSort()

1. The array is divided into sub-arrays and that
   sub-arrays is again divided into their sub-arrays,
   until the minimum level of detail in a set of array.
2. Arrays are sorted individually by multiple thread.
3. The parallel sort uses Fork/Join Concept for sorting.
4. Sorted sub-arrays are then merged.

Syntax :

For sorting data in ascending order :
public static void parallelSort(Object obj[])
For sorting data in specified range in ascending order :
public static void parallelSort(Object obj[], int from, int to)

Advantage :
parallelSort() method uses concept of MultiThreading which makes the sorting faster as compared to normal sorting method.


1) Arrays.sort() : is a sequential sorting.


The API uses single thread for the operation.
It takes bit longer time to perform the operation.
2. Arrays.ParallelSort() : is a parallel sorting.

The API uses multiple threads for the operation.
It’s faster when there are a lot of elements whereas slower for lesser elements.
Analysis : The results show that parallel sorting on a multicore machine can achieve performance improvements at 1 million or more elements.
This result meets the expectation, and the suitable size here may be 1 million.
Your mileage may vary, it depends on your environment.

*/


import java.util.Arrays;
import java.util.Random;

public class ParallelSortExample {

    public static void main(String[] args) {

        // Creating an array
        int numbersTest[] = {9, 8, 7, 6, 3, 1};

        // Printing unsorted Array
        System.out.print("Unsorted Array: ");
        // Iterating the Elements using stream
        Arrays.stream(numbersTest)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Using Arrays.parallelSort()
        Arrays.parallelSort(numbersTest);

        // Printing sorted Array
        System.out.print("Sorted Array: ");
        // Iterating the Elements using stream
        Arrays.stream(numbersTest)
                .forEach(n -> System.out.print(n + " "));


        //Parallel Sort Vs Series Sortig

        System.out.println("Illustration of Parallel vs Series Sorting");

        // Creating an array
        int numbers[] = new int[100];

        // Iterating Loop till i = 1000
        // with interval of 10
        for (int i = 0; i < 1000; i += 10) {

            System.out.println("\nFor iteration number: "
                    + (i / 10 + 1));

            // Random Int Array Generation
            Random rand = new Random();

            for (int j = 0; j < 100; j++) {
                numbers[j] = rand.nextInt();
            }

            // Start and End Time of Arrays.sort()
            long startTime = System.nanoTime();

            // Performing Serial Sort
            Arrays.sort(numbers);

            long endTime = System.nanoTime();

            // Printing result of Serial Sort
            System.out.println("Start and End Time in Serial (in ns): "
                    + startTime + ":" + endTime);
            System.out.println("Time taken by Serial Sort(in ns): "
                    + (endTime - startTime));

            // Start and End Time of Arrays.parallelSort()
            startTime = System.nanoTime();

            // Performing Parallel Sort
            Arrays.parallelSort(numbers);

            endTime = System.nanoTime();

            // Printing result of Parallel Sort
            System.out.println("Start and End Time in parallel (in ns): "
                    + startTime + ":" + endTime);
            System.out.println("Time taken by Parallel Sort(in ns): "
                    + (endTime - startTime));
            System.out.println();

        }
    }

}

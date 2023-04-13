import java.util.*;
public class SortingTechniques {
   /*   Comparable provides compareTo() method to sort elements in Java.
        Comparator provides compare() method to sort elements in Java.
        Comparable interface is present in java.lang package.
        Comparator interface is present in java.util package.
        Comparable - The logic of sorting must be in the same class whose object you are going to sort.
        Comparator - The logic of sorting should be in separate class to write different sorting based on different attributes of objects.
        Comparable - The class whose objects you want to sort must implement comparable interface.
        Comparator - Class, whose objects you want to sort, do not need to implement a comparator interface.
        Comparable - It provides single sorting sequences.
        Comparator - It provides multiple sorting sequences. https://stackoverflow.com/questions/37370750/what-do-you-mean-by-you-can-create-many-sort-sequences-using-comparator-in-java
        Comparable - This method can sort the data according to the natural sorting order.
        Comparator - This method sorts the data according to the customized sorting order.
        Comparable - It affects the original class. i.e., actual class is altered.
        Comparator - It doesn’t affect the original class, i.e., actual class is not altered.
        Comparable - Implemented frequently in the API by: Calendar, Wrapper classes, Date, and String.
        Comparator - It is implemented to sort instances of third-party classes.
        Comparable - All wrapper classes and String class implement comparable interface.
        Comparator - The only implemented classes of Comparator are Collator and RuleBasedColator.
*/


    public static void main(String[] args) {
        List<Integer> numList = Arrays.asList(2,1,5,8,7,9);
        System.out.println("Not sorted List");
        System.out.println(numList);

        Collections.sort(numList);//This is oneway of sorting. If we need to implement custom sorting, we need to create class and implement comparator
                                    // and then write custom sorting in that
        System.out.println("Sorted using Collections.sort()");
        System.out.println(numList);

        //Custom Sorting using Comparator Interface

        //Comparator for ascending order
        //Anonymous comparator class created below
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2){
                    return 1;//Swaps if first one is greater than second one
                } else {
                    return -1;
                }
            }
        };


        //Comparator for descending order
        //Anonymous comparator class created below
        Comparator<Integer> comparatorDesc = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1<o2){
                    return 1;//Swaps if first one is greater than second one
                } else {
                    return -1;
                }
            }
        };

        numList = Arrays.asList(3,1,6,8,7,9);//Number list
        System.out.println(numList);
        Collections.sort(numList,comparator);//Sort the number list in ascending order
        System.out.println(numList);
        Collections.sort(numList, comparatorDesc);//Sort the number list descending order
        System.out.println(numList);

        //Comparator is a functional interface. So, it can be written in lambda expression way

        Collections.sort(numList,(o1, o2) -> o1>o2 ? 1 : -1);// Passing comparator sorting in ascending order
        System.out.println(numList);

        Collections.sort(numList,(o1, o2) -> o1<o2 ? 1 : -1);// Passing comparator sorting in descending order
        System.out.println(numList);

        // Sample with object
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(23, "Joby"));
        studentList.add(new Student(28, "Arun"));
        studentList.add(new Student(25, "Bibin"));
        studentList.add(new Student(24, "Tom"));
        printStudents(studentList);

        //Sorting using comparator class created  - Multiple sorting sequence can be created
        System.out.println("========Sorting using comparator class created===========");
        Collections.sort(studentList, new StudentAgeComparator());
        printStudents(studentList);

        System.out.println("========Name Sorting===========");
        Collections.sort(studentList, new StudentNameComparator());
        printStudents(studentList);

        System.out.println("========Name Sorting reversed===========");
        Collections.sort(studentList, new StudentNameComparator().reversed());
        printStudents(studentList);

        //Using functional method simplicity
        System.out.println("=========Age sorting - Using Lambda expression since comparator is a functional Interface=========");
        Collections.sort(studentList, ((o1, o2) -> o1.age > o2.age ? 1 : -1));
        printStudents(studentList);
        System.out.println("=========Name sorting - Using Lambda expression since comparator is a functional Interface=========");
        Collections.sort(studentList, (o1, o2) -> o1.name.compareTo(o2.name));
        printStudents(studentList);
        System.out.println("=========Name sorting reversed - Using Lambda expression since comparator is a functional Interface=========");
        Collections.sort(studentList, (o1, o2) -> o2.name.compareTo(o1.name));
        printStudents(studentList);

        System.out.println("===========Comparable Example =============");
        //Comparable example
        //Employee class created with comparable interface implemented



        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(29, "Ram"));
        employeeList.add(new Employee(24, "Arun"));
        employeeList.add(new Employee(28, "Sree"));
        employeeList.add(new Employee(29, "Boby"));

        System.out.println("========Employee List without sorting ============");
        printEmployees(employeeList);
        System.out.println("========Employee List with sorting using comparable============");
        Collections.sort(employeeList);
        printEmployees(employeeList);

    }

    private static void printEmployees(List<Employee> employeeList) {
        employeeList.forEach(employee -> {
            System.out.println(employee.name+" : "+employee.age);
        });
    }

    static void printStudents(List<Student> studentList) {
        studentList.forEach(student -> {
            System.out.println(student.name+" : "+student.age);
        });
    }


}


class Student{//Student Class
    Student(int age, String name){
        this.age=age;
        this.name= name;
    }
    int age;
    String name;
}

class StudentAgeComparator implements Comparator<Student>{//Comparator created for Student Age sorting

    @Override
    public int compare(Student o1, Student o2) {
        if(o1.age>o2.age){
            return 1;
        } else {
            return -1;
        }
    }
}

class StudentNameComparator implements Comparator<Student>{//Comparator created for Student Name sorting
    @Override
    public int compare(Student o1, Student o2) {
        return o1.name.compareTo(o2.name);
    }


}


class Employee implements Comparable<Employee>{//Employee class created for comparable example. Implemented Comparable example

    int age;
    String name;

    Employee(int age, String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);//To reverse return o.name.compareTo(this.name);
    }
}












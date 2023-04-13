import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsExample {

    public static void main(String[] args) {



        System.out.println("==============Stream API==================");
        List<Integer> numberList = Arrays.asList(1,3,2,8,6);//Creating a List
        List<String> nameLists = Arrays.asList("Ram","John","Jacob","Paul");
        Stream<Integer> numberListStream = numberList.stream();//Creating a stream from the list
        System.out.println("Count in the stream : "+numberListStream.count());//Consuming stream. Once stream consumed, we won't be able to use it further
        try{
            System.out.println(numberListStream.count());//Trying to use it again
        }catch (Exception e){
            System.out.println("Can't use stream again once it is consumed. Message : "+e.getMessage());// This message will be shown due to the exception
        }

        //Iterating through the list without creating stream
        System.out.println("Iterating through the list without creating stream.");
        numberList.forEach(e->{
            System.out.print(e);
        });

        //Iterating through the list with stream
        System.out.println();
        System.out.println("Iterating through the list with stream.");
        numberList.stream().forEach(s->{
            System.out.print(s);
        });
        System.out.println();

        //Sorted Stream
        System.out.println("Creating sorted list of items using stream");
        System.out.println();
        numberList.stream().sorted().forEach(System.out::print);//Creating a sorted stream and printing it using method reference
        System.out.println();
        numberList.stream().sorted().forEach(e->{//Creating a sorted stream and printing it
            System.out.print(e);
        });
        System.out.println();
        System.out.println("Sorting using comparator - Reverse order");

        numberList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);
        System.out.println();

        System.out.println("Sorting using comparator - Using Lambda expression");
        numberList.stream().sorted((o1, o2) -> o1>o2?1:-1).forEach(System.out::print);
        System.out.println();

        System.out.println("Sorting using comparator - Reverse -  Using Lambda expression");
        numberList.stream().sorted((o1, o2) -> o2>o1?1:-1).forEach(System.out::print);

        System.out.println("Names sorted - String");
        nameLists.stream().sorted().forEach(s -> {
            System.out.println(s);
        });

        System.out.println();


        List<Students> studentsList = new ArrayList<>();
        studentsList.add(new Students(30, "Rahul"));
        studentsList.add(new Students(28, "Arun"));
        studentsList.add(new Students(34,"Bobby"));
        studentsList.forEach(students -> {
            System.out.println(students.name + ":" +students.age);
        });

        try {
            System.out.println("After sorting");
            studentsList.stream().sorted().forEach(students -> {
                System.out.println(students.name + ":" + students.age);
            });
        } catch (Exception e){
            System.out.println("Exception thrown ::"+e.getMessage());//Exception will be there since Students class doesn't have comparer class extended
                                                                    // (in case of Integer, String, etc, it is already extended
        }

        // So, creating a studentsComparator to handle sorting
        Comparator<Students> studentsComparator = new Comparator<Students>() {
            @Override
            public int compare(Students o1, Students o2) {
                return o1.age>o2.age ? 1 : -1;
            }
        };
        System.out.println("Sorted by name using Comparator");
        studentsList.stream().sorted(studentsComparator).forEach(students -> {
            System.out.println(students.name + ":" + students.age);
        });

        //With lambda expression
        System.out.println("Sorted based on the name");
        studentsList.stream().sorted((o1, o2) -> o1.name.compareTo(o2.name)).forEach(students -> {
            System.out.println(students.name + ":" + students.age);
        });

        //Replaced lambad with default Comparator option
        System.out.println("Sorted based on the name again with Compartor.comparing option");
        studentsList.stream().sorted(Comparator.comparing(o -> o.name)).forEach(students -> {
            System.out.println(students.name + ":" + students.age);
        });


        //Mapping part
        System.out.println("Stream created through map");
       Stream<String> nameStream = studentsList.stream().map(students -> students.name);
       nameStream.forEach(System.out::println);

        System.out.println("Stream created through map after name sorting");
        // Map in stream is used to transform one form of stream to another form of stream
       studentsList.stream()
               .sorted(Comparator.comparing(students -> students.name))//Transformed Students stream to String stream
               .map(students -> students.name)
               .forEach(System.out::println);

        System.out.println("Transforming Students to Employees");
        //Transforming Students to Employees object
       List<Employees> employeeList = studentsList.stream()
                                        .sorted(Comparator.comparing(students -> students.name))
                                        .map(students -> {
                                            Employees employees = new Employees(students.name,students.age);//Transformed Students stream to Employees stream
                                        return employees;
                                        }).collect(Collectors.toList());

        employeeList.stream().forEach(employees -> {
           System.out.println("Employee Name : "+employees.name);
        });

       //Simpler form
        System.out.println("Tried in more simpler form ========= ");
        studentsList
            .stream()
            .sorted(Comparator.comparing(students -> students.name))
            .map(students -> new Employees(students.name,students.age))
            .forEach(employees -> {
                System.out.println("Employee Name : "+employees.name);
            });

        //Flat Map
        System.out.println("======Flat Map ==========");
        //Flat map is used for both transforming and flatting the streams (multiple streams to single streams)

        List<String> nameList1 = Arrays.asList("Robin", "Arun", "Alex");
        List<String> nameList2 = Arrays.asList("Simon", "Raj", "Paul");
        List<String> nameList3 = Arrays.asList("Ram", "Tom", "Tim","Paul","Raj");

        List<List<String>> fullNameList = Arrays.asList(nameList1,nameList2, nameList3);
        System.out.println("Fullname List");
        System.out.println(fullNameList);

        //Flatting using flatMap
        List<String> fullNameListFlattened = fullNameList.stream().flatMap(str -> str.stream()).collect(Collectors.toList());
        System.out.println("After Flatmap");
        System.out.println(fullNameListFlattened);

        //Taking the second character of eah word
        List<Character> fullNameCharacter = fullNameList.stream().flatMap(str -> str.stream()).map(s -> s.charAt(2)).collect(Collectors.toList());

        System.out.println(fullNameCharacter);

        //Stream Filter

        List<String> nameListOne = nameList1.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());

        System.out.println("Name List filtered that starts with A");

        System.out.println(nameListOne);

        //Filtering the name that has exact 3 letter length

        List<String> nameListThreeLength = fullNameListFlattened.stream().filter(s -> s.length() == 3).collect(Collectors.toList());

        System.out.println("After filtering exact 3 length match : "+nameListThreeLength);

        //All Match
        boolean startsWithRAllMatch = fullNameListFlattened.stream().allMatch(s -> s.startsWith("R"));

        System.out.println("After Applying All Match - starts with R");
        //Any Match

        System.out.println(startsWithRAllMatch);

        boolean startsWithAnyMatch = fullNameListFlattened.stream().anyMatch(s -> s.startsWith("R"));

        System.out.println("After Applying Any Match - starts with R");

        System.out.println(startsWithAnyMatch);

        //Reduce
        int total = fullNameListFlattened.stream().map(s -> s.length()).reduce(0,(sum, stLegnth) -> sum + stLegnth);
        System.out.println("After applying reduce to the legth of the strings - Sum");
        System.out.println(total);
        int totalMul = fullNameListFlattened.stream().map(s -> s.length()).reduce(1,(sum, stLegnth) -> sum * stLegnth);
        System.out.println("After applying reduce to the legth of the strings - Total Multiplication");
        System.out.println(totalMul);

        //Collecting to Set

        Set<String> flattenedSet = fullNameList.stream().flatMap(strings -> strings.stream()).collect(Collectors.toSet());
        System.out.println("Flattened Set");
        System.out.println(flattenedSet);


    }

}

class Students{
    int age;
    String name;

    public Students(int age, String name) {
        this.age = age;
        this.name = name;
    }

}

class Employees{
    String name;
    int age;

    Employees(String name, int age){
        this.name = name;
        this.age = age;
    }
}



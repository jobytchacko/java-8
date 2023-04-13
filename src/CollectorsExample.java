/*

Collectors is one of the utility class in JDK which contains a lot of utility functions.
It is mostly used with Stream API as a final step.

When it comes to the functional style of programming in Java,
we typically have few functions which we use widely and those functions are filter(), map(), reduce(), and collect() which belongs to the Streams API.
collect() and reduce() methods are called as the terminal methods because here, the operation gets terminated with some outcome.
Functions associated with Collectors usually get used inside collect() methods.
Collectors class is part of Stream package

*/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsExample {

    public static void main(String[] args) {
        // The following statement filters 
        // cities having temp > 10 
        // The map function transforms only 
        // the names of the cities 
        // The collect function collects the 
        // output as a List 
        System.out.println(prepareTemperature().stream()
                .filter(f -> f.getTemprature() > 10)
                .map(f -> f.getName())
                .collect(Collectors.toList()));

        //Output as comma seperated for city names

        System.out.println(prepareTemperature().stream()
                .filter(f -> f.getTemprature() > 10)
                .map(f -> f.getName())
                .collect(Collectors.joining(",")));

        List<String> cityNameList = new ArrayList<>();

        //Collected to custom List
        System.out.println(prepareTemperature()
                .stream()
                .map(f -> f.getName())
                .collect(Collectors.toCollection(() -> cityNameList)));
        System.out.println(" ===== City Name List Collected======= ");
        System.out.println(cityNameList);

        //Collected to Set
        System.out.println(prepareTemperature().stream()
                .filter(f -> f.getTemprature() > 10)
                .map(f -> f.getName())
                .collect(Collectors.toSet()));

        //Collected to Map

        System.out.println(prepareTemperature().stream()
                .filter(f -> f.getTemprature() > 10)
                .collect(Collectors.toMap(
                        City::getName,
                        City::getTemprature
                        )));

    }

    private static List<City> prepareTemperature() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City("Delhi", 14));
        cityList.add(new City("Mumbai", 25));
        cityList.add(new City("Banglore", 15));
        cityList.add(new City("Chennai", 30));
        cityList.add(new City("Jammu", 15));
        return cityList;
    }
}
    
class City{

    String name;
    float temprature;
    
    City(String name, float temprature){
        this.name = name;
        this.temprature = temprature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTemprature() {
        return temprature;
    }

    public void setTemprature(float temprature) {
        this.temprature = temprature;
    }

}

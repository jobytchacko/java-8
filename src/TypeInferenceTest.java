import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*

Type inference is a Java compiler's ability to look at each method invocation and corresponding declaration to determine the type argument (or arguments)
that make the invocation applicable.
The inference algorithm determines the types of arguments and, if available, the type that the result is being assigned, or returned.
Finally, the inference algorithm tries to find the most specific type that works with all of the arguments.

*/

public class TypeInferenceTest {
    public static void main(String[] args) {

        //Eg 1

        List<String> test = new ArrayList<>();//Not mentioned the type at the right side new ArrayList<>(). Java understands it
        showInferenceValue(10, 20);
        showInferenceValue("10", "20");
        Integer val1 = showInferenceValueCheck(10, 20);
        String val2 = showInferenceValueCheck("10", "20");
        Serializable val3 = showInferenceValueCheck(10,20);
        System.out.println(val3);

        showInferenceValueCheck(new ArrayList<>(),10,20);
        showInferenceValueCheck(new ArrayList<Integer>(),10,20);
        //showInferenceValueCheck(new ArrayList<String>(),10,20);//Will be error since second and third argument trying to add to list is Integer
        showInferenceValueCheck(new ArrayList<>(),"20", "10");
        showInferenceValueCheck(new ArrayList<String>(),"20", "10");
        showInferenceValueCheck(new ArrayList<>(),10,"20");
        //showInferenceValueCheck(new ArrayList<String>(),10,"20");//Error since second and third args are different data types
    }

    private static <T> void showInferenceValue(T n1, T n2) {//This determines the type automatically
        System.out.println(n1+" - "+n2);
        System.out.println(n1.getClass().getName());
        System.out.println(n2.getClass().getName());
    }

    private static <T> T showInferenceValueCheck(T n1, T n2) {//This determines the type automatically
        return n1;
    }

    private static <T> void showInferenceValueCheck(List<T> list, T n1, T n2) {//This determines the type automatically
        list.add(n1);
        list.add(n2);
    }

}

/*
Nashorn: Nashorn is a JavaScript engine which is introduced in JDK 8. With the help of Nashorn,
we can execute JavaScript code at Java Virtual Machine. Nashorn is introduced in JDK 8 to replace existing JavaScript engine i.e. Rhino.
Nashorn is far better than Rhino in term of performance.
The uses of invoking dynamic feature, conversion of JavaScript code into the bytecode directly into the memory etc makes
the Nashorn more famous in JDK 8. We can execute JavaScript code by using the command-line tool and by embedding the JavaScript code into Java source code.

Executing JavaScript code by using console: For Nashorn engine, Java 8 introduced one new command-line tool i.e.jjs.
We have to follow the below steps to execute JavaScript code through the console:

Create one file named with geeks.js.
Open geeks.js and write following code into the file and save it.

        var gfg= function(){
        print("Welcome!!!");
        };
        gfg();
Open CMD, write jjs geeks.js and press enter. It will generate the below output:
Welcome!!!

*/

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

// Program to illustrate embedding
// of JavaScript file into Java code
public class NashornJavaScriptEngine {

/*
    Executing JavaScript file by embedding JavaScript file into Java code:
    We can execute JavaScript file by embedding JavaScript file into Java code with the help of ScriptEngine class.
    ScriptEngine class is introduced in JDK 6. By the help of the ScriptEngine class,
    we can create a JavaScript engine and with the JavaScript engine, we can execute the javaScript file.
*/


    public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {

        // Here we are generating Nashorn JavaScript Engine
        ScriptEngine sc = new ScriptEngineManager().getEngineByName("Nashorn");

        // Reading JavaScript file create
        sc.eval(new FileReader("js/nashornTest.js"));


        // Instead of reading JavaScript code from a file.
        // We can directly paste the JavaScript
        // code inside Java Code
        sc.eval("print('Welcome!!!"
                + " Executing JavaScript code with the"
                + " help of Nashorn engine');");


        /*
        Providing JavaScript variable from Java Code: Suppose we have one JavaScript file name with geeks.js and
        geeks.js requires one variable during execution. With the help of Nashorn, we can pass the variable to JavaScript file from java code.
        */

        Bindings bind
                = sc.getBindings(
                ScriptContext.ENGINE_SCOPE);
        bind.put("name", "Joby chacko");
        sc.eval(new FileReader("js/nashornBindTest.js"));

        /*
        Calling JavaScript function from Java code: We can call JavaScript function from Java code with the help of Nashorn.
        Suppose we create one file name with nashornTest.js and the file contains two functions like below:

        */

        Invocable invocable = (Invocable)sc;

        // Here we are calling func1
        invocable.invokeFunction("func1");

        // Here we are calling func2
        // as well as passing argument
        invocable.invokeFunction("func2",
                "Joby");
    }
}





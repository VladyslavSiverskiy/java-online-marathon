import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.*;
import java.lang.reflect.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CamelCase {

}



class CheckCamelCase {
	public final static String CAMELCASE_PATTERN = "^[a-z]+([A-Z][a-z]+)*$";
	public final static String ERROR_MESSAGE_TEMPLATE = "method %s.%s doesn't satisfy camelCase naming convention";

	private static boolean testMethodName(String name){
		return name.matches(CAMELCASE_PATTERN);
	}

	public static boolean checkAndPrint(Class classToTest){
		boolean allNamesAreValid = true;
		var methods = classToTest.getDeclaredMethods();
		for (Method m: methods){
			String methodName = m.getName();
			for (Annotation annotations: m.getDeclaredAnnotations()){
				if(annotations instanceof CamelCase){
					if(!testMethodName(methodName)){
						System.out.printf((ERROR_MESSAGE_TEMPLATE) + "%n", classToTest.getName(), methodName);
						allNamesAreValid = false;
					}
				}
			}
		}
		return allNamesAreValid;
	}
}

class ClassForAnnot {
    @CamelCase
    public static void example() {
    }

    @CamelCase
    public void Example() {
    }

    public static void _main(String args[]) {
    }
}
public class Class1{
@CamelCase
public void correct(){} 
@CamelCase
public void InCorrect(){} 
@CamelCase
public void JustMethod(){}
}

public class Class2{
@CamelCase
public void correct(){} 
@CamelCase
public void oneMoreCorrect(){} 
}


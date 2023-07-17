import java.util.function.BinaryOperator;
import java.util.ArrayList;
import java.util.List;
public class App {
    
  public static BinaryOperator<String> greetingOperator = (name, surname) -> "Hello " + name + " " + surname + "!!!";

	public static List<String> createGreetings(List<Person> people, BinaryOperator<String> greetingOperator) {
		List<String> greetings = new ArrayList<>();
		for(Person p: people){
			greetings.add(greetingOperator.apply(p.name, p.surname));
		}
		return greetings;
	}
}

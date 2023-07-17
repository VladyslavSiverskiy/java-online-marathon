import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.ArrayList;
import java.util.List;


class MyUtils {
    public static int findMaxByCondition(List<Integer> numbers, Predicate<Integer> pr) {
        // Write your code here
        return numbers.
				stream().
				filter(pr).
				mapToInt(number -> number).
				max().
				orElse(-1);
    }
}

class User {
    public final List<Integer> values = new ArrayList<Integer>();

    int getFilterdValue(BiFunction<List<Integer>, Predicate<Integer>, Integer> filterFunction, Predicate<Integer> predicate) {
		return filterFunction.apply(values, predicate);
    }

    int getMaxValueByCondition(Predicate<Integer> predicate) {
        return getFilterdValue(MyUtils::findMaxByCondition, predicate);
    }
}

import java.util.Arrays;
import java.util.function.Predicate;
public class MyUtils {
    
    public static int getCount(int[] arr, Predicate<Integer> predicate) {
        return (int) Arrays
            .stream(arr)
            .mapToObj(value -> value).toList()
            .stream()
            .filter(predicate)
            .count();
    }
}

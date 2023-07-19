import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Objects;


public class MyUtils {
   public Stream<Integer> duplicateElements(Stream<Integer> stream) {
        return stream
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(el -> el ,Collectors.counting()))
            .entrySet()
            .stream()
            .filter(el -> el.getValue() > 1)
            .map(el -> el.getKey()).collect(Collectors.toSet())
            .stream()
            .sorted();
    }
}

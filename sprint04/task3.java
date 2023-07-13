import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MyUtils {
    // Code
    public boolean listMapCompare(List<String> list, Map<String, String> map) {
        return new HashSet<>(list)
            .equals(
                    map
                            .entrySet()
                            .stream()
                            .map(entry -> entry.getValue())
                            .collect(Collectors.toSet())
            );
    }
}

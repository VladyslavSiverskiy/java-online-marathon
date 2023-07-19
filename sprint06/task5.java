import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class MyUtils {
    public Stream<String> nameList(Map<String, Stream<String>> map) {
        if(map.isEmpty()) throw new NullPointerException();
        return map.values()
                .stream()
                .flatMap(stringStream -> stringStream)
                .filter(el -> el != null && !el.equals(""))
                .map(el -> {
                    el = el.toLowerCase();
                    el = el.replaceAll(" ", "");
                    if(!el.equals("")){
                        el = el.substring(0, 1).toUpperCase() + el.substring(1);
                    }
                    return el;
                })
                .filter(el -> !el.equals(""))
                .distinct()
                .sorted();
    }
}

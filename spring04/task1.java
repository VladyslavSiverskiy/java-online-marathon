import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MyUtils {
    
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        if(phones.isEmpty()) return new HashMap<>();
        List<String> nullNumbers = new ArrayList<>();
        Map<String, List<String>> resultMap = phones
                .entrySet()
                .stream()
                .filter(pair -> {
                    if(pair.getValue() == null){
                        nullNumbers.add(pair.getKey());
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.groupingBy(
                        Map.Entry<String, String>::getValue,
                        Collectors.mapping(Map.Entry<String, String>::getKey, Collectors.toList())));
        if(nullNumbers.size() != 0) resultMap.put(null, nullNumbers);
        return resultMap;
    }
}    

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.Set;

class MyUtils {
   
   // Write your code here
    public static Predicate<Integer> getPredicateFromSet(Set<Predicate<Integer>> set){
        Iterator<Predicate<Integer>> iterator = set.iterator();
        Predicate<Integer> complexPredicate = iterator.next();
        while (iterator.hasNext()) {
            complexPredicate = complexPredicate.and(iterator.next());
        }
        return complexPredicate;
    }
   
}

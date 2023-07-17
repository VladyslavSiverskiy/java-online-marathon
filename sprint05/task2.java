import java.util.function.Consumer;
import java.util.Arrays;

public class App {

    public static Consumer<double[]> cons = (array) -> {
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 2) {
                array[i] *= 0.8;
            } else {
                array[i] *= 0.9;
            }
        }
    };

    public static double[] getChanged(double[] initialArray, Consumer<double[]> consumer) {
    	double[] arrayCopy = Arrays.copyOf(initialArray, initialArray.length);
        consumer.accept(arrayCopy);
        return arrayCopy;
    }
}

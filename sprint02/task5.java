import java.util.ArrayList;
import java.util.List;

class Rectang {
    private double height;
    private double wigth;
    public Rectang(double width, double height) {
        this.wigth = width;
        this.height = height;
    }

    public double getWidth() {
        return wigth;
    }

    public double getHeight() {
        return height;
    }

    public double getPerimeter(){
        return 2 * (wigth + height);
    }
}
class Square extends Rectang{
    public Square(double width) {
        super(width, width);
    }

    public double getWidth() {
        return super.getWidth();
    }

    public double getPerimeter(){
        return 4 * getWidth();
    }
}
public class MyUtils {
    public double sumPerimeter(List<? extends Rectang> firures) {
        return firures.stream()
                .filter(figure -> figure != null)
                .mapToDouble(figure -> figure.getPerimeter())
                .sum();
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

abstract class Shape {
        private String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getArea();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Objects.equals(name, shape.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
class Circle extends Shape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius,2);
    }

    public double getRadius(){
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }

}
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 && Double.compare(rectangle.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width, height);
    }

}
public class MyUtils {
    public List<Shape> maxAreas(List<Shape> shapes) {
        List<Shape> maxShapes = new ArrayList<>();
        if(shapes.isEmpty()){
            return maxShapes;
        }
        double maxCircleArea = 0;
        double maxRectangleArea = 0;

        for(Shape s: shapes){
            if(s instanceof Circle && s.getArea() > maxCircleArea){
                maxCircleArea = s.getArea();
            }else if(s instanceof Rectangle && s.getArea() > maxRectangleArea){
                maxRectangleArea = s.getArea();
            }
        }

        for(Shape s: shapes){
            if(s instanceof Circle && s.getArea() == maxCircleArea){
                maxShapes.add(s);
            }else if(s instanceof Rectangle && s.getArea() == maxRectangleArea){
                maxShapes.add(s);
            }
        }
        return maxShapes;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //First approach for creating objets
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(10,9));
        shapes.add(new Circle(26.5));
        shapes.add(new Square(1000));
        shapes.add(new Triangle(4,5,3,8));

        for(Shape sh : shapes){
            sh.draw();
            System.out.println("Perimeter of " + sh.getShapeName() + " = " + sh.calculatePerimeter());
            System.out.println("Area of " + sh.getShapeName() + " = " + sh.calculateArea() + "\n");
        }

        /*//Second approachCreating a shape reference variable
        Shape shape;
        // Creating a new Rectangle object and assigning it to the shape variable
        shape = new Rectangle(10,15);
        shape.draw();
        System.out.println("Perimeter of rectangle: " + shape.calculatePerimeter());
        System.out.println("Area of rectangle: " + shape.calculateArea() + "\n");

        // Creating a new Circle object and assigning it to the shape variable
        shape = new Circle(4.56875f);
        shape.draw();
        System.out.println("Perimeter of circle: " + shape.calculatePerimeter());
        System.out.println("Area of circle: " + shape.calculateArea() + "\n");

        // Creating a new Square object and assigning it to the shape variable
        shape = new Square();
        shape.draw();
        System.out.println("Perimeter of square: " + shape.calculatePerimeter());
        System.out.println("Area of square: " + shape.calculateArea() + "\n");

        // Creating a new Triangle object and assigning it to the shape variable
        shape = new Triangle(1.92f,4,5, 3);
        shape.draw();
        System.out.println("Perimeter of triangle: " + shape.calculatePerimeter());
        System.out.println("Area of triangle: " + shape.calculateArea());*/
    }
}
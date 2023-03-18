import java.io.Serializable;

// The Shape class is declared as abstract because it has abstract methods which are not implemented
// in this class but will be implemented in its child classes.
public abstract class Shape implements Serializable {
    // The color property is declared as static because it's a common property across all the child classes
    static String color = "Yellow";

    // The calculateArea() and calculatePerimeter() methods are declared as abstract because the calculation
    // logic will be different for each shape, hence it's left to the child classes to implement these methods.
    protected abstract double calculateArea();

    protected abstract double calculatePerimeter();

    protected  abstract  String getShapeName();

    // The draw() method is responsible for printing the color of the shape and drawing it.
    // The implementation of drawing will be different for each shape, but color will be the same.
    public void draw(){
        System.out.println("Draw any shape with color " + color);
    }
}

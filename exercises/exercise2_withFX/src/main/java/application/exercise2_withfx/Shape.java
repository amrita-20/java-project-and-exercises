package application.exercise2_withfx;

import javafx.scene.paint.Color;

// The Shape class is declared as abstract because it has abstract methods which are not implemented
// in this class but will be implemented in its child classes.
public abstract class Shape {
    // The color property is declared as static because it's a common property across all the child classes
    static Color color = Color.YELLOW;

    // The calculateArea() and calculatePerimeter() methods are declared as abstract because the calculation
    // logic will be different for each shape, hence it's left to the child classes to implement these methods.
    protected abstract double calculateArea();

    protected abstract double calculatePerimeter();

}
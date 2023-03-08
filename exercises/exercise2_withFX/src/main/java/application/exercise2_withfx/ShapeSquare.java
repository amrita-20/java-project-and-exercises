package application.exercise2_withfx;

public class ShapeSquare extends Shape {
    private double side;

    ShapeSquare(double side){
        this.side = side;
    }

    @Override
    public double calculateArea() {
        double area = side*side;
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = 4*side;
        return perimeter;
    }
}


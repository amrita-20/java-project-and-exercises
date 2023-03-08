package application.exercise2_withfx;

public class ShapeCircle extends Shape{
    private double radius;

    ShapeCircle(double radius){
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        double area = Math.PI * ( radius * radius );
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = 2 * Math.PI * radius;
        return perimeter;
    }

}

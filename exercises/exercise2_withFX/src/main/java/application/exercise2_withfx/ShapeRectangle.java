package application.exercise2_withfx;

public class ShapeRectangle extends Shape{
    private double length;
    private double width;

    ShapeRectangle(double l, double w){
        this.length = l;
        this.width = w;
    }
    @Override
    public double calculateArea() {
        double area = length*width;
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = 2*(length+width);
        return perimeter;
    }

}

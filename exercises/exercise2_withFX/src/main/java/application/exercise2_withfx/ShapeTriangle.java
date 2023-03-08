package application.exercise2_withfx;

public class ShapeTriangle extends Shape{
    private double height;
    private double width;

    ShapeTriangle(double h, double b){
        this.height = h;
        this.width = b;
    }
    @Override
    public double calculateArea() {
        double area = (height*width)/2;
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = height + width + (Math.sqrt((height * height) + (width * width)));
        return perimeter;
    }
}

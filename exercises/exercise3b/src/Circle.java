public class Circle extends Shape{
    private double radius;
    private String name;

    public String getShapeName() {
        return name;
    }

    Circle(double radius){
        this.radius = radius;
        this.name = "Circle";
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

    @Override
    public void draw() {
        System.out.println("Draw a circle with " + color + "color");
    }

}

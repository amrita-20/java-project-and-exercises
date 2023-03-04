public class Triangle extends Shape{
    private double height;
    private double base;
    private double sideOne;
    private double sideTwo;
    private String name;

    public String getShapeName() {
        return name;
    }

    Triangle(double h, double b, double sideOne, double sideTwo){
        this.height = h;
        this.base = b;
        this.sideOne = sideOne;
        this.sideTwo = sideTwo;
        this.name = "Triangle";
    }
    @Override
    public double calculateArea() {
        double area = (height * base)/2;
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = sideOne + sideTwo + base;
        return perimeter;
    }

    @Override
    public void draw() {
        System.out.println("Draw a triangle with " + color + " color");
    }
}

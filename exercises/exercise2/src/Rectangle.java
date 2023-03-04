public class Rectangle extends Shape{
    private double length;
    private double width;
    private String name;


    public String getShapeName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Rectangle(double length, double width){
       this.length = length;
       this.width = width;
       this.name = "Rectangle";
    }
    @Override
    public double calculateArea() {
        double area = length * width;
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = 2 * (length + width);
        return perimeter;
    }

    @Override
    public void draw() {
        System.out.println("Draw a rectangle with " + color + " color");
    }
}

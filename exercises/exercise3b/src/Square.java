public class Square extends Shape {
    private double side;
    private String name;

    Square (double side){
        this.name = "Square";
        this.side = side;
    }
    public String getShapeName() {
        return name;
    }

    @Override
    public double calculateArea() {
        double area = side * side;
        return area;
    }

    @Override
    protected double calculatePerimeter() {
        double perimeter = 4 * side;
        return perimeter;
    }

    @Override
    public void draw() {
        System.out.println("Draw a square with " + color + " color");
    }
}

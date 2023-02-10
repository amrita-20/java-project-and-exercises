public class Bottle {
    private String brand;
    private String material;
    private boolean isRecyclable;
    private String color;
    private float capacity;
    private float height;
    private float diameter;
    private float price;

    public Bottle(){
        System.out.println("Inside default constructor");
    }
    public Bottle(float height, float diameter){
        this.height = height;
        this.diameter = diameter;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isRecyclable() {
        return isRecyclable;
    }

    public void setRecyclable(boolean recyclable) {
        isRecyclable = recyclable;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public void fill() {
       System.out.println("Fill the bottle");
    }

    public double getVolume() {
       double volume;
       float radius = diameter / 2;
       volume = 3.14 * Math.pow(radius, 2) * height;
       return volume;
    }

    public void recycle() {
       System.out.println("Recycle");
    }




}

public class Laptop {
    private String brand;
    private int screenSize;
    private int memory;
    private int storage;
    private float price;
    private String model;
    private String OS;
    private boolean isTouchPad;

    public Laptop(String brand, String OS, boolean isTouchPad, float price){
        this.brand = brand;
        this.OS = OS;
        this.isTouchPad = isTouchPad;
        this.price = price;
    }

    public void showDetails(){
        System.out.println(brand+ "\t" + OS + "\t" + isTouchPad + "\t" + price);
    }
    public void clean(){
        System.out.println("Cleaning");
    }
    public void type(){
        System.out.println("Typing");
    }
}

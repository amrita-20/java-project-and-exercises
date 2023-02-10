public class Clothe {
    private String brand;
    private String fabric;
    private String color;
    private String size;
    private String type;
    private double price;
    private int manufacturingYear;
    private boolean isMachineWash;

    Clothe(String brand, String fabric, String color, String size, String type, double price, int manufacturingYear, boolean isMachineWash) {
        this.brand = brand;
        this.fabric = fabric;
        this.color = color;
        this.size = size;
        this.type = type;
        this.price = price;
        this.manufacturingYear = manufacturingYear;
        this.isMachineWash = isMachineWash;
    }

    void purchase() {
        System.out.println("Purchase");
    }

    void showClotheDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Size: " + size);
        System.out.println("Type: " + type);
        System.out.println("fabric: " + fabric);
        System.out.println("Color: " + color);
        System.out.println("manufacturingYear: " + manufacturingYear);
        System.out.println("Price: " + price);
        System.out.println("isMachineWash: " + isMachineWash);
    }

    void wash() {
        if (isMachineWash) System.out.println("You can wash clothe in machine");
        else System.out.println("Don't wash in machine");
    }
}

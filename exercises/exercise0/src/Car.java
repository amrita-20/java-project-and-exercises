public class Car {
    public enum DesignType {sedan, hatchback, suv, truck, pullover};
    public enum FuelType {gas, electric};
    public enum RoofType {sunroof, moonroof};
    private DesignType designType;
    private FuelType fuelType;
    private RoofType roofType;
    private String brandName;
    private String modelName;
    private String color;
    private Boolean isAirbagInstalled;
    private Boolean isHeatedSeat;
    private int noOfSeats;

    public Car(){
        this.designType = designType.sedan;
        this.fuelType = fuelType.gas;
        this.roofType = RoofType.sunroof;
        this.brandName = "Toyota";
        this.modelName = "Corolla";
        this.color = "Black";
        this.isAirbagInstalled = true;
        this.isHeatedSeat = false;
        this.noOfSeats = 5;
        System.out.println("Inside Car Class Constructor");
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void drive(){
        System.out.println("You can drive your car");
    }

    public void clean(){
        System.out.println("You can clean or get cleaned your car");
    }

    public void brake(){
        System.out.println("Yo can put your car on brake whenever you want to do so");
    }
}

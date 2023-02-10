public class Building {
    private String name;
    private String address;
    private int yearBuilt;
    private int noOfFloor;
    private boolean hasElevator;
    private int numberOfRooms;
    private boolean isOccupied;
    private boolean hasParkingSpace;

    Building(String name, int yearBuilt, boolean hasElevator, int noOfFloor){
        this.name = name;
        this.yearBuilt = yearBuilt;
        this.hasElevator = hasElevator;
        this.noOfFloor = noOfFloor;
    }
    public void getFloorNo() {
        System.out.println("Floor No: " + noOfFloor);
    }
    public void occupy(boolean isOccupied) {
       if(!isOccupied){
           System.out.println("Yor are welcome to occupy it");
       }else{
           System.out.println("Its full please look somewhere else");
       }
    }
    public void displayBuildingDetails() {
       System.out.println(name + " " + yearBuilt + " " + hasElevator);
    }
}

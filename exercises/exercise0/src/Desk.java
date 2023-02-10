public class Desk {
    private String shape;
    private String color;
    private String material;
    private boolean isAssembled;
    private int width;
    private int height;
    private int length;
    private boolean hasCabinet;

    public void getShape(String shape) {
        System.out.println("Shape of desk is: " + shape);
    }

    public void assemble(boolean isAssembled) {
        if(!isAssembled)
            System.out.println("Your desk is not assembled please assemble it");
        else
            System.out.println("Place it in your room");
    }

    public void addCabinet(boolean hasCabinet) {
        if(hasCabinet)
            System.out.println("Desk has cabinet no need to add it");
        else
            System.out.println("add cabinet to desk");
    }
}


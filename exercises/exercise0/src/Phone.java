public class Phone {
   private float displaySize;
   private float weight;
   private String processorVersion;
   private int memory;
   private float price;
   private String brandName;
   private String modelName;
   private String color;
   private int storage;
   private String OS;

   public Phone(String brandName, float displaySize, String modelName){
       this.brandName = brandName;
       this.displaySize = displaySize;
       this.modelName = modelName;
       System.out.println("Instantiate");
   }
    void makeCall(){
        System.out.println("Make call");
    }
    void sendText(){
        System.out.println("Send Text");
    }
    void displayPhoneDetails(){
        System.out.println("Details " + displaySize + "\t" + brandName + "\t" + modelName);
    }

    //Inner or Nested class
    class BasicPhone{
       void takePicture(){
           System.out.println("Click picture");
       }
       void playMusic(){
           System.out.println("Play music");
       }
    }

    //Inner or Nested class
    class SmartPhone{
       SmartPhone(int storageCapacity, String brand){
           storage = storageCapacity;
           brandName = brand;
           System.out.println("Inside smartphone constructor " + storage + "\t" + brandName);
       }
        void makeVidoeCall(){
            System.out.println("make video call");
        }
        void playMusic(){
            System.out.println("play music");
        }
        void playGame(){
            System.out.println("play pubg");
        }
    }
}

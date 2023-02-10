public class Game {
    public enum Availability {In_Stock, Out_Of_Stock};
    private String title;
    private String genre;
    private String platform;
    private int releaseYear;
    private int rating;
    private int price;
    String systemRequirement;
    Availability availability;

    public Game(String title, int rating, Availability availability){
        this.title = title;
        this.rating = rating;
        this.availability = availability;

    }

    public void purchase(){
        if(availability.equals(Availability.In_Stock)){
            System.out.println("This game is available you can purchase it");
        }else{
            System.out.println("This game is unavailable currently please check later");
        }
    }
    public void play(){
        System.out.println("Playing");
    }
    public void pause(){
        System.out.println("Pause for sometime");
    }


}

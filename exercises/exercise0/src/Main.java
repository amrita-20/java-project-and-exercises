public class Main {
    public static void main(String[] args) {

        //Objects for Car
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        car1.drive();
        car2.brake();
        car1.clean();
        car3.setBrandName("BMW");
        System.out.println(car3.getBrandName());

        //Objects for Book
        Book book1 = new Book("Penguin", 2018, 306, "Atomic Habits", "James Clear", "4.5", "Self Help", 1804220205);
        Book book2 = new Book();
        Book book3 = new Book();
        book1.displayBookDetails();
        book2.setGenre("Fiction");
        System.out.println(book2.getGenre());
        book3.purchase();

        //Objects for Phone
        Phone ph = new Phone("Samsung", 5.5f, "Galaxy");
        Phone.SmartPhone sp = ph.new SmartPhone(128, "OnePlus");
        Phone.BasicPhone bp = ph.new BasicPhone();
        ph.displayPhoneDetails();
        bp.takePicture();
        sp.playGame();

        //Objects for Bottle
        Bottle bottle1 = new Bottle();
        Bottle bottle2 = new Bottle(3.0f, 25.0f);
        Bottle bottle3 = new Bottle(5.0f, 30.0f);
        bottle1.setBrand("Quechua");
        bottle1.setDiameter(2.2f);
        bottle1.setHeight(20.5f);
        System.out.println(bottle1.getVolume());
        System.out.println(bottle2.getVolume());
        System.out.println(bottle3.getVolume());

        //Objects for Dog
        Dog dog1 = new Dog("tommy", 4, 'M');
        Dog dog2 = new Dog("tiger", 6, 'F');
        Dog dog3 = new Dog("jockey", 10, 'M');

        //Objects for Desk
        Desk desk1 = new Desk();
        Desk desk2 = new Desk();
        Desk desk3 = new Desk();
        desk1.getShape("rectangle");
        desk1.addCabinet(true);
        desk2.assemble(false);
        desk3.getShape("square");

        //Objects for Building
        Building build1 = new Building("Prestige Mansion", 2015, true, 3);
        Building build2 = new Building("Lawrence", 2010, false, 1);
        Building build3 = new Building("Pacific Grove", 2018, true, 5);
        build1.displayBuildingDetails();
        build2.getFloorNo();
        build3.occupy(false);
        build2.displayBuildingDetails();

        //Objects for clothe
        Clothe clothe1 = new Clothe("Zara", "Cotton", "Black", "Small", "Topwear", 200, 2022, true);
        Clothe clothe2 = new Clothe("Adidas", "polyester", "Blue", "Medium", "Topwear", 150, 2021, true);
        Clothe clothe3 = new Clothe("Nike", "Viscose", "White", "Small", "Bottomwear", 100, 2020, false);
        clothe1.showClotheDetails();
        clothe2.showClotheDetails();
        clothe3.wash();
        clothe2.purchase();

        //Objects for Game
        Game game1 = new Game("Last of Us", 5, Game.Availability.In_Stock);
        Game game2 = new Game("FIFA", 4, Game.Availability.Out_Of_Stock);
        Game game3 = new Game("CS", 4, Game.Availability.In_Stock);
        game1.purchase();
        game2.play();
        game2.purchase();
        game3.pause();

        //Objects for Laptop
        Laptop laptop1 = new Laptop("Apple", "IOS", false, 1200);
        Laptop laptop2 = new Laptop("HP", "Window", true, 1000);
        Laptop laptop3 = new Laptop("Dell", "Window", false, 900);
        laptop1.showDetails();
        laptop2.showDetails();
        laptop3.type();
    }
}
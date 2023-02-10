public class Dog {
    private String name;
    private String breed;
    private boolean isTrained;
    private int age;
    private String color;
    private char gender;
    private float weight;
    private float height;

    Dog(String name, int age, char gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
        System.out.println(name +"\t" + age + "\t" + gender);
    }
    void bark(){
        System.out.println("Dog is barking");
    }
    void takeBath(){
        System.out.println("Dog is taking bath");
    }
    void getSleep(){
        System.out.println("Dog is sleeping");
    }
}

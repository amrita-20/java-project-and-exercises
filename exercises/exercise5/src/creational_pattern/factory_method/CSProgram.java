package creational_pattern.factory_method;

public class CSProgram implements MastersProgram{

    @Override
    public void register() {
        System.out.println("Registration for CS program is open till 15th May");
    }

    @Override
    public void drop() {
        System.out.println("Last date for dropping from CS program is 5th June \n");
    }

}

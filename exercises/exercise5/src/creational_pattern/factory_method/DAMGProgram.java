package creational_pattern.factory_method;

public class DAMGProgram implements MastersProgram{
    @Override
    public void register() {
        System.out.println("Registration for DAMG program is open till 8th May");
    }

    @Override
    public void drop() {
        System.out.println("Last date for dropping from DAMG program is 8th June \n");
    }
}

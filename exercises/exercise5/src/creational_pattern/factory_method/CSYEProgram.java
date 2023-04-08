package creational_pattern.factory_method;

public class CSYEProgram implements MastersProgram{
    @Override
    public void register() {
        System.out.println("Registration for CSYE program is open till 5th May");
    }

    @Override
    public void drop() {
        System.out.println("Last date for dropping from CSYE program is 1st June \n");
    }
}

package creational_pattern.factory_method;

public class ISProgram implements MastersProgram{
    @Override
    public void register() {
        System.out.println("Registration for IS program is open till 10th May");
    }

    @Override
    public void drop() {
        System.out.println("Last date for dropping from IS program is 30th May \n");
    }

}

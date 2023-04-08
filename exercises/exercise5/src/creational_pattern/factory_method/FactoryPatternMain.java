package creational_pattern.factory_method;

public class FactoryPatternMain {
    public static void main(String [] args){
        //Create object of program factory
        ProgramFactory programFactory = new ProgramFactory();
        MastersProgram cs = programFactory.getProgram("CS");
        cs.register();
        cs.drop();
        MastersProgram is = programFactory.getProgram("IS");
        is.register();
        is.drop();
        MastersProgram csye = programFactory.getProgram("CSYE");
        csye.register();
        csye.drop();
        MastersProgram damg = programFactory.getProgram("DAMG");
        damg.register();
        damg.drop();

    }
}

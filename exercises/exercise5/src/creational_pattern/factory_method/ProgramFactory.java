package creational_pattern.factory_method;

public class ProgramFactory {
    //Factory method that creates and returns a MastersProgram object based on the program type
    public MastersProgram getProgram(String programType){
        switch (programType.toUpperCase()){
            case "CS":
                return new CSProgram();
            case "IS":
                return new ISProgram();
            case "DAMG":
                return new DAMGProgram();
            case "CSYE":
                return new CSYEProgram();
            default:
                throw new IllegalArgumentException("Invalid course type: " + programType);
        }
    }
}

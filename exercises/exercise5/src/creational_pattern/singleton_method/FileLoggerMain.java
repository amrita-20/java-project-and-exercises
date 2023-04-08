package creational_pattern.singleton_method;

public class FileLoggerMain {
    public static void main(String... args){
        //Call static method using class name
        FileLogger logger = FileLogger.getInstance();
        logger.setLog("Application is being initiated! \n");
        logger.setLog("Application is ready to start! \n");
        logger.close();
    }

}

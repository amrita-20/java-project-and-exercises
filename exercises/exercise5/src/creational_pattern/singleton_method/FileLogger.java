package creational_pattern.singleton_method;

import java.io.FileWriter;

/*  Using the Singleton pattern ensures that there is only one instance of the logger file
    throughout the application, which helps to prevent conflicts between multiple instances of the logger
    and ensures consistent logging. */

public class FileLogger {
    private static FileLogger fileLogger = null;
    private FileWriter fileWriter;

    // Created Private constructor to prevent instantiation of the class from outside
    private FileLogger(){
        try{
            fileWriter = new FileWriter("log.txt");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
        Created method to get the instance of the singleton class.
        This method ensures that only one instance of the class
        is created and returned throughout the application.
     */
    public static synchronized FileLogger getInstance(){
        if(fileLogger == null){
            fileLogger = new FileLogger();
        }
        return fileLogger;
    }

    //Created method to set the log message and write it to the file
    public void setLog(String text){
        try{
            fileWriter.write(text);
            fileWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Created method to close the file writer
    public void close(){
        try{
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

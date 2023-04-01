import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //Create object
        RegexPatternExample regex = new RegexPatternExample();

        //First Approach

        //Positive Scenarios
        System.out.println("Positive Scenarios:");
        HashMap <String, String> validInputs = new HashMap<>();
        validInputs.put("username", "john_20");
        validInputs.put("password", "joHn123");
        validInputs.put("email", "john20@gmail.com");
        validInputs.put("ssn", "234-76-4529");
        validInputs.put("color_code", "#0000FF");
        validInputs.put("custom_input", "JOHN@2");

        for(String key : validInputs.keySet()){
            regex.displayValidationResult(validInputs.get(key), key);
        }

        //Negative Scenarios
        System.out.println("Negative Scenarios:");
        HashMap <String, String> inValidInputs = new HashMap<>();
        inValidInputs.put("username", "203_john");
        inValidInputs.put("password", "adam234");
        inValidInputs.put("email", "$324adom");
        inValidInputs.put("ssn", "234-7634-529");
        inValidInputs.put("color_code", "#9F07");
        inValidInputs.put("custom_input", "john@2");

        for(String key : inValidInputs.keySet()){
            regex.displayValidationResult(inValidInputs.get(key), key);
        }


       // Second Approach

       //Positive scenarios
      /* regex.displayValidationResult("john_20", "username");
       regex.displayValidationResult("Adom30", "username");

       regex.displayValidationResult("@joHn123", "password");
       regex.displayValidationResult("Adom#47@12", "password");

       regex.displayValidationResult("john20@gmail.com", "email");
       regex.displayValidationResult("adom@yahoo.in", "email");

       regex.displayValidationResult("234-76-4529", "ssn");
       regex.displayValidationResult("892-46-0768", "ssn");

       regex.displayValidationResult("#0000FF", "color_code");
       regex.displayValidationResult("#89CFF0", "color_code");

       regex.displayValidationResult("JOHN@2", "custom_input");
       regex.displayValidationResult("ADOM@@", "custom_input");
       System.out.println("\n");


       //Negative Scenarios
        regex.displayValidationResult("203_john", "username");
        regex.displayValidationResult("40789", "username");

        regex.displayValidationResult("jonh#", "password");
        regex.displayValidationResult("adam234", "password");

        regex.displayValidationResult("joh$com", "email");
        regex.displayValidationResult("$324adom", "email");

        regex.displayValidationResult("234-7634-529", "ssn");
        regex.displayValidationResult("2044-763-522", "ssn");

        regex.displayValidationResult("#00FF", "color_code");
        regex.displayValidationResult("#89F07", "color_code");

        regex.displayValidationResult("john^4", "custom_input");
        regex.displayValidationResult("89#am2", "custom_input");*/
    }
}
import java.util.regex.Pattern;

public class RegexPatternExample {
    //To create a valid username, use a combination of lower case and upper case letters, digits, and underscores.
    // The username should be between 2 and 35 characters long. Please note that the username cannot consist only of digits
    // and should not start with a digit
    // ^(?!\d+$)[a-zA-Z0-9_]+$  ^(?!^\d)[a-zA-Z0-9_]{2,}$   ^(?![0-9]+$)[a-zA-Z0-9_]{2,35}$
    final String usernamePattern = "^(?!^\\d)[a-zA-Z0-9_]{2,35}$";
    //password should be at least 8 characters long and should have 1 lowercase, 1 uppercase, 1 digit and 1 special char
    final String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@~$#^!%*?&])[A-Za-z\\d@~$#^!%*?&]{8,20}$";
    //Email
    final String emailPattern = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$";
    //SSN
    final String ssnPattern = "^\\d{3}-\\d{2}-\\d{4}$";
    //hexadecimal color code (#FFFFFF)
    final String colorCodePattern = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    //Pattern for a string which must contain only uppercase, special characters #, $, @ and any digits between 0-3
    // And it should be of exactly 6 characters
    final String customizedPattern = "^[A-Z0-3/#$@]{6}$";


    //Create a method to validate input based on the provided type
    private boolean validator (String str, String type){
        switch (type.toLowerCase()){
            case "username":
                return Pattern.matches(usernamePattern, str);
            case "password":
                return Pattern.matches(passwordPattern, str);
            case "email":
                return Pattern.matches(emailPattern, str);
            case "ssn":
                return Pattern.matches(ssnPattern, str);
            case "color_code":
                return Pattern.matches(colorCodePattern, str);
            case "custom_input":
                return Pattern.matches(customizedPattern, str);
            default:
                return false;
        }
    }

    //Create method to display whether input is passed or failed given regex pattern
    void displayValidationResult (String str, String type){
        if(validator(str, type)){
            System.out.println("Your " + type + " has been successfully validated. You may proceed now \n");
        }else{
            System.out.println("The " + type + " you provided is not in the correct format. Please enter a valid " + type + " to proceed \n");
        }
    }
}

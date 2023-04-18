import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Elements Count
        List<Integer> numbers = Arrays.asList(3,5,8,19,30,23,65,121);
        List<String> strings = Arrays.asList("Robin", "Husky", "Kanak", "ab a", "Jasmine", "Thomas");
        int oddElements =  CountElements.countElements(numbers,  num -> CountElements.isOdd((Integer) num));
        System.out.println("No of odd elements: " + oddElements);
        int palindromeNumbers =  CountElements.countElements(numbers,  num -> CountElements.isPalindrome((Integer) num));
        System.out.println("No of number Palindrome: " + palindromeNumbers);
        int palindromeStrings =  CountElements.countElements(strings,  num -> CountElements.isPalindrome((String) num));
        System.out.println("No of string Palindrome: " + palindromeStrings);
        int primeElements =  CountElements.countElements(numbers,  num -> CountElements.isPrime((Integer) num));
        System.out.println("No of prime elements: " + primeElements);


        //Exchange element's position
        Integer [] intArray = {2,4,5,8,9};
        CollectionsProblems.swapElements(intArray, 6, 4);
        String [] strArray = {"sam", "jam", "cam", "pam", "tam"};
        CollectionsProblems.swapElements(strArray, 1,3);


        // Maximal element
        List<Integer> listOfNumbers = Arrays.asList(2,3,54,5,1);
        CollectionsProblems.getMaxElement(listOfNumbers, 2,3);
        List<String> listOfStrings = Arrays.asList("Robin", "Saket", "Shivani", "Sneha", "Alley");
        CollectionsProblems.getMaxElement(listOfStrings, 1, 4);
        CollectionsProblems.getMaxElement(listOfStrings, 1, 3);
        CollectionsProblems.getMaxElement(listOfStrings, 4, 8); //This will throw out of bound exception
        CollectionsProblems.getMaxElement(listOfStrings, 4, 2);
    }
}
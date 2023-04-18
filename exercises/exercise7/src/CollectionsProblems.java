import java.util.Arrays;
import java.util.List;
public class CollectionsProblems {

    /*Create method to swap elements.
        Make this method as static since
        there is no instance variable needed
        so no object creation is required, And
        can directly access this method by class name
     */

    public static <T> void swapElements(T[] elements, int index1, int index2){
        //Added checks for out of bound exception
        if(elements.length > 1 && index2 < elements.length && index1 < elements.length){
            T temp = elements[index1];
            elements[index1] = elements[index2];
            elements[index2] = temp;
            System.out.println("List after swapping elements: " + Arrays.toString(elements));
        }else {
            System.out.println("Either Provided array length is less than 1 or provided index is out of bound");
        }
    }

    /*Create method to get maximal element
        T extends Comparable to ensure passed
        elements are comparable.
     */
    public static <T extends Comparable<? super T>> void getMaxElement (List<T> list, int begin, int end){
        try{
            if(list.size()!=0 && begin < end){
                T max = list.get(begin);
                for(int i=begin+1; i<end; i++){
                    T current  = list.get(i);
                    if(current.compareTo(max) > 0){
                        max = current;
                    }
                }
                System.out.println("Maximal element is: " + max);
            }else{
                System.out.println("Please Provide valid input");
            }
        }catch (Exception e){
           System.out.println("Error Occurred: " + e.getMessage());
        }
    }
}

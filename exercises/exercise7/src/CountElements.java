import java.util.Collection;
import java.util.function.Predicate;

public class CountElements {
    public static <T> int  countElements (Collection<T> collection, Predicate predicate){
        int count = 0 ;
        for(T ele : collection){
            if(predicate.test(ele)){
                count++;
            }
        }
        return count;
    }

    //Created method to check whether provided number is palindrome or not
    public static boolean isPalindrome(Integer num){
        int rem, temp, sum = 0;
        temp = num;
        while(num>0){
            rem= num%10;
            sum = (sum*10) + rem;
            num = num/10;
        }
        return temp==sum;
    }

    /* Overloaded isPalindrome method to
        check whether provided String is
        palindrome or not
    */
    public static boolean isPalindrome(String str){
        //First Approach

       /* String str1 = str.toLowerCase().replaceAll("[^a-z0-9]", "");;
        int len = str1.length();
        for(int i=0;i<len/2;i++){
            if(str1.charAt(i)!=str1.charAt(len-1-i))
                return false;
        }
        return true;*/

        /*Second Approach
            Since string operations are costly,
            using String Builder
         */
        StringBuilder sb = new StringBuilder();
        String s = str.toLowerCase();
        for(int i=0; i<s.length(); i++){
            if((s.charAt(i) >= 97 && s.charAt(i) <= 122)  || (s.charAt(i) >= 0 && s.charAt(i) <=9)){
                sb.append(s.charAt(i));
            }
        }
        int n = sb.length();
        for(int i=0; i<n/2;i++){
            if(sb.charAt(i)!= sb.charAt(n-1-i))
                return false;
        }
        return true;
    }

    public static boolean isOdd(Integer num){
        if(num%2 == 0)
            return false;
        return true;
    }
   public static boolean isPrime(Integer num){
        if(num<=1)
            return false;
        for(int i=2; i<num; i++){
            if(num%i == 0)
                return false;

        }
        return true;
    }
}

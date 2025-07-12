import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int s=0;
        for (Integer x:L){
            s+= x;
        }
        return s;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> a=new ArrayList<>();
        for (Integer x:L){
            if(x%2==0){
                a.add(x);
            }
        }
        return a;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer>a=new ArrayList<>();
        for(Integer x:L1){
            if(L2.contains(x)){
                a.add(x);
            }
        }
        return a;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int cnt=0;
        for(String x:words){
            for (char y:x.toCharArray()){
                if (y==c){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

import java.util.Arrays;
public class SortingAlgorithmLectures {
    // this method combines two arrays of integers, assuming they are sorted
    public static int[] combine(int[] lst1, int[] lst2){
        // store latest index of each lst
        int[] index = {0, 0};
        int[] combined = new int[lst1.length + lst2.length];

        // add all values in order of size to "combined" array
        while(index[0] < lst1.length && index[1] < lst2.length){
            // if lst1 has greater value, add lst1's value, otherwise add lst2's
            if(lst1[index[0]] < lst2[index[1]]){
                combined[index[0] + index[1]] = lst1[index[0]];
                index[0]++;
            }
            else {
                combined[index[0] + index[1]] = lst2[index[1]];
                index[1]++;
            }
        }

        // add leftovers
        while(index[0] < lst1.length){
            combined[index[0] + index[1]] = lst1[index[0]];
            index[0]++;
        }
        while(index[1] < lst2.length){
            combined[index[0] + index[1]] = lst2[index[1]];
            index[1]++;
        }
        return combined;
    }
    /*
     * Base case: if lst.length = 1, return lst.
     * Recursive case: if lst.length > 1, return combine(sort(lst[0:lst.length/2]), sort(lst[lst.length/2:lst.length-1]))
     */
    public static int[] sort(int[] lst){
        if(lst.length == 1) return lst;
        int[] firstHalf = Arrays.copyOfRange(lst, 0, lst.length/2);
        int[] secondHalf = Arrays.copyOfRange(lst, lst.length/2, lst.length);
        return combine(sort(firstHalf), sort(secondHalf));
    }
}  

// These are some imports that the course staff found useful, feel free to use them
// along with other imports from java.util.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;


public class PartitionOracle {

    /**
     * Feel free to use this method to call partition. It catches any exceptions or
     * errors and returns a definitely-invalid pivot (-1) to turn errors into
     * potential failures. For example, in testPartition, you may use
     * 
     * runPartition(p, someArray, someLow, someHigh)
     * 
     * instead of
     * 
     * p.partition(someArray, someLow, someHigh)
     * 
     * @param p
     * @param strs
     * @param low
     * @param high
     * @return
     */
    public static int runPartition(Partitioner p, String[] strs, int low, int high) {
        try {
            return p.partition(strs, low, high);
        } catch (Throwable t) {
            return -1;
        }
    }

    // The three methods below are for you to fill in according to the PA writeup.
    // Feel free to make other helper methods as needed.


    /*
    * Return null if the pivot and after array reflect a correct partitioning of 
    * the before array between low and high.
    *
    * Return a non-null String (your choice) describing why it isn't a valid
    * partition if it is not a valid result. You might choose Strings like these,
    * though there may be more you want to report:
    *
    * - "after array doesn't have same elements as before"
    * - "Item before pivot too large"
    * - "Item after pivot too small"
    */
    public static String isValidPartitionResult(String[] before, int low, int high, int pivot, String[] after) {
        //TESTING TIME!
        List<String> beforeArrayList = Arrays.asList(before);  
        List<String> afterArrayList = Arrays.asList(after);  

        // bad pivot index (resulting from failed partition)
        if (pivot < 0) {
            String reason = "Partition failed and the pivot index is " + pivot + ". (If this is -1, it's likely caused by your program crashing.)";
            return reason;
        }

        // pivot must be between low and high
        if(!(pivot < high) || !(pivot >= low)) {
            String reason = "Pivot is out of bounds.";
            return reason;
        }

        // after string should be the same length as before
        if (after.length != before.length) {
            String reason = "The length of the string after partitioning is different. The 'After' string's length subtracted by the 'Before' string's length is " + (after.length - before.length);
            return (reason);
        }

        // kind of useless
        if (low == high-1) {
            return null;
        }


        // checking if it has the same elements (without having been duplicated)
            //making copy so that what i'm about to do won't modify the originals
        ArrayList<String> beforeArrayListSorted = new ArrayList<String>(beforeArrayList); 
        ArrayList<String> afterArrayListSorted = new ArrayList<String>(afterArrayList);   

            // In order to account for duplicate characters in after that aren't in "before," I
            // am going to sort, and see if the result is equal
        Collections.sort(beforeArrayListSorted);
        Collections.sort(afterArrayListSorted);

        if(!beforeArrayListSorted.equals(afterArrayListSorted)) {
            String reason = "The 'after' string array contains different characters than before sorting";
            return reason;
        }


        // for the next two loops, i'll use these variables as the pivot's value
        String ItemAtPivotIndex = after[pivot];
        String letterAfterPivot = afterArrayListSorted.get(high-1);
        String letterBeforePivot = afterArrayListSorted.get(low);

        // // going crazy trying to fix this, adding these if-statements here as well to see if works
        // if (!((letterAfterPivot.compareTo(ItemAtPivotIndex)) >= 0)) {
        //     String reason = "Letters after pivot index are too small.";
        //     return reason;
        // }   

        // if (!((letterBeforePivot.compareTo(ItemAtPivotIndex)) <= 0)) {
        //     String reason = "Letters before pivot index are too large.";
        //     return reason;
        // }    

        // checking if items after pivot are too small
        //for (int i = pivot; i < after.length; i++) {
        for (int i = pivot; i < high-1; i++) {
            // charAt will turn a string into a char, and typecasting chars into ints make them 
            // into a comparable numeric value
            letterAfterPivot = after[i];

            if (!((letterAfterPivot.compareTo(ItemAtPivotIndex)) >= 0)) {
                String reason = "Letters after pivot index are too small. Pivot index was " + pivot;
                return reason;
            }      
        }

        // checking if items before pivot are too small
        //for (int i = 0; i <= pivot; i++) {
        for (int i = low; i <= pivot; i++) {
            // charAt will turn a string into a char, and typecasting chars into ints make them 
            // into a comparable numeric value
            letterBeforePivot = after[i];

            if (!((letterBeforePivot.compareTo(ItemAtPivotIndex)) <= 0)) {
                String reason = "Letters before pivot index are too large. Pivot index was " + pivot;
                return reason;
            }      
        }
        
        
        // what is testIsInvalidReversedInTheMiddleAndOthersChange? NO ONE KNOWS
        // afterArrayList
        // beforeArrayList
        // pivot; // the pivot index of the 'after' array
        // OK. Values. Low to high. I GOT IT. 

        // this here takes everything after high in the after array, checks its the same as the before array
        if(high!=afterArrayList.size()) {
            ArrayList<String> subListzAftr = new ArrayList<String>(afterArrayList.subList(high,afterArrayList.size()-1));
            ArrayList<String> subListzBfr = new ArrayList<String>(afterArrayList.subList(high,beforeArrayList.size()-1));

            // if anything after the 'high' bound is changed, DIE!
            for (int i = 0; i < subListzAftr.size(); i++) {
                if(subListzAftr.get(i) != subListzBfr.get(i)) {
                    String reason = "You've modified past the 'high' bound!";
                    return reason;
                }

            }
        }

        if (low != 0) {
            // this here takes everything before low in the array, checks if its the same before and after
            ArrayList<String> subListzAftr = new ArrayList<String>(afterArrayList.subList(0,low));
            ArrayList<String> subListzBfr = new ArrayList<String>(afterArrayList.subList(0,low));

            // if anything before the 'low' bound is changed, DIE!
            for (int i = 0; i < subListzAftr.size(); i++) {
                if(subListzAftr.get(i) != subListzBfr.get(i)) {
                    String reason = "You've modified past the 'low' bound!";
                    return reason;
                }

            }
        }



        
        return null;
    } // end of is valid


    // generates a string[] from a-z A-Z with the size n
    // @input int n - the size string to be generated
    public static String[] generateInput(int n) {

        // every possible lowercase alphabetical character to use (yes, a bit of a weird implementation)
        // the reason i'm not including caps as well is because it's an implementation choice to have lowercase or caps first
        // when sorting
        String alphabet = "qwertyuiopasdfghjklzxcvbnm";

        Random random = new Random();

        String[] returnable = new String[n];

        for(int i = 0; i<n; i++) {
            // getting the index of a random letter in the alphabet
            int index = random.nextInt(alphabet.length());

            // the returnable string will be updated with a random char turned string
            returnable[i] = alphabet.charAt(index) + "";
        }

        return returnable;
    } // end of generateInput method


    // find a counterexample for an implementation of partitioner that fails it
    // @param Partitioner p - the implementation to be tested
    public static CounterExample findCounterExample(Partitioner p) {
        // if it hasn't failed after 100,000 diff inputs, this method likely can't find a counterexample.
        int amountOfInputs = 50;

        // going through 100,000 diff inputs
        for(int i = 0; i < amountOfInputs; i++) {

            int size = 10;

            // string to test on
            String[] testerInput = PartitionOracle.generateInput(size);

            // a copy, because the original will be changed
            String[] originalTesterInput = Arrays.copyOf(testerInput, testerInput.length);

            // pivot index
            // 1, 9
            // 0, size
            int pivotIndex = PartitionOracle.runPartition(p, testerInput, 1, 9);
            //int pivotIndex = p.partition(testerInput, 0, 10);

            //time to test
            String reason = isValidPartitionResult(originalTesterInput, 1, 9, pivotIndex, testerInput);

            // result of test
            if(reason != null) {
                CounterExample returnableCounter = new CounterExample(originalTesterInput, 1, 9, pivotIndex, testerInput, reason);
                return returnableCounter;
            }

        }
        // all is good? return null
        return null;  

    } // end of method findCounterExample

}


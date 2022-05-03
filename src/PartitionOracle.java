
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


        // after string should be the same length as before
        if (after.length != before.length) {
            String reason = "The length of the string after partitioning is different. The 'After' string's length subtracted by the 'Before' string's length is " + (after.length - before.length);
            return (reason);
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
        String ItemAtPivotIndex = afterArrayListSorted.get(pivot);
        int comparablePivot = (int)ItemAtPivotIndex.charAt(0);

        
        // checking if items after pivot are too small
        for (int i = pivot; i < afterArrayListSorted.size(); i++) {
            // charAt will turn a string into a char, and typecasting chars into ints make them 
            // into a comparable numeric value
            int lettersAfterPivot = (int)afterArrayListSorted.get(i).charAt(0);

            if (lettersAfterPivot < comparablePivot) {
                String reason = "Letters after pivot index are too small.";
                return reason;
            }      
        }

        // checking if items before pivot are too large
        for (int i = 0; i < pivot; i++) {
            // charAt will turn a string into a char, and typecasting chars into ints make them 
            // into a comparable numeric value
            int lettersBeforePivot = (int)afterArrayListSorted.get(i).charAt(0);

            if (lettersBeforePivot > comparablePivot) {
                String reason = "Letters before pivot index are too large.";
                return reason;
            }      
        }

        return null;
    }


    // generates a string[] from a-z A-Z with the size n
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

    public static CounterExample findCounterExample(Partitioner p) {
        // if it hasn't failed after 100,000 diff inputs, this method likely can't find a counterexample.
        int amountOfInputs = 100000;

        // going through 100,000 diff inputs
        for(int i = 0; i < amountOfInputs; i++) {

            // string to test on
            String[] testerInput = PartitionOracle.generateInput(10);

            // a copy, because the original will be changed
            String[] originalTesterInput = Arrays.copyOf(testerInput, testerInput.length);

            // pivot index
            int pivotIndex = p.partition(testerInput, 0, 10);

            //time to test
            String flag = isValidPartitionResult(originalTesterInput, 0, 10, pivotIndex, testerInput);

            // result of test
            if(flag != null) {
                CounterExample returnableCounter = new CounterExample(originalTesterInput, 0, 10, pivotIndex, testerInput, flag);
                return returnableCounter;
            }

        }
        // all is good? return null
        return null;  

    } // end of method findCounterExample

}

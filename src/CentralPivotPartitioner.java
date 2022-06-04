import java.util.*;
////////////////////////////////////////////////////////////////////////////////////////////////////
public class CentralPivotPartitioner implements Partitioner{
    @Override
    // You might have noticed that this is remarkably similar to FirstElePivotPartitioner. 
    // That's because it is. It's the same code but I changed the pivotIndex and pivotStr

    /* This method uses the partition algorithm to sort with the pivot being (high + low) / 2
     * It will sort the array so that all values smaller than the pivot come before,
     * and all greater come after.
     * Returns the new index of the pivot index.
     * String strs: array to partition
     * int low: index to start
     * int high: index to end
     */
    // 
    public int partition(String[] strs, int low, int high){
        // pivot data
        int pivotIndex = (high + low) / 2;
        String pivotStr = strs[pivotIndex];
        // to hold everything smallerThan and greaterThan the pivotStr
        ArrayList<String> smallerThan = new ArrayList<String>();
        ArrayList<String> greaterThan = new ArrayList<String>();

        // add to smallerThan if string length < pivotStr length
        // otherwise add to greaterThan
        // ignore if you encounter pivotStr 
        String curr;
        for(int i = low; i < high; i++){
            curr = strs[i];
            if(pivotIndex == i){
                continue;
            } 

            if(curr.compareTo(pivotStr) <= 0){
                smallerThan.add(curr);
            }

            else if(curr.compareTo(pivotStr) > 0){
                greaterThan.add(curr);
            }
        }
        ArrayList<String> combined = new ArrayList<String>();
        // I found this method on StackOverflow
        combined.addAll(smallerThan);
        combined.add(pivotStr);
        pivotIndex = combined.size()-1+low;
        combined.addAll(greaterThan);
        // adding all ArrayList values to the strs array
        for(int i = 0; i < combined.size(); i++){
            strs[i+low] = combined.get(i);
        }
        return pivotIndex;
    }
}

import java.util.*;
// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class CentralPivotPartitioner implements Partitioner{
    @Override
    // You might have noticed that this is remarkably similar to FirstElePivotPartitioner. 
    // That's because it is. It's the same code but I changed the pivotIndex and pivotStr
    public int partition(String[] strs, int low, int high){
        // pivot data
        int pivotIndex = (high + low) / 2;
        String pivotStr = new String(strs[pivotIndex]);
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
            else if(curr.compareTo(pivotStr) < 0){
                smallerThan.add(curr);
            }
            else {
                greaterThan.add(curr);
            }
        }
        ArrayList<String> combined = new ArrayList<String>();
        // I found this method on StackOverflow
        combined.addAll(smallerThan);
        combined.add(pivotStr);
        combined.addAll(greaterThan);
        // adding all ArrayList values to the strs array
        for(int i = 0; i < combined.size(); i++){
            strs[i+low] = combined.get(i);
        }
        return pivotIndex;
    }
}
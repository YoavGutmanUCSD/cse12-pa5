import java.util.*;
// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class FirstElePivotPartitioner implements Partitioner {
    @Override
    public int partition(String[] strs, int low, int high){
        // pivot point data
        int pivotIndex = low;
        String pivotStr = new String(strs[low]);

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
            else if(curr.length() < pivotStr.length()){
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
        // System.out.println("\nsmallerThan and greaterThan:");
        // printEveryValue(smallerThan);
        // printEveryValue(greaterThan);
        // System.out.println("This is the array currently:");
        // System.out.println(strs.toString());
        // pivotIndex = smallerThan.size() + low;
        // for(int i = low; i < low + smallerThan.size(); i++){
        //     strs[i] = smallerThan.get(i - low);
        // }
        // strs[pivotIndex] = pivotStr;
        // // I removed the "+1" here, because it seemed to be ignoring the first value every time. I also changed the bound in case that's helpful
        // for(int j = low + smallerThan.size(); j < low + greaterThan.size(); j++){
        //     strs[j] = greaterThan.get(j - low);
        // }
        // System.out.println("This is the array now:");
        // System.out.println(strs.toString());
        return pivotIndex;
    }
}
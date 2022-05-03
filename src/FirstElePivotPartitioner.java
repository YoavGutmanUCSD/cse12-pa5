import java.util.ArrayList;
// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class FirstElePivotPartitioner implements Partitioner {
    @Override
    public int partition(String[] strs, int low, int high){
        // pivot point data
        // int pivotIndex = low;
        String pivotStr = strs[low];

        // to hold everything smallerThan and greaterThan the pivotStr
        ArrayList<String> smallerThan = new ArrayList<String>();
        ArrayList<String> greaterThan = new ArrayList<String>();

        // add to smallerThan if string length < pivotStr length
        // otherwise add to greaterThan
        // ignore if you encounter pivotStr 
        for(String curr: strs){
            if(curr.equals(pivotStr)) continue;
            if(curr.length() < pivotStr.length()){
                smallerThan.add(curr);
            }
            else {
                greaterThan.add(curr);
            }
        }
        int pivotIndex = smallerThan.size() + low;
        String[] newStrs = new String[strs.length];
        for(int i = low; i < low + smallerThan.size(); i++){
            strs[i] = smallerThan[i - low];
        }
        newStrs[pivotIndex] = pivotStr;
        for(int j = low + smallerThan.size() + 1; j < low + greaterThan.size(); j++){
            strs[j] = smallerThan[j - low];
        }
        return pivotIndex;
    }
}

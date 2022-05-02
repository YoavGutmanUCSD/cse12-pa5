import static org.junit.Assert.*;
import org.junit.Test;
// import SortingAlgorithmLectures.*;

public class TestLectureMaterial{
    @Test
    public void testCombineInts(){
        int[] int1 = {1,2,3};
        int[] int2 = {4,5,6};
        int[] desired = {1,2,3,4,5,6};
        assertArrayEquals(desired, SortingAlgorithmLectures.combine(int1, int2));
    }
    @Test
    public void testSortBasic(){
        int[] int1 = {2,1,4,3,5,6};
        int[] desired = {1,2,3,4,5,6};
        assertArrayEquals(desired, SortingAlgorithmLectures.sort(int1));
    }

}

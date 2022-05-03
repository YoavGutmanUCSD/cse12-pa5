import static org.junit.Assert.*;

import org.junit.Test;

public class TestPartitionerYoav {
    FirstElePivotPartitioner e = new FirstElePivotPartitioner();

    @Test
    public void testSameLength(){
        String[] strs = {"aaa", "a", "aa", "aaaa", "aaaaaa", "aaaa", "aaaaaaaa", "aaaaaaaaa"};
        String[] strsPre = {"aaa", "a", "aa", "aaaa", "aaaaaa", "aaaa", "aaaaaaaa", "aaaaaaaaa"};
        int index = e.partition(strs, 0, strs.length);
        assertTrue(strs.length == strsPre.length);
    }
    @Test
    public void testPrePivotAccuracy() {
        String[] strs = {"aaa", "a", "aa", "aaaa", "aaaaaa", "aaaa", "aaaaaaaa", "aaaaaaaaa"};
        String[] strsPre = {"aaa", "a", "aa", "aaaa", "aaaaaa", "aaaa", "aaaaaaaa", "aaaaaaaaa"};
        int index = e.partition(strs, 0, strs.length);
        for(int i = 0; i < index; i++){
            assertTrue(strs[i].length() <= strs[index]);
        }
    }
    @Test
    public void testPostPivotAccuracy() {
        String[] strs = {"aaa", "a", "aa", "aaaa", "aaaaaa", "aaaa", "aaaaaaaa", "aaaaaaaaa"};
        String[] strsPre = {"aaa", "a", "aa", "aaaa", "aaaaaa", "aaaa", "aaaaaaaa", "aaaaaaaaa"};
        int index = e.partition(strs, 0, strs.length);
        for(int i = index+1; i < strs.length; i++){
            assertTrue(strs[i].length() >= strs[index]);
        }
    }
}

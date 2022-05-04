// You can (and should) add "implements Partitioner" below once you have the implementation ready

// QUESTIONS FOR ASSIGNMENT:
    // Taken from this URL: https://gist.github.com/unnikked/14c19ba13f6a4bfd00a3
    // Author: Unknown
    // LICENSE: https://creativecommons.org/licenses/by-sa/3.0/
    // Changes made: works with strings now and picks pivot inside partition, method is now public
    // Is It Buggy?: Yes. The program crashes.
    // Worst case runtime: n, when it picks a pivot that's at the end of the array, and runs on a large array. 

import java.io.*;
import java.util.*;



public class WebPartitioner implements Partitioner {

    // TAKEN FROM THE INTERNET!
	private static void swap(String[] array, int a, int b) {
		String tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}

    private static int randomPivot(int left, int right) {
		return left + (int) Math.floor(Math.random() * (right - left + 1));
	}	

    public int partition(String[] array, int left, int right) {
        int pivotIndex = randomPivot(left, right);
		String pivotValue = array[pivotIndex];
		swap(array, pivotIndex, right); // move pivot to end
		int storeIndex = left;
		for(int i = left; i < right; i++) {
			if(array[i].compareTo(pivotValue) < 0) {
				swap(array, storeIndex, i);
				storeIndex++;
			}
		}
		swap(array, right, storeIndex); // Move pivot to its final place
		return storeIndex;
	}



}

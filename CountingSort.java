/**
 * 
 */
package ie.gmit.dip;

import java.util.Arrays;

/**
 * @author Ciaran Mooney
 * 
 *         Counting sort is an non-comparative sorting algorithm. The algorithm
 *         sorts elements between specific ranges. That is for a given range 1
 *         to K, the algorithm will sort elements O(n) times. The algorithm
 *         finds the number of elements less than X, so X can be positioned
 *         correctly. 
 *         14/04/2020
 */

public class CountingSort {

	/**
	 * Time Complexity:
	 * Best case : O(n+k) 
	 * Average case : O(n+k) 
	 * Worst case : O(n+k)
	 */

	public CountingSort() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Test the counting sort, K is highest value for array 1 to K

		int[] numbers2 = { 2, 8, 7, 1, 3, 5, 6, 4 };
		int[] numbers = { 26, 14, 15, 16, 3, 13 };
		System.out.println("Testing counting sort: " + Arrays.toString(countSort(numbers, 26)));
		
		System.out.println("Test helper method to find K: ");
		int[] numbers3 = { 2, 8, 7, 1, 3, 5, 6, 4 };
		System.out.println(Arrays.toString(numbers3));
		
		//Test the count sort for handling negaive integrs in worst case revrsed sorted arrays
		int[] arro = {0,0,0};
		int[] arr = {-5, -10, -10, -3, -8, -5, -1, -10}; 
        System.out.println(Arrays.toString(negativeCountingSort(arro)));
        
        int[] arr5 = { 26, 14, 15, 16, 3, 13 };
        System.out.println("testing In PLace version: " + Arrays.toString(counSortInPlace(arr5, 26)));
		
	}

	/**
	 * This will only work on positive integers 0 to K.
	 * For negative  worst case testing we use the negative count sort below.
	 * 
	 * Step 1: Use an array to store the frequency of each element. For array
	 * elements 1 to K initialize an array with size K. Step 2: Add elements of
	 * count array so each element stores summation of its previous elements. Step
	 * 3: The modified count array stores the position of elements in actual sorted
	 * array. Step 5: Iterate over array and position elements in correct position
	 * based on modified count array and reduce count by 1.
	 */
	public static int[] countSort(int[] arr, int k) {
		

		int[] result = new int[arr.length];
		int[] count = new int[k + 1];
		for (int x = 0; x < arr.length; x++) {
			count[arr[x]]++;
		}

		/*
		 * Store count of each element in the count array Count[y] holds the number of
		 * values of y in the array 'arr'
		 */

		for (int y = 1; y <= k; y++) {
			count[y] += count[y - 1];
		}

		/*
		 * Change count[i] so that count[i] now contains actual Position of this element
		 * in result array
		 */

		for (int i = arr.length - 1; i >= 0; i--) {
			result[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}

		//System.out.println("COUNTSORT SORTED ARRAY = " + Arrays.toString(result));
		return result;

	}
	

	/**
	 * Counting sort algorithm that handles 
	 * negative numbers. 
	 * 
	 * Step 1: Find the min element 
	 * Step 2: Store the count of the min element at zero index
	 */
	public static int[] negativeCountingSort(int[] arr) {
		
		if (arr.length == 0) {
			return new int[] {};
		}
		int max = Arrays.stream(arr).max().getAsInt(); 
        int min = Arrays.stream(arr).min().getAsInt(); 
        int range = max - min + 1; 
        int count[] = new int[range]; 
        int output[] = new int[arr.length]; 
        for (int i = 0; i < arr.length; i++)  
        { 
            count[arr[i] - min]++; 
        } 
  
        for (int i = 1; i < count.length; i++)  
        { 
            count[i] += count[i - 1]; 
        } 
  
        for (int i = arr.length - 1; i >= 0; i--)  
        { 
            output[count[arr[i] - min] - 1] = arr[i]; 
            count[arr[i] - min]--; 
        } 
  
        for (int i = 0; i < arr.length; i++) 
        { 
            arr[i] = output[i]; 
        } 
        
        return arr;
	}

	
	/**
	 * A sharp rise in running time is evident when n
	 * reaches 60-70,000. Counting sort has n+k space complexity
	 * and thus is out of place. 
	 * In order to ascertain whether this degeneration 
	 *  is due to out of place sorting
	 * we will run the same benchmarking on a in-place 
	 * version of counting sort.
	 */
	
	public static int[] counSortInPlace(int[] arr, int k) {
		
		if (arr.length == 0) {
			return new int[] {};
		}
		
		int[] count = new int[k + 1];
	    for (int x = 0; x < arr.length; x++) { 	
	        count[arr[x]]++;
	    }

	    int idx=0;
	    for (int x=0; x<=k; x++) {
	        Arrays.fill(arr, idx, idx+=count[x], x);
	    }
	    
	    return arr;
		
	}

}

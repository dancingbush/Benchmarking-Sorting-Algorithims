/**
 * 
 */
package ie.gmit.dip;

/**
 * @author Ciaran Mooney
 *
 *         Quicksort is a divide and conquer recursive algorithm. One element of
 *         the area is chosen as a pivot and all elements are sorted around it.
 * 
 *         The worst case scenario occurs when the biggest or smallest element
 *         is always picked as the pivot where time complexity is: O(n^2). This
 *         is demonstrated with the partitionHoare method.
 * 
 *         14/04/2020
 */

public class Quicksort {

	/**
	 * Time Complexity: T(n)=T(k)+T(n−k−1)+O(n) 
	 * Best case : O(nlogn) 
	 * Average case :O(nlogn)
	 * Worst case : O(n^2) 
	 * Stable - No (stable ver do exist) In place: No
	 * Memory: O(n) variants exist with O (nlog n)
	 */

	public Quicksort() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Test the quick sort algorithm

		int[] numbers2 = { 2, 8, 7, 35, 34, 1, 3, 5, 6, 4 };
		int[] numbers = { 123, 1, 145677, -190, 200, -2, 0, 23, 23, -456 - 456, 23 };
		System.out.println("\nTesting QuickSort: \n");
		quickSort(numbers, 0, numbers.length - 1);
		printArray(numbers);

	}

	private static void printArray(int[] numbers) {
		/*
		 * Print array to console
		 */

		for (int x : numbers) {
			System.out.print(x + ",");
		}

	}

	
	/**
	 * Base case: subarrays of length 1 or 0 do need to be sorted. Step 1: Pivot: An
	 * element is chosen form the array and assigned the pivot Step 2: Partitioning:
	 * elements are reordered comparatively around the pivot. Step 3: Recursion:
	 * steps 1 and 2 are applied to each of the two sub arrays
	 */

	public static void quickSort(int[] arr, int left, int right) {
		
		// Base
		if (left == right) {
			return;
		}

		// Binary recursion
		if (left < right) {

			// Get the pivot
			int pivot = partitionArray(arr, left, right);
			quickSort(arr, left, pivot - 1);
			quickSort(arr, pivot + 1, right);

		}
	}

	private static int partitionArray(int[] arr, int left, int right) {
		/*
		 *  Return the index of the pivot position
		 */

		int pivot = arr[right];

		// The last index to be smaller than the pivot
		int lastSmallest = left - 1;

		for (int i = left; i <= right; i++) {
			if (arr[i] <= pivot) {
				// Swap
				lastSmallest++;
				int temp = arr[lastSmallest];
				arr[lastSmallest] = arr[i];
				arr[i] = temp;
			}
		}
		
		// Finally return the position of the pivot
		return lastSmallest;
	}

	
	/**
	 * Demonstrate worst case where the pivot is chosen at the ends of the array
	 * been partitioned.
	 * 
	 */
	private static int partitionHoare(int[] arr, int left, int right) {
		
		
		int x = arr[left];
		int i = left - 1;
		int j = right + 1;
		while (true) {
			do {
				j = j - 1;
			} while (arr[j] > x);
			do {
				i = i + 1;
			} while (arr[i] < x);

			if (i < j) {
				/*
				 * Preserve the original inavraints and swap arr[i] and arr[j]
				 *
				 */
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			} else {
				return j;
			}
		}
	}
	
	public static void quickSort(int[] arr) {
		/**
		 * A utility method that allows call from
		 * lambda function in Runner
		 */
	    quickSort(arr, 0, arr.length - 1);
	  }

	
	/**
	 * This version finds which subarray from the partition
	 * is smaller and recursively computes quick sort on same.
	 * It uses iteration to perform quick sort on the larger of
	 * the two sub arrays so no new stack frames are created and
	 * Pushed to the stack. This prevents a stackOverFlow on
	 * large sorted arrays.
	 */
	public static void optimalQuickSort(int[] arr, int p, int r) {
		
		
	    while (p < r) {
	      int pivot = partitionArray(arr, p, r);
	      if (pivot - p < r - pivot) {
	        quickSort(arr, p, pivot - 1);
	        p = pivot + 1;
	      } else {
	        quickSort(arr, pivot + 1, r);
	        r = pivot - 1;
	      }
	    }
	  }
	
	public static void optimalQuickSort(int[] arr) {
		//Helper for optimal QiuikSort
	    optimalQuickSort(arr, 0, arr.length - 1);
	  }
}
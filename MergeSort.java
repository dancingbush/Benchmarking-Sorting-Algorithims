/**
 * 
 */
package ie.gmit.dip;

/**
 * @author Ciaran Mooney
 * 
 *         Merge sort is a divide and conquer sorting recursive algorithm. It is
 *         an efficient comparison based sorting algorithm.
 * 
 *         Merge sorts works by first dividing a list / array into a sublist of
 *         approximately half the size in each iteration until each sublist has
 *         one element.
 * 
 *         Then each sublist is merged repeatedly to create a final sorted list.
 *         Most of the work is performed at the Merge step.
 *
 */

public class MergeSort {

	/**
	 * Best case : O(nlogn) 
	 * Average case : O(nlogn) 
	 * Worst case : O(nlogn) Stable -
	 * Yes In place - No.
	 */
	
	public MergeSort() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Test the Merge sort array

		System.out.println("Testing Merge Sort: \n");
		int[] numbers = { -1075510490, 679660285, -325116253, 1187020704, -887141406, 1134328233, 2055195167,
				-356991681, 2069724824, -1258458656 };
		int[] numbers2 = { 23234, 2, 123, 3456, 12, 34, 1, -10, 121313, 122 };
		mergeSort(numbers2);
		printArray(numbers2);

	}

	private static void printArray(int[] arr) {
		// Prints the array to the console

		for (int x : arr) {
			System.out.println(x);
		}
	}

	/**
	 * Merges the sublists / elements produced by the merge sort algorithm. The
	 * subsets arr[left, midpoint] and a[midpoint + 1, right ] are merged where left
	 * < midpoint < right Firstitem 1 and 2 hold the array of first item in the
	 * subsequence arr[left, midpoint] and subsequence arr[midpoint+1, right]
	 * respectively.
	 * 
	 * The items in the combined subsequence a[left, right] is (right - left +1).
	 * 
	 */

	private static int[] mergeSublists(int[] arr2, int left, int midpoint, int right) {
		
		int firstItem1 = left;
		int firstItem2 = midpoint + 1;
		int[] nova = new int[right - left + 1];
		for (int i = 0; i < right - left + 1; i++) {
			if (firstItem2 > right || (firstItem1 <= midpoint && arr2[firstItem1] < arr2[firstItem2])) {
				nova[i] = arr2[firstItem1];
				firstItem1++;
			} else {
				nova[i] = arr2[firstItem2];
				firstItem2++;
			}
		}
		for (int i = 0; i < (right - left + 1); i++) {
			arr2[left + i] = nova[i];
		}
		return arr2;

	}

	public static void mergeRecursievly(int[] arr, int left, int right) {
		/**
		 * Dived and conquer recursion. We pass the left most and right most element of
		 * the sub array we want to sort. Initially these will be 0 and arr.lenght-1
		 * respectively. The base case executes when left and right meet each other. A
		 * midpoint is determined for each sub array and is used to further sort into
		 * sub-arrays recursively.
		 * 
		 */

		// Base
		if (left == right) {
			return;
		}
		
		if (left < right) {

		// Find midpoint to split the array
		int midpoint = (left + right) / 2;
		mergeRecursievly(arr, left, midpoint);
		mergeRecursievly(arr, midpoint + 1, right);

		// Finally merge the elements form the sublisits
		mergeSublists(arr, left, midpoint, right);
		}

	}
	
	/**
	 * Utility method to call form labma expression in Runner
	 * 
	 */
	public static void mergeSort(int[] a) {
		
		mergeRecursievly(a, 0, a.length - 1);
	}

}

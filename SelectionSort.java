/**
 * 
 */
package ie.gmit.dip;

/**
 * @author Ciaran Mooney
 * 
 *         Selection sort is a comparison based sorting algorithm. In-place :
 *         Yes. Unstable - does not maintain sorted input. Time complexity is n2
 *         in best, worst and average cases. Generally performs better than
 *         bubble sort but is still not practical in real world applications for
 *         large data sets.
 * 
 *         12/04/2020
 * 
 */
public class SelectionSort {

	/**
	 * Time Complexity:
	 *  Best case : O(N^2) 
	 *  Average case : O(N^2) 
	 *  Worst case : O(N^2)
	 */

	public SelectionSort() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Selection sort sorting algorithm. Cycle: The minimum element form the
	 * unsorted sub-array on he right is picked and moved to the sorted sub-array on
	 * the left.
	 * 
	 * The outer loop runs n-1 times. Inner loop n/2 on average. this results in
	 * (𝑛−1)×𝑛2≈𝑛2 best, worst and average cases.
	 * 
	 */
	public static void selectionSort(int[] arr) {
		

		// Count outer
		for (int i = 0; i < arr.length; i++) {
			// Assign the default min index
			int min = i;
			// Find the index with smallest value
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			// Swap index arr[min] with a[i]
			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
	}

	private static void printArray(int[] arr) {
		// Prints the sorted array

		for (int x : arr) {
			System.out.print(x);
		}
	}

	public static void main(String[] args) {
		// Test the Selection sort algorithm sorts correctly

		System.out.println("Testing selction sort: \n");
		int[] b = { 3, 9, 8, 3, 5, 2, 1 };
		selectionSort(b);
		printArray(b);
	}

}

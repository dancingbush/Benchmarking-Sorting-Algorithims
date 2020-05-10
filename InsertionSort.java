/**
 * 
 */
package ie.gmit.dip;

/**
 * @author Ciaran Mooney
 * 
 *         Insertion sort is a comparison based sort. The algorithm is in place
 *         and stable. Is very efficient on data-sets that are substantial sorted
 *         Runs in n+d time in same where d is the number of inversions.
 * 
 *         10/04/2020
 */
public class InsertionSort {

	/**
	 * Time Complexity: 
	 * Best case: O(n)
	 * Average case: O(n^2)
	 * Worst case: O(n^2)
	 */
	public InsertionSort() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Test the insertion sort algorithm
		int b[] = { 4, 6, 3, 5, 2, 9, 11, 9 };
		insertionSort(b);
		printArray(b);

	}

	private static void printArray(int[] b) {
		// Print the arrays

		for (int x : b) {
			System.out.print(x);
		}

	}
	
	
	/*
	 * Total comparisons made is the number of inversions (d) plus n-1 (worst case).
	 * Executes in constant time (n) on sorted lists. Is inefficient with large
	 * datasets randomly assigned. This algorithm splits a input list iteratively
	 * into sorted (head) and unsorted (tail) sublists.
	 * 
	 * On average a array of given size n has 𝑛−1+𝑛−1×𝑛4≈𝑛2 comparisons. Worst
	 * case the entire array will be iterated and running time is 𝑂(𝑛2). Best case
	 * the array is already sorted the running time is constant 𝑂(𝑛).
	 * 
	 * 
	 * Compared to selection sort the inner loop only iterates as long as it takes
	 * to find the insertion point. Like the bubble sort it is in place and stable
	 * whereas Selection sort is in place but unstable.
	 * 
	 */
	public static void insertionSort(int[] arr) {
		

		for (int i = 1; i < arr.length; i++) {

			// Assign the key index and the check index should precede it
			int key = arr[i];
			int j = i - 1;

			// Move all elements > key index value to the right one position
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			// Reassign the key to the next position
			arr[j + 1] = key;
		}
	}

}

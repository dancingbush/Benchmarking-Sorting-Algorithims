/**
 * 
 */
package ie.gmit.dip;

import java.util.Arrays;

/**
 * @author Ciaran Mooney 16/04/2020
 * Bubble sort or sinking sort is a simple comparison sorting
 * algorithm that works by repeatedly moving through the 
 * the array to be sorted. The algorithm compares each pair of 
 * Adjacent elements and swaps them if they are in the incorrect order.
 * The process continues until no more swaps are required and the list is sorted.
 * The small elements  'bubble' to the top top of the list hence the algorithm name.
 *  although it is simple it inefficient  for large lists.
 *
 */
public class BubbleSort {
	  /*
	   * Time Complexity: 
	   * Best case : Θ(n^2)
	   * Average case  Θ(n^2)
	   * Worst case Θ(n^2)
	   */

	  public static void main(String[] args) {
		    int[] testBubbleSort = {9, 8, 7, 6, 5, 4, 3, 2};
		    bubbleSort(testBubbleSort);
		    System.out.println(Arrays.toString(testBubbleSort));
		    optimalBubbleSort(testBubbleSort);
		    System.out.println(Arrays.toString(testBubbleSort));
		    
		  }
	  
	  /*
       * Iteration of the outer loop will ensure
       * that at the end the largest element is in the 
       * (array.lenght-(i+1))th index.      
       * In other words, the loop invariant is that
       * the subsection bounded by the indices
       * [arr.length - i, arr.length] is sorted and
       * contains the i biggest elements in array.
       */

	  public static void bubbleSort(int[] arr) {
	    for (int i = 0; i < arr.length - 1; i++) {
	      
	      for (int j = 0; j < arr.length - i - 1; j++) {
	        if (arr[j] > arr[j + 1]) {
	          /*
	           * In the case where an inversion exists in 
	           * arr[j] > arr[j + 1],
	           * arr[j] and arr[j + 1] are
	           * thus swapped.
	           */
	          int temp = arr[j + 1];
	          arr[j + 1] = arr[j];
	          arr[j] = temp;
	        }
	      }
	    }
	  }

	  
	  /**
	   * The optimized version will check whether the list
	   * is sorted at each iteration. If the list is sorted the 
	   * program will exist.
	   * Thus the best case for the optimized bubble sort 
	   * is O(n). Conversely the above algorithm
	   * the best case will always be the same as the average case.
	   * 
	   */
	  public static void optimalBubbleSort(int[] arr) {
		  
	    boolean sorted = false;
	    int n = arr.length;
	    while (!sorted) {
	      sorted = true;
	      for (int i = 0; i < n - 1; i++) {
	        if (arr[i] > arr[i + 1]) {
	          int temp = arr[i + 1];
	          arr[i + 1] = arr[i];
	          arr[i] = temp;
	          sorted = false;
	        }
	      }
	      n--;
	    }
	  }

	

}
